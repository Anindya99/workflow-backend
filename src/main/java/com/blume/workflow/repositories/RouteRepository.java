package com.blume.workflow.repositories;

import com.blume.workflow.models.Route;
import com.blume.workflow.models.RouteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, RouteId> {
}
