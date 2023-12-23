package com.blume.workflow.models;

import com.blume.workflow.enums.ItemTypeEnum;
import com.blume.workflow.enums.LoadTypeEnum;
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
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private ItemTypeEnum itemType;

    private int weight;

    @Enumerated(EnumType.STRING)
    private LoadTypeEnum loadType;

    private int budget;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "work_order_route",
            joinColumns = @JoinColumn(name = "work_order_id",referencedColumnName = "id"),
            inverseJoinColumns ={
                    @JoinColumn(name = "route_origin", referencedColumnName = "origin"),
                    @JoinColumn(name = "route_destination", referencedColumnName = "destination")}
    )
    @JsonBackReference(value="route-workOrder")
    private Route route;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "workflow_id", referencedColumnName = "id",insertable = true,nullable = false,updatable = false)
    @JsonBackReference(value="workFlow-workOrder")
    private WorkFlow workFlow;

    @OneToOne(mappedBy = "workOrder")
    private WorkOrderStatus workOrderStatus;

    @OneToMany(mappedBy = "workOrder")
    @JsonManagedReference(value = "workOrder-Available-Carrier-List")
    private List<WorkOrderAvailableCarriers> workOrderAvailableCarriersList;
}
