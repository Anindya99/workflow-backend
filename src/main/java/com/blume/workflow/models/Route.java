package com.blume.workflow.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
}
