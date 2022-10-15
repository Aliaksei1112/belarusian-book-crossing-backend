package by.hackaton.bookcrossing.service.exceptions;

public enum ServerError {
    EMAIL_ALREADY_EXISTS(1002, "User with email already exists"),
    INTERNAL_SERVER_ERROR(1003, "Internal server error");

    private int statusCode;
    private String message;

    ServerError(int statusCode, String msg) {
        this.message = msg;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
