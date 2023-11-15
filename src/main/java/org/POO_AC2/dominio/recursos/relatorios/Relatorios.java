package org.POO_AC2.dominio.recursos.relatorios;

import org.POO_AC2.dominio.cliente.Cliente;
import org.POO_AC2.dominio.compra.Compra;
import org.POO_AC2.dominio.produto.Pereciveis;
import org.POO_AC2.dominio.produto.Produto;
import org.POO_AC2.dominio.recursos.Json.Json;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Relatorios {

    File fileCliente = new File("Cliente.json");
    File fileProduto = new File("Produto.json");

    File fileCompra = new File("Compras.json");

    // Função que procura um cliente dado o inicio de um nome
    public void procurarClientePorNome(String nome) {
        try {
            ArrayList<Cliente> arrayCliente = new ArrayList<>(Json.readAllData(fileCliente, Cliente.class));

            // Cria a tabela que será exibida
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("ID");
            tableModel.addColumn("Nome");
            tableModel.addColumn("CEP");
            tableModel.addColumn("Data de Cadastro");

            // Coloca os itens na tabela
            for (Cliente cliente : arrayCliente) {
                if (cliente.getNome().startsWith(nome)) {
                    // Valor padrao se nao tiver cep
                    String cep = cliente.getEndereco() != null ? String.valueOf(cliente.getEndereco().getCep()) : "";
                    tableModel.addRow(
                            new Object[] { cliente.getId(), cliente.getNome(), cep, cliente.getDataCadastro() });
                }
            }

            JTable table = new JTable(tableModel);

            JScrollPane scrollPane = new JScrollPane(table);

            JOptionPane.showMessageDialog(null, scrollPane, "Clientes Encontrados",
                    JOptionPane.INFORMATION_MESSAGE);

            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null,
                        "Não existem clientes que comecem com '" + nome + "' na base de dados.",
                        "Nenhum Cliente Encontrado", JOptionPane.WARNING_MESSAGE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoTodosProdutos() {
        try {
            ArrayList<Produto> arrayProduto = new ArrayList<>(Json.readAllData(fileProduto, Produto.class));

            // Cria a tabela que será exibida
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Código");
            tableModel.addColumn("Nome do Produto");
            tableModel.addColumn("Descrição");
            tableModel.addColumn("Preço");
            tableModel.addColumn("Data de Validade");

            // Coloca todos os itens na tabela
            for (Produto produto : arrayProduto) {
                String dataValidade = "N.A."; // Valor padrao se nao for perecivel

                if (produto instanceof Pereciveis) {
                    // Se for perecivel, pega a validade
                    dataValidade = ((Pereciveis) produto).getDataValidade();
                }

                tableModel.addRow(new Object[] { produto.getCodigo(), produto.getNomeProduto(),
                        produto.getDescricao(), produto.getPreco(), dataValidade });
            }

            JTable table = new JTable(tableModel);

            JScrollPane scrollPane = new JScrollPane(table);

            JOptionPane.showMessageDialog(null, scrollPane, "Relação de Todos os Produtos",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscaProdutoNome(String nome) {
        boolean encontrou = false;
        try {
            ArrayList<Produto> arrayProduto = new ArrayList<>(Json.readAllData(fileProduto, Produto.class));

            // Monta a tabela que será exibida
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Código");
            tableModel.addColumn("Nome do Produto");
            tableModel.addColumn("Descrição");
            tableModel.addColumn("Preço");
            tableModel.addColumn("Data de Validade");

            // Procura o produto com o nome correto
            for (Produto produto : arrayProduto) {
                if (produto.getNomeProduto().compareTo(nome) == 0) {
                    String dataValidade = "N.A."; // Valor padrao se nao for perecivel

                    // Se for perecivel, pega a validade
                    if (produto instanceof Pereciveis) {
                        dataValidade = ((Pereciveis) produto).getDataValidade();
                    }

                    tableModel.addRow(new Object[] { produto.getCodigo(), produto.getNomeProduto(),
                            produto.getDescricao(), produto.getPreco(), dataValidade });
                    encontrou = true;
                }
            }

            if (!encontrou) {
                JOptionPane.showMessageDialog(null, "Produto com nome '" + nome + "' não foi encontrado.",
                        "Produto Não Encontrado", JOptionPane.WARNING_MESSAGE);
            } else {
                JTable table = new JTable(tableModel);

                JScrollPane scrollPane = new JScrollPane(table);

                JOptionPane.showMessageDialog(null, scrollPane, "Informações do Produto",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscaVencidos() {
        try {
            ArrayList<Produto> arrayProduto = new ArrayList<>(Json.readAllData(fileProduto, Produto.class));

            // Monta a tabela que será exibida
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Código");
            tableModel.addColumn("Nome do Produto");
            tableModel.addColumn("Descrição");
            tableModel.addColumn("Preço");
            tableModel.addColumn("Data de Validade");
            tableModel.addColumn("Vencido"); // Adding the Vencido column

            // Coloca apenas os vencidos na tabela
            for (Produto produto : arrayProduto) {
                if (produto instanceof Pereciveis) {
                    // Se for perecivel, verifica se está vencido e coloca na tabela
                    if(((Pereciveis) produto).Vencido()){
                        tableModel.addRow(new Object[] { produto.getCodigo(), produto.getNomeProduto(),
                                produto.getDescricao(), produto.getPreco(), ((Pereciveis) produto).getDataValidade(),"Sim"});
                    }

                }
            }

            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não existem produtos perecíveis vencidos.",
                        "Produtos Perecíveis Vencidos Não Encontrados", JOptionPane.WARNING_MESSAGE);
            } else {
                JTable table = new JTable(tableModel);

                JScrollPane scrollPane = new JScrollPane(table);

                JOptionPane.showMessageDialog(null, scrollPane, "Produtos Perecíveis Vencidos",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoTodasCompras() {
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class));

            // Cria a tabela que será exibida
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Número da Compra");
            tableModel.addColumn("Data da Compra");
            tableModel.addColumn("Valor Total");
            tableModel.addColumn("Parcelas Pagas");
            tableModel.addColumn("Parcelas Totais");

            // Coloca os itens na tabela
            for (Compra compra : arrayCompras) {
                tableModel.addRow(new Object[] { compra.getId(), compra.getDataCompra(),
                        compra.getValorTotal(), compra.getParcelasPagas(), compra.getParcelasTotais() });
            }

            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não existem compras registradas.",
                        "Compras Não Encontradas", JOptionPane.WARNING_MESSAGE);
            } else {
                JTable table = new JTable(tableModel);

                JScrollPane scrollPane = new JScrollPane(table);

                JOptionPane.showMessageDialog(null, scrollPane, "Relação de Todas as Compras",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void buscaCompraNumero(Long numero) {
        boolean encontrou = false;
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class));

            // Procura a conta correta
            for (Compra compra : arrayCompras) {
                if (Objects.equals(compra.getId(), numero)) {
                    //Monta a mensagem que será exibida na tela
                    StringBuilder message = new StringBuilder();
                    message.append("Número da Compra: ").append(compra.getId()).append("\n");
                    message.append("Data da Compra: ").append(compra.getDataCompra()).append("\n");
                    message.append("Valor Total: ").append(compra.getValorTotal()).append("\n");
                    message.append("Parcelas Pagas: ").append(compra.getParcelasPagas()).append("\n");
                    message.append("Parcelas Totais: ").append(compra.getParcelasTotais()).append("\n");


                    JOptionPane.showMessageDialog(null, message.toString(), "Detalhes da Compra",
                            JOptionPane.INFORMATION_MESSAGE);

                    encontrou = true;
                    break;
                }
            }

            if (!encontrou) {
                JOptionPane.showMessageDialog(null, "Não foi encontrada nenhuma compra com o número: " + numero,
                        "Compra Não Encontrada", JOptionPane.WARNING_MESSAGE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoPendencias() {
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class));

            // Criando tabela
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Número da Compra");
            tableModel.addColumn("Data da Compra");
            tableModel.addColumn("Valor Total");
            tableModel.addColumn("Parcelas Pagas");
            tableModel.addColumn("Parcelas Totais");

            // Procura os itens que tem pagamentos a ser feitos e coloca na tabela
            for (Compra compra : arrayCompras) {
                if (compra.getParcelasTotais() > compra.getParcelasPagas()) {
                    tableModel.addRow(new Object[]{compra.getId(), compra.getDataCompra(),
                            compra.getValorTotal(), compra.getParcelasPagas(), compra.getParcelasTotais()});
                }
            }

            // Verifica se há algo a ser exibido
            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não existem compras com parcelas pendentes.",
                        "Compras sem Pagamento", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JTable table = new JTable(tableModel);

                JScrollPane scrollPane = new JScrollPane(table);

                JOptionPane.showMessageDialog(null, scrollPane, "Relação de Compras com Pagamentos Pendentes",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoDezUltimas() {
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class)); // Le o
                                                                                                          // arquivo
                                                                                                          // json e
                                                                                                          // armazena em
                                                                                                          // um array
                                                                                                          // list

            // Filtra o array apenas pelos itens que têm uma data de ultimo pagamento
            ArrayList<Compra> ultimasDez = new ArrayList<>(arrayCompras.stream().filter(o1 -> o1.getUltimoPagamento() != null).toList());
            // Ordena o array do maior para o menor uilizando a data do ultimo pagamento
            ultimasDez.sort(Comparator.comparing(o -> LocalDateTime.parse(o.getUltimoPagamento())));


            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Número da Compra");
            tableModel.addColumn("Data da Compra");
            tableModel.addColumn("Valor Total");
            tableModel.addColumn("Parcelas Pagas");
            tableModel.addColumn("Parcelas Totais");

            int i =0;

            // Coloca os itens na tabela
            for (Compra compra : ultimasDez) {

                    tableModel.addRow(new Object[]{compra.getId(), compra.getDataCompra(),
                            compra.getValorTotal(), compra.getParcelasPagas(), compra.getParcelasTotais()});

                i++;
                if(i == 10) break;
            }

            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não existem compras cadastradas.",
                        "Nenhuma compra cadastrada", JOptionPane.INFORMATION_MESSAGE);
            } else {

                JTable table = new JTable(tableModel);

                JScrollPane scrollPane = new JScrollPane(table);

                JOptionPane.showMessageDialog(null, scrollPane, "Ultimas 10 compras pagas",
                        JOptionPane.INFORMATION_MESSAGE);
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void compraMaisCara() {
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class));

            if (!arrayCompras.isEmpty()) {
                // Ordena o array do maior para o menor
                arrayCompras.sort((o1, o2) -> o2.getValorTotal().compareTo(o1.getValorTotal()));
                
                //Monta a mensagem que será exibida na tela
                StringBuilder message = new StringBuilder();
                Compra compraMaisCara = arrayCompras.get(0);
                message.append("Número da Compra: ").append(compraMaisCara.getId()).append("\n");
                message.append("Data da Compra: ").append(compraMaisCara.getDataCompra()).append("\n");
                message.append("Valor Total: ").append(compraMaisCara.getValorTotal()).append("\n");
                message.append("Parcelas Pagas: ").append(compraMaisCara.getParcelasPagas()).append("\n");
                message.append("Parcelas Totais: ").append(compraMaisCara.getParcelasTotais()).append("\n");

                JOptionPane.showMessageDialog(null, message.toString(), "Compra Mais Cara",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Não existem compras registradas.",
                        "Compras Não Encontradas", JOptionPane.WARNING_MESSAGE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void compraMaisBarata() {
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class));

            if (!arrayCompras.isEmpty()) {
                // Ordena o array do menor para o maior
                arrayCompras.sort((o1, o2) -> o1.getValorTotal().compareTo(o2.getValorTotal()));

                //Monta a mensagem que será exibida na tela
                StringBuilder message = new StringBuilder();
                Compra compraMaisBarata = arrayCompras.get(0);
                message.append("Número da Compra: ").append(compraMaisBarata.getId()).append("\n");
                message.append("Data da Compra: ").append(compraMaisBarata.getDataCompra()).append("\n");
                message.append("Valor Total: ").append(compraMaisBarata.getValorTotal()).append("\n");
                message.append("Parcelas Pagas: ").append(compraMaisBarata.getParcelasPagas()).append("\n");
                message.append("Parcelas Totais: ").append(compraMaisBarata.getParcelasTotais()).append("\n");

                JOptionPane.showMessageDialog(null, message.toString(), "Compra Mais Barata",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Não existem compras registradas.",
                        "Compras Não Encontradas", JOptionPane.WARNING_MESSAGE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoMensalDoze() {
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class));

            if (!arrayCompras.isEmpty()) {
                HashMap<String, Double> meses = new HashMap<>(); // Cria um hashmap chave <Mês/Ano, valor>

                //  COloca os ultimos 12 meses na collection
                for(int i = 11; i >= 0; i--){
                    LocalDate date = LocalDate.now();
                    date = date.minusMonths(i);

                    String month = String.format("%02d",date.getMonthValue());
                    int year = date.getYear();
                    StringBuilder monthYear = new StringBuilder().append(month).append("/").append(year);

                    meses.put(monthYear.toString(),0.0);
                }

                // Compara o mes/ano e soma o valor dos correspondentes
                for (Compra c : arrayCompras) {
                    LocalDateTime date = LocalDateTime.parse(c.getDataCompra(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                    String month = String.format("%02d",date.getMonthValue());
                    int year = date.getYear();
                    StringBuilder monthYear = new StringBuilder().append(month).append("/").append(year);

                    if(meses.containsKey(monthYear.toString())){
                        double valorAtual = meses.get(monthYear.toString());
                        meses.put(monthYear.toString(), valorAtual + c.getValorTotal());
                    }
                }

                List<String> ordenar = new ArrayList<>(meses.keySet());
                ordenar.sort(Collections.reverseOrder());

                // Cria uma janela
                StringBuilder message = new StringBuilder();
                message.append("Total de Compras nos Últimos 12 Meses:\n\n");

                for (int i = 0; i <= 11; i++) {
                    message.append(ordenar.get(i) + " : ");
                    message.append(meses.getOrDefault(ordenar.get(i), 0.0)).append("\n");
                }

                // Mostra a janela
                JOptionPane.showMessageDialog(null, message.toString(), "Relação Mensal dos Últimos 12 Meses",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Não existem compras registradas.",
                        "Compras Não Encontradas", JOptionPane.WARNING_MESSAGE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
