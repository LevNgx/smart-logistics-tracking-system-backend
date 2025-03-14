package com.logistics.controller;

import com.logistics.entity.ShipmentDelayPrediction;
import com.logistics.service.ShipmentDelayPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class ShipmentDelayPredictionController {

    @Autowired
    private ShipmentDelayPredictionService service;

    @PostMapping
    public ShipmentDelayPrediction savePrediction(@RequestBody ShipmentDelayPrediction prediction) {
        return service.savePrediction(prediction);
    }

    @GetMapping("/user/{userId}")
    public List<ShipmentDelayPrediction> getPredictionsByUser(@PathVariable Long userId) {
        return service.getPredictionsByUser(userId);
    }

    @GetMapping("/shipment/{shipmentId}")
    public List<ShipmentDelayPrediction> getPredictionsByShipment(@PathVariable Long shipmentId) {
        return service.getPredictionsByShipment(shipmentId);
    }
}
