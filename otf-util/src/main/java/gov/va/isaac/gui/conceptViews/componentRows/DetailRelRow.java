package gov.va.isaac.gui.conceptViews.componentRows;

import gov.va.isaac.gui.conceptViews.helpers.ConceptViewerHelper.ComponentType;
import gov.va.isaac.gui.conceptViews.helpers.ConceptViewerLabelHelper;
import gov.va.isaac.util.WBUtility;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Rectangle;

import org.ihtsdo.otf.tcc.api.relationship.RelationshipVersionBI;

public class DetailRelRow extends RelRow {
	
	public DetailRelRow(ConceptViewerLabelHelper labelHelper) {
		super(labelHelper);
	}

	@Override
	public void addRelRow(RelationshipVersionBI<?> rel) {
		Rectangle rec = createAnnotRectangle(rel);
		Label relLabel;
		if (isDetailed) {
			relLabel = labelHelper.createLabel(rel, WBUtility.getConPrefTerm(rel.getConceptNid()), ComponentType.RELATIONSHIP, rel.getConceptNid());
		} else {
			relLabel = labelHelper.createLabel(rel, WBUtility.getConPrefTerm(rel.getDestinationNid()), ComponentType.RELATIONSHIP, rel.getDestinationNid());
		}
		Label relTypeLabel = labelHelper.createLabel(rel, WBUtility.getConPrefTerm(rel.getTypeNid()), ComponentType.RELATIONSHIP, rel.getTypeNid());
		Label relGroupLabel = labelHelper.createLabel(rel, String.valueOf(rel.getGroup()), ComponentType.RELATIONSHIP, 0);
		Label relCharLabel = labelHelper.createLabel(rel, WBUtility.getConPrefTerm(rel.getCharacteristicNid()), ComponentType.RELATIONSHIP, rel.getCharacteristicNid());
		Label relRefLabel = labelHelper.createLabel(rel, WBUtility.getConPrefTerm(rel.getRefinabilityNid()), ComponentType.RELATIONSHIP, rel.getRefinabilityNid());
		
		if (rel.isUncommitted()) {
			relLabel.setUnderline(true);
			relTypeLabel.setUnderline(true);
			relCharLabel.setUnderline(true);
			relRefLabel.setUnderline(true);
		}

		//setConstraints(Node child, int columnIndex, int rowIndex, int columnspan, int rowspan, HPos halignment, 
		//				 VPos valignment, Priority hgrow, Priority vgrow, Insets margin)
		GridPane.setConstraints(rec,  0,  0,  1,  1,  HPos.CENTER,  VPos.CENTER, Priority.NEVER, Priority.ALWAYS);
		GridPane.setConstraints(relLabel,  1,  0,  1,  1,  HPos.LEFT,  VPos.CENTER, Priority.SOMETIMES, Priority.ALWAYS);
		GridPane.setConstraints(relTypeLabel,  3,  0,  1,  1,  HPos.CENTER,  VPos.CENTER, Priority.SOMETIMES, Priority.ALWAYS);
		GridPane.setConstraints(relGroupLabel,  4,  0,  1,  1,  HPos.CENTER,  VPos.CENTER, Priority.SOMETIMES, Priority.ALWAYS);
		GridPane.setConstraints(relCharLabel,  5,  0,  1,  1,  HPos.CENTER,  VPos.CENTER, Priority.SOMETIMES, Priority.ALWAYS);
		GridPane.setConstraints(relRefLabel,  6,  0,  1,  1,  HPos.CENTER,  VPos.CENTER, Priority.SOMETIMES, Priority.ALWAYS);
		
		GridPane.setMargin(relLabel, new Insets(0, 20, 0, 20));
		GridPane.setMargin(relTypeLabel, new Insets(0, 20, 0, 20));
		GridPane.setMargin(relGroupLabel, new Insets(0, 20, 0, 20));
		GridPane.setMargin(relCharLabel, new Insets(0, 20, 0, 20));
		GridPane.setMargin(relRefLabel, new Insets(0, 0, 0, 20));

		currentPane.addRow(counter++, rec, relLabel, relTypeLabel, relGroupLabel, relCharLabel, relRefLabel);
	}

	@Override
	public void createGridPane() {
		gp = new GridPane();
		gp.setHgap(3);
		currentPane = gp;
		
		dgp = new GridPane();
		dgp.setHgap(3);
		
		//ColumnConstraints(double minWidth, double prefWidth, double maxWidth, Priority hgrow, HPos halignment, boolean fillWidth) 
/*		ColumnConstraints column1 = new ColumnConstraints(10);

		ColumnConstraints column2 = new ColumnConstraints(10, 394, 404);
		column2.setFillWidth(true);
		
		ColumnConstraints column3 = new ColumnConstraints(10, 94, 120);
		column3.setFillWidth(true);
	    
		gp.getColumnConstraints().addAll(column1, column2, column3 ); // first column gets any extra width		
*/	
	}
}
