package com.blume.workflow.models;

import com.blume.workflow.enums.ItemTypeEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String truckNumber;

    private int capacity;

    private int speed;

    @ElementCollection
    @CollectionTable(
            name="TRUCK_INFO",
            joinColumns = @JoinColumn(name="TRUCK_ID", referencedColumnName = "id")
    )
    private List<TruckInfo> info;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "truck_id", referencedColumnName = "id")
    private TruckStatus status;

    @ManyToOne
    @JoinColumn(name="carrier_id",referencedColumnName = "id")
    @JsonBackReference(value = "carrier-truck")
    private Carrier carrier;


    public int getTotalCostForTruck(int distance,int weight, ItemTypeEnum itemType){
        for(TruckInfo info: this.getInfo()){
            if(info.getItemType()==itemType) return (info.getRatePerKmPerKg()*weight*distance + info.getOtherCharges());
        }
        return 0;
    }
    public  boolean checkIfTruckSupportsItemType(ItemTypeEnum itemType){
        for(TruckInfo info: this.getInfo()) if(info.getItemType()==itemType) return true;
        return false;
    }
}
