package com.aponia.aponia_hotel.service.reservas;

import com.aponia.aponia_hotel.entities.reservas.ReservaServicio;
import java.util.List;
import java.util.Optional;

public interface ReservaServicioService {
    List<ReservaServicio> listar();
    ReservaServicio crear(ReservaServicio reservaServicio);
    Optional<ReservaServicio> obtener(String id);
    ReservaServicio actualizar(ReservaServicio reservaServicio);
    void eliminar(String id);
    List<ReservaServicio> findByReservaId(String reservaId);
    List<ReservaServicio> findByServicioId(String servicioId);
}