package com.blume.workflow.models;

import com.blume.workflow.enums.WorkOrderStatusEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class WorkOrderAvailableCarriers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private WorkOrderStatusEnum status= WorkOrderStatusEnum.UNASSIGNED;

    @ManyToOne
    @JoinColumn(name = "work_order_id",referencedColumnName = "id")
    @JsonBackReference(value = "workOrder-Available-Carrier-List")
    private WorkOrder workOrder;

    @ManyToOne
    @JoinColumn(name= "carrier_id", referencedColumnName = "id")
    @JsonBackReference(value="workOrder-Available-Carrier")
    private Carrier carrier;

}
