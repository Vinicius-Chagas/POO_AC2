package org.POO_AC2.domain.compra;

import java.time.LocalDate;
import java.util.ArrayList;

import org.POO_AC2.domain.cliente.Cliente;

public class Compra {
    private Long Id;
    private LocalDate dataCompra;
    private Double valorTotal;
    private Cliente cliente;
    private ArrayList<ItemCompra> itensCompra;
    private int parcelasPagas;

    public Long getId(){
        return Id;
    }
    public Long setId(Long id){
        this.Id = id;
    }

    public LocalDate getDataCompra(){
        return dataCompra;
    }
    public LocalDate setDataCompra(LocalDate data){
        this.dataCompra = data;
    }

    public Double getValorTotal(){
        return valorTotal;
    }
    public Double setValorTotal(Double valor){
        this.valorTotal = valor;
    }

    public Cliente getCliente(){
        return cliente;
    }
    public Cliente setCliente(Cliente clie){
        this.cliente = clie;
    }

    public int getParcelasPagas(){
        return parcelasPagas;
    }
    public int setParcelasPagas(int NumParcelas){
        this.parcelasPagas = NumParcelas;
    }
}
