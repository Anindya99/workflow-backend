package com.blume.workflow.services;

import com.blume.workflow.models.Route;
import com.blume.workflow.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    @Autowired
    private RouteRepository routeRepository;

    public Route saveRoute(Route route){
        return this.routeRepository.save(route);
    }
    public List<Route> saveAllRoutes(List<Route> routes){return this.routeRepository.saveAll(routes);}
}
