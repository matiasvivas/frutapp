package com.howtodoinjava.rest.dto;

public class FrutaDTO {

    private Integer id;
    private String Nombre;
    private Boolean disponible;
    private Integer stockInicial;
    private Integer stockReal;

    public FrutaDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Integer getStockInicial() {
        return stockInicial;
    }

    public void setStockInicial(Integer stockInicial) {
        this.stockInicial = stockInicial;
    }

    public Integer getStockReal() {
        return stockReal;
    }

    public void setStockReal(Integer stockReal) {
        this.stockReal = stockReal;
    }
}
