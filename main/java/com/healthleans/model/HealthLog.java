package com.healthleans.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "health_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HealthLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDate date;

    private String generalNote;

    // Diabetes fields
    private Integer fastingGlucose;
    private Integer postMealGlucose;
    private String insulinNote;

    // Obesity / Weight fields
    private Double weight;
    private Double waist;
    private String energyMood;

    // Post-Surgery fields
    private Integer proteinIntake;
    private Double fluidIntake;
    private String recoverySymptoms;
}