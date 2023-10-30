package org.POO_AC2;

import org.POO_AC2.dominio.cliente.PF;
import org.POO_AC2.dominio.recursos.Json.Json;
import org.POO_AC2.dominio.cliente.Cliente;
import org.POO_AC2.dominio.cliente.Endereco;
import org.POO_AC2.dominio.cliente.PJ;
import org.POO_AC2.dominio.recursos.relatorios.Relatorios;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Main{

    public static void main(String[] args) throws IOException {
        PJ a = new PJ("jonas",new Endereco(), LocalDate.now().toString(), "21312312", "J&J",12);
        PF b = new PF("jao",new Endereco(),LocalDate.now().toString(),"321321",23);

        ArrayList<Cliente> ba = new ArrayList<>();

        ba.add(a);
        ba.add(b);

        File file = new File("Cliente.json");

        Json.writeAllData(ba,file);

        ba.clear();

        ba.addAll(Json.readAllData(file, Cliente.class));

        ba.get(1).setNome("arroy");

        Json.writeAllData(ba, file);

        ba.clear();

        ba.addAll(Json.readAllData(file, Cliente.class));

        Json.stringfy(ba);
    }
}