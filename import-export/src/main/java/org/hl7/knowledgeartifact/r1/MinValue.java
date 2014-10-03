//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:23 PM PDT 
//

package org.hl7.knowledgeartifact.r1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;

/**
 * The MinValue operator returns the minimum representable value for the given
 * type.
 * 
 * <p>
 * Java class for MinValue complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="MinValue">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:knowledgeartifact:r1}Expression">
 *       &lt;attribute name="valueType" use="required" type="{http://www.w3.org/2001/XMLSchema}QName" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MinValue")
public class MinValue extends Expression {

  @XmlAttribute(name = "valueType", required = true)
  protected QName valueType;

  /**
   * Gets the value of the valueType property.
   * 
   * @return possible object is {@link QName }
   * 
   */
  public QName getValueType() {
    return valueType;
  }

  /**
   * Sets the value of the valueType property.
   * 
   * @param value allowed object is {@link QName }
   * 
   */
  public void setValueType(QName value) {
    this.valueType = value;
  }

}