package web.app.moldunity.enums;

public enum ConfirmUserStatus {
    USERNAME_EXISTS("USERNAME_ALREADY_EXIST"),
    EMAIL_EXISTS("EMAIL_ALREADY_EXIST"),
    SUCCESS("SUCCESS"),
    ERROR("ERROR");

    ConfirmUserStatus(String status) {
    }
}
