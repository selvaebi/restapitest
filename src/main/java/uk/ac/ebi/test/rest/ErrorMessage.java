package uk.ac.ebi.test.rest;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorMessage {

    private long timestamp;

    private int status;

    private String error;

    private String exception;

    private String message;

    public ErrorMessage(HttpStatus status, Exception ex, String message) {
        timestamp = new Date().getTime();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.exception = ex.getClass().getCanonicalName();
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getException() {
        return exception;
    }

    public String getMessage() {
        return message;
    }
}
