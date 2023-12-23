package com.blume.workflow.enums;

import lombok.Getter;

@Getter
public enum WorkOrderStatusEnum {
    UNASSIGNED("UNASSIGNED"),
    ASSIGNED("ASSIGNED"),
    ACCEPTED("ACCEPTED"),
    REJECTED("REJECTED");

    private final String workOrderStatus;
    WorkOrderStatusEnum(String workOrderStatus){this.workOrderStatus=workOrderStatus;}
}
