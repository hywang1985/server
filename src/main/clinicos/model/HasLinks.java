package app.ws.clinicos.model;

import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Arjun Golabhanvi
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class HasLinks {

    /**
     * The links container.
     */
    @Transient
    protected Links links;

    /**
     * Gets the links container.
     *
     * @return the container.
     */
    @XmlElement(name = "links")
    public Links getLinks() {
        return links;
    }

    /**
     * Sets the links container.
     *
     * @param links a links container
     */
    public void setLinks(Links links) {
        this.links = links;
    }
}
