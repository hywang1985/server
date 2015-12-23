package app.ws.clinicos.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Arjun Golabhanvi
 */
@MappedSuperclass
public abstract class RecordKeeper extends HasLinks implements Serializable {

    @Column(name = "CREATED_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "MODIFIED_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = "CREATED_BY")
    private int createdBy;

    @Column(name = "MODIFIED_BY")
    private int modifiedBy;

    @XmlElement
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlElement
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @XmlElement
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @XmlElement
    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
