package com.howtodoinjava.rest.dto;

public class ListadoDTO {

    private Integer id;
    private FrutaDTO fruta;
    private Integer cantidad;
    private Integer creador;

    public ListadoDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FrutaDTO getFruta() {
        return fruta;
    }

    public void setFruta(FrutaDTO fruta) {
        this.fruta = fruta;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCreador() {
        return creador;
    }

    public void setCreador(Integer creador) {
        this.creador = creador;
    }
}
