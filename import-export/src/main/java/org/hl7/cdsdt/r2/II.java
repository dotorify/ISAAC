//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:25 PM PDT 
//

package org.hl7.cdsdt.r2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.hl7.vmr.r2.CodedIdentifier;

/**
 * An identifier that uniquely identifies a thing or object.
 * 
 * Examples are object identifier for HL7 RIM objects, medical record number,
 * order id, service catalog item id, Vehicle Identification Number (VIN), etc.
 * Instance identifiers are usually defined based on ISO object identifiers.
 * 
 * An identifier allows someone to select one record, object or thing from a set
 * of candidates. Usually an identifier alone without any context is not usable.
 * Identifiers are distinguished from concept descriptors as concept descriptors
 * never identify an individual thing, although there may sometimes be an
 * individual record or object that represents the concept.
 * 
 * Information Processing Entities claiming direct or indirect conformance SHALL
 * never assume that receiving applications can infer the identity of issuing
 * authority or the type of the identifier from the identifier or components
 * thereof.
 * 
 * <p>
 * Java class for II complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="II">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:cdsdt:r2}ANY">
 *       &lt;attribute name="root" use="required" type="{urn:hl7-org:cdsdt:r2}Uid" />
 *       &lt;attribute name="extension" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="identifierName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "II")
@XmlSeeAlso({
  CodedIdentifier.class
})
public class II extends ANY {

  @XmlAttribute(name = "root", required = true)
  protected String root;

  @XmlAttribute(name = "extension")
  protected String extension;

  @XmlAttribute(name = "identifierName")
  protected String identifierName;

  /**
   * Gets the value of the root property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getRoot() {
    return root;
  }

  /**
   * Sets the value of the root property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setRoot(String value) {
    this.root = value;
  }

  /**
   * Gets the value of the extension property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getExtension() {
    return extension;
  }

  /**
   * Sets the value of the extension property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setExtension(String value) {
    this.extension = value;
  }

  /**
   * Gets the value of the identifierName property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getIdentifierName() {
    return identifierName;
  }

  /**
   * Sets the value of the identifierName property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setIdentifierName(String value) {
    this.identifierName = value;
  }

}