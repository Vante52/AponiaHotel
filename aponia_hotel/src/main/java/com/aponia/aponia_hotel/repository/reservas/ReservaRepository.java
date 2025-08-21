package com.aponia.aponia_hotel.repository.reservas;

import com.aponia.aponia_hotel.entities.reservas.Reserva;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservaRepository {
    private final JdbcTemplate jdbc;

    public ReservaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Reserva> findAll() {
        return jdbc.query("SELECT * FROM reservas", new BeanPropertyRowMapper<>(Reserva.class));
    }

    public Optional<Reserva> findById(String id) {
        return jdbc.query("SELECT * FROM reservas WHERE id = ?", 
                new BeanPropertyRowMapper<>(Reserva.class), id)
                .stream().findFirst();
    }

    public int save(Reserva reserva) {
        return jdbc.update(
            "INSERT INTO reservas (id, codigo, cliente_id, fecha_creacion, estado, notas) VALUES (?, ?, ?, ?, ?, ?)",
            reserva.getId(), reserva.getCodigo(), reserva.getCliente().getId(),
            reserva.getFechaCreacion(), reserva.getEstado(), reserva.getNotas()
        );
    }

    public int update(Reserva reserva) {
        return jdbc.update(
            "UPDATE reservas SET codigo = ?, cliente_id = ?, fecha_creacion = ?, estado = ?, notas = ? WHERE id = ?",
            reserva.getCodigo(), reserva.getCliente().getId(),
            reserva.getFechaCreacion(), reserva.getEstado(), 
            reserva.getNotas(), reserva.getId()
        );
    }

    public int deleteById(String id) {
        return jdbc.update("DELETE FROM reservas WHERE id = ?", id);
    }

    public List<Reserva> findByClienteId(String clienteId) {
        return jdbc.query("SELECT * FROM reservas WHERE cliente_id = ?", 
                new BeanPropertyRowMapper<>(Reserva.class), clienteId);
    }

    public Optional<Reserva> findByCodigo(String codigo) {
        return jdbc.query("SELECT * FROM reservas WHERE codigo = ?", 
                new BeanPropertyRowMapper<>(Reserva.class), codigo)
                .stream().findFirst();
    }

    public int updateEstado(String id, String estado) {
        return jdbc.update("UPDATE reservas SET estado = ? WHERE id = ?", estado, id);
    }
}