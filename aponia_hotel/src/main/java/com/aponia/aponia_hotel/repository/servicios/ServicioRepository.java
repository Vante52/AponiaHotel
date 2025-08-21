package com.aponia.aponia_hotel.repository.servicios;

import com.aponia.aponia_hotel.entities.servicios.Servicio;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ServicioRepository {
    private final JdbcTemplate jdbc;

    public ServicioRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Servicio> findAll() {
        return jdbc.query("SELECT * FROM servicios", new BeanPropertyRowMapper<>(Servicio.class));
    }

    public Optional<Servicio> findById(String id) {
        return jdbc.query("SELECT * FROM servicios WHERE id = ?", 
                new BeanPropertyRowMapper<>(Servicio.class), id)
                .stream().findFirst();
    }

    public int save(Servicio servicio) {
        return jdbc.update(
            "INSERT INTO servicios (id, nombre, descripcion, precio_por_persona, lugar, duracion_minutos, capacidad_maxima, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
            servicio.getId(), servicio.getNombre(), servicio.getDescripcion(),
            servicio.getPrecioPorPersona(), servicio.getLugar(),
            servicio.getDuracionMinutos(), servicio.getCapacidadMaxima(), servicio.getActivo()
        );
    }

    public int update(Servicio servicio) {
        return jdbc.update(
            "UPDATE servicios SET nombre = ?, descripcion = ?, precio_por_persona = ?, lugar = ?, duracion_minutos = ?, capacidad_maxima = ?, activo = ? WHERE id = ?",
            servicio.getNombre(), servicio.getDescripcion(),
            servicio.getPrecioPorPersona(), servicio.getLugar(),
            servicio.getDuracionMinutos(), servicio.getCapacidadMaxima(), 
            servicio.getActivo(), servicio.getId()
        );
    }

    public int deleteById(String id) {
        return jdbc.update("DELETE FROM servicios WHERE id = ?", id);
    }

    public List<Servicio> findByActivo(boolean activo) {
        return jdbc.query("SELECT * FROM servicios WHERE activo = ?", 
                new BeanPropertyRowMapper<>(Servicio.class), activo);
    }
}