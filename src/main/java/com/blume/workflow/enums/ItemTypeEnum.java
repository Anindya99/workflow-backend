package com.blume.workflow.enums;

import lombok.Getter;

@Getter
public enum ItemTypeEnum {
    REGULAR("REGULAR"),
    HAZMAT("HAZMAT"),
    FRAGILE("FRAGILE"),
    PERISHABLE("PERISHABLE"),
    EMPTY("EMPTY");

    private final String itemType;
    ItemTypeEnum(String itemType) {
        this.itemType = itemType;
    }
}
