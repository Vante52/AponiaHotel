package com.aponia.aponia_hotel.repository.habitaciones;

import com.aponia.aponia_hotel.entities.habitaciones.HabitacionTipo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HabitacionTipoRepository {
    private final JdbcTemplate jdbc;

    public HabitacionTipoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<HabitacionTipo> findAll() {
        return jdbc.query("SELECT * FROM habitaciones_tipos", new BeanPropertyRowMapper<>(HabitacionTipo.class));
    }

    public Optional<HabitacionTipo> findById(String id) {
        return jdbc.query("SELECT * FROM habitaciones_tipos WHERE id = ?", 
                new BeanPropertyRowMapper<>(HabitacionTipo.class), id)
                .stream().findFirst();
    }

    public int save(HabitacionTipo habitacionTipo) {
        return jdbc.update(
            "INSERT INTO habitaciones_tipos (id, nombre, descripcion, aforo_maximo, precio_por_noche, rango_inicio, rango_fin, activa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
            habitacionTipo.getId(), habitacionTipo.getNombre(), habitacionTipo.getDescripcion(),
            habitacionTipo.getAforoMaximo(), habitacionTipo.getPrecioPorNoche(),
            habitacionTipo.getRangoInicio(), habitacionTipo.getRangoFin(), habitacionTipo.getActiva()
        );
    }

    public int update(HabitacionTipo habitacionTipo) {
        return jdbc.update(
            "UPDATE habitaciones_tipos SET nombre = ?, descripcion = ?, aforo_maximo = ?, precio_por_noche = ?, rango_inicio = ?, rango_fin = ?, activa = ? WHERE id = ?",
            habitacionTipo.getNombre(), habitacionTipo.getDescripcion(),
            habitacionTipo.getAforoMaximo(), habitacionTipo.getPrecioPorNoche(),
            habitacionTipo.getRangoInicio(), habitacionTipo.getRangoFin(), 
            habitacionTipo.getActiva(), habitacionTipo.getId()
        );
    }

    public int deleteById(String id) {
        return jdbc.update("DELETE FROM habitaciones_tipos WHERE id = ?", id);
    }
}