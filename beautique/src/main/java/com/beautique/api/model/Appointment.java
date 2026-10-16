package com.beautique.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "tb_appointment")
public class Appointment extends BaseEntity {

    @Column(nullable = false, updatable = true)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private Boolean appointmentOpen;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beauty_procedure_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Procedure procedure;
}
