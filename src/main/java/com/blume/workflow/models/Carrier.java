package com.blume.workflow.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Carrier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "carrier_routes",
            joinColumns = @JoinColumn(name = "carrier_id", referencedColumnName = "id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "route_origin", referencedColumnName = "origin"),
                    @JoinColumn(name = "route_destination", referencedColumnName = "destination")}
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Route> routes;

    @OneToMany(mappedBy = "carrier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "carrier-truck")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Truck> trucks;

    /*@OneToMany(mappedBy = "carrier")
    @JsonManagedReference(value = "workOrder-Available-Carrier")
    private List<WorkOrderAvailableCarriers> workOrderAvailableCarriersList;*/
}
