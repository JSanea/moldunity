package web.app.moldunity.enums;

public enum ChangePasswordStatus {
    SUCCESS("SUCCESS"),
    INVALID_CURRENT_PASSWORD("INVALID_OLD_PASSWORD"),
    ERROR("ERROR");
    ChangePasswordStatus(String status){}
}
