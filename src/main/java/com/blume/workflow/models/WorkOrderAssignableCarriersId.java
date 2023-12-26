package com.blume.workflow.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class WorkOrderAssignableCarriersId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "work_order_id",referencedColumnName = "id")
    @JsonBackReference(value = "workOrder-Assignable-Carrier-List")
    private WorkOrder workOrder;

    @ManyToOne
    @JoinColumn(name= "carrier_id", referencedColumnName = "id")
    private Carrier carrier;
}
