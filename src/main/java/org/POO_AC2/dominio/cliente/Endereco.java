package org.POO_AC2.dominio.cliente;

public class Endereco {
    private String rua;
    private int numero;
    private String bairro;
    private int cep;
    private String cidade;
    private String estado;

    public Endereco(String rua, int numero, String bairro, int cep, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    public Endereco() {
    }

    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int num) {
        this.numero = num;
    }

    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getCep() {
        return cep;
    }
    public void setCep(int CEP) {
        this.cep = CEP;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String paraString() {
        return "Endereco{" +
                "rua='" + rua + '\'' +
                ", numero=" + numero +
                ", bairro='" + bairro + '\'' +
                ", cep=" + cep +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
