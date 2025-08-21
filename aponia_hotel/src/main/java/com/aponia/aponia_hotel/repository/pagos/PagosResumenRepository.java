package com.aponia.aponia_hotel.repository.pagos;

import com.aponia.aponia_hotel.entities.pagos.ResumenPago;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ResumenPagoRepository {
    private final JdbcTemplate jdbc;

    public ResumenPagoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<ResumenPago> findAll() {
        return jdbc.query("SELECT * FROM resumen_pagos", new BeanPropertyRowMapper<>(ResumenPago.class));
    }

    public Optional<ResumenPago> findById(String reservaId) {
        return jdbc.query("SELECT * FROM resumen_pagos WHERE reserva_id = ?", 
                new BeanPropertyRowMapper<>(ResumenPago.class), reservaId)
                .stream().findFirst();
    }

    public int save(ResumenPago resumenPago) {
        return jdbc.update(
            "INSERT INTO resumen_pagos (reserva_id, total_habitaciones, total_servicios, total_reserva, total_pagado, saldo_pendiente, ultima_actualizacion) VALUES (?, ?, ?, ?, ?, ?, ?)",
            resumenPago.getReservaId(), resumenPago.getTotalHabitaciones(),
            resumenPago.getTotalServicios(), resumenPago.getTotalReserva(),
            resumenPago.getTotalPagado(), resumenPago.getSaldoPendiente(),
            resumenPago.getUltimaActualizacion()
        );
    }

    public int update(ResumenPago resumenPago) {
        return jdbc.update(
            "UPDATE resumen_pagos SET total_habitaciones = ?, total_servicios = ?, total_reserva = ?, total_pagado = ?, saldo_pendiente = ?, ultima_actualizacion = ? WHERE reserva_id = ?",
            resumenPago.getTotalHabitaciones(), resumenPago.getTotalServicios(),
            resumenPago.getTotalReserva(), resumenPago.getTotalPagado(),
            resumenPago.getSaldoPendiente(), resumenPago.getUltimaActualizacion(),
            resumenPago.getReservaId()
        );
    }

    public int deleteById(String reservaId) {
        return jdbc.update("DELETE FROM resumen_pagos WHERE reserva_id = ?", reservaId);
    }

    public int updateResumen(String reservaId, BigDecimal totalHabitaciones, BigDecimal totalServicios, 
                           BigDecimal totalPagado, BigDecimal saldoPendiente) {
        return jdbc.update(
            "UPDATE resumen_pagos SET total_habitaciones = ?, total_servicios = ?, total_reserva = ?, total_pagado = ?, saldo_pendiente = ?, ultima_actualizacion = CURRENT_TIMESTAMP WHERE reserva_id = ?",
            totalHabitaciones, totalServicios, 
            totalHabitaciones.add(totalServicios), totalPagado, 
            saldoPendiente, reservaId
        );
    }
}