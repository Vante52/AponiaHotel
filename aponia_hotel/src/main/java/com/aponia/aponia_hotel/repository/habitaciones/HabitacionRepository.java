package com.aponia.aponia_hotel.repository.habitaciones;

import com.aponia.aponia_hotel.entities.habitaciones.Habitacion;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HabitacionRepository {
    private final JdbcTemplate jdbc;

    public HabitacionRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Habitacion> findAll() {
        return jdbc.query("SELECT * FROM habitaciones", new BeanPropertyRowMapper<>(Habitacion.class));
    }

    public Optional<Habitacion> findById(String id) {
        return jdbc.query("SELECT * FROM habitaciones WHERE id = ?", 
                new BeanPropertyRowMapper<>(Habitacion.class), id)
                .stream().findFirst();
    }

    public int save(Habitacion habitacion) {
        return jdbc.update(
            "INSERT INTO habitaciones (id, tipo_id, numero, activa) VALUES (?, ?, ?, ?)",
            habitacion.getId(), habitacion.getTipo().getId(), 
            habitacion.getNumero(), habitacion.getActiva()
        );
    }

    public int update(Habitacion habitacion) {
        return jdbc.update(
            "UPDATE habitaciones SET tipo_id = ?, numero = ?, activa = ? WHERE id = ?",
            habitacion.getTipo().getId(), habitacion.getNumero(), 
            habitacion.getActiva(), habitacion.getId()
        );
    }

    public int deleteById(String id) {
        return jdbc.update("DELETE FROM habitaciones WHERE id = ?", id);
    }

    public List<Habitacion> findByTipoId(String tipoId) {
        return jdbc.query("SELECT * FROM habitaciones WHERE tipo_id = ?", 
                new BeanPropertyRowMapper<>(Habitacion.class), tipoId);
    }

    public List<Habitacion> findDisponiblesByTipoAndFechas(String tipoId, String checkIn, String checkOut) {
        String sql = "SELECT h.* FROM habitaciones h " +
                    "WHERE h.tipo_id = ? AND h.activa = true " +
                    "AND NOT EXISTS (" +
                    "   SELECT 1 FROM estancias e " +
                    "   WHERE e.habitacion_asignada = h.id " +
                    "   AND NOT (? <= e.check_in OR ? >= e.check_out)" +
                    ")";
        return jdbc.query(sql, new BeanPropertyRowMapper<>(Habitacion.class), 
                tipoId, checkOut, checkIn);
    }
}