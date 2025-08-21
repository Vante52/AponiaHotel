package com.aponia.aponia_hotel.repository.resources;

import com.aponia.aponia_hotel.entities.resources.Imagen;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ImagenRepository {
    private final JdbcTemplate jdbc;

    public ImagenRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Imagen> findAll() {
        return jdbc.query("SELECT * FROM imagenes", new BeanPropertyRowMapper<>(Imagen.class));
    }

    public Optional<Imagen> findById(String id) {
        return jdbc.query("SELECT * FROM imagenes WHERE id = ?", 
                new BeanPropertyRowMapper<>(Imagen.class), id)
                .stream().findFirst();
    }

    public int save(Imagen imagen) {
        return jdbc.update(
            "INSERT INTO imagenes (id, servicio_id, tipo_habitacion_id, url) VALUES (?, ?, ?, ?)",
            imagen.getId(), imagen.getServicio() != null ? imagen.getServicio().getId() : null,
            imagen.getTipoHabitacion() != null ? imagen.getTipoHabitacion().getId() : null,
            imagen.getUrl()
        );
    }

    public int update(Imagen imagen) {
        return jdbc.update(
            "UPDATE imagenes SET servicio_id = ?, tipo_habitacion_id = ?, url = ? WHERE id = ?",
            imagen.getServicio() != null ? imagen.getServicio().getId() : null,
            imagen.getTipoHabitacion() != null ? imagen.getTipoHabitacion().getId() : null,
            imagen.getUrl(), imagen.getId()
        );
    }

    public int deleteById(String id) {
        return jdbc.update("DELETE FROM imagenes WHERE id = ?", id);
    }

    public List<Imagen> findByServicioId(String servicioId) {
        return jdbc.query("SELECT * FROM imagenes WHERE servicio_id = ?", 
                new BeanPropertyRowMapper<>(Imagen.class), servicioId);
    }

    public List<Imagen> findByTipoHabitacionId(String tipoHabitacionId) {
        return jdbc.query("SELECT * FROM imagenes WHERE tipo_habitacion_id = ?", 
                new BeanPropertyRowMapper<>(Imagen.class), tipoHabitacionId);
    }
}