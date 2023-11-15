package org.POO_AC2.dominio.compra;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.POO_AC2.dominio.cliente.Cliente;
import org.POO_AC2.dominio.recursos.Json.Json;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@class") // Notação Json que define como o tipo da classe deve ser armazenado no json
@JsonDeserialize(using = CompraDeserializer.class)
public class Compra {
    private Long Id;
    private String dataCompra;
    private Double valorTotal;
    private Cliente cliente;

    private List<ItemCompra> itensCompra;
    private int parcelasTotais;
    private int parcelasPagas;

    private String ultimoPagamento;

    public Compra(String dataCompra,Long codigo,  Long idCliente, List<ItemCompra> itensCompra, int parcelasTotais) throws IOException {
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal((ArrayList<ItemCompra>) itensCompra);
        this.cliente = procurarCliente(idCliente); // procura o cliente pelo ID passado e o retorna para inicializar a compra.
        this.itensCompra = itensCompra;
        this.parcelasPagas = 0;
        this.parcelasTotais = parcelasTotais;
        this.ultimoPagamento = null;
        this.Id = codigo;
    }

    public Compra() {
    }

    public Compra(String dataCompra, double valorTotal, Cliente cliente, List<ItemCompra> itensCompra, int parcelasTotais, int parcelasPagas, String ultimoPagamento, Long id) {
        this.dataCompra = dataCompra;
        this.valorTotal = valorTotal;
        this.cliente = cliente;
        this.itensCompra = itensCompra;
        this.parcelasPagas = parcelasPagas;
        this.parcelasTotais = parcelasTotais;
        this.ultimoPagamento = ultimoPagamento;
        this.Id = id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
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

    public List<ItemCompra> getItensCompra() {
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

    //Procura um cliente pelo ID e o retorna para instanciar a compra
    public Cliente procurarCliente(Long id) throws IOException {

        //Fazer método para substituir isso.
        File fileCliente = new File("Cliente.json");
        ArrayList<Cliente> arrayCliente = new ArrayList<>();
        if(!fileCliente.createNewFile() && fileCliente.length()>0){
            arrayCliente.addAll(Json.readAllData(fileCliente,Cliente.class));
        }

        Cliente cliente = null;

        // Encontra o cliente correto baseado no ID
        for (Cliente c: arrayCliente) {
            if(c.getId().compareTo(id) == 0){
                cliente = c;
            }

        }

        return cliente;
    }

    public double valorTotal(ArrayList<ItemCompra> itensCompra){
        double total = 0;
        for (ItemCompra i: itensCompra) {
            total += i.getValorTotal();
        }

        return total;
    }
}
