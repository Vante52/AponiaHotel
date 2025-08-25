package com.aponia.aponia_hotel.entities.habitaciones;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.aponia.aponia_hotel.entities.resources.Imagen;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "habitaciones_tipos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HabitacionTipo {

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "aforo_maximo", nullable = false)
    private Integer aforoMaximo;

    @Column(name = "precio_por_noche", nullable = false, precision = 12, scale = 2)
    private BigDecimal precioPorNoche;

    @Column(name = "rango_inicio", nullable = false)
    private Integer rangoInicio;

    @Column(name = "rango_fin", nullable = false)
    private Integer rangoFin;

    @Column(name = "activa", nullable = false)
    private Boolean activa = true;

    @OneToMany(mappedBy = "tipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Habitacion> habitaciones;

    @OneToMany(mappedBy = "tipoHabitacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Imagen> imagenes;

    @PrePersist
    @PreUpdate
    public void validate() {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalStateException("El nombre es requerido");
        }
        if (aforoMaximo == null || aforoMaximo <= 0) {
            throw new IllegalStateException("El aforo máximo debe ser positivo");
        }
        if (precioPorNoche == null || precioPorNoche.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("El precio por noche debe ser no negativo");
        }
        if (rangoInicio == null) {
            throw new IllegalStateException("El rango inicial es requerido");
        }
        if (rangoFin == null) {
            throw new IllegalStateException("El rango final es requerido");
        }
        if (rangoInicio >= rangoFin) {
            throw new IllegalStateException("El rango final debe ser mayor que el rango inicial");
        }
    }
}