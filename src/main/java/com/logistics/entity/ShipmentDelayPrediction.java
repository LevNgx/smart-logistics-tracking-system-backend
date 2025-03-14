package com.logistics.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shipment_delay_predictions")
public class ShipmentDelayPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "shipment_id", nullable = false)
    private Long shipmentId;

    @Column(name = "distance_km", nullable = false)
    private Double distanceKm;

    @Column(name = "traffic_level", nullable = false)
    private String trafficLevel;

    @Column(name = "weather_conditions", nullable = false)
    private String weatherConditions;

    @Column(name = "hour_of_day", nullable = false)
    private Integer hourOfDay;

    @Column(name = "weekend", nullable = false)
    private Boolean weekend;

    @Column(name = "shipment_type", nullable = false)
    private String shipmentType;

    @Column(name = "prediction_result", nullable = false)
    private Boolean predictionResult;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
