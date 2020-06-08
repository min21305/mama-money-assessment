package co.za.mm.enums;

public enum Menus {
    MENU_1("Menu1"),
    MENU_2("Menu2"),
    MENU_3("Menu3"),
    MENU_4("Menu4"),
    FAILED("Failed");

    private String value;

    Menus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
