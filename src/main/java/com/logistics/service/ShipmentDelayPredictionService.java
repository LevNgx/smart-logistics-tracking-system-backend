package com.logistics.service;

import com.logistics.entity.ShipmentDelayPrediction;
import com.logistics.repository.ShipmentDelayPredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.http.*;
import java.util.Map;
import org.springframework.web.client.RestTemplate;

@Service
public class ShipmentDelayPredictionService {
//    private final String FASTAPI_URL = "http://3.110.136.70:8000/predict";

    @Value("${ml.api.base-url}")
    private String FASTAPI_URL;

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

    public Map<String, Object> predictDelay(Map<String, Object> input) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(input, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(FASTAPI_URL, request, Map.class);

        return response.getBody();
    }
}
