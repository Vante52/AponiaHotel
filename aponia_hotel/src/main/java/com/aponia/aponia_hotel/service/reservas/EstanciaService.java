package com.aponia.aponia_hotel.service.reservas;

import com.aponia.aponia_hotel.entities.reservas.Estancia;
import java.util.List;
import java.util.Optional;

public interface EstanciaService {
    List<Estancia> listar();
    Estancia crear(Estancia estancia);
    Optional<Estancia> obtener(String id);
    Estancia actualizar(Estancia estancia);
    void eliminar(String id);
    List<Estancia> findByReservaId(String reservaId);
    void asignarHabitacion(String estanciaId, String habitacionId);
}