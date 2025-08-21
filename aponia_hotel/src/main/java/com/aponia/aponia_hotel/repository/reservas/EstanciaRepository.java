package com.aponia.aponia_hotel.repository.reservas;

import com.aponia.aponia_hotel.entities.reservas.Estancia;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EstanciaRepository {
    private final JdbcTemplate jdbc;

    public EstanciaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Estancia> findAll() {
        return jdbc.query("SELECT * FROM estancias", new BeanPropertyRowMapper<>(Estancia.class));
    }

    public Optional<Estancia> findById(String id) {
        return jdbc.query("SELECT * FROM estancias WHERE id = ?", 
                new BeanPropertyRowMapper<>(Estancia.class), id)
                .stream().findFirst();
    }

    public int save(Estancia estancia) {
        return jdbc.update(
            "INSERT INTO estancias (id, reserva_id, tipo_habitacion_id, check_in, check_out, numero_huespedes, precio_por_noche, total_estadia, habitacion_asignada) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
            estancia.getId(), estancia.getReserva().getId(), estancia.getTipoHabitacion().getId(),
            estancia.getCheckIn(), estancia.getCheckOut(), estancia.getNumeroHuespedes(),
            estancia.getPrecioPorNoche(), estancia.getTotalEstadia(), 
            estancia.getHabitacionAsignada() != null ? estancia.getHabitacionAsignada().getId() : null
        );
    }

    public int update(Estancia estancia) {
        return jdbc.update(
            "UPDATE estancias SET reserva_id = ?, tipo_habitacion_id = ?, check_in = ?, check_out = ?, numero_huespedes = ?, precio_por_noche = ?, total_estadia = ?, habitacion_asignada = ? WHERE id = ?",
            estancia.getReserva().getId(), estancia.getTipoHabitacion().getId(),
            estancia.getCheckIn(), estancia.getCheckOut(), estancia.getNumeroHuespedes(),
            estancia.getPrecioPorNoche(), estancia.getTotalEstadia(), 
            estancia.getHabitacionAsignada() != null ? estancia.getHabitacionAsignada().getId() : null,
            estancia.getId()
        );
    }

    public int deleteById(String id) {
        return jdbc.update("DELETE FROM estancias WHERE id = ?", id);
    }

    public List<Estancia> findByReservaId(String reservaId) {
        return jdbc.query("SELECT * FROM estancias WHERE reserva_id = ?", 
                new BeanPropertyRowMapper<>(Estancia.class), reservaId);
    }

    public int updateHabitacionAsignada(String id, String habitacionId) {
        return jdbc.update("UPDATE estancias SET habitacion_asignada = ? WHERE id = ?", habitacionId, id);
    }
}