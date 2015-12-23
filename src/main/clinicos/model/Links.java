package app.ws.clinicos.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Arjun Golabhanvi
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "linksType", propOrder = { "links" })
public class Links {

    /**
     * The list of link relations.
     */
    @XmlElement(name= "link", required = true)
    protected List<Link> links;

    /**
     * Gets the list of link relations.
     * @return the list.
     */
    public List<Link> getLinks() 
    {
        return this.links;
    }

    /**
     * Sets the list of link relations.
     */
    public void setLinks(List<Link> links)
    {
        this.links = links;
    }
    
    /**
     * A representation of the "linkType" type from the XML Schema for ALM.  Represents a link
     * relation.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "linkType")
    public static class Link 
    {
	/**
	 * The link target.
	 */
        @XmlAttribute(name = "href", required = true)
        @XmlSchemaType(name = "anyURI")
        protected String href;
        
        /**
         * The name of the relation.
         */
        @XmlAttribute(name = "rel", required = true)
        protected String rel;
        
        /**
         * The MIME type of the link target. 
         */
        @XmlAttribute(name = "method")
        protected String method;

        /**
         * Gets the link target.
         * @return the target.
         */
        public String getHref() 
        {
            return href;
        }

        /**
         * Sets the link target.
         * @param href the target
         */
        public void setHref(String href) 
        {
            this.href = href;
        }

        /**
         * Gets the name of the link relation.
         * @return the name.
         */
        public String getRel() 
        {
            return rel;
        }

        /**
         * Sets the name of the link relation.
         * @param name the name
         */
        public void setRel(String name) 
        {
            this.rel = name;
        }

        /**
         * Gets the http method of the link target.
         * @return the http method type.
         */
        public String getMethod() 
        {
            return method;
        }

        /**
         * Sets the http method of the link target.
         * @param http method type
         */
        public void setMethod(String method) 
        {
            this.method = method;
        }
    }    
}
