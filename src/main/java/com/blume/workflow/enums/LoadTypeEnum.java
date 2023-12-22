package com.blume.workflow.enums;

import lombok.Getter;

@Getter
public enum LoadTypeEnum {
    FCL("FCL"),
    LCL("LCL");

    private final String loadType;
    LoadTypeEnum(String loadType) {
        this.loadType = loadType;
    }
}
