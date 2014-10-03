//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:25 PM PDT 
//

package org.hl7.vmr.r2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.CD;
import org.hl7.cdsdt.r2.IVLTS;

/**
 * Proposal, e.g., by a CDS system, for a Supply to be delivered.
 * 
 * <p>
 * Java class for SupplyProposal complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SupplyProposal">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}SupplyBase">
 *       &lt;sequence>
 *         &lt;element name="proposedSupplyTime" type="{urn:hl7-org:cdsdt:r2}IVL_TS" minOccurs="0"/>
 *         &lt;element name="frequency" type="{urn:hl7-org:vmr:r2}Schedule" minOccurs="0"/>
 *         &lt;element name="reason" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="urgency" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="originationMode" type="{urn:hl7-org:cdsdt:r2}CD" minOccurs="0"/>
 *         &lt;element name="proposalEventTime" type="{urn:hl7-org:cdsdt:r2}IVL_TS" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SupplyProposal", propOrder = {
    "proposedSupplyTime", "frequency", "reason", "urgency", "originationMode",
    "proposalEventTime"
})
public class SupplyProposal extends SupplyBase {

  protected IVLTS proposedSupplyTime;

  protected Schedule frequency;

  protected CD reason;

  protected CD urgency;

  protected CD originationMode;

  protected IVLTS proposalEventTime;

  /**
   * Gets the value of the proposedSupplyTime property.
   * 
   * @return possible object is {@link IVLTS }
   * 
   */
  public IVLTS getProposedSupplyTime() {
    return proposedSupplyTime;
  }

  /**
   * Sets the value of the proposedSupplyTime property.
   * 
   * @param value allowed object is {@link IVLTS }
   * 
   */
  public void setProposedSupplyTime(IVLTS value) {
    this.proposedSupplyTime = value;
  }

  /**
   * Gets the value of the frequency property.
   * 
   * @return possible object is {@link Schedule }
   * 
   */
  public Schedule getFrequency() {
    return frequency;
  }

  /**
   * Sets the value of the frequency property.
   * 
   * @param value allowed object is {@link Schedule }
   * 
   */
  public void setFrequency(Schedule value) {
    this.frequency = value;
  }

  /**
   * Gets the value of the reason property.
   * 
   * @return possible object is {@link CD }
   * 
   */
  public CD getReason() {
    return reason;
  }

  /**
   * Sets the value of the reason property.
   * 
   * @param value allowed object is {@link CD }
   * 
   */
  public void setReason(CD value) {
    this.reason = value;
  }

  /**
   * Gets the value of the urgency property.
   * 
   * @return possible object is {@link CD }
   * 
   */
  public CD getUrgency() {
    return urgency;
  }

  /**
   * Sets the value of the urgency property.
   * 
   * @param value allowed object is {@link CD }
   * 
   */
  public void setUrgency(CD value) {
    this.urgency = value;
  }

  /**
   * Gets the value of the originationMode property.
   * 
   * @return possible object is {@link CD }
   * 
   */
  public CD getOriginationMode() {
    return originationMode;
  }

  /**
   * Sets the value of the originationMode property.
   * 
   * @param value allowed object is {@link CD }
   * 
   */
  public void setOriginationMode(CD value) {
    this.originationMode = value;
  }

  /**
   * Gets the value of the proposalEventTime property.
   * 
   * @return possible object is {@link IVLTS }
   * 
   */
  public IVLTS getProposalEventTime() {
    return proposalEventTime;
  }

  /**
   * Sets the value of the proposalEventTime property.
   * 
   * @param value allowed object is {@link IVLTS }
   * 
   */
  public void setProposalEventTime(IVLTS value) {
    this.proposalEventTime = value;
  }

}