package gov.va.isaac.gui.conceptViews.componentRows;

import gov.va.isaac.gui.conceptViews.RelRow;
import gov.va.isaac.gui.conceptViews.helpers.ConceptViewerHelper.ComponentType;
import gov.va.isaac.gui.conceptViews.helpers.ConceptViewerLabelHelper;
import gov.va.isaac.util.WBUtility;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.shape.Rectangle;

import org.ihtsdo.otf.tcc.api.relationship.RelationshipVersionBI;

public class SimpleRelRow extends RelRow {

	public SimpleRelRow(ConceptViewerLabelHelper labelHelper) {
		super(labelHelper);
	}

	public GridPane createRelGridPane(RelationshipVersionBI rel) {
		if (!rel.isInferred()) {
			Rectangle rec = createAnnotRectangle(rel);
			Label relLabel = labelHelper.createComponentLabel(rel, WBUtility.getConPrefTerm(rel.getDestinationNid()), ComponentType.RELATIONSHIP, rel.getDestinationNid(), true);
			Label relTypeLabel = labelHelper.createComponentLabel(rel, WBUtility.getConPrefTerm(rel.getTypeNid()), ComponentType.RELATIONSHIP, rel.getTypeNid(), true);
	
			GridPane gp = new GridPane();
			gp.setHgap(3);
			
			//ColumnConstraints(double minWidth, double prefWidth, double maxWidth, Priority hgrow, HPos halignment, boolean fillWidth) 
			ColumnConstraints column1 = new ColumnConstraints(10);
	
			ColumnConstraints column2 = new ColumnConstraints(10, 394, 404);
			column2.setFillWidth(true);
			
			ColumnConstraints column3 = new ColumnConstraints(10, 94, 120);
			column3.setFillWidth(true);
		    
			gp.getColumnConstraints().addAll(column1, column2, column3 ); // first column gets any extra width		
			
			//setConstraints(Node child, int columnIndex, int rowIndex, int columnspan, int rowspan, HPos halignment, 
			//				 VPos valignment, Priority hgrow, Priority vgrow, Insets margin)
			gp.setConstraints(rec,  0,  0,  1,  1,  HPos.CENTER,  VPos.CENTER, Priority.NEVER, Priority.ALWAYS);
			gp.setConstraints(relLabel,  1,  0,  1,  1,  HPos.LEFT,  VPos.CENTER, Priority.SOMETIMES, Priority.ALWAYS);
			gp.setConstraints(relTypeLabel,  2,  0,  1,  1,  HPos.RIGHT,  VPos.CENTER, Priority.SOMETIMES, Priority.ALWAYS);
			
			gp.getChildren().addAll(rec, relLabel, relTypeLabel);
			
			return gp;
		}
		
		return null;
	}
}
