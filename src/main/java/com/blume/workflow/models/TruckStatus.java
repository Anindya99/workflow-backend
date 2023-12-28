package com.blume.workflow.models;

import com.blume.workflow.enums.ItemTypeEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


//stores information about current status of a truck
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TruckStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Builder.Default
    private int totalCurrentLoad=0;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ItemTypeEnum itemType= ItemTypeEnum.EMPTY;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "truck_route",
            joinColumns = @JoinColumn(name = "truck_status_id",referencedColumnName = "id"),
            inverseJoinColumns ={
                    @JoinColumn(name = "route_origin", referencedColumnName = "origin"),
                    @JoinColumn(name = "route_destination", referencedColumnName = "destination")}
    )
    @JsonBackReference(value="current-truck-route")
    //check the route when truck is not empty i.e. for LCL check if the truck is in same route as work order
    private Route truckRoute;

    @OneToOne(mappedBy = "status")
    private Truck truck;
}
