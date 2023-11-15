package org.POO_AC2.dominio.produto;


import com.fasterxml.jackson.annotation.JsonTypeName;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//classe dependente de Produto

@JsonTypeName("Pereciveis") // Define o tipo desta classe para que o jackson armazene no json
public class Pereciveis extends Produto {
    private String dataValidade;

    public Pereciveis(Long codigo, String nomeProduto, String descricao, double preco, String dataValidade) {
        super(codigo, nomeProduto, descricao, preco);
        this.dataValidade = dataValidade;
    }

    public Pereciveis() {
    }

    public String getDataValidade(){
        return dataValidade;
    }
    public void setDataValidade(String data){
        this.dataValidade = data;
    }

    //funções
    public boolean Vencido(){
        return LocalDate.now().isAfter(LocalDate.parse(dataValidade, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public String ParaString(){
        return "Produto{" +
        "Codigo: '" + codigo + '\'' +
        ", Nome : '" + nomeProduto + '\'' +
        ", Descricao: " + descricao + '\'' +
        ", Preco: " + preco + '\'' +
        ", Vencido: "+ (Vencido() ? "Sim":"Não") +
        '}';
    }
}
