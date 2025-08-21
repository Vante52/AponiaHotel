package com.aponia.aponia_hotel.repository.reservas;

import com.aponia.aponia_hotel.entities.reservas.ReservaServicio;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservaServicioRepository {
    private final JdbcTemplate jdbc;

    public ReservaServicioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<ReservaServicio> findAll() {
        return jdbc.query("SELECT * FROM reservas_servicios", new BeanPropertyRowMapper<>(ReservaServicio.class));
    }

    public Optional<ReservaServicio> findById(String id) {
        return jdbc.query("SELECT * FROM reservas_servicios WHERE id = ?",
                        new BeanPropertyRowMapper<>(ReservaServicio.class), id)
                .stream().findFirst();
    }

    public int save(ReservaServicio reservaServicio) {
        return jdbc.update(
                "INSERT INTO reservas_servicios (id, reserva_id, servicio_id, fecha, hora_inicio, numero_personas, precio_por_persona, total_servicio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                reservaServicio.getId(), reservaServicio.getReserva().getId(),
                reservaServicio.getServicio().getId(), reservaServicio.getFecha(),
                reservaServicio.getHoraInicio(), reservaServicio.getNumeroPersonas(),
                reservaServicio.getPrecioPorPersona(), reservaServicio.getTotalServicio()
        );
    }

    public int update(ReservaServicio reservaServicio) {
        return jdbc.update(
                "UPDATE reservas_servicios SET reserva_id = ?, servicio_id = ?, fecha = ?, hora_inicio = ?, numero_personas = ?, precio_por_persona = ?, total_servicio = ? WHERE id = ?",
                reservaServicio.getReserva().getId(), reservaServicio.getServicio().getId(),
                reservaServicio.getFecha(), reservaServicio.getHoraInicio(),
                reservaServicio.getNumeroPersonas(), reservaServicio.getPrecioPorPersona(),
                reservaServicio.getTotalServicio(), reservaServicio.getId()
        );
    }

    public int deleteById(String id) {
        return jdbc.update("DELETE FROM reservas_servicios WHERE id = ?", id);
    }

    public List<ReservaServicio> findByReservaId(String reservaId) {
        return jdbc.query("SELECT * FROM reservas_servicios WHERE reserva_id = ?",
                new BeanPropertyRowMapper<>(ReservaServicio.class), reservaId);
    }

    public List<ReservaServicio> findByServicioId(String servicioId) {
        return jdbc.query("SELECT * FROM reservas_servicios WHERE servicio_id = ?",
                new BeanPropertyRowMapper<>(ReservaServicio.class), servicioId);
    }
}