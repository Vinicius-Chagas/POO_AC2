package org.POO_AC2.dominio.produto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.POO_AC2.dominio.cliente.PF;
import org.POO_AC2.dominio.cliente.PJ;

import java.time.LocalDate;

//classe dependente de Produto

@JsonTypeName("Pereciveis") // Define o tipo desta classe para que o jackson armazene no json
public class Pereciveis extends Produto {
    private LocalDate dataValidade;

    public Pereciveis(Long codigo, String nomeProduto, String descricao, double preco, LocalDate dataValidade) {
        super(codigo, nomeProduto, descricao, preco);
        this.dataValidade = dataValidade;
    }

    public Pereciveis() {
    }

    public LocalDate getDataValidade(){
        return dataValidade;
    }
    public void setDataValidade(LocalDate data){
        this.dataValidade = data;
    }

    //funções
    public boolean Vencido(){
        return LocalDate.now().isAfter(dataValidade);
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
