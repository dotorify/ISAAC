package gov.va.isaac.gui.mapping;

import gov.va.isaac.AppContext;
import gov.va.isaac.ExtendedAppContext;
import gov.va.isaac.gui.ConceptNode;
import gov.va.isaac.gui.SimpleDisplayConcept;
import gov.va.isaac.gui.mapping.data.MappingItem;
import gov.va.isaac.gui.mapping.data.MappingItemDAO;
import gov.va.isaac.gui.mapping.data.MappingObject;
import gov.va.isaac.gui.mapping.data.MappingSet;
import gov.va.isaac.gui.mapping.data.MappingUtils;
import gov.va.isaac.gui.util.CustomClipboard;
import gov.va.isaac.gui.util.Images;
import gov.va.isaac.search.CompositeSearchResult;
import gov.va.isaac.search.SearchHandle;
import gov.va.isaac.util.CommonMenus;
import gov.va.isaac.util.CommonMenusNIdProvider;
import gov.va.isaac.util.OTFUtility;
import gov.va.isaac.util.Utility;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;


import org.ihtsdo.otf.tcc.api.concept.ConceptVersionBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller class for the Create Mapping View.
 *
 * @author dtriglianos
 * @author <a href="mailto:dtriglianos@apelon.com">David Triglianos</a>
 */

public class CreateMappingItemController {
	private static final Logger LOG = LoggerFactory.getLogger(CreateMappingItemController.class);
	
	private final Label LABEL_NO_RESULTS = new Label("Search returned no results");
	private final Label LABEL_SEARCHING  = new Label("Searching...");

	@FXML private BorderPane 		mainPane;
	@FXML private Label				titleLabel;
	@FXML private GridPane			mainGridPane;
	
	@FXML private TextField 		criteriaText;
	@FXML private Button 			searchButton;

	@FXML private TableView<ConceptVersionBI> 		candidatesTableView;
	@FXML private TableColumn<ConceptVersionBI, ConceptVersionBI> candidatesConceptColumn;
	@FXML private TableColumn<ConceptVersionBI, ConceptVersionBI> candidatesCodeSystemColumn;
	@FXML private TableColumn<ConceptVersionBI, ConceptVersionBI> candidatesStatusColumn;

	@FXML private ComboBox<?> 		codeSystemRestrictionCombo;
	@FXML private ComboBox<?> 		refsetRestrictionCombo;
	
	@FXML private RadioButton 		noRestrictionRadio;
	@FXML private RadioButton 		descriptionRestrictionRadio;
	@FXML private RadioButton 		synonymRestrictionRadio;
	@FXML private RadioButton 		fsnRestrictionRadio;
	
	@FXML private ComboBox<?> 		childRestrictionCombo;
	@FXML private ComboBox<?> 		descriptionRestrictionCombo;
	@FXML private ComboBox<SimpleDisplayConcept>	statusCombo;
	@FXML private ComboBox<SimpleDisplayConcept>	qualifierCombo;
	@FXML private ToggleGroup 		desc;	

	@FXML private Button 			clearRestrictionButton;
	@FXML private Button 			saveButton;
	@FXML private Button 			cancelButton;

	private ConceptNode 			sourceConceptNode = new ConceptNode(null, true);
	private ConceptNode				targetConceptNode = new ConceptNode(null, true);

	private MappingSet mappingSet_;

	public Region getRootNode() {
		//return region;
		return mainPane;
	}
	
	public StringProperty getTitle() {
		return titleLabel.textProperty();
	}
	
	@FXML
	public void initialize() {

		assert mainPane						!= null: "fx:id=\"mainPane\" was not injected. Check 'CreateMapping.fxml' file.";
		assert mainGridPane					!= null: "fx:id=\"mainGridPane\" was not injected. Check 'CreateMapping.fxml' file.";
		assert criteriaText					!= null: "fx:id=\"criteriaText\" was not injected. Check 'CreateMapping.fxml' file.";
		assert searchButton					!= null: "fx:id=\"searchButton\" was not injected. Check 'CreateMapping.fxml' file.";
		assert candidatesTableView			!= null: "fx:id=\"candidatesTableView\" was not injected. Check 'CreateMapping.fxml' file.";
		assert candidatesConceptColumn		!= null: "fx:id=\"candidatesConceptColumn\" was not injected. Check 'CreateMapping.fxml' file.";
		assert candidatesCodeSystemColumn	!= null: "fx:id=\"candidatesCodeSystemColumn\" was not injected. Check 'CreateMapping.fxml' file.";
		assert candidatesStatusColumn		!= null: "fx:id=\"candidatesStatusColumn\" was not injected. Check 'CreateMapping.fxml' file.";
		assert codeSystemRestrictionCombo	!= null: "fx:id=\"codeSystemRestrictionCombo\" was not injected. Check 'CreateMapping.fxml' file.";
		assert refsetRestrictionCombo		!= null: "fx:id=\"refsetRestrictionCombo\" was not injected. Check 'CreateMapping.fxml' file.";
		assert noRestrictionRadio			!= null: "fx:id=\"noRestrictionRadio\" was not injected. Check 'CreateMapping.fxml' file.";
		assert descriptionRestrictionRadio	!= null: "fx:id=\"descriptionRestrictionRadio\" was not injected. Check 'CreateMapping.fxml' file.";
		assert synonymRestrictionRadio		!= null: "fx:id=\"synonymRestrictionRadio\" was not injected. Check 'CreateMapping.fxml' file.";
		assert fsnRestrictionRadio			!= null: "fx:id=\"fsnRestrictionRadio\" was not injected. Check 'CreateMapping.fxml' file.";
		assert childRestrictionCombo		!= null: "fx:id=\"childRestrictionCombo\" was not injected. Check 'CreateMapping.fxml' file.";
		assert descriptionRestrictionCombo	!= null: "fx:id=\"descriptionRestrictionCombo\" was not injected. Check 'CreateMapping.fxml' file.";
		assert statusCombo					!= null: "fx:id=\"statusCombo\" was not injected. Check 'CreateMapping.fxml' file.";
		assert qualifierCombo				!= null: "fx:id=\"qualifierCombo\" was not injected. Check 'CreateMapping.fxml' file.";
		assert clearRestrictionButton		!= null: "fx:id=\"clearRestrictionButton\" was not injected. Check 'CreateMapping.fxml' file.";
		assert saveButton					!= null: "fx:id=\"saveButton\" was not injected. Check 'CreateMapping.fxml' file.";
		assert cancelButton					!= null: "fx:id=\"cancelButton\" was not injected. Check 'CreateMapping.fxml' file.";

		setupColumnTypes();
		
		mainGridPane.add(sourceConceptNode.getNode(), 1, 0);
		mainGridPane.add(targetConceptNode.getNode(), 1, 4);
		
		statusCombo.setEditable(false);
		qualifierCombo.setEditable(false);
		
		Utility.execute(() ->
		{
			try
			{
				List<SimpleDisplayConcept> qualifiers = MappingUtils.getQualifierConcepts();
				qualifiers.add(0, new SimpleDisplayConcept("NO QUALIFIER", Integer.MIN_VALUE));
				
				List<SimpleDisplayConcept> status = MappingUtils.getStatusConcepts();
				status.add(0, new SimpleDisplayConcept("NO STATUS", Integer.MIN_VALUE));
				
				Platform.runLater(() ->
				{
					statusCombo.getItems().addAll(status);
					statusCombo.getSelectionModel().select(0);
					qualifierCombo.getItems().addAll(qualifiers);
					qualifierCombo.getSelectionModel().select(0);
				});
			}
			catch (Exception e1)
			{
				LOG.error("Unexpected error populating qualifier and/or status combo fields", e1);
				AppContext.getCommonDialogs().showErrorDialog("Unexpected error configuring status and qualifier options.  See logs.", e1);
			}
		});

		saveButton.setDefaultButton(true);
		saveButton.setOnAction((event) -> {
			MappingItem mi = null;
			try	{
				ConceptVersionBI sourceConcept = sourceConceptNode.getConcept();
				ConceptVersionBI targetConcept = targetConceptNode.getConcept();
				
				if (sourceConcept == null || targetConcept == null) {
					AppContext.getCommonDialogs().showInformationDialog("Cannot Create Mapping Item", "Source and Target Concepts must be specified.");
				} else {
					UUID qualifierUUID = (qualifierCombo.getSelectionModel().getSelectedItem().getNid() == Integer.MIN_VALUE ? null : 
						ExtendedAppContext.getDataStore().getUuidPrimordialForNid(qualifierCombo.getSelectionModel().getSelectedItem().getNid()));
					UUID statusUUID = (statusCombo.getSelectionModel().getSelectedItem().getNid() == Integer.MIN_VALUE ? null : 
						ExtendedAppContext.getDataStore().getUuidPrimordialForNid(statusCombo.getSelectionModel().getSelectedItem().getNid()));
					
					mi = MappingItemDAO.createMappingItem(sourceConcept.getPrimordialUuid(), 
														  mappingSet_.getPrimordialUUID(), 
														  targetConcept.getPrimordialUuid(),
														  qualifierUUID,
														  statusUUID);
				}
			} catch (Exception e)	{
				LOG.error("unexpected", e);
				AppContext.getCommonDialogs().showInformationDialog("Cannot Create Mapping Item", e.getMessage());
			}
			
			if (mi != null) {
				saveButton.getScene().getWindow().hide();
				AppContext.getService(Mapping.class).refreshMappingItems();
			} else {
				saveButton.getScene().getWindow().requestFocus();
			}
		});
		
		cancelButton.setCancelButton(true);
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) { 
				mappingSet_ = null;
				cancelButton.getScene().getWindow().hide();
			}
		});
		cancelButton.setOnKeyPressed(new EventHandler<KeyEvent>()  {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					event.consume();
					cancelButton.fire();
				}
			}
		});
		
		sourceConceptNode.getConceptProperty().addListener(new ChangeListener<ConceptVersionBI>() {
			@Override
			public void changed(ObservableValue<? extends ConceptVersionBI> observable, ConceptVersionBI oldValue, ConceptVersionBI newValue) {
				if (newValue != null) {
					SimpleDisplayConcept sdc = new SimpleDisplayConcept(newValue);
					criteriaText.setText(sdc.getDescription());
					// TODO implement restrictions
					try {
						SearchHandle searchHandle = MappingUtils.search(newValue.getNid(), null, null, null, null, null, null);
						populateSearchResult(searchHandle);
					} catch (IOException e) {
						LOG.error("Error performing search", e);
					}
					
				}
			}
		});
	}
	
	public void setMappingSet(MappingSet mappingSet) {
		mappingSet_ = mappingSet;
	}
	
	public void setSourceConcept(UUID sourceConceptID) {
		ConceptVersionBI sourceConcept = OTFUtility.getConceptVersion(sourceConceptID);
		if (sourceConcept != null) {
			sourceConceptNode.set(sourceConcept);
		}
	}
	
	private void populateSearchResult(SearchHandle searchHandle) {
		candidatesTableView.getItems().clear();
		candidatesTableView.setPlaceholder(LABEL_SEARCHING);
		Utility.execute(() ->
		{
			ObservableList<ConceptVersionBI> conceptList = FXCollections.observableArrayList(); 
			try {
				Collection<CompositeSearchResult> results = searchHandle.getResults();	
				for (CompositeSearchResult result : results) {
					ConceptVersionBI concept = result.getContainingConcept();
					if (concept != null) {
						conceptList.add(concept);
					}
				}
			} catch (Exception e) {
				LOG.error("Error populating search results", e);
				AppContext.getCommonDialogs().showErrorDialog("There was an error populating search results", e);
			}

			Platform.runLater(() -> {			
				if (conceptList.size() == 0) {
					candidatesTableView.setPlaceholder(LABEL_NO_RESULTS);
				} else {
					candidatesTableView.getItems().addAll(conceptList);	
				}
			});
		});
	}

	private Callback<TableColumn.CellDataFeatures<ConceptVersionBI, ConceptVersionBI>, ObservableValue<ConceptVersionBI>> conceptCellValueFactory = 
			new Callback<TableColumn.CellDataFeatures<ConceptVersionBI, ConceptVersionBI>, ObservableValue<ConceptVersionBI>>()	{
		@Override
		public ObservableValue<ConceptVersionBI> call(CellDataFeatures<ConceptVersionBI, ConceptVersionBI> param) {
			return new SimpleObjectProperty<ConceptVersionBI>(param.getValue());
		}
	};
	
	private Callback<TableColumn<ConceptVersionBI, ConceptVersionBI>, TableCell<ConceptVersionBI, ConceptVersionBI>> conceptCellFactory =
			new Callback<TableColumn<ConceptVersionBI, ConceptVersionBI>, TableCell<ConceptVersionBI, ConceptVersionBI>>() {

		@Override
		public TableCell<ConceptVersionBI, ConceptVersionBI> call(TableColumn<ConceptVersionBI, ConceptVersionBI> param) {
			return new TableCell<ConceptVersionBI, ConceptVersionBI>() {
				@Override
				public void updateItem(final ConceptVersionBI concept, boolean empty) {
					super.updateItem(concept, empty);
					updateCell(this, concept);
				}
			};
		}
	};

	private void setupColumnTypes() {
		candidatesConceptColumn.setUserData(MappingColumnType.CONCEPT);
		candidatesCodeSystemColumn.setUserData(MappingColumnType.CODE_SYSTEM);
		candidatesStatusColumn.setUserData(MappingColumnType.STATUS_STRING);
		
		candidatesConceptColumn.setCellFactory(conceptCellFactory);
		candidatesCodeSystemColumn.setCellFactory(conceptCellFactory);
		candidatesStatusColumn.setCellFactory(conceptCellFactory);

		candidatesConceptColumn.setCellValueFactory(conceptCellValueFactory);
		candidatesCodeSystemColumn.setCellValueFactory(conceptCellValueFactory);
		candidatesStatusColumn.setCellValueFactory(conceptCellValueFactory);
	}
	
	private void updateCell(TableCell<?, ?> cell, ConceptVersionBI concept) {
		if (!cell.isEmpty() && concept != null) {
			ContextMenu cm = new ContextMenu();
			cell.setContextMenu(cm);
			final SimpleStringProperty property = new SimpleStringProperty();
			int conceptNid  = 0;
			MappingColumnType columnType = (MappingColumnType) cell.getTableColumn().getUserData();

			cell.setText(null);
			cell.setGraphic(null);

			switch (columnType) {
			case CONCEPT:
				property.set("-");
				conceptNid = concept.getNid();
				Utility.execute(() ->
				{
					String conceptName = OTFUtility.getDescription(concept);
					Platform.runLater(() -> {
						property.set(conceptName);
					});
				});
				break;
				
			case CODE_SYSTEM:
				property.set("-");
				final int pathNid = concept.getPathNid();
				conceptNid = pathNid;
				Utility.execute(() ->
				{
					String pathName = OTFUtility.getDescription(pathNid);
					Platform.runLater(() -> {
						property.set(pathName);
					});
				});
				break;
				
			case STATUS_STRING:
				try {
					property.set(concept.isActive()? "Active":"Inactive");	
				} catch (IOException e) {
					property.set("-error-");
					LOG.error("Error fetching isActive", e);
				}
				
				break;
			default:
				// Nothing
			}
			
			if (property.get() != null) {
				Text text = new Text();
				text.textProperty().bind(property);
				text.wrappingWidthProperty().bind(cell.getTableColumn().widthProperty());
				cell.setGraphic(text);
	
				MenuItem mi = new MenuItem("Copy Value");
				mi.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						CustomClipboard.set(((Text)cell.getGraphic()).getText());
					}
				});
				mi.setGraphic(Images.COPY.createImageView());
				cm.getItems().add(mi);
	
				if (columnType.isConcept() && conceptNid != 0) {
					final int nid = conceptNid;
					CommonMenus.addCommonMenus(cm, new CommonMenusNIdProvider() {
						@Override
						public Collection<Integer> getNIds() {
						   return Arrays.asList(new Integer[] {nid});
						}
					});
				}
			}
		} else {
			cell.setText(null);
			cell.setGraphic(null);
		}
	}

}
