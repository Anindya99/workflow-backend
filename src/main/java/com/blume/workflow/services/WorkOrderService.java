package com.blume.workflow.services;

import com.blume.workflow.models.WorkOrder;
import com.blume.workflow.repositories.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderService {
    @Autowired
    private WorkOrderRepository workOrderRepository;

    public WorkOrder saveWorkOrder(WorkOrder workOrder){
        return this.workOrderRepository.save(workOrder);
    }
}
