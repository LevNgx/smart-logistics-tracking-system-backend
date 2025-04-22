package com.logistics.controller;

import com.logistics.DTO.UserRegistration;
import com.logistics.entity.ShipmentDelayPrediction;
import com.logistics.service.ShipmentDelayPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/predict_delay")
    public ResponseEntity<?> predictDelay(@RequestBody Map<String, Object> requestData) {
        return ResponseEntity.ok(service.predictDelay(requestData));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify() {
        System.out.println("came here to authenticate jwt");
        try{
            Map<String, String> response = new HashMap<>();
            response.put("message", "Successfully Verified jwt token");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        catch(Exception e)
        {
            System.out.println("Exception"  + e);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Verification failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

    }
}
