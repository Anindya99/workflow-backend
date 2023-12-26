package com.blume.workflow.services;

import com.blume.workflow.enums.ItemTypeEnum;
import com.blume.workflow.enums.LoadTypeEnum;
import com.blume.workflow.models.Carrier;
import com.blume.workflow.models.Route;
import com.blume.workflow.models.WorkOrder;
import com.blume.workflow.repositories.CarrierRepository;
import com.blume.workflow.repositories.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarrierService {
    @Autowired
    private CarrierRepository carrierRepository;
    @Autowired
    private WorkOrderRepository workOrderRepository;

    public Carrier saveCarrier(Carrier carrier){
        return this.carrierRepository.save(carrier);
    }

    public void getCarriersForWorkOrder(long workOrderId){
        WorkOrder workOrder= this.workOrderRepository.findById(workOrderId).orElseThrow(()-> new NoSuchElementException("No work order found with id- "+ workOrderId));
        Route route= workOrder.getRoute();
        int budget= workOrder.getBudget();
        int weight= workOrder.getWeight();
        ItemTypeEnum itemType= workOrder.getItemType();
        LoadTypeEnum loadType= workOrder.getLoadType();

        List<Carrier> filteredCarriersByRoute= this.carrierRepository.findCarriersByRoute(route.getId().getOrigin(),route.getId().getDestination());
        System.out.println(filteredCarriersByRoute);
    }
}
