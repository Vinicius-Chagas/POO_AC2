package org.POO_AC2.domain.produto;

import java.time.LocalDate;
import java.time.LocalDateTime;

//classe dependente de Produto
public class Pereciveis extends Produto {
    private LocalDate dataValidade;

    public LocalDate getDataValidade(){
        return dataValidade;
    }
    public void setDataValidade(LocalDate data){
        this.dataValidade = data;
    }

    //funções
    public boolean Vencido(){
        boolean vencido;
        if (LocalDateTime.now() > dataValidade)
        {vencido = true;}else vencido=false;
        return vencido;
    }
}
