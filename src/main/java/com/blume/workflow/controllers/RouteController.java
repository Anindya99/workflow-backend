package com.blume.workflow.controllers;

import com.blume.workflow.models.Route;
import com.blume.workflow.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {
    @Autowired
    private RouteService routeService;
    @PostMapping("/save")
    public ResponseEntity<Route> saveRoute(@RequestBody Route route){
        Route newRoute= this.routeService.saveRoute(route);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRoute);
    }
    @PostMapping("/save/all")
    public ResponseEntity<List<Route>> saveAllRoutes(@RequestBody List<Route> routes){
        List<Route> newRoutes= this.routeService.saveAllRoutes(routes);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRoutes);
    }
}
