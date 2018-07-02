package apps.dabinu.com.secureafrica.networkutils;

import com.google.gson.annotations.SerializedName;

public class SecureAfricaBase<T> {
    @SerializedName("status")
    private String status;
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SecureAfricaBase{" +
                "status='" + status + '\'' +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
