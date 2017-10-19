package org.ajexp;

/**
 * Created by SSh on 19.10.2017.
 */
public class AjxException extends Exception {
    public AjxException() {
    }

    public AjxException(String message) {
        super(message);
    }

    public AjxException(String message, Throwable cause) {
        super(message, cause);
    }

    public AjxException(Throwable cause) {
        super(cause);
    }

    public AjxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
