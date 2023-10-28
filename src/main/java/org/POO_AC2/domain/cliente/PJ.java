public class PJ extends Cliente {
    private String cnpj;
    private String razaoSocial;
    private int prazoMaximo;

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

   @Override
   public String ParaString() {
       return "PJ{" +
               "cnpj='" + cnpj + '\'' +
               ", razaoSocial='" + razaoSocial + '\'' +
               ", prazoMaximo=" + prazoMaximo +
               '}';
   }
}
