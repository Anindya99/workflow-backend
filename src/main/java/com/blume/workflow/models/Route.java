package com.blume.workflow.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Route {
    @EmbeddedId
    private RouteId id;

    private int distance;

    @ManyToMany(mappedBy = "routes")
    private Set<Carrier> carriers;

    @OneToMany(mappedBy = "route")
    @JsonManagedReference(value="route-workOrder")
    private List<WorkOrder> workOrder;
}
