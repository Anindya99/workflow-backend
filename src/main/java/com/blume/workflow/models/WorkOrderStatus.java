package com.blume.workflow.models;

import com.blume.workflow.enums.WorkOrderStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WorkOrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private WorkOrderStatusEnum status= WorkOrderStatusEnum.ASSIGNED;

    @Builder.Default
    private int workFlowCurrentStep=0;

    @Builder.Default
    private int assignedCarrierId=0;

    @OneToOne
    @JoinColumn(name = "work_order_id",referencedColumnName = "id")
    private WorkOrder workOrder;


}
