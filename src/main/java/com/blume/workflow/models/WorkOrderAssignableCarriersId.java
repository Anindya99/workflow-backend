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

    private long workOrderId;


    private long carrierId;
}
