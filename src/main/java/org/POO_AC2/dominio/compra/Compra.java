package org.POO_AC2.dominio.compra;

import java.time.LocalDate;
import java.util.ArrayList;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.POO_AC2.dominio.cliente.Cliente;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type") // Notação Json que define como o tipo da classe deve ser armazenado no json
public class Compra {
    private Long Id;
    private LocalDate dataCompra;
    private Double valorTotal;
    private Cliente cliente;
    private ArrayList<ItemCompra> itensCompra;
    private int parcelasTotais;
    private int parcelasPagas;

    private String ultimoPagamento;

    public Compra(LocalDate dataCompra, Double valorTotal, Cliente cliente, ArrayList<ItemCompra> itensCompra, int parcelasPagas, int parcelasTotais, String ultimoPagamento) {
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.itensCompra = itensCompra;
        this.parcelasPagas = parcelasPagas;
        this.parcelasTotais = parcelasTotais;
        this.ultimoPagamento = ultimoPagamento;
    }

    public Compra() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<ItemCompra> getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(ItemCompra itensCompra) {
        this.itensCompra.add(itensCompra);
    }

    public int getParcelasPagas() {
        return parcelasPagas;
    }

    public void setParcelasPagas(int parcelasPagas) {
        this.parcelasPagas = parcelasPagas;
    }

    public int getParcelasTotais() {
        return parcelasTotais;
    }

    public void setParcelasTotais(int parcelasTotais) {
        this.parcelasTotais = parcelasTotais;
    }

    public String getUltimoPagamento() {
        return ultimoPagamento;
    }

    public void setUltimoPagamento(String ultimoPagamento) {
        this.ultimoPagamento = ultimoPagamento;
    }
}
