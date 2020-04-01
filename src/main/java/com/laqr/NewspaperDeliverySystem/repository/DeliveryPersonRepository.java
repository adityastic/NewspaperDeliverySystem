package com.laqr.NewspaperDeliverySystem.repository;

import com.laqr.NewspaperDeliverySystem.model.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Integer> {
}
