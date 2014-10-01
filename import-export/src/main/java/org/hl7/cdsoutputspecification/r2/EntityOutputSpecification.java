//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.cdsoutputspecification.r2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.hl7.vmr.r2.CodedIdentifier;


/**
 * Specifies the entities to be provided as a part of the output.  
 * 
 * <p>Java class for EntityOutputSpecification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EntityOutputSpecification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityTemplate" type="{urn:hl7-org:vmr:r2}CodedIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="potentialEntityTemplate" type="{urn:hl7-org:vmr:r2}CodedIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntityOutputSpecification", propOrder = {
    "entityTemplate",
    "potentialEntityTemplate"
})
public class EntityOutputSpecification {

    protected List<CodedIdentifier> entityTemplate;
    protected List<CodedIdentifier> potentialEntityTemplate;

    /**
     * Gets the value of the entityTemplate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entityTemplate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntityTemplate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodedIdentifier }
     * 
     * 
     */
    public List<CodedIdentifier> getEntityTemplate() {
        if (entityTemplate == null) {
            entityTemplate = new ArrayList<CodedIdentifier>();
        }
        return this.entityTemplate;
    }

    /**
     * Gets the value of the potentialEntityTemplate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the potentialEntityTemplate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPotentialEntityTemplate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodedIdentifier }
     * 
     * 
     */
    public List<CodedIdentifier> getPotentialEntityTemplate() {
        if (potentialEntityTemplate == null) {
            potentialEntityTemplate = new ArrayList<CodedIdentifier>();
        }
        return this.potentialEntityTemplate;
    }

}
