package com.aponia.aponia_hotel.service.servicios;

import com.aponia.aponia_hotel.entities.imagenes.Imagen;
import com.aponia.aponia_hotel.repository.ImagenRepository;
import com.aponia.aponia_hotel.service.ImagenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenServiceImpl implements ImagenService {

    private final ImagenRepository repository;

    public ImagenServiceImpl(ImagenRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Imagen> listar() {
        return repository.findAll();
    }

    @Override
    public Imagen crear(Imagen imagen) {
        repository.save(imagen);
        return imagen;
    }

    @Override
    public Optional<Imagen> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public Imagen actualizar(Imagen imagen) {
        repository.update(imagen);
        return imagen;
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Imagen> findByServicioId(String servicioId) {
        return repository.findByServicioId(servicioId);
    }

    @Override
    public List<Imagen> findByTipoHabitacionId(String tipoHabitacionId) {
        return repository.findByTipoHabitacionId(tipoHabitacionId);
    }
}