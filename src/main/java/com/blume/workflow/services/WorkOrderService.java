package com.blume.workflow.services;

import com.blume.workflow.controllers.dtos.AssignableCarriersCostSortedResponseDTO;
import com.blume.workflow.enums.WorkOrderStatusEnum;
import com.blume.workflow.models.*;
import com.blume.workflow.repositories.WorkOrderAssignableCarriersRepository;
import com.blume.workflow.repositories.WorkOrderRepository;
import com.blume.workflow.repositories.WorkOrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkOrderService {
    @Autowired
    private WorkOrderRepository workOrderRepository;
    @Autowired
    private WorkOrderStatusRepository workOrderStatusRepository;

    @Autowired
    private WorkOrderAssignableCarriersRepository workOrderAssignableCarriersRepository;

    @Autowired
    private CarrierService carrierService;

    public WorkOrder saveWorkOrder(WorkOrder workOrder){
        return this.prerequisitesToInitializeWorkFlowConfiguration(workOrder);
    }
    private WorkOrder prerequisitesToInitializeWorkFlowConfiguration(WorkOrder workOrder){
        WorkOrderStatus obj= WorkOrderStatus.builder()
                .assignedCarrierId(0)
                .status(WorkOrderStatusEnum.UNASSIGNED)
                .workFlowCurrentStep(0)
                .workOrder(workOrder)
                .build();
        workOrder.setWorkOrderStatus(obj);
        WorkOrder newWorkOrder= this.workOrderRepository.save(workOrder);
        List<AssignableCarriersCostSortedResponseDTO> assignableCarriers= this.carrierService.getCarrierListBasedOnBudget(newWorkOrder.getId());
        List<WorkOrderAssignableCarriers> workOrderAssignableCarriersList= this.getListForAssignableCarriersList(assignableCarriers, newWorkOrder.getId());
        this.workOrderAssignableCarriersRepository.saveAll(workOrderAssignableCarriersList);
        return newWorkOrder;
    }

    private List<WorkOrderAssignableCarriers> getListForAssignableCarriersList(List<AssignableCarriersCostSortedResponseDTO> assignableCarriers, long workOrderId){
        List<WorkOrderAssignableCarriers> workOrderAssignableCarriersList= new ArrayList<>();

        for(AssignableCarriersCostSortedResponseDTO assignableCarrier: assignableCarriers){
            WorkOrderAssignableCarriers obj= WorkOrderAssignableCarriers.builder()
                    .id(WorkOrderAssignableCarriersId.builder().workOrderId(workOrderId).carrierId(assignableCarrier.getCarrierId()).build())
                    .cost(assignableCarrier.getCost())
                    .status(assignableCarrier.getStatus())
                    .workOrder(WorkOrder.builder().id(workOrderId).build())
                    .carrier(Carrier.builder().id(assignableCarrier.getCarrierId()).build())
                    .build();
            workOrderAssignableCarriersList.add(obj);
        }
        return workOrderAssignableCarriersList;
    }
}
