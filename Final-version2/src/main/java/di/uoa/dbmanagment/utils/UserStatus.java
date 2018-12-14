package di.uoa.dbmanagment.utils;

public enum UserStatus {
    ACTIVE("ACTIVE"), PASSIVE("PASSIVE");
    private String value;

    UserStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
