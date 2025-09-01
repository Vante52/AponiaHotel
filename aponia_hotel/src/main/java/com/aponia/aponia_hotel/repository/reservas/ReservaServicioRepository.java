package com.aponia.aponia_hotel.repository.reservas;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aponia.aponia_hotel.entities.reservas.ReservaServicio;

@Repository
public interface ReservaServicioRepository extends JpaRepository<ReservaServicio, String> {

    // relaciones: private Reserva reserva; private Servicio servicio;
    List<ReservaServicio> findByReservaId(String reservaId);

    List<ReservaServicio> findByServicioId(String servicioId);

    // Nuevo: por servicio y fecha
    List<ReservaServicio> findByServicioIdAndFecha(String servicioId, LocalDate fecha);
}
