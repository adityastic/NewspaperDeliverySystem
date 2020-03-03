package com.laqr.NewspaperDeliverySystem.repository;

import com.laqr.NewspaperDeliverySystem.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Integer> {

    Optional<Route> findTopByName(String routeName);
}
