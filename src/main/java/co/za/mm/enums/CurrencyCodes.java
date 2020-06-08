package co.za.mm.enums;

public enum CurrencyCodes {
    KES("Kenya"),
    MWK("Malawi");

    private String value;

    CurrencyCodes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
