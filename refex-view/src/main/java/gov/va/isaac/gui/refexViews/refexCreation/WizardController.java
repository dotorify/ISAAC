package gov.va.isaac.gui.refexViews.refexCreation;

import java.util.ArrayList;
import java.util.List;

import org.ihtsdo.otf.tcc.api.concept.ConceptChronicleBI;
import org.ihtsdo.otf.tcc.api.concept.ConceptVersionBI;
import org.ihtsdo.otf.tcc.api.refexDynamic.data.RefexDynamicColumnInfo;
import org.ihtsdo.otf.tcc.api.refexDynamic.data.RefexDynamicDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WizardController {
	private ConceptChronicleBI refsetCon;

	private String refexName;
	private String refsetDescription;
	private int extendedFieldsCount;
	private boolean isAnnotated;
	private ConceptVersionBI parentConcept;
	private boolean isReadOnly;
	
	private List<ConceptVersionBI> columnNids = new ArrayList<ConceptVersionBI>();
	private List<RefexDynamicDataType> columnTypeStrings = new ArrayList<RefexDynamicDataType>();
	private List<String> columnDefaultValues = new ArrayList<String>();
	private List<Boolean> columnIsMandatory = new ArrayList<Boolean>();

	private static final Logger logger = LoggerFactory.getLogger(WizardController.class);

	public void setRefsetConcept(ConceptChronicleBI con) {
		refsetCon = con;
	}
	public ConceptChronicleBI getRefsetConcept() {
		return refsetCon;
	}

	public void setNewRefsetConceptVals(String name, String description, ConceptVersionBI parentConcept, int extendedFieldsCount, boolean isAnnotated, boolean isReadOnly) {
		this.refexName = name;
		this.refsetDescription = description;
		this.parentConcept = parentConcept;
		this.extendedFieldsCount = extendedFieldsCount + 1;
		this.isAnnotated = isAnnotated;
		this.isReadOnly = isReadOnly;
	}

	public void setReferencedComponentVals(ConceptVersionBI colCon, RefexDynamicDataType type, String defaultValue, boolean isMandatory) {
		setColumnVals(colCon, type, defaultValue, isMandatory);
	}

	public void setColumnVals(ConceptVersionBI colCon, RefexDynamicDataType type, String defaultValue, boolean isMandatory) {
		columnNids.add(colCon);
		columnTypeStrings.add(type);
		columnDefaultValues.add(defaultValue);		
		columnIsMandatory.add(isMandatory);
	}

	public String getRefexName() {
		return refexName;
	}

	public String getRefexDescription() {
		return refsetDescription;
	}

	public String getParentConceptFsn() {
		return getConceptFsn(parentConcept);
	}

	private String getConceptFsn(ConceptVersionBI con) {
		try {
			if (con == null) {
				return "";
			}
			return con.getFullySpecifiedDescription().getText().trim();
		} catch (Exception e) {
			logger.error("Unable to identify FSN of concept" + con.getPrimordialUuid().toString(), e);
			return "Not Accessible";
		}
	}

	public boolean isReadOnlyRefex() {
		return isReadOnly;
	}

	public boolean isAnnotated() {
		return isAnnotated;
	}
	
	public int getExtendedFieldsCount() {
		return extendedFieldsCount;
	}

	public String getColumnDescription(int column) {
		try {
			return columnNids.get(column).getFullySpecifiedDescription().getText().trim();
		} catch (Exception e) {
			logger.error("Unable to identify FSN of column #" + column, e);
			return "Not Accessible";
		}
	}

	public String getColumnType(int column) {
		return columnTypeStrings.get(column).getDisplayName();
	}

	public String getColumnDefaultValue(int column) {
		return columnDefaultValues.get(column);
	}

	public String getColumnIsMandatory(int column) {
		if (columnIsMandatory.get(column)) {
			return "Mandatory";
		} else {
			return "Optional";
		}
	}

	public String getRefCompDesc() {
		return getConceptFsn(columnNids.get(0));
	}

	public RefexDynamicColumnInfo[] getColumnInfo()
	{
		//TODO this is also broken, and building two columns, when only one is specified.  funny stuff happening in the GUI.
		RefexDynamicColumnInfo[] result = new RefexDynamicColumnInfo[columnTypeStrings.size()];
		for (int i = 0; i < result.length; i++)
		{
			//TODO defaultValues needs to be an Object, not a String, and it needs to match the type declared in columnTypes.
			result[i] = new RefexDynamicColumnInfo(i, columnNids.get(i).getPrimordialUuid(), columnTypeStrings.get(i), columnDefaultValues.get(i));
		}
		return result;
	}

}
