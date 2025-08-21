package com.aponia.aponia_hotel.service.servicios;

import com.aponia.aponia_hotel.entities.servicios.Servicio;
import com.aponia.aponia_hotel.repository.ServicioRepository;
import com.aponia.aponia_hotel.service.ServicioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository repository;

    public ServicioServiceImpl(ServicioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Servicio> listar() {
        return repository.findAll();
    }

    @Override
    public Servicio crear(Servicio servicio) {
        repository.save(servicio);
        return servicio;
    }

    @Override
    public Optional<Servicio> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public Servicio actualizar(Servicio servicio) {
        repository.update(servicio);
        return servicio;
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Servicio> findByActivo(boolean activo) {
        return repository.findByActivo(activo);
    }
}