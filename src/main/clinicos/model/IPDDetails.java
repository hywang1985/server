package app.ws.clinicos.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Arjun Golabhanvi
 */
@XmlRootElement(name = "ipdDetails")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name = "ipd_details")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class IPDDetails extends RecordKeeper {

    @Id
    @Column(name = "MRN")
    private long mrn;

    @Column(name = "ROOM_NUMBER")
    private String roomNumber;

    @Column(name = "ADMIT_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date admitDate;

    @Column(name = "DISCHARGE_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dischargeDate;

    @Column(name = "REFERRED_BY")
    private int referredBy;

    @Column(name = "ADMIT_REASON")
    private String admitReason;

    @XmlTransient
    public long getMrn() {
        return mrn;
    }

    public void setMrn(long mrn) {
        this.mrn = mrn;
    }

    @XmlElement
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @XmlElement
    public Date getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(Date admitDate) {
        this.admitDate = admitDate;
    }

    @XmlElement
    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    @XmlElement
    public int getReferredBy() {
        return referredBy;
    }

    public void setReferredBy(int referredBy) {
        this.referredBy = referredBy;
    }

    @XmlElement
    public String getAdmitReason() {
        return admitReason;
    }

    public void setAdmitReason(String admitReason) {
        this.admitReason = admitReason;
    }
}
