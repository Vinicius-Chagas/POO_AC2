package org.POO_AC2.dominio.recursos.relatorios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.POO_AC2.dominio.cliente.Cliente;
import org.POO_AC2.dominio.cliente.PF;
import org.POO_AC2.dominio.cliente.PJ;
import org.POO_AC2.dominio.compra.Compra;
import org.POO_AC2.dominio.produto.Pereciveis;
import org.POO_AC2.dominio.produto.Produto;
import org.POO_AC2.dominio.recursos.Json.Json;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Relatorios {

    File fileCliente = new File("Cliente.json");
    File fileProduto = new File("Produto.json");

    File fileCompra = new File("Compra.json");

    public void procurarClientePorNome(String nome){
        boolean encontrado = false;
        try {
            ArrayList<Cliente> arrayCliente = new ArrayList<>(Json.readAllData(fileCliente, Cliente.class));

            for (Cliente a: arrayCliente) {
                if(a.getNome().startsWith(nome)){
                    Json.stringfy(a);
                    encontrado = true;
                }
            }
            if(!encontrado){
                System.out.println("Não existem clientes que comecem com '"+nome+"' na base de dados.");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void relacaoTodosProdutos(){
        try {
            Json.stringfy(Json.readAllData(fileProduto, Produto.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscaProdutoNome(String nome){
        boolean encontrou = false;
        try {
            ArrayList<Produto> arrayProduto = new ArrayList<>(Json.readAllData(fileProduto, Produto.class));
            for (Produto p: arrayProduto) {
                if(p.getNomeProduto().compareTo(nome) == 0){
                    Json.stringfy(p);
                    encontrou = true;
                }
            }
            if(!encontrou) System.out.println("Produto com nome " + nome + " não foi encontrado.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscaVencidos(){
        try {
            ArrayList<Produto> arrayProduto = new ArrayList<>(Json.readAllData(fileProduto, Produto.class));
            for (Produto p: arrayProduto) {
                if(p.getClass() == Pereciveis.class){
                    if(((Pereciveis) p).Vencido()){
                        Json.stringfy(p);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoTodasCompras(){
        try {
            Json.stringfy(Json.readAllData(fileCompra, Compra.class));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscaCompraNumero(Long numero){
        boolean encontrou = false;
        try {
            ArrayList<Compra> arrayCompra = new ArrayList<>(Json.readAllData(fileCompra, Compra.class));
            for (Compra c: arrayCompra) {
                if(Objects.equals(c.getId(), numero)){
                    Json.stringfy(c);
                    encontrou = true;
                }
            }
            if(!encontrou) System.out.println("Não foi encontrado nenhuma compra que corresponda ao número: " + numero);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoPendencias(){
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class));
            for(Compra c: arrayCompras){
                if(c.getParcelasTotais() < c.getParcelasPagas())
                    Json.stringfy(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoDezUltimas(){
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class));
            ArrayList<Compra> ultimasDez = new ArrayList<>();

            arrayCompras.sort((o1, o2) -> LocalDate.parse(o2.getUltimoPagamento()).compareTo(LocalDate.parse(o1.getUltimoPagamento())));
            ultimasDez.addAll(arrayCompras.subList(0,10));

            Json.stringfy(ultimasDez);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void compraMaisCara(){
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class));
            arrayCompras.sort((o1, o2) -> o2.getValorTotal().compareTo(o1.getValorTotal()));

            Json.stringfy(arrayCompras.get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void compraMaisBarata(){
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class));
            arrayCompras.sort((o1, o2) -> o1.getValorTotal().compareTo(o2.getValorTotal()));

            Json.stringfy(arrayCompras.get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
