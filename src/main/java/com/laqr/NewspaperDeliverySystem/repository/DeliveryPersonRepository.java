package com.laqr.NewspaperDeliverySystem.repository;

import com.laqr.NewspaperDeliverySystem.model.DeliveryPerson;
import com.laqr.NewspaperDeliverySystem.model.Route;
import com.laqr.NewspaperDeliverySystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Integer> {
    Optional<DeliveryPerson> findTopByUser(User user);
}
