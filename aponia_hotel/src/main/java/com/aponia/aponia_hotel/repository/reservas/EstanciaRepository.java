package com.aponia.aponia_hotel.repository.reservas;

import com.aponia.aponia_hotel.entities.reservas.Estancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EstanciaRepository extends JpaRepository<Estancia, String> {
    List<Estancia> findByReservaId(String reservaId);

    @Query("SELECT e FROM Estancia e WHERE e.habitacionAsignada = :habitacionId " +
           "AND ((e.checkIn BETWEEN :checkIn AND :checkOut) OR " +
           "(e.checkOut BETWEEN :checkIn AND :checkOut))")
    List<Estancia> findOverlappingStays(String habitacionId, LocalDate checkIn, LocalDate checkOut);
}