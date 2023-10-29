package org.POO_AC2.domain.compra;

import org.POO_AC2.domain.cliente.Cliente;

public class ItemCompra {
    private int qtde;
    private Long codigoProduto;
    private Double precoUnitario;
    private Double valorTotal;

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
}
