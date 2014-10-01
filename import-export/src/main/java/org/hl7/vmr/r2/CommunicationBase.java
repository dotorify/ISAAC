//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.30 at 06:15:10 PM PDT 
//


package org.hl7.vmr.r2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.CD;
import org.hl7.cdsdt.r2.ED;


/**
 * A communication is a message sent between a sender and a recipient for a purpose and about a topic. 
 * 
 * The specific type of entity (e.g., Attending Physician or Public Health Agency) is identified by the entityType of the sender or recipient.  
 * 
 * There maybe a related clinical statement that identifies the topic of the communication in greater detail.
 * 
 * <p>Java class for CommunicationBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CommunicationBase">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}ClinicalStatement">
 *       &lt;sequence>
 *         &lt;element name="medium" type="{urn:hl7-org:cdsdt:r2}CD" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="message" type="{urn:hl7-org:cdsdt:r2}ED" minOccurs="0"/>
 *         &lt;element name="reason" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="recipient" type="{urn:hl7-org:vmr:r2}Entity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sender" type="{urn:hl7-org:vmr:r2}Entity" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CommunicationBase", propOrder = {
    "medium",
    "message",
    "reason",
    "recipient",
    "sender"
})
@XmlSeeAlso({
    CommunicationOrder.class,
    CommunicationProposal.class,
    CommunicationEvent.class
})
public abstract class CommunicationBase
    extends ClinicalStatement
{

    protected List<CD> medium;
    protected ED message;
    protected CD reason;
    protected List<Entity> recipient;
    protected Entity sender;

    /**
     * Gets the value of the medium property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the medium property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMedium().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CD }
     * 
     * 
     */
    public List<CD> getMedium() {
        if (medium == null) {
            medium = new ArrayList<CD>();
        }
        return this.medium;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link ED }
     *     
     */
    public ED getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link ED }
     *     
     */
    public void setMessage(ED value) {
        this.message = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link CD }
     *     
     */
    public CD getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link CD }
     *     
     */
    public void setReason(CD value) {
        this.reason = value;
    }

    /**
     * Gets the value of the recipient property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recipient property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecipient().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Entity }
     * 
     * 
     */
    public List<Entity> getRecipient() {
        if (recipient == null) {
            recipient = new ArrayList<Entity>();
        }
        return this.recipient;
    }

    /**
     * Gets the value of the sender property.
     * 
     * @return
     *     possible object is
     *     {@link Entity }
     *     
     */
    public Entity getSender() {
        return sender;
    }

    /**
     * Sets the value of the sender property.
     * 
     * @param value
     *     allowed object is
     *     {@link Entity }
     *     
     */
    public void setSender(Entity value) {
        this.sender = value;
    }

}
