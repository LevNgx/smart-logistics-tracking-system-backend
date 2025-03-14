package com.logistics.service;

import com.logistics.entity.ShipmentDelayPrediction;
import com.logistics.repository.ShipmentDelayPredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ShipmentDelayPredictionService {

    @Autowired
    private ShipmentDelayPredictionRepository repository;

    public ShipmentDelayPrediction savePrediction(ShipmentDelayPrediction prediction) {
        return repository.save(prediction);
    }

    public List<ShipmentDelayPrediction> getPredictionsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    public List<ShipmentDelayPrediction> getPredictionsByShipment(Long shipmentId) {
        return repository.findByShipmentId(shipmentId);
    }
}
