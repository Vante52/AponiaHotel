package com.aponia.aponia_hotel.repository.servicios;

import com.aponia.aponia_hotel.entities.servicios.ServicioDisponibilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ServicioDisponibilidadRepository extends JpaRepository<ServicioDisponibilidad, String> {
    List<ServicioDisponibilidad> findByServicioIdAndFechaAndCapacidadDisponibleGreaterThan(
        String servicioId, LocalDate fecha, int minCapacidad);

    @Query("SELECT sd FROM ServicioDisponibilidad sd WHERE sd.servicio.id = :servicioId " +
           "AND sd.fecha = :fecha AND sd.horaInicio = :horaInicio")
    ServicioDisponibilidad findByServicioAndFechaAndHora(
        @Param("servicioId") String servicioId,
        @Param("fecha") LocalDate fecha,
        @Param("horaInicio") LocalTime horaInicio);
}