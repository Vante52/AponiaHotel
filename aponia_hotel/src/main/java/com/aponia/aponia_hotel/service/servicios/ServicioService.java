package com.aponia.aponia_hotel.service.servicios;

import com.aponia.aponia_hotel.entities.servicios.Servicio;
import java.util.List;
import java.util.Optional;

public interface ServicioService {
    List<Servicio> listar();
    Servicio crear(Servicio servicio);
    Optional <Servicio> obtener(String id); //porque puede devolver null
    Servicio actualizar(Servicio servicio);
    void eliminar(String id);
    List<Servicio> findByActivo(boolean activo);
}