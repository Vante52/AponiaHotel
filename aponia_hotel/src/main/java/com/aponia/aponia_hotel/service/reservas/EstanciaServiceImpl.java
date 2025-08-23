package com.aponia.aponia_hotel.service.reservas;

import com.aponia.aponia_hotel.entities.reservas.Estancia;
import com.aponia.aponia_hotel.repository.reservas.EstanciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EstanciaServiceImpl implements EstanciaService {

    private final EstanciaRepository repository;

    public EstanciaServiceImpl(EstanciaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Estancia> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Estancia> listarPorReserva(String reservaId) {
        return repository.findByReservaId(reservaId);
    }

    @Override
    public Estancia crear(Estancia estancia) {
        validarDisponibilidad(estancia);
        return repository.save(estancia);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Estancia> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public Estancia actualizar(Estancia estancia) {
        if (estancia.getHabitacionAsignada() != null) {
            validarDisponibilidad(estancia);
        }
        return repository.save(estancia);
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    private void validarDisponibilidad(Estancia estancia) {
        if (estancia.getHabitacionAsignada() != null) {
            List<Estancia> overlapping = repository.findOverlappingStays(
                estancia.getHabitacionAsignada().getId(), // Usando el ID en lugar del objeto completo
                estancia.getCheckIn(),
                estancia.getCheckOut()
            );

            if (!overlapping.isEmpty() &&
                (overlapping.size() > 1 || !overlapping.get(0).getId().equals(estancia.getId()))) {
                throw new IllegalStateException("La habitación no está disponible para las fechas seleccionadas");
            }
        }
    }
}