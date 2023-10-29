package org.POO_AC2.domain.cliente;

public class PF extends Cliente {
    private String cpf;
    private int qntParcelasMax;

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
