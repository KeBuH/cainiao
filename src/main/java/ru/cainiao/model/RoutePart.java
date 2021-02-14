package ru.cainiao.model;



public enum RoutePart {

    FIRST_MILE("First Mile"),
    TRANSPORT("Transport"),
    LAST_MILE("Last Mile");

    private final String value;

    RoutePart(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
