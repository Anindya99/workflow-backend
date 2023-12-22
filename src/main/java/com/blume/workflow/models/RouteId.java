package com.blume.workflow.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class RouteId implements Serializable {
    private String origin;
    private String destination;
}
