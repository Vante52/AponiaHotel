package com.aponia.aponia_hotel.repository;

import com.aponia.aponia_hotel.entities.servicios.Servicio;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServicioRepository {
    private final JdbcTemplate jdbc;

    public ServicioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Servicio> findAll() {
        return jdbc.query("SELECT * FROM servicios ORDER BY nombre",
                new BeanPropertyRowMapper<>(Servicio.class));
    }

    public int save(Servicio s) {
        return jdbc.update("""
                INSERT INTO servicios
                (id, nombre, descripcion, precio_base_amount, precio_base_currency, publico, activo)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """,
                s.getId(), s.getNombre(), s.getDescripcion(),
                s.getPrecioBaseAmount(), s.getPrecioBaseCurrency(),
                s.getPublico(), s.getActivo()
        );
    }

    public Servicio findById(String id) {
        return jdbc.queryForObject(
                "SELECT * FROM servicios WHERE id = ?",
                new BeanPropertyRowMapper<>(Servicio.class),
                id
        );
    }

    public int update(Servicio s) {
        return jdbc.update("""
                UPDATE servicios SET
                  nombre = ?, descripcion = ?,
                  precio_base_amount = ?, precio_base_currency = ?,
                  publico = ?, activo = ?
                WHERE id = ?
                """,
                s.getNombre(), s.getDescripcion(),
                s.getPrecioBaseAmount(), s.getPrecioBaseCurrency(),
                s.getPublico(), s.getActivo(),
                s.getId()
        );
    }

    public int deleteById(String id) {
        return jdbc.update("DELETE FROM servicios WHERE id = ?", id);
    }
}
