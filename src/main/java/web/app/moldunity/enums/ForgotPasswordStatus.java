package web.app.moldunity.enums;


public enum ForgotPasswordStatus {
    SUCCESS("SUCCESS"),
    ERROR("ERROR"),
    INVALID_EMAIL("INVALID_EMAIL"),
    INVALID_CODE("INVALID_CODE");

    ForgotPasswordStatus(String status) {
    }
}
