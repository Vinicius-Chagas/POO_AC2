package org.POO_AC2.dominio.produto;

public class Produto {
    protected Long codigo;
    protected String nomeProduto;
    protected String descricao;
    protected double preco;

    public Produto(Long codigo, String nomeProduto, String descricao, double preco) {
        this.codigo = codigo;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto() {
    }

    //funções de pegar e definir o nome dos componentes
    public Long getCodigo(){
        return codigo;
    }
    public void setCodigo(Long codigo){
        this.codigo = codigo;
    }
    public String getNomeProduto(){
        return nomeProduto;
    }
    public void setNomeProduto(String nomeProd){
        this.nomeProduto = nomeProd;
    }
    public String getDescricao(){
        return descricao;
    }
    public void setDescricao(String desc){
        this.descricao = desc;
    }
    public double getPreco(){
        return preco;
    }
    public void setPreco(double preco){
        this.preco = preco;
    }

    //função ParaString da classe Produto
    public String ParaString() {
        return "Produto{" +
                "Codigo: '" + codigo + '\'' +
                ", Nome : '" + nomeProduto + '\'' +
                ", Descricao: " + descricao + '\'' +
                ", Preco: " + preco +
                '}';
    }

}