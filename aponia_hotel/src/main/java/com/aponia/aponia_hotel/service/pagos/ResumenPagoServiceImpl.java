package com.aponia.aponia_hotel.service.pagos;

import com.aponia.aponia_hotel.entities.pagos.ResumenPago;
import com.aponia.aponia_hotel.repository.pagos.ResumenPagoRepository;
import com.aponia.aponia_hotel.service.pagos.ResumenPagoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ResumenPagoServiceImpl implements ResumenPagoService {

    private final ResumenPagoRepository repository;

    public ResumenPagoServiceImpl(ResumenPagoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ResumenPago> listar() {
        return repository.findAll();
    }

    @Override
    public ResumenPago crear(ResumenPago resumenPago) {
        repository.save(resumenPago);
        return resumenPago;
    }

    @Override
    public Optional<ResumenPago> obtener(String reservaId) {
        return repository.findById(reservaId);
    }

    @Override
    public ResumenPago actualizar(ResumenPago resumenPago) {
        repository.update(resumenPago);
        return resumenPago;
    }

    @Override
    public void eliminar(String reservaId) {
        repository.deleteById(reservaId);
    }

    @Override
    public void actualizarResumen(String reservaId, BigDecimal totalHabitaciones, BigDecimal totalServicios, BigDecimal totalPagado) {
        BigDecimal saldoPendiente = totalHabitaciones.add(totalServicios).subtract(totalPagado);
        repository.updateResumen(reservaId, totalHabitaciones, totalServicios, totalPagado, saldoPendiente);
    }
}