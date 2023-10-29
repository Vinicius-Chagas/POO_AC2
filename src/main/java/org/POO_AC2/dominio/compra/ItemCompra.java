package org.POO_AC2.dominio.compra;

public class ItemCompra {
    private int qtde;
    private Long codigoProduto;
    private Double precoUnitario;
    private Double valorTotal;

    public ItemCompra(int qtde, Long codigoProduto, Double precoUnitario, Double valorTotal) {
        this.qtde = qtde;
        this.codigoProduto = codigoProduto;
        this.precoUnitario = precoUnitario;
        this.valorTotal = valorTotal;
    }

    public ItemCompra() {
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
}
