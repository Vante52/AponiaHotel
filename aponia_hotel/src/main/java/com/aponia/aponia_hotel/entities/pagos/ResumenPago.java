package com.aponia.aponia_hotel.entities.pagos;

import com.aponia.aponia_hotel.entities.reservas.Reserva;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "resumen_pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResumenPago {

    @Id
    @Column(name = "reserva_id", length = 36)
    private String reservaId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "reserva_id")
    private Reserva reserva;

    @Column(name = "total_habitaciones", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalHabitaciones;

    @Column(name = "total_servicios", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalServicios;

    @Column(name = "total_reserva", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalReserva;

    @Column(name = "total_pagado", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPagado;

    @Column(name = "saldo_pendiente", nullable = false, precision = 12, scale = 2)
    private BigDecimal saldoPendiente;

    @Column(name = "ultima_actualizacion", nullable = false)
    private LocalDateTime ultimaActualizacion;
}