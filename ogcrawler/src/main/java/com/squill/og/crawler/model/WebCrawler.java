//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.04.11 at 01:56:57 AM IST 
//


package com.squill.og.crawler.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WebCrawler complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WebCrawler">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="webLinkTracker" type="{http://www.squilla.co.in/WebCrawlers}WebTracker" minOccurs="0"/>
 *         &lt;element name="contentHandler" type="{http://www.squilla.co.in/WebCrawlers}ContentHandler"/>
 *         &lt;element name="geoTagResolver" type="{http://www.squilla.co.in/WebCrawlers}GeoTagResolver" minOccurs="0"/>
 *         &lt;element name="taxonomyClassifier" type="{http://www.squilla.co.in/WebCrawlers}TaxonomyClassifier" minOccurs="0"/>
 *         &lt;element name="articleSummarizer" type="{http://www.squilla.co.in/WebCrawlers}ArticleSummarizer" minOccurs="0"/>
 *         &lt;element name="linkFilter" type="{http://www.squilla.co.in/WebCrawlers}LinkFilter" minOccurs="0"/>
 *         &lt;element name="scheduler" type="{http://www.squilla.co.in/WebCrawlers}scheduler"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="domainUrl" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="flushFrequency" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="thresholdFrequency" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="robotRulesExists" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WebCrawler", propOrder = {
    "name",
    "description",
    "webLinkTracker",
    "contentHandler",
    "geoTagResolver",
    "taxonomyClassifier",
    "articleSummarizer",
    "linkFilter",
    "scheduler"
})
public class WebCrawler {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String description;
    protected WebTracker webLinkTracker;
    @XmlElement(required = true)
    protected ContentHandler contentHandler;
    protected GeoTagResolver geoTagResolver;
    protected TaxonomyClassifier taxonomyClassifier;
    protected ArticleSummarizer articleSummarizer;
    protected LinkFilter linkFilter;
    @XmlElement(required = true)
    protected Scheduler scheduler;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "domainUrl", required = true)
    protected String domainUrl;
    @XmlAttribute(name = "flushFrequency")
    protected Integer flushFrequency;
    @XmlAttribute(name = "thresholdFrequency")
    protected Integer thresholdFrequency;
    @XmlAttribute(name = "robotRulesExists", required = true)
    protected boolean robotRulesExists;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the webLinkTracker property.
     * 
     * @return
     *     possible object is
     *     {@link WebTracker }
     *     
     */
    public WebTracker getWebLinkTracker() {
        return webLinkTracker;
    }

    /**
     * Sets the value of the webLinkTracker property.
     * 
     * @param value
     *     allowed object is
     *     {@link WebTracker }
     *     
     */
    public void setWebLinkTracker(WebTracker value) {
        this.webLinkTracker = value;
    }

    /**
     * Gets the value of the contentHandler property.
     * 
     * @return
     *     possible object is
     *     {@link ContentHandler }
     *     
     */
    public ContentHandler getContentHandler() {
        return contentHandler;
    }

    /**
     * Sets the value of the contentHandler property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentHandler }
     *     
     */
    public void setContentHandler(ContentHandler value) {
        this.contentHandler = value;
    }

    /**
     * Gets the value of the geoTagResolver property.
     * 
     * @return
     *     possible object is
     *     {@link GeoTagResolver }
     *     
     */
    public GeoTagResolver getGeoTagResolver() {
        return geoTagResolver;
    }

    /**
     * Sets the value of the geoTagResolver property.
     * 
     * @param value
     *     allowed object is
     *     {@link GeoTagResolver }
     *     
     */
    public void setGeoTagResolver(GeoTagResolver value) {
        this.geoTagResolver = value;
    }

    /**
     * Gets the value of the taxonomyClassifier property.
     * 
     * @return
     *     possible object is
     *     {@link TaxonomyClassifier }
     *     
     */
    public TaxonomyClassifier getTaxonomyClassifier() {
        return taxonomyClassifier;
    }

    /**
     * Sets the value of the taxonomyClassifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxonomyClassifier }
     *     
     */
    public void setTaxonomyClassifier(TaxonomyClassifier value) {
        this.taxonomyClassifier = value;
    }

    /**
     * Gets the value of the articleSummarizer property.
     * 
     * @return
     *     possible object is
     *     {@link ArticleSummarizer }
     *     
     */
    public ArticleSummarizer getArticleSummarizer() {
        return articleSummarizer;
    }

    /**
     * Sets the value of the articleSummarizer property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArticleSummarizer }
     *     
     */
    public void setArticleSummarizer(ArticleSummarizer value) {
        this.articleSummarizer = value;
    }

    /**
     * Gets the value of the linkFilter property.
     * 
     * @return
     *     possible object is
     *     {@link LinkFilter }
     *     
     */
    public LinkFilter getLinkFilter() {
        return linkFilter;
    }

    /**
     * Sets the value of the linkFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinkFilter }
     *     
     */
    public void setLinkFilter(LinkFilter value) {
        this.linkFilter = value;
    }

    /**
     * Gets the value of the scheduler property.
     * 
     * @return
     *     possible object is
     *     {@link Scheduler }
     *     
     */
    public Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * Sets the value of the scheduler property.
     * 
     * @param value
     *     allowed object is
     *     {@link Scheduler }
     *     
     */
    public void setScheduler(Scheduler value) {
        this.scheduler = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the domainUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomainUrl() {
        return domainUrl;
    }

    /**
     * Sets the value of the domainUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomainUrl(String value) {
        this.domainUrl = value;
    }

    /**
     * Gets the value of the flushFrequency property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFlushFrequency() {
        return flushFrequency;
    }

    /**
     * Sets the value of the flushFrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFlushFrequency(Integer value) {
        this.flushFrequency = value;
    }

    /**
     * Gets the value of the thresholdFrequency property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getThresholdFrequency() {
        return thresholdFrequency;
    }

    /**
     * Sets the value of the thresholdFrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setThresholdFrequency(Integer value) {
        this.thresholdFrequency = value;
    }

    /**
     * Gets the value of the robotRulesExists property.
     * 
     */
    public boolean isRobotRulesExists() {
        return robotRulesExists;
    }

    /**
     * Sets the value of the robotRulesExists property.
     * 
     */
    public void setRobotRulesExists(boolean value) {
        this.robotRulesExists = value;
    }

}
