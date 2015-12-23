package app.ws.clinicos.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KIRANN
 */

@Entity
@Table(name = "appointments")
@XmlRootElement(name = "appointment")
@XmlAccessorType(XmlAccessType.NONE)
public class Appointment extends RecordKeeper {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;
    
    @Column(name = "MOBILE")
    private String mobile;
    
    @Column(name = "APT_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    @Column(name = "APT_TIME")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date time;

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement   
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @XmlElement
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlElement
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }       
}
