package Laboratory_Work_1_SOLID_Principles.Enums;

public enum AccountStatusEnum {
    ACTIVE("active"),
    INACTIVE("inactive"),
    FROZEN("frozen");

    private final String status;

    AccountStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
