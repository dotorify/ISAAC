package gov.va.isaac.gui.conceptViews;

import gov.va.isaac.AppContext;
import gov.va.isaac.gui.conceptViews.helpers.ConceptViewerLabelHelper;
import gov.va.isaac.gui.conceptViews.helpers.ConceptViewerTooltipHelper;
import gov.va.isaac.gui.conceptViews.helpers.EnhancedConceptBuilder;
import gov.va.isaac.interfaces.gui.TaxonomyViewI;
import gov.va.isaac.interfaces.gui.views.ConceptViewMode;
import gov.va.isaac.interfaces.gui.views.PopupConceptViewI;
import gov.va.isaac.util.UpdateableBooleanBinding;

import java.util.Stack;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnhancedConceptViewController {
	@FXML protected AnchorPane enhancedConceptPane;

	// Descriptions & Relationships
	@FXML private VBox termVBox;
	@FXML private VBox relVBox;
	@FXML private VBox destVBox;
	@FXML private ScrollPane destScrollPane;
	
	// Top Labels
	@FXML protected Label releaseIdLabel;
	@FXML protected Label isPrimLabel;
	@FXML protected Label fsnLabel;
	@FXML protected VBox conAnnotVBox;
	@FXML protected VBox fsnAnnotVBox;
	
	// Radio Buttons
	@FXML protected ToggleGroup viewGroup;
	@FXML protected RadioButton  historicalRadio;
	@FXML protected RadioButton basicRadio;
	@FXML protected RadioButton detailedRadio;

	// Buttons
	@FXML protected Button closeButton;
	@FXML protected Button taxonomyButton;
	@FXML protected Button modifyButton;
	@FXML protected Button previousButton;
	
	protected ConceptViewerLabelHelper labelHelper;
	protected ConceptViewerTooltipHelper tooltipHelper = new ConceptViewerTooltipHelper();
	
	protected UUID conceptUuid;
	private UpdateableBooleanBinding prevButtonQueueFilled;
	
	public PopupConceptViewI conceptView;
	private ConceptViewMode currentMode;

	private EnhancedConceptBuilder creator;

	private boolean initialized = false;

	private static final Logger LOG = LoggerFactory.getLogger(EnhancedConceptViewController.class);

	AnchorPane getRootNode() {
		return enhancedConceptPane;
	}

	public void setConceptView(EnhancedConceptView enhancedConceptView) {
		conceptView = enhancedConceptView;		
	}

	void setConcept(UUID currentCon, ConceptViewMode mode, Stack<Integer> stack) {
//		if (!initialized ) {
			initialized = true;
			initializeWindow(stack, mode);
//		}
		clearContents();
		conceptUuid = currentCon;
		creator.setConceptValues(currentCon, mode);
	}
	
	void setConcept(UUID currentCon, ConceptViewMode mode) {
		intializePane(mode);
		conceptUuid = currentCon;
		clearContents();
		creator.setConceptValues(currentCon, mode);
	}

	void initializeWindow(Stack<Integer> stack, ConceptViewMode view) {
		commonInit(view);
		
		labelHelper.setPrevConStack(stack);
		labelHelper.setIsWindow(true);

		closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				((Stage)getRootNode().getScene().getWindow()).close();
			}
		});
		
		taxonomyButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (conceptUuid != null)
				{
					AppContext.getService(TaxonomyViewI.class).locateConcept(conceptUuid, null);
				}
				else
				{
					AppContext.getCommonDialogs().showInformationDialog("Invalid Concept", "Can't locate an invalid concept");
				}
			}
		});
		
		previousButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				int prevConNid = labelHelper.getPreviousConceptStack().pop();
				conceptView.setConcept(prevConNid);
			}
		});
		
		prevButtonQueueFilled = new UpdateableBooleanBinding()
		{
			{
//				addBinding(labelHelper.getPreviousConceptStack().);
//				setComputeOnInvalidate(true);
			}
			
			@Override
			protected boolean computeValue()
			{
				return !labelHelper.getPreviousConceptStack().isEmpty();
			}
		};
		previousButton.disableProperty().bind(prevButtonQueueFilled.not());

		if (getRootNode() == null) {
			LOG.error("getRootNode() is null");
		}
		if (tooltipHelper == null) {
			LOG.error("tooltipHelper is null");
		}
		getRootNode().setOnKeyPressed(tooltipHelper.getCtrlKeyPressEventHandler());
		getRootNode().setOnKeyReleased(tooltipHelper.getCtrlKeyReleasedEventHandler());
		
		detailedRadio.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				conceptView.setViewMode(ConceptViewMode.DETAIL_VIEW);
			}
		});

		basicRadio.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				conceptView.setViewMode(ConceptViewMode.SIMPLE_VIEW);
			}
		});
	}
	
	public void setViewMode(ConceptViewMode mode) {
		currentMode = mode;
		clearContents();
		creator.setConceptValues(conceptUuid, mode);
	}

	private void commonInit(ConceptViewMode mode) {
		creator = new EnhancedConceptBuilder(termVBox, relVBox, destVBox, destScrollPane, fsnAnnotVBox, conAnnotVBox, fsnLabel, releaseIdLabel, isPrimLabel);
		
		labelHelper = new ConceptViewerLabelHelper(conceptView);
		labelHelper.setPane(getRootNode());
		creator.setLabelHelper(labelHelper);
		
		setModeType(mode);

		// TODO (Until handled, make disabled)
		modifyButton.setDisable(true);
		historicalRadio.setDisable(true);

		Tooltip notYetImplTooltip = new Tooltip("Not Yet Implemented");
		modifyButton.setTooltip(notYetImplTooltip);
		historicalRadio.setTooltip(notYetImplTooltip);
	}

	void intializePane(ConceptViewMode view) {
		commonInit(view);
		closeButton.setVisible(false);
		previousButton.setVisible(false);
		modifyButton.setVisible(false);
		taxonomyButton.setVisible(false);
	}

	public void setModeType(ConceptViewMode mode) {
		currentMode = mode;
		
		if (mode == ConceptViewMode.SIMPLE_VIEW) {
			basicRadio.setSelected(true);
		} else if (mode == ConceptViewMode.DETAIL_VIEW) {
			detailedRadio.setSelected(true);
		}
	}

	public ConceptViewMode getViewMode() {
		return currentMode;
	}
	
	public String getTitle() {
        return fsnLabel.getText();
    }
	
	private void clearContents() {
		releaseIdLabel.setText("");
		isPrimLabel.setText("");
		fsnLabel.setText("");
		termVBox.getChildren().clear();
		destVBox.getChildren().clear();
		relVBox.getChildren().clear();
		conAnnotVBox.getChildren().clear();
		fsnAnnotVBox.getChildren().clear();
	}
}
