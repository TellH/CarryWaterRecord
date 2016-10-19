package tellh.com.carrywaterrecord.net;

public class Message {
    private boolean isOk;
    private String message;

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message(boolean isOk) {
        this.isOk = isOk;
    }

    public Message() {
    }
}