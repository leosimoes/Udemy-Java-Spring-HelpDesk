package com.udemy.helpdesk.api.enums;

public enum StatusEnum {
    New("New"),
    Assigned("Assigned"),
    Resolved("Resolved"),
    Approved("Approved"),
    Disapproved("Disapproved"),
    Closed("Closed");

    private final String value;

    StatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StatusEnum fromValue(String value) {
        String valueCapitalized =  value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
        for (StatusEnum status : StatusEnum.values()) {
            if (status.value.equals(valueCapitalized)) {
                return status;
            }
        }
        return null;
    }
}
