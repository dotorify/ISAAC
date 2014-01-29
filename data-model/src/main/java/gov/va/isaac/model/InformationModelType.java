package gov.va.isaac.model;

import javafx.collections.ObservableList;

import com.sun.javafx.collections.ImmutableObservableList;


/**
 * An enumerated type representing various information models.
 *
 * @author ocarlsen
 */
public enum InformationModelType {

    FHIM("Federal Health Information Model"),
    CEM("Clinical Element Model"),
    CIMI("Clinical Information Model Initiative"),
    HeD("Health eDecision");

    private static final ImmutableObservableList<InformationModelType> VALUES =
            new ImmutableObservableList<>(InformationModelType.values());

    private final String displayName;

    private InformationModelType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static ObservableList<InformationModelType> asObservableList() {
        return VALUES;
    }
}