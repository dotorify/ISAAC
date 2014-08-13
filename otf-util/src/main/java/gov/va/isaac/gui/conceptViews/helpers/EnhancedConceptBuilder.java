package gov.va.isaac.gui.conceptViews.helpers;

import gov.va.isaac.AppContext;
import gov.va.isaac.gui.conceptViews.componentRows.DetailRelRow;
import gov.va.isaac.gui.conceptViews.componentRows.DetailTermRow;
import gov.va.isaac.gui.conceptViews.componentRows.RelRow;
import gov.va.isaac.gui.conceptViews.componentRows.SimpleRelRow;
import gov.va.isaac.gui.conceptViews.componentRows.SimpleTermRow;
import gov.va.isaac.gui.conceptViews.componentRows.TermRow;
import gov.va.isaac.gui.conceptViews.helpers.ConceptViewerHelper.ComponentType;
import gov.va.isaac.interfaces.gui.TaxonomyViewI;
import gov.va.isaac.interfaces.gui.views.ConceptViewMode;
import gov.va.isaac.interfaces.gui.views.ConceptWorkflowViewI;
import gov.va.isaac.interfaces.gui.views.ListBatchViewI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import javax.validation.ValidationException;

import org.ihtsdo.otf.tcc.api.conattr.ConceptAttributeVersionBI;
import org.ihtsdo.otf.tcc.api.concept.ConceptVersionBI;
import org.ihtsdo.otf.tcc.api.contradiction.ContradictionException;
import org.ihtsdo.otf.tcc.api.description.DescriptionVersionBI;
import org.ihtsdo.otf.tcc.api.metadata.binding.Snomed;
import org.ihtsdo.otf.tcc.api.relationship.RelationshipVersionBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnhancedConceptBuilder {
	
	private VBox termVBox;
	private VBox relVBox;
	private VBox destVBox;
	private ScrollPane destScrollPane;
	private VBox fsnAnnotVBox;
	private VBox conAnnotVBox;
	private ConceptVersionBI con;
	private Label fsnLabel;
	private Label releaseIdLabel;
	private Label isPrimLabel;

	private TermRow tr;
	private RelRow rr;
	private ConceptViewerLabelHelper labelHelper;
	private AnchorPane enhancedConceptPane;

	private static final Logger LOG = LoggerFactory.getLogger(EnhancedConceptBuilder.class);

	public EnhancedConceptBuilder (AnchorPane enhancedConceptPane, VBox termVBox, VBox relVBox, VBox destVBox, ScrollPane destScrollPane, VBox fsnAnnotVBox, VBox conAnnotVBox, Label fsnLabel, Label releaseIdLabel, Label isPrimLabel) {
		this.enhancedConceptPane = enhancedConceptPane;
		
		this.termVBox = termVBox;
		this.relVBox = relVBox;
		this.destVBox = destVBox;
		this.destScrollPane = destScrollPane;
		
		this.fsnAnnotVBox = fsnAnnotVBox;
		this.conAnnotVBox = conAnnotVBox;
		this.fsnLabel = fsnLabel;
		this.releaseIdLabel = releaseIdLabel;
		this.isPrimLabel = isPrimLabel;
	}
	
	public void setLabelHelper(ConceptViewerLabelHelper labelHelper) {
			this.labelHelper = labelHelper;
	}
	

	public void setConceptValues(ConceptVersionBI concept, ConceptViewMode mode) {
		setMode(mode);
		con = concept;
		
		executeConceptBuilder();
		executeTermBuilder();
		executeRelBuilder(mode);
	}
	

	private void executeConceptBuilder() {
		try {
			ConceptAttributeVersionBI attr = ConceptViewerHelper.getConceptAttributes(con);

			// FSN
			DescriptionVersionBI fsn = con.getFullySpecifiedDescription();
			Rectangle fsnRec = AnnotationRectangle.create(fsn);
			fsnAnnotVBox.getChildren().add(fsnRec);
			labelHelper.initializeLabel(fsnLabel, fsn, ComponentType.DESCRIPTION, fsn.getText(), 0);
			
		
			// SCT Id
			labelHelper.initializeLabel(releaseIdLabel, attr, ComponentType.CONCEPT, ConceptViewerHelper.getSctId(attr), 0);
			Rectangle conRec = AnnotationRectangle.create(con);
			conAnnotVBox.getChildren().add(conRec);

			// Defined Status
			labelHelper.initializeLabel(isPrimLabel, attr, ComponentType.CONCEPT, ConceptViewerHelper.getPrimDef(attr), ConceptViewerHelper.getPrimDefNid(attr));
			if (attr.isUncommitted()) {
				isPrimLabel.setUnderline(true);
			}

			// Concept ContextMenu
			createConceptContextMenu();
			
		} catch (Exception e) {
			LOG.error("Cannot access basic attributes for concept: " + con.getPrimordialUuid());
		}

	}

	
	
	
	
	// Description Methods (one)
	private void executeTermBuilder() {
		// Descriptions
		try {
			// Sort Descriptions filtering out FSN and special storage for PT
			Map<Integer, Set<DescriptionVersionBI>> sortedDescs = new HashMap<>();
			DescriptionVersionBI ptDesc = null;
			
			for (DescriptionVersionBI desc : con.getDescriptionsActive()) {
				if (desc.getNid() != con.getFullySpecifiedDescription().getNid()) {
					if (desc.getNid() == con.getPreferredDescription().getNid()) {
						ptDesc = desc;
					} else {
						if (!sortedDescs.containsKey(desc.getTypeNid())) {
							Set<DescriptionVersionBI> descs = new HashSet<>();
							sortedDescs.put(desc.getTypeNid(), descs);
						}
	
						sortedDescs.get(desc.getTypeNid()).add(desc);
					}
				}
			}
		   	
			// Create GridPane
			tr.createGridPane();

			// Add PT Row to GridPane
			tr.addTermRow(ptDesc);

			// Add other terms to GridPane
			for (Integer descType: sortedDescs.keySet()) {
				for (DescriptionVersionBI desc: sortedDescs.get(descType)) {
					if (desc.getNid() != con.getPreferredDescription().getNid()) {
						tr.addTermRow(desc);
					}
				}
			}
			
			// Add GridPane to VBox
			termVBox.getChildren().add(tr.getGridPane());
		} catch (Exception e) {
			LOG.error("Cannot access descriptions for concept: " + con.getPrimordialUuid());
		}		
	}

	
	
	
	
	// Relationship Methods
	private void executeRelBuilder(ConceptViewMode mode) {

		try {
			rr.createGridPane();
			executeRelBuilderWithSpecifiedRels(con.getRelationshipsOutgoingActive());
			relVBox.getChildren().add(rr.getGridPane());

			if (mode != ConceptViewMode.SIMPLE_VIEW) {
				rr.resetCounter();
				executeRelBuilderWithSpecifiedRels(con.getRelationshipsIncomingActive());
				GridPane gp = ((DetailRelRow)rr).getDestinationGridPane();

				if (!gp.getChildren().isEmpty()) {
					destVBox.getChildren().add(gp);
					destVBox.setVisible(true);
					destScrollPane.setVisible(true);
				}
			} else {
				destVBox.setVisible(false);
				destScrollPane.setVisible(false);
			}
		} catch (IOException | ContradictionException e) {
			LOG.error("Cannot access relationships for concept: " + con.getPrimordialUuid());
		}		
	}

	
	private void executeRelBuilderWithSpecifiedRels(Collection<? extends RelationshipVersionBI> rels) throws ValidationException, IOException {
		// Capture for sorting (storing is-a in different collection
		Map<Integer, Set<RelationshipVersionBI>> sortedRels = new HashMap<>();
		Set<RelationshipVersionBI> isaRels = new HashSet<>();
		sortRels(sortedRels, isaRels, rels);

		// Display IS-As
		addRels(isaRels);

		for (Integer relType: sortedRels.keySet()) {
			// Display non-IS-As
			addRels(sortedRels.get(relType));
		}
	}

	private void setMode(ConceptViewMode mode) {
		if (mode == ConceptViewMode.SIMPLE_VIEW) {
			tr = new SimpleTermRow(labelHelper);
			rr = new SimpleRelRow(labelHelper);
		} else if (mode == ConceptViewMode.DETAIL_VIEW) {
			tr = new DetailTermRow(labelHelper);
			rr = new DetailRelRow(labelHelper);
		}
		
	}

	private void addRels(Set<RelationshipVersionBI> rels) {
		for (RelationshipVersionBI rel: rels) {
			rr.addRelRow(rel);
		}
	}

	private void sortRels(Map<Integer, Set<RelationshipVersionBI>> sortedRels, Set<RelationshipVersionBI> isaRels,
			Collection<? extends RelationshipVersionBI> relsToSort) throws ValidationException, IOException {
		for (RelationshipVersionBI rel : relsToSort) {
			if (rel.getTypeNid() == Snomed.IS_A.getNid()) {
				isaRels.add(rel);
			} else {
				if (!sortedRels.containsKey(rel.getTypeNid())) {
					Set<RelationshipVersionBI> rels = new HashSet<>();
					sortedRels.put(rel.getTypeNid(), rels);
				}

				sortedRels.get(rel.getTypeNid()).add(rel);
			}
		}
	}

	
	
	
	
	// Concept Conctext Menu Method
	private void createConceptContextMenu() {
		final ContextMenu rtClickMenu = new ContextMenu();

		MenuItem newWorkflowItem = new MenuItem("Send to Workflow Instance");
		newWorkflowItem.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				ConceptWorkflowViewI view = AppContext.getService(ConceptWorkflowViewI.class);

				view.setConcept(con.getNid());
				view.showView(AppContext.getMainApplicationWindow().getPrimaryStage());
			}
		});

		MenuItem listViewItem = new MenuItem("Send to List View");
		listViewItem.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				ListBatchViewI lv = AppContext.getService(ListBatchViewI.class);
				AppContext.getMainApplicationWindow().ensureDockedViewIsVisble(lv);
				List<Integer> nidList = new ArrayList<>();
				nidList.add(con.getNid());
				lv.addConcepts(nidList);		
			}
		});

		MenuItem taxonomyViewItem = new MenuItem("Show in Taxonomy");
		taxonomyViewItem.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				AppContext.getService(TaxonomyViewI.class).locateConcept(con.getNid(), null);			
			}
		});

		Menu copyIdMenu = labelHelper.addIdMenus(con);
		Menu modifyComponentMenu = labelHelper.addModifyMenus(ConceptViewerHelper.getConceptAttributes(con), ComponentType.CONCEPT);
		Menu createComponentMenu = labelHelper.addCreateNewComponent();

		rtClickMenu.getItems().addAll(newWorkflowItem, listViewItem, taxonomyViewItem, copyIdMenu, modifyComponentMenu, createComponentMenu);

		BorderPane bp = (BorderPane)enhancedConceptPane.getChildren().get(0);
		bp.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {  
            public void handle(ContextMenuEvent e) {  
                rtClickMenu.show(bp.getBottom(), e.getScreenX(), e.getScreenY());  
            }  
        }); 
	}
}