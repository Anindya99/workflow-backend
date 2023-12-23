package com.blume.workflow.models;

import com.blume.workflow.enums.LoadTypeEnum;
import com.blume.workflow.enums.WorkFlowConfigurationOptionsEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WorkFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private LoadTypeEnum loadType;

    private int budget;

    @ElementCollection(targetClass = WorkFlowConfigurationOptionsEnum.class)
    @CollectionTable(name = "workflow_configurations", joinColumns = @JoinColumn(name = "workflow_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "configuration")
    private List<WorkFlowConfigurationOptionsEnum> configurations;

    @OneToMany(mappedBy = "workFlow")
    @JsonManagedReference(value="workFlow-workOrder")
    private List<WorkOrder> workOrder;
}
