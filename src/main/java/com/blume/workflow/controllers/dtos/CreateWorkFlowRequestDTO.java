package com.blume.workflow.controllers.dtos;

import com.blume.workflow.enums.LoadTypeEnum;
import com.blume.workflow.enums.WorkFlowConfigurationOptionsEnum;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateWorkFlowRequestDTO {
    private String name;
    private LoadTypeEnum loadType;
    private int budget;
    private List<WorkFlowConfigurationOptionsEnum> configurations;
}
