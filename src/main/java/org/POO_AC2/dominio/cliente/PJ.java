package org.POO_AC2.dominio.cliente;

import com.fasterxml.jackson.annotation.JsonTypeName;

//Pessoa Jurídica, filho de Cliente
@JsonTypeName("PJ") // Define o tipo desta classe para que o jackson armazene no json
public class PJ extends Cliente {
    private String cnpj;
    private String razaoSocial;
    private int prazoMaximo;

    public PJ(String nome, Endereco endereco, String dataCadastro, String cnpj, String razaoSocial, int prazoMaximo) {
        super(nome, endereco, dataCadastro);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.prazoMaximo = prazoMaximo;
    }

    public PJ() {
    }

    //funções de pegar e definir o nome dos componentes
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    public int getPrazoMaximo() {
        return prazoMaximo;
    }
    public void setPrazoMaximo(int prazoMaximo) {
        this.prazoMaximo = prazoMaximo;
    }

    //Chamada da função ParaString em método filho.
   @Override
   public String ParaString() {
       return "PJ{" +
               "CNPJ: '" + cnpj + '\'' +
               ", Razao social: '" + razaoSocial + '\'' +
               ", Prazo maximo: " + prazoMaximo +
               '}';
   }


}
