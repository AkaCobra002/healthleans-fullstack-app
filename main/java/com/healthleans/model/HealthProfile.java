package com.healthleans.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "health_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HealthProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private boolean diabetes;
    private String diabetesType;        // type1, type2, gestational, etc.

    private boolean obesity;
    private String obesityClass;        // class1, class2, class3
    private String weightGoal;          // lose, energy, specific, maintain

    private boolean surgery;
    private String surgeryType;         // sleeve, bypass, band, switch, etc.

    private boolean other;
    private String otherDescription;
}