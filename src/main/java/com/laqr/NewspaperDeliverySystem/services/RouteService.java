package com.laqr.NewspaperDeliverySystem.services;

import com.laqr.NewspaperDeliverySystem.model.Route;
import com.laqr.NewspaperDeliverySystem.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

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
        return routeRepository.getOne(routeID);
    }

    public void editRoute(Integer routeID, String routeName) {
        Route foundRoute = routeRepository.getOne(routeID);
        foundRoute.setName(routeName);
        routeRepository.save(foundRoute);
    }

    public boolean checkRouteName(String routeName) {
        return routeRepository.findTopByName(routeName).isPresent();
    }

    public boolean checkNotThisRouteName(Integer routeId, String routeName) {
        Optional<Route> maybeRoute = routeRepository.findTopByName(routeName);
        return maybeRoute.isPresent() && maybeRoute.get().getId() != routeId;
    }
}
