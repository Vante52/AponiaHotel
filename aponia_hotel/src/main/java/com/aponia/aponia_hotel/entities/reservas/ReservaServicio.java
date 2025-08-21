package com.aponia.aponia_hotel.entities.reservas;

import com.aponia.aponia_hotel.entities.servicios.Servicio;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservas_servicios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaServicio {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id", nullable = false)
    private Reserva reserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_id", nullable = false)
    private Servicio servicio;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "numero_personas", nullable = false)
    private Integer numeroPersonas;

    @Column(name = "precio_por_persona", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioPorPersona;

    @Column(name = "total_servicio", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalServicio;
}