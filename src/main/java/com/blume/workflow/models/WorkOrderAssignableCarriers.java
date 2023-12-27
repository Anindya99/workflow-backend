package com.blume.workflow.models;

import com.blume.workflow.enums.WorkOrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


//for a work order stores all the carriers that are available so each work order has multiple entries
//each for one carrier. WorkOderId + CarrierId will give us the primary key
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WorkOrderAssignableCarriers {
    @Id
    private WorkOrderAssignableCarriersId id;

    private int cost;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private WorkOrderStatusEnum status= WorkOrderStatusEnum.UNASSIGNED;

    @MapsId("workOrderId")
    @ManyToOne
    @JsonBackReference(value = "workOrder-Assignable-Carrier-List")
    private WorkOrder workOrder;

    @MapsId("carrierId")
    @ManyToOne
    private Carrier carrier;

}
