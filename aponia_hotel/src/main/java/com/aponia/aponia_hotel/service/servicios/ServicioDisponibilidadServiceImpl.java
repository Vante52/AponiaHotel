package com.aponia.aponia_hotel.service.servicios;

import com.aponia.aponia_hotel.entities.servicios.ServicioDisponibilidad;
import com.aponia.aponia_hotel.repository.ServicioDisponibilidadRepository;
import com.aponia.aponia_hotel.service.ServicioDisponibilidadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioDisponibilidadServiceImpl implements ServicioDisponibilidadService {

    private final ServicioDisponibilidadRepository repository;

    public ServicioDisponibilidadServiceImpl(ServicioDisponibilidadRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ServicioDisponibilidad> listar() {
        return repository.findAll();
    }

    @Override
    public ServicioDisponibilidad crear(ServicioDisponibilidad disponibilidad) {
        repository.save(disponibilidad);
        return disponibilidad;
    }

    @Override
    public Optional<ServicioDisponibilidad> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public ServicioDisponibilidad actualizar(ServicioDisponibilidad disponibilidad) {
        repository.update(disponibilidad);
        return disponibilidad;
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<ServicioDisponibilidad> findByServicioId(String servicioId) {
        return repository.findByServicioId(servicioId);
    }

    @Override
    public List<ServicioDisponibilidad> findByServicioIdAndFecha(String servicioId, String fecha) {
        return repository.findByServicioIdAndFecha(servicioId, fecha);
    }
}