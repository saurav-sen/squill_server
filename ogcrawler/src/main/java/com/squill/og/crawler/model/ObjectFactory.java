//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.04.14 at 11:24:30 AM IST 
//


package com.squill.og.crawler.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.squill.og.crawler.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _WebCrawlers_QNAME = new QName("http://www.squilla.co.in/WebCrawlers", "WebCrawlers");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.squill.og.crawler.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WebCrawlers }
     * 
     */
    public WebCrawlers createWebCrawlers() {
        return new WebCrawlers();
    }

    /**
     * Create an instance of {@link WebCrawler }
     * 
     */
    public WebCrawler createWebCrawler() {
        return new WebCrawler();
    }

    /**
     * Create an instance of {@link ApiReader }
     * 
     */
    public ApiReader createApiReader() {
        return new ApiReader();
    }

    /**
     * Create an instance of {@link WebTracker }
     * 
     */
    public WebTracker createWebTracker() {
        return new WebTracker();
    }

    /**
     * Create an instance of {@link Config }
     * 
     */
    public Config createConfig() {
        return new Config();
    }

    /**
     * Create an instance of {@link ArticleSummarizer }
     * 
     */
    public ArticleSummarizer createArticleSummarizer() {
        return new ArticleSummarizer();
    }

    /**
     * Create an instance of {@link GeoTagResolver }
     * 
     */
    public GeoTagResolver createGeoTagResolver() {
        return new GeoTagResolver();
    }

    /**
     * Create an instance of {@link Properties }
     * 
     */
    public Properties createProperties() {
        return new Properties();
    }

    /**
     * Create an instance of {@link ContentHandler }
     * 
     */
    public ContentHandler createContentHandler() {
        return new ContentHandler();
    }

    /**
     * Create an instance of {@link Scheduler }
     * 
     */
    public Scheduler createScheduler() {
        return new Scheduler();
    }

    /**
     * Create an instance of {@link ApiRequestExecutor }
     * 
     */
    public ApiRequestExecutor createApiRequestExecutor() {
        return new ApiRequestExecutor();
    }

    /**
     * Create an instance of {@link TaxonomyClassifier }
     * 
     */
    public TaxonomyClassifier createTaxonomyClassifier() {
        return new TaxonomyClassifier();
    }

    /**
     * Create an instance of {@link FeedUploader }
     * 
     */
    public FeedUploader createFeedUploader() {
        return new FeedUploader();
    }

    /**
     * Create an instance of {@link LinkFilter }
     * 
     */
    public LinkFilter createLinkFilter() {
        return new LinkFilter();
    }

    /**
     * Create an instance of {@link Property }
     * 
     */
    public Property createProperty() {
        return new Property();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WebCrawlers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.squilla.co.in/WebCrawlers", name = "WebCrawlers")
    public JAXBElement<WebCrawlers> createWebCrawlers(WebCrawlers value) {
        return new JAXBElement<WebCrawlers>(_WebCrawlers_QNAME, WebCrawlers.class, null, value);
    }

}
