package com.blume.workflow.controllers.dtos;

import com.blume.workflow.enums.WorkOrderStatusEnum;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignableCarriersCostSortedResponseDTO {
    private long carrierId;

    private String name;

    private int cost;

    @Builder.Default
    private WorkOrderStatusEnum status= WorkOrderStatusEnum.UNASSIGNED;
}
