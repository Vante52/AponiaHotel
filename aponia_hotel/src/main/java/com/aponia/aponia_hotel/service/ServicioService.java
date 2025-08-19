package com.aponia.aponia_hotel.service;

import com.aponia.aponia_hotel.entities.servicios.Servicio;
import com.aponia.aponia_hotel.repository.ServicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioService {

    private final ServicioRepository repo;

    public ServicioService(ServicioRepository repo) {
        this.repo = repo;
    }

    public List<Servicio> listar() {
        return repo.findAll();
    }

    public void crear(Servicio s) {
        repo.save(s);
    }

    public Servicio obtener(String id) {
        return repo.findById(id);
    }

    public void actualizar(Servicio s) {
        repo.update(s);
    }

    public void eliminar(String id) {
        repo.deleteById(id);
    }
}
