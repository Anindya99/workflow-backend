package com.blume.workflow.controllers.dtos;

import com.blume.workflow.enums.WorkOrderStatusEnum;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignableCarriersResponseDTO {
    private long id;

    private String name;

    @Builder.Default
    private WorkOrderStatusEnum status= WorkOrderStatusEnum.UNASSIGNED;
}
