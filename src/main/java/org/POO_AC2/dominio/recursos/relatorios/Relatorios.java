package org.POO_AC2.dominio.recursos.relatorios;

import org.POO_AC2.dominio.cliente.Cliente;
import org.POO_AC2.dominio.compra.Compra;
import org.POO_AC2.dominio.produto.Pereciveis;
import org.POO_AC2.dominio.produto.Produto;
import org.POO_AC2.dominio.recursos.Json.Json;


import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Relatorios {

    File fileCliente = new File("Cliente.json");
    File fileProduto = new File("Produto.json");

    File fileCompra = new File("Compra.json");

    //Função que procura um cliente dado o inicio de um nome
    public void procurarClientePorNome(String nome){
        boolean encontrado = false;
        try {
            ArrayList<Cliente> arrayCliente = new ArrayList<>(Json.readAllData(fileCliente, Cliente.class)); // Le o arquivo json e armazena em um array list

            for (Cliente a: arrayCliente) {
                if(a.getNome().startsWith(nome)){ //StartsWith para verificar se o nome do cliente atual começa com o argumento passado
                    Json.stringfy(a);
                    encontrado = true;
                }
            }
            if(!encontrado){ //Se nao encontrar, mostra mensagem não encontrado
                System.out.println("Não existem clientes que comecem com '"+nome+"' na base de dados.");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void relacaoTodosProdutos(){
        try {
            Json.stringfy(Json.readAllData(fileProduto, Produto.class)); //Lê o arquivo de texto e printa tudo na tela
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscaProdutoNome(String nome){
        boolean encontrou = false;
        try {
            ArrayList<Produto> arrayProduto = new ArrayList<>(Json.readAllData(fileProduto, Produto.class)); // Le o arquivo json e armazena em um array list
            for (Produto p: arrayProduto) {
                if(p.getNomeProduto().compareTo(nome) == 0){ // Verifica se o nome do produto atual é o nome passado como argumento
                    Json.stringfy(p);
                    encontrou = true;
                }
            }
            if(!encontrou) System.out.println("Produto com nome " + nome + " não foi encontrado.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscaVencidos(){
        try {
            ArrayList<Produto> arrayProduto = new ArrayList<>(Json.readAllData(fileProduto, Produto.class)); // Le o arquivo json e armazena em um array list
            for (Produto p: arrayProduto) {
                if(p.getClass() == Pereciveis.class){ //Verifica se a classe do produto atual é de um produto perecivel
                    if(((Pereciveis) p).Vencido()){ //Verifica se o produto atual está vencido
                        Json.stringfy(p);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoTodasCompras(){
        try {
            Json.stringfy(Json.readAllData(fileCompra, Compra.class)); //Lê o arquivo json e imprime tudo na tela
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscaCompraNumero(Long numero){
        boolean encontrou = false;
        try {
            ArrayList<Compra> arrayCompra = new ArrayList<>(Json.readAllData(fileCompra, Compra.class)); // Le o arquivo json e armazena em um array list
            for (Compra c: arrayCompra) {
                if(Objects.equals(c.getId(), numero)){ //Verifica se o id da compra é o numero passado como argumento
                    Json.stringfy(c);
                    encontrou = true;
                }
            }
            if(!encontrou) System.out.println("Não foi encontrado nenhuma compra que corresponda ao número: " + numero);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoPendencias(){
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class)); // Le o arquivo json e armazena em um array list
            for(Compra c: arrayCompras){
                if(c.getParcelasTotais() < c.getParcelasPagas()) // Verifica se ainda há parcelas a serem pagas na compra atual
                    Json.stringfy(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoDezUltimas(){
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class)); // Le o arquivo json e armazena em um array list

            //Ordena o array do maior para o menor uilizando a data do ultimo pagamento
            arrayCompras.sort((o1, o2) -> LocalDate.parse(o2.getUltimoPagamento()).compareTo(LocalDate.parse(o1.getUltimoPagamento())));
            // Pega os dez primeiros itens do array
            ArrayList<Compra> ultimasDez = new ArrayList<>(arrayCompras.subList(0, 10));

            Json.stringfy(ultimasDez);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void compraMaisCara(){
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class)); // Le o arquivo json e armazena em um array list

            //Ordena o array do maior para o menor
            arrayCompras.sort((o1, o2) -> o2.getValorTotal().compareTo(o1.getValorTotal()));
            //Imprime o primeiro item do array
            Json.stringfy(arrayCompras.get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void compraMaisBarata(){
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class)); // Le o arquivo json e armazena em um array list
            //Ordena o array do menor para o maior
            arrayCompras.sort((o1, o2) -> o1.getValorTotal().compareTo(o2.getValorTotal()));
            //Pega o primeiro item do array
            Json.stringfy(arrayCompras.get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoMensalDoze(){
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class)); // Le o arquivo json e armazena em um array list
            HashMap<Integer,Double> meses = new HashMap<Integer, Double>(); // Cria um hashmap chave <Mês, valor>

            for (Compra c:arrayCompras){
                if(meses.containsKey(c.getDataCompra().getMonthValue())){ // Verifica se o mes lido já existe no hashmap
                    //Soma o valor da compra ao valor do mês correspondente
                    meses.put(c.getDataCompra().getMonthValue(), meses.get(c.getDataCompra().getMonthValue()) +c.getValorTotal());
                }
                else {
                    meses.get(c.getDataCompra().getMonthValue()); //Adiciona o mês ao hashmap
                    //Soma o valor da compra ao valor do mês correspondente
                    meses.put(c.getDataCompra().getMonthValue(), meses.get(c.getDataCompra().getMonthValue()) +c.getValorTotal());
                }
            }

            Json.stringfy(meses);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
