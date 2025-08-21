package com.aponia.aponia_hotel.repository.servicios;

import com.aponia.aponia_hotel.entities.servicios.ServicioDisponibilidad;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ServicioDisponibilidadRepository {
    private final JdbcTemplate jdbc;

    public ServicioDisponibilidadRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<ServicioDisponibilidad> findAll() {
        return jdbc.query("SELECT * FROM servicio_disponibilidad", new BeanPropertyRowMapper<>(ServicioDisponibilidad.class));
    }

    public Optional<ServicioDisponibilidad> findById(String id) {
        return jdbc.query("SELECT * FROM servicio_disponibilidad WHERE id = ?", 
                new BeanPropertyRowMapper<>(ServicioDisponibilidad.class), id)
                .stream().findFirst();
    }

    public int save(ServicioDisponibilidad disponibilidad) {
        return jdbc.update(
            "INSERT INTO servicio_disponibilidad (id, servicio_id, fecha, hora_inicio, hora_fin, capacidad_disponible) VALUES (?, ?, ?, ?, ?, ?)",
            disponibilidad.getId(), disponibilidad.getServicio().getId(),
            disponibilidad.getFecha(), disponibilidad.getHoraInicio(),
            disponibilidad.getHoraFin(), disponibilidad.getCapacidadDisponible()
        );
    }

    public int update(ServicioDisponibilidad disponibilidad) {
        return jdbc.update(
            "UPDATE servicio_disponibilidad SET servicio_id = ?, fecha = ?, hora_inicio = ?, hora_fin = ?, capacidad_disponible = ? WHERE id = ?",
            disponibilidad.getServicio().getId(), disponibilidad.getFecha(),
            disponibilidad.getHoraInicio(), disponibilidad.getHoraFin(),
            disponibilidad.getCapacidadDisponible(), disponibilidad.getId()
        );
    }

    public int deleteById(String id) {
        return jdbc.update("DELETE FROM servicio_disponibilidad WHERE id = ?", id);
    }

    public List<ServicioDisponibilidad> findByServicioId(String servicioId) {
        return jdbc.query("SELECT * FROM servicio_disponibilidad WHERE servicio_id = ?", 
                new BeanPropertyRowMapper<>(ServicioDisponibilidad.class), servicioId);
    }

    public List<ServicioDisponibilidad> findByServicioIdAndFecha(String servicioId, String fecha) {
        return jdbc.query("SELECT * FROM servicio_disponibilidad WHERE servicio_id = ? AND fecha = ?", 
                new BeanPropertyRowMapper<>(ServicioDisponibilidad.class), servicioId, fecha);
    }
}