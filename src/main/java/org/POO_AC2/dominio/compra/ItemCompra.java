package org.POO_AC2.dominio.compra;

import org.POO_AC2.dominio.produto.Produto;
import org.POO_AC2.dominio.recursos.Json.Json;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class ItemCompra {
    private int qtde;
    private Long codigoProduto;
    private Double precoUnitario;
    private Double valorTotal;

    public ItemCompra(int qtde, Long codigoProduto) throws IOException {
        Produto p = procurarProdutoPorID(codigoProduto);
        this.qtde = qtde;
        this.codigoProduto = codigoProduto;
        this.precoUnitario = p.getPreco();
        this.valorTotal = p.getPreco() * qtde;
    }

    public ItemCompra() {
    }

    public ItemCompra(Produto selectedProduto, int quantidade) {
    }

    public int getQtde(){
        return qtde;
    }
    public void setQtde(int quantidade){
        this.qtde = quantidade;
    }

    public Long getCodigoProduto(){
        return codigoProduto;
    }
    public void setCodigoProduto(Long cod){
        this.codigoProduto = cod;
    }

    public Double getPrecoUnitario(){
        return precoUnitario;
    }
    public void setPrecoUnitario(Double preco){
        this.precoUnitario = preco;
    }

    public Double getValorTotal(){
        return valorTotal;
    }
    public void setValorTotal(Double val){
        this.valorTotal = val;
    }


    public String paraString() {
        return "ItemCompra{" +
                "qtde=" + qtde +
                ", codigoProduto=" + codigoProduto +
                ", precoUnitario=" + precoUnitario +
                ", valorTotal=" + valorTotal +
                '}';
    }

    public Produto procurarProdutoPorID(Long id) throws IOException {
        File fileProduto = new File("Produto.json");
        ArrayList<Produto> arrayProduto = new ArrayList<>();
        if (!fileProduto.createNewFile() && fileProduto.length() > 0) {
            arrayProduto.addAll(Json.readAllData(fileProduto, Produto.class));
        }

        Produto item = null;

        for (Produto p: arrayProduto) {
            if(p.getCodigo().compareTo(id) == 0){
                item = p;
            }
        }

        return item;
    }
}
