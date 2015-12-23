package app.ws.clinicos.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arjun Golabhanvi
 */
@XmlRootElement(name = "patients")
@XmlAccessorType(XmlAccessType.NONE)
public class PatientList extends HasLinks {

    private int more;
    private List<Patient> patient;

    @XmlElement(name = "patient")
    public List<Patient> getPatient() {
        return patient;
    }

    public void setPatient(List<Patient> patient) {
        this.patient = patient;
    }

    @XmlElement
    public int getMore() {
        return more;
    }

    public void setMore(int more) {
        this.more = more;
    }
}
