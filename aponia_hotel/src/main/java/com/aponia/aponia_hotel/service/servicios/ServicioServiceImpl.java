package com.aponia.aponia_hotel.service.servicios;

import com.aponia.aponia_hotel.entities.servicios.Servicio;
import com.aponia.aponia_hotel.repository.servicios.ServicioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository repository;

    public ServicioServiceImpl(ServicioRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> listar() {
        return repository.findAll();
    }

    @Override
    public Servicio crear(Servicio servicio) {
        if (repository.existsByNombre(servicio.getNombre())) {
            throw new IllegalArgumentException("Ya existe un servicio con ese nombre");
        }
        return repository.save(servicio);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Servicio> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public Servicio actualizar(Servicio servicio) {
        Optional<Servicio> existente = repository.findById(servicio.getId());
        if (existente.isPresent() && !existente.get().getNombre().equals(servicio.getNombre())) {
            if (repository.existsByNombre(servicio.getNombre())) {
                throw new IllegalArgumentException("Ya existe un servicio con ese nombre");
            }
        }
        return repository.save(servicio);
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }
}
