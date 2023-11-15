package org.POO_AC2.dominio.cliente;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class") // Notação Json que define como o tipo da classe deve ser armazenado no json

public abstract class Cliente {
    private Long id;
    private String nome;
    private Endereco endereco;
    private String dataCadastro;

    public Cliente(String nome, Endereco endereco, String dataCadastro, Long id) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Chamada da função ParaString na classe abstrata.
    public abstract String ParaString();


}
