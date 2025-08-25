package com.aponia.aponia_hotel.service.pagos;

import com.aponia.aponia_hotel.entities.pagos.Pago;
import com.aponia.aponia_hotel.entities.pagos.Pago.EstadoPago;
import com.aponia.aponia_hotel.entities.pagos.Pago.TipoPago;
import com.aponia.aponia_hotel.repository.pagos.PagoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PagoServiceImpl implements PagoService {

    private final PagoRepository repository;

    public PagoServiceImpl(PagoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> listar() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> listarPorReserva(String reservaId) {
        return repository.findByReservaId(reservaId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> listarPorReservaYEstado(String reservaId, EstadoPago estado) {
        return repository.findByReservaIdAndEstado(reservaId, estado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pago> listarPorTipo(TipoPago tipo) {
        return repository.findByTipo(tipo);
    }

    @Override
    public Pago crear(Pago pago) {
        validarPago(pago);
        if (pago.getEstado() == null) {
            pago.setEstado(EstadoPago.PENDIENTE);
        }
        return repository.save(pago);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pago> obtener(String id) {
        return repository.findById(id);
    }

    @Override
    public Pago actualizar(Pago pago) {
        validarPago(pago);
        return repository.save(pago);
    }

    @Override
    public void eliminar(String id) {
        repository.deleteById(id);
    }

    @Override
    public Pago completarPago(String id) {
        return actualizarEstadoPago(id, EstadoPago.COMPLETADO);
    }

    @Override
    public Pago marcarPagoFallido(String id) {
        return actualizarEstadoPago(id, EstadoPago.FALLIDO);
    }

    @Override
    public Pago procesarReembolso(String id) {
        Pago pago = obtenerYValidar(id);
        if (pago.getEstado() != EstadoPago.COMPLETADO) {
            throw new IllegalStateException("Solo se pueden reembolsar pagos completados");
        }

        Pago reembolso = new Pago();
        reembolso.setReserva(pago.getReserva());
        reembolso.setTipo(TipoPago.REEMBOLSO);
        reembolso.setMonto(pago.getMonto());
        reembolso.setEstado(EstadoPago.PENDIENTE);
        reembolso.setConcepto("Reembolso del pago " + pago.getId());

        pago.setEstado(EstadoPago.REEMBOLSADO);
        repository.save(pago);

        return repository.save(reembolso);
    }

    @Override
    public double calcularTotalPagosCompletados(String reservaId) {
        return repository.findByReservaIdAndEstado(reservaId, EstadoPago.COMPLETADO)
                .stream()
                .map(Pago::getMonto)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .doubleValue();
    }

    private Pago actualizarEstadoPago(String id, EstadoPago nuevoEstado) {
        Pago pago = obtenerYValidar(id);
        pago.setEstado(nuevoEstado);
        return repository.save(pago);
    }

    private Pago obtenerYValidar(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el pago con ID: " + id));
    }

    private void validarPago(Pago pago) {
        if (pago.getMonto().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del pago debe ser positivo");
        }
        if (pago.getTipo() == null) {
            throw new IllegalArgumentException("El tipo de pago es requerido");
        }
        if (pago.getReserva() == null) {
            throw new IllegalArgumentException("La reserva es requerida");
        }
    }
}