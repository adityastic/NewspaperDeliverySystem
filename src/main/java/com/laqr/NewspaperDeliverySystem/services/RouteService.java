package com.laqr.NewspaperDeliverySystem.services;

import com.laqr.NewspaperDeliverySystem.model.Route;
import com.laqr.NewspaperDeliverySystem.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    @Autowired
    RouteRepository routeRepository;

    public void addRoute(String routeName) {
        Route newRoute = new Route(routeName);
        routeRepository.save(newRoute);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public void deleteRoute(int routeID) {
        routeRepository.deleteById(routeID);
    }

    public Route getRouteById(int routeID) {
        return routeRepository.findById(routeID).get();
    }

    public void editRoute(Integer routeID, String routeName) {
        Route foundRoute = routeRepository.findById(routeID).get();
        foundRoute.setName(routeName);
        routeRepository.save(foundRoute);
    }
}
