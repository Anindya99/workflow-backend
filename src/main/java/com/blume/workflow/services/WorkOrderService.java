package com.blume.workflow.services;

import com.blume.workflow.enums.WorkOrderStatusEnum;
import com.blume.workflow.models.WorkOrder;
import com.blume.workflow.models.WorkOrderStatus;
import com.blume.workflow.repositories.WorkOrderRepository;
import com.blume.workflow.repositories.WorkOrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderService {
    @Autowired
    private WorkOrderRepository workOrderRepository;
    @Autowired
    private WorkOrderStatusRepository workOrderStatusRepository;

    public WorkOrder saveWorkOrder(WorkOrder workOrder){
        WorkOrder newWorkOrder= this.workOrderRepository.save(workOrder);
        this.initializeWorkFlowConfiguration(newWorkOrder);
        return newWorkOrder;
    }
    private void initializeWorkFlowConfiguration(WorkOrder newWorkOrder){
        WorkOrderStatus obj= WorkOrderStatus.builder()
                .assignedCarrierId(0)
                .status(WorkOrderStatusEnum.UNASSIGNED)
                .workFlowCurrentStep(0)
                .workOrder(newWorkOrder)
                .build();
        this.workOrderStatusRepository.save(obj);
    }
}
