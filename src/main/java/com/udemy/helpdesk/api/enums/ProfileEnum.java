package com.udemy.helpdesk.api.enums;

public enum ProfileEnum {
    ROLE_ADMIN("Admin"),
    ROLE_CUSTOMER("Customer"),
    ROLE_TECHNICIAN("Technician");

    private final String value;

    ProfileEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProfileEnum fromValue(String value) {
        String valueCapitalized =  value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
        for (ProfileEnum profile : ProfileEnum.values()) {
            if (profile.value.equals(value)) {
                return profile;
            }
        }
        return null;
    }
}