//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.04.03 at 01:32:58 AM IST 
//


package com.squill.og.crawler.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContentHandler complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContentHandler">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="handler" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="feedUploader" type="{http://www.squilla.co.in/WebCrawlers}FeedUploader"/>
 *       &lt;/sequence>
 *       &lt;attribute name="preClassifiedType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContentHandler", propOrder = {
    "handler",
    "feedUploader"
})
public class ContentHandler {

    @XmlElement(required = true)
    protected String handler;
    @XmlElement(required = true)
    protected FeedUploader feedUploader;
    @XmlAttribute(name = "preClassifiedType")
    protected String preClassifiedType;

    /**
     * Gets the value of the handler property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandler() {
        return handler;
    }

    /**
     * Sets the value of the handler property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandler(String value) {
        this.handler = value;
    }

    /**
     * Gets the value of the feedUploader property.
     * 
     * @return
     *     possible object is
     *     {@link FeedUploader }
     *     
     */
    public FeedUploader getFeedUploader() {
        return feedUploader;
    }

    /**
     * Sets the value of the feedUploader property.
     * 
     * @param value
     *     allowed object is
     *     {@link FeedUploader }
     *     
     */
    public void setFeedUploader(FeedUploader value) {
        this.feedUploader = value;
    }

    /**
     * Gets the value of the preClassifiedType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreClassifiedType() {
        return preClassifiedType;
    }

    /**
     * Sets the value of the preClassifiedType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreClassifiedType(String value) {
        this.preClassifiedType = value;
    }

}
