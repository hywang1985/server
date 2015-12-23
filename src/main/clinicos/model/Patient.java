package app.ws.clinicos.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;

/**
 * A patient model class which maps PATIENTS table in database
 * and serves as XML base for patient representation.
 * @author Arjun Golabhanvi
 * @since Jan 06, 2014
 * @version 1.0
 */
@XmlRootElement(name = "patient")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Indexed
@Table(name = "patients")
public class Patient extends RecordKeeper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MRN")
    @Field(index = Index.YES)
    private long mrn;
    
    @Column(name = "FIRST_NAME")    
    @Field(index = Index.YES)
    private String firstName;
    
    @Column(name = "LAST_NAME")
    @Field(index = Index.YES)
    private String lastName;
    
    @Column(name = "MOBILE")
    @Field(index = Index.YES)
    private String mobile;
    
    @Column(name = "ADDRESS")
    private String address;
    
    @Column(name = "AGE")
    private byte age;
    
    @Column(name = "SEX")
    private char sex;
    
    @Column(name = "CATEGORY")
    @Enumerated(EnumType.STRING)
    private Category category;
    
    @Column(name = "VISIT_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date visitDate;
    
    @Column(name = "CONSULTED_BY")
    private String consultedBy;
    
    @Transient
    private IPDDetails ipdDetails;
    
    @XmlElement(name = "mrn", required = true)
    public long getMrn() {
        return mrn;
    }

    public void setMrn(long mrn) {
        this.mrn = mrn;
    }

    @XmlElement(name = "firstName", required = true)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "lastName", required = true)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @XmlElement(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlElement(name = "age")
    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    @XmlElement(name = "sex")
    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    @XmlElement(name = "category")    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @XmlElement(name = "visitDate")
    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
    
    @XmlElement(name = "consultedBy")
    public String getConsultedBy() {
        return consultedBy;
    }

    public void setConsultedBy(String consultedBy) {
        this.consultedBy = consultedBy;
    }
    
    @XmlElement
    public IPDDetails getIpdDetails() {
        return ipdDetails;
    }

    public void setIpdDetails(IPDDetails ipdDetails) {
        this.ipdDetails = ipdDetails;
    }
    
    @XmlEnum(String.class)
    public enum Category { OPD, IPD };    
}
