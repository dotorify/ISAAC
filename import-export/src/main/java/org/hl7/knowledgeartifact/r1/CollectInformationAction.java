//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.knowledgeartifact.r1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This action requests information from the actor. The
 * 				information request is specified as a DocumentationItem.
 * 			
 * 
 * <p>Java class for CollectInformationAction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CollectInformationAction">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:knowledgeartifact:r1}AtomicAction">
 *       &lt;sequence>
 *         &lt;element name="documentationConcept" type="{urn:hl7-org:knowledgeartifact:r1}DocumentationItem"/>
 *         &lt;element name="initialValue" type="{urn:hl7-org:knowledgeartifact:r1}Expression" minOccurs="0"/>
 *         &lt;element name="responseBinding" type="{urn:hl7-org:knowledgeartifact:r1}ResponseBinding" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CollectInformationAction", propOrder = {
    "documentationConcept",
    "initialValue",
    "responseBinding"
})
public class CollectInformationAction
    extends AtomicAction
{

    @XmlElement(required = true)
    protected DocumentationItem documentationConcept;
    protected Expression initialValue;
    protected ResponseBinding responseBinding;

    /**
     * Gets the value of the documentationConcept property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentationItem }
     *     
     */
    public DocumentationItem getDocumentationConcept() {
        return documentationConcept;
    }

    /**
     * Sets the value of the documentationConcept property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentationItem }
     *     
     */
    public void setDocumentationConcept(DocumentationItem value) {
        this.documentationConcept = value;
    }

    /**
     * Gets the value of the initialValue property.
     * 
     * @return
     *     possible object is
     *     {@link Expression }
     *     
     */
    public Expression getInitialValue() {
        return initialValue;
    }

    /**
     * Sets the value of the initialValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Expression }
     *     
     */
    public void setInitialValue(Expression value) {
        this.initialValue = value;
    }

    /**
     * Gets the value of the responseBinding property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseBinding }
     *     
     */
    public ResponseBinding getResponseBinding() {
        return responseBinding;
    }

    /**
     * Sets the value of the responseBinding property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseBinding }
     *     
     */
    public void setResponseBinding(ResponseBinding value) {
        this.responseBinding = value;
    }

}
