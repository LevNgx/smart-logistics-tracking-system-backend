package com.logistics.repository;

import com.logistics.entity.ShipmentDelayPrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ShipmentDelayPredictionRepository extends JpaRepository<ShipmentDelayPrediction, Long> {

    // Get all predictions for a specific user
    List<ShipmentDelayPrediction> findByUserId(Long userId);

    // Get all predictions for a specific shipment
    List<ShipmentDelayPrediction> findByShipmentId(Long shipmentId);
}
