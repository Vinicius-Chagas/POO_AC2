package org.POO_AC2.domain.cliente;

import java.time.LocalDate;

public abstract class Cliente {
    private String nome;
    private Endereco endereco;
    private LocalDate dataCadastro;

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
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    //Chamada da função ParaString na classe abstrata.
    public abstract String ParaString();
}
