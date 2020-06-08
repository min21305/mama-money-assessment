package co.za.mm.enums;

public enum Countries {
    KENYA("Kenya"),
    MALAWI("Malawi");

    private String value;

    Countries(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
