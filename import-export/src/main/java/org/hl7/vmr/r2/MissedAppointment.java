//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:25 PM PDT 
//

package org.hl7.vmr.r2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.hl7.cdsdt.r2.IVLTS;

/**
 * An appointment that was (i) scheduled, (ii) not rescheduled or canceled, and
 * (iii) for which the EvaluatedPerson did not show up.
 * 
 * <p>
 * Java class for MissedAppointment complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="MissedAppointment">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:vmr:r2}EncounterBase">
 *       &lt;sequence>
 *         &lt;element name="appointmentTime" type="{urn:hl7-org:cdsdt:r2}IVL_TS"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MissedAppointment", propOrder = {
  "appointmentTime"
})
public class MissedAppointment extends EncounterBase {

  @XmlElement(required = true)
  protected IVLTS appointmentTime;

  /**
   * Gets the value of the appointmentTime property.
   * 
   * @return possible object is {@link IVLTS }
   * 
   */
  public IVLTS getAppointmentTime() {
    return appointmentTime;
  }

  /**
   * Sets the value of the appointmentTime property.
   * 
   * @param value allowed object is {@link IVLTS }
   * 
   */
  public void setAppointmentTime(IVLTS value) {
    this.appointmentTime = value;
  }

}