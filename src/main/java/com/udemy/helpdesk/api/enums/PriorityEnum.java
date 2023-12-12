package com.udemy.helpdesk.api.enums;

public enum PriorityEnum {
    High("High"),
    Normal("Normal"),
    Low("Low");

    private final String value;

    PriorityEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PriorityEnum fromValue(String value) {
        String valueCapitalized =  value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
        for (PriorityEnum priority : PriorityEnum.values()) {
            if (priority.value.equals(valueCapitalized)) {
                return priority;
            }
        }
        return null;
    }
}
