package app.ws.clinicos.model;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Arjun Golabhanvi
 */
@XmlRootElement(name = "errors")
public class Errors implements Serializable {

    private List<Error> errors;

    /**
     * Gets the error list.
     *
     * @return the list.
     */
    @XmlElement(name = "error", required = true)
    public List<Error> getErrors() {
        return this.errors;
    }

    /**
     * Sets the error list.
     *
     * @param errors the list
     */
    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name = "errorType", propOrder = {})
    public static class Error {

        /**
         * The error message.
         */
        protected String message;

        /**
         * A code that indicates the type of error.
         */
        protected String type;

        /**
         * Gets the error message.
         *
         * @return the message.
         */
        @XmlElement(name = "message", required = true)
        public String getMessage() {
            return message;
        }

        /**
         * Sets the error message.
         *
         * @param message a message
         */
        public void setMessage(String message) {
            this.message = message;
        }

        /**
         * Gets the error code.
         *
         * @return the code.
         */
        @XmlAttribute(name = "type", required = true)
        public String getType() {
            return type;
        }

        /**
         * Sets the error type.
         *
         * @param type an error type
         */
        public void setType(String type) {
            this.type = type;
        }
    }
}
