//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:23 PM PDT 
//

package org.hl7.knowledgeartifact.r1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * Returns the date and time of the start timestamp associated with the
 * evaluation request. Now is defined in this way for two reasons: 1) The
 * operation will always return the same value within any given evaluation,
 * ensuring that the result of an expression containing Now will always return
 * the same result.
 * 
 * 2) The operation will return the timestamp associated with the evaluation
 * request, allowing the evaluation to be performed with the same timezone
 * information as the data delivered with the evaluation request.
 * 
 * <p>
 * Java class for Now complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Now">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:knowledgeartifact:r1}Expression">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Now")
public class Now extends Expression {

}