package app.ws.clinicos.utils;

/**
 *
 * @author Arjun Golabhanvi
 */
public class CustomException extends Exception {
    
    private String type;

    private String errMsg;
    
    public CustomException(Throwable ex, String type) {
        if (ex.getCause() != null && ex.getCause().getMessage() != null) {
            this.errMsg = ex.getCause().getMessage();
        } else if (ex.getMessage() != null) {
            this.errMsg = ex.getMessage();
        } else {
            this.errMsg = ex.getLocalizedMessage();
        }
        this.type = type;
    }
    
    public CustomException (String message, String type){
        this.errMsg = message;
        this.type = type;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
