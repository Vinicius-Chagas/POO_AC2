package org.POO_AC2.dominio.recursos.relatorios;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.POO_AC2.dominio.cliente.Cliente;
import org.POO_AC2.dominio.cliente.PF;
import org.POO_AC2.dominio.cliente.PJ;
import org.POO_AC2.dominio.recursos.Json.Json;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Relatorios {

    File fileCliente = new File("Cliente.json");

    public void procurarClientePorNome(String nome){
        boolean encontrado = false;
        try {
            ArrayList<Cliente> arrayCliente = new ArrayList<>(Json.readAllData(fileCliente, Cliente.class));

            for (Cliente a: arrayCliente) {
                if(a.getNome().startsWith(nome)){
                    System.out.println(Json.stringfy(a));
                    encontrado = true;
                }
            }
            if(!encontrado){
                System.out.println("Não existem clientes que comecem com '"+nome+"' na base de dados.");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
