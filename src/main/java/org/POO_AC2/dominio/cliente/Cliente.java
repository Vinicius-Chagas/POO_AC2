package org.POO_AC2.dominio.cliente;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PF.class, name = "PF"),
        @JsonSubTypes.Type(value = PJ.class, name = "PJ")
})
public abstract class Cliente {
    private String nome;
    private Endereco endereco;
    private String dataCadastro;

    public Cliente(String nome, Endereco endereco, String dataCadastro) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
    }

    public Cliente() {
    }

    //funções de pegar e definir o nome dos componentes
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public String getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    //Chamada da função ParaString na classe abstrata.
    public abstract String ParaString();
}
