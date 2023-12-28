package com.blume.workflow.services;

import com.blume.workflow.controllers.dtos.AssignableCarriersCostSortedResponseDTO;
import com.blume.workflow.enums.ItemTypeEnum;
import com.blume.workflow.enums.LoadTypeEnum;
import com.blume.workflow.enums.WorkOrderStatusEnum;
import com.blume.workflow.models.Carrier;
import com.blume.workflow.models.Route;
import com.blume.workflow.models.Truck;
import com.blume.workflow.models.WorkOrder;
import com.blume.workflow.repositories.CarrierRepository;
import com.blume.workflow.repositories.RouteRepository;
import com.blume.workflow.repositories.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarrierService {
    @Autowired
    private CarrierRepository carrierRepository;
    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private RouteRepository routeRepository;

    public Carrier saveCarrier(Carrier carrier) {
        return this.carrierRepository.save(carrier);
    }

    public List<Carrier> getCarriersForWorkOrder(long workOrderId) {
        WorkOrder workOrder = this.workOrderRepository.findById(workOrderId)
                .orElseThrow(() -> new NoSuchElementException("No work order found with id- " + workOrderId));
        Route workOrderRoute = workOrder.getRoute();
        List<Carrier> filteredCarriersByRoute = this.carrierRepository
                .findCarriersByRoute(workOrderRoute.getId().getOrigin(), workOrderRoute.getId().getDestination());

        ItemTypeEnum itemType = workOrder.getItemType();
        LoadTypeEnum loadType = workOrder.getLoadType();
        int grossWeight = workOrder.getWeight();

        List<Carrier> filteredCarriersByItemType= this.getFilterCarriersByItemType(filteredCarriersByRoute,itemType);
        List<Carrier> filteredCarriersByLoadType=
                this.getFilteredCarriersByLoadType(filteredCarriersByItemType,loadType,workOrderRoute);
        List<Carrier> filteredCarriersByGrossWeight=
                this.getFilteredCarriersByGrossWeight(
                        filteredCarriersByLoadType,
                        grossWeight,
                        itemType,
                        loadType,
                        workOrderRoute);
        return filteredCarriersByGrossWeight;
    }

    public List<AssignableCarriersCostSortedResponseDTO> getCarrierListBasedOnBudget(long workOrderId){
        WorkOrder workOrder = this.workOrderRepository.findById(workOrderId)
                .orElseThrow(() -> new NoSuchElementException("No work order found with id- " + workOrderId));
        Route workOrderRoute = routeRepository.findById(workOrder.getRoute().getId())
                .orElseThrow(() -> new NoSuchElementException("No route found for work order id- " + workOrderId));;
        ItemTypeEnum itemType = workOrder.getItemType();
        LoadTypeEnum loadType = workOrder.getLoadType();
        int grossWeight = workOrder.getWeight();
        int budget = workOrder.getBudget();

        List<Carrier> filteredCarriersByGrossWeight= this.getCarriersForWorkOrder(workOrderId);
        List<AssignableCarriersCostSortedResponseDTO> filteredCarriersByBudget= this.getFilteredCarriersByBudget(
                filteredCarriersByGrossWeight,
                budget,
                grossWeight,
                itemType,
                loadType,
                workOrderRoute
        );
        return filteredCarriersByBudget;
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

    private List<Carrier> getFilteredCarriersByLoadType(List<Carrier> carriers, LoadTypeEnum loadType, Route workOrderRoute){
        List<Carrier> filteredList;
        filteredList = carriers.stream()
                .filter(carrier ->
                        carrier.getTrucks().stream()
                                .anyMatch(truck-> {
                                    if(loadType==LoadTypeEnum.FCL) return truck.getStatus().getTotalCurrentLoad()==0;
                                    else{
                                        if(truck.getStatus().getTotalCurrentLoad()==0) return true;
                                        return truck.getStatus().getTotalCurrentLoad()< truck.getCapacity()
                                                && truck.getStatus().getTruckRoute().getId().getOrigin()
                                                .equals( workOrderRoute.getId().getOrigin())
                                                && truck.getStatus().getTruckRoute().getId().getDestination()
                                                .equals(workOrderRoute.getId().getDestination());
                                    }
                                })
                )
                .toList();

        return filteredList;
    }

    private List<Carrier> getFilteredCarriersByGrossWeight(List<Carrier> carriers,
                                                           int grossWeight,
                                                           ItemTypeEnum itemType,
                                                           LoadTypeEnum loadType,
                                                           Route workOrderRoute){
        List<Carrier> filteredList= new ArrayList<>();
        for(Carrier carrier: carriers){
            int availableWeightInAllTrucks= 0;
            for(Truck truck: carrier.getTrucks()){
                if(loadType==LoadTypeEnum.FCL){
                    if(truck.getStatus().getTotalCurrentLoad()==0) availableWeightInAllTrucks+= truck.getCapacity();
                }
                else{
                    if(truck.getStatus().getItemType().equals(itemType)
                    && truck.getStatus().getTruckRoute().getId().getOrigin()
                            .equals( workOrderRoute.getId().getOrigin())
                    && truck.getStatus().getTruckRoute().getId().getDestination()
                            .equals(workOrderRoute.getId().getDestination()))
                        availableWeightInAllTrucks+= truck.getCapacity();
                }
                if(availableWeightInAllTrucks>=grossWeight){
                    filteredList.add(carrier);
                    break;
                }
            }
        }
        return filteredList;
    }

    private List<AssignableCarriersCostSortedResponseDTO> getFilteredCarriersByBudget(List<Carrier> carriers,
                                                      int budget,
                                                      int grossWeight,
                                                      ItemTypeEnum itemType,
                                                      LoadTypeEnum loadType,
                                                      Route workOrderRoute){
        List<AssignableCarriersCostSortedResponseDTO> filteredList= new ArrayList<>();

        for(Carrier carrier:carriers){
            List<Truck> sortedTrucksBasedOnCost= carrier.getTrucks().stream()
                    .sorted(Comparator.comparingInt(truck ->
                            truck.getInfo().stream()
                                    .mapToInt(info -> info.getRatePerKmPerKg() + info.getOtherCharges())
                                    .sum()
                    ))
                    .toList();
            int weightLeftToConsolidate= grossWeight;
            int currentCost=0;
            for(Truck truck: sortedTrucksBasedOnCost){
                if(!truck.checkIfTruckSupportsItemType(itemType)) continue;
                if(loadType==LoadTypeEnum.FCL){
                    if(truck.getStatus().getTotalCurrentLoad()!=0) continue;
                    currentCost+= truck.getTotalCostForTruck(
                            workOrderRoute.getDistance(),
                            truck.getCapacity(),
                            itemType
                    );
                }
                else{
                    currentCost+= truck.getTotalCostForTruck(
                            workOrderRoute.getDistance(),
                            Math.min(truck.getCapacity(),weightLeftToConsolidate),
                            itemType
                    );
                }
                weightLeftToConsolidate-= Math.min(truck.getCapacity(),weightLeftToConsolidate);
                if(currentCost>budget || weightLeftToConsolidate==0) break;
            }
            if(currentCost<=budget && weightLeftToConsolidate==0) filteredList.add(convertToAssignableCarriersCostSortedDto(carrier,currentCost));
        }
        filteredList.sort(Comparator.comparingInt(AssignableCarriersCostSortedResponseDTO::getCost));
        return filteredList;
    }

    private AssignableCarriersCostSortedResponseDTO convertToAssignableCarriersCostSortedDto(Carrier carrier,int cost){
        return AssignableCarriersCostSortedResponseDTO.builder()
                .carrierId(carrier.getId())
                .name(carrier.getName())
                .cost(cost)
                .status(WorkOrderStatusEnum.UNASSIGNED)
                .build();
    }
}

