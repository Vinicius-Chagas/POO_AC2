package org.POO_AC2.dominio.cliente;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("PF") // Define o tipo desta classe para que o jackson armazene no json
public class PF extends Cliente {
    private String cpf;
    private int qntParcelasMax;

    public PF(String nome, Endereco endereco, String dataCadastro, String cpf, int qntParcelasMax) {
        super(nome, endereco, dataCadastro);
        this.cpf = cpf;
        this.qntParcelasMax = qntParcelasMax;
    }

    public PF() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getQntParcelasMax() {
        return qntParcelasMax;
    }

    public void setQntParcelasMax(int qntParcelasMax) {
        this.qntParcelasMax = qntParcelasMax;
    }

    @Override
    public String ParaString() {
        return "PF{" +
                "cpf: '" + cpf + '\'' +
                ", qntParcelasMax: " + qntParcelasMax +
                '}';
    }
}
