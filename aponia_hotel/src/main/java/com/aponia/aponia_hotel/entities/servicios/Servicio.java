package com.aponia.aponia_hotel.entities.servicios;

import java.math.BigDecimal;

public class Servicio {
    private String id;
    private String nombre;
    private String descripcion;
    private BigDecimal precioBaseAmount;
    private String precioBaseCurrency;
    private Boolean publico;
    private Boolean activo;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public BigDecimal getPrecioBaseAmount() { return precioBaseAmount; }
    public void setPrecioBaseAmount(BigDecimal precioBaseAmount) { this.precioBaseAmount = precioBaseAmount; }

    public String getPrecioBaseCurrency() { return precioBaseCurrency; }
    public void setPrecioBaseCurrency(String precioBaseCurrency) { this.precioBaseCurrency = precioBaseCurrency; }

    public Boolean getPublico() { return publico; }
    public void setPublico(Boolean publico) { this.publico = publico; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}
