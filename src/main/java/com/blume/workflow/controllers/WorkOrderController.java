package com.blume.workflow.controllers;

import com.blume.workflow.models.WorkOrder;
import com.blume.workflow.services.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/work-order")
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;

    @PostMapping("/save")
    public ResponseEntity<WorkOrder> saveWorkOrder(@RequestBody WorkOrder workOrder){
        WorkOrder newWorkOrder= this.workOrderService.saveWorkOrder(workOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(newWorkOrder);
    }

    @GetMapping("/{workOrderId}/status")
    public void saveCarriersForWorkOrder(@PathVariable("workOrderId") long workOrderId ){

    }
}
