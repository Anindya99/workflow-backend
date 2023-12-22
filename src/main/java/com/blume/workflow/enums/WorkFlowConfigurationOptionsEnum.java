package com.blume.workflow.enums;

import lombok.Getter;

@Getter
public enum WorkFlowConfigurationOptionsEnum {
    ALLOCATE_TO_LEAST_COST_CARRIER("ALLOCATE_TO_LEAST_COST_CARRIER"),
    ALLOCATE_TO_ALL("ALLOCATE_TO_ALL");

    private final String workflowConfiguration;

    WorkFlowConfigurationOptionsEnum(String workflowConfiguration){
        this.workflowConfiguration= workflowConfiguration;
    }
}
