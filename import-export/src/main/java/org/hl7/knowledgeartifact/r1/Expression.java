//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.09.26 at 04:34:23 PM PDT 
//

package org.hl7.knowledgeartifact.r1;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * The Expression type defines the abstract base type for all expressions used
 * in the HeDS expression language.
 * 
 * <p>
 * Java class for Expression complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Expression">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="annotation" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Expression", propOrder = {
    "description", "annotation"
})
@XmlSeeAlso({
    Property.class, First.class, Date.class, Distinct.class, ForEach.class,
    Filter.class, DatePart.class, Null.class, Conditional.class,
    MinValue.class, Interval.class, MaxValue.class, ObjectExpression.class,
    Combine.class, ObjectRedefine.class, Split.class, Indexer.class,
    Case.class, ParameterRef.class, Current.class, Sort.class, Literal.class,
    Last.class, DateDiff.class, Today.class, ExpressionRef.class,
    TernaryExpression.class, NaryExpression.class, ObjectDescriptor.class,
    Substring.class, org.hl7.knowledgeartifact.r1.List.class, Round.class,
    ComplexLiteral.class, IndexOf.class, Now.class, Pos.class,
    BinaryExpression.class, AggregateExpression.class, DateAdd.class,
    UnaryExpression.class
})
public abstract class Expression {

  protected String description;

  protected java.util.List<Object> annotation;

  /**
   * Gets the value of the description property.
   * 
   * @return possible object is {@link String }
   * 
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the value of the description property.
   * 
   * @param value allowed object is {@link String }
   * 
   */
  public void setDescription(String value) {
    this.description = value;
  }

  /**
   * Gets the value of the annotation property.
   * 
   * <p>
   * This accessor method returns a reference to the live list, not a snapshot.
   * Therefore any modification you make to the returned list will be present
   * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
   * for the annotation property.
   * 
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getAnnotation().add(newItem);
   * </pre>
   * 
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list {@link Object }
   * 
   * 
   */
  public java.util.List<Object> getAnnotation() {
    if (annotation == null) {
      annotation = new ArrayList<Object>();
    }
    return this.annotation;
  }

}