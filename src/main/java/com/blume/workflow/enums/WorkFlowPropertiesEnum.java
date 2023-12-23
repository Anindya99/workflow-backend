package com.blume.workflow.enums;

import lombok.Getter;

@Getter
public enum WorkFlowPropertiesEnum {
    LOAD_TYPE("LOAD_TYPE"),
    BUDGET("BUDGET"),
    CONFIGURATION("CONFIGURATION");

    private final String workFlow;
    WorkFlowPropertiesEnum(String workFlow){
        this.workFlow= workFlow;
    }
}
