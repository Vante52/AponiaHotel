package com.aponia.aponia_hotel.service.servicios;

import com.aponia.aponia_hotel.entities.servicios.Servicio;
import com.aponia.aponia_hotel.repository.servicios.ServicioRepository;
import com.aponia.aponia_hotel.service.servicios.ServicioService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return repository.save(servicio);
    }

    @Override
    public Servicio obtener(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Servicio actualizar(Servicio servicio) {
        return repository.save(servicio);
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }
}
