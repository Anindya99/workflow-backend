package com.blume.workflow.services;

import com.blume.workflow.enums.ItemTypeEnum;
import com.blume.workflow.enums.LoadTypeEnum;
import com.blume.workflow.models.Carrier;
import com.blume.workflow.models.Route;
import com.blume.workflow.models.WorkOrder;
import com.blume.workflow.repositories.CarrierRepository;
import com.blume.workflow.repositories.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarrierService {
    @Autowired
    private CarrierRepository carrierRepository;
    @Autowired
    private WorkOrderRepository workOrderRepository;

    public Carrier saveCarrier(Carrier carrier) {
        return this.carrierRepository.save(carrier);
    }

    public void getCarriersForWorkOrder(long workOrderId) {
        WorkOrder workOrder = this.workOrderRepository.findById(workOrderId)
                .orElseThrow(() -> new NoSuchElementException("No work order found with id- " + workOrderId));
        Route route = workOrder.getRoute();
        List<Carrier> filteredCarriersByRoute = this.carrierRepository
                .findCarriersByRoute(route.getId().getOrigin(), route.getId().getDestination());

        ItemTypeEnum itemType = workOrder.getItemType();
        LoadTypeEnum loadType = workOrder.getLoadType();
        int weight = workOrder.getWeight();
        int budget = workOrder.getBudget();

        List<Carrier> filteredCarriersByItemType= this.getFilterCarriersByItemType(filteredCarriersByRoute,itemType);

    }

    private List<Carrier> getFilterCarriersByItemType(List<Carrier> carriers, ItemTypeEnum itemType) {
        List<Carrier> filteredList;
        filteredList = carriers.stream()
                .filter(carrier ->
                        carrier.getTrucks().stream()
                                .flatMap(truck -> truck.getInfo().stream())
                                .anyMatch(info -> info.getItemType() == itemType)
                )
                .toList();

        return filteredList;
    }

    /*private List<Carrier> getFilteredCarriersByLoadType(List<Carrier> carriers, LoadTypeEnum loadType){
        List<Carrier> filteredList;
        filteredList = carriers.stream()
                .filter(carrier ->
                        carrier.getTrucks().stream()
                                .flatMap(truck -> truck.getInfo().stream())
                                .anyMatch(info -> info.getItemType() == itemType)
                )
                .toList();

        return filteredList;
    }*/
}
