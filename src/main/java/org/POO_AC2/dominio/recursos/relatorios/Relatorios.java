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

            // Create a table model to hold the data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("ID");
            tableModel.addColumn("Nome");
            tableModel.addColumn("CEP"); // Only displaying the CEP from Endereco
            tableModel.addColumn("Data de Cadastro");

            // Fill the table model with matching clients
            for (Cliente cliente : arrayCliente) {
                if (cliente.getNome().startsWith(nome)) {
                    // Get the CEP from the Endereco object
                    String cep = cliente.getEndereco() != null ? String.valueOf(cliente.getEndereco().getCep()) : "";
                    tableModel.addRow(
                            new Object[] { cliente.getId(), cliente.getNome(), cep, cliente.getDataCadastro() });
                }
            }

            // Create a table using the table model
            JTable table = new JTable(tableModel);

            // Create a scroll pane to hold the table
            JScrollPane scrollPane = new JScrollPane(table);

            // Show the table in a dialog
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

            // Create a table model to hold the data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Código");
            tableModel.addColumn("Nome do Produto");
            tableModel.addColumn("Descrição");
            tableModel.addColumn("Preço");
            tableModel.addColumn("Data de Validade"); // Adding the Data de Validade column

            // Fill the table model with product data
            for (Produto produto : arrayProduto) {
                String dataValidade = "N.A."; // Default value if Data de Validade doesn't exist

                if (produto instanceof Pereciveis) {
                    // If Pereciveis, get the dataValidade
                    dataValidade = ((Pereciveis) produto).getDataValidade();
                }

                tableModel.addRow(new Object[] { produto.getCodigo(), produto.getNomeProduto(),
                        produto.getDescricao(), produto.getPreco(), dataValidade });
            }

            // Create a table using the table model
            JTable table = new JTable(tableModel);

            // Create a scroll pane to hold the table
            JScrollPane scrollPane = new JScrollPane(table);

            // Show the table in a dialog
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

            // Create a table model to hold the data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Código");
            tableModel.addColumn("Nome do Produto");
            tableModel.addColumn("Descrição");
            tableModel.addColumn("Preço");
            tableModel.addColumn("Data de Validade"); // Adding the Data de Validade column

            // Fill the table model with product data that matches the given name
            for (Produto produto : arrayProduto) {
                if (produto.getNomeProduto().compareTo(nome) == 0) {
                    String dataValidade = "N.A."; // Default value if Data de Validade doesn't exist

                    if (produto instanceof Pereciveis) {
                        // If Pereciveis, get the dataValidade
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
                // Create a table using the table model
                JTable table = new JTable(tableModel);

                // Create a scroll pane to hold the table
                JScrollPane scrollPane = new JScrollPane(table);

                // Show the table in a dialog
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

            // Create a table model to hold the data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Código");
            tableModel.addColumn("Nome do Produto");
            tableModel.addColumn("Descrição");
            tableModel.addColumn("Preço");
            tableModel.addColumn("Data de Validade");
            tableModel.addColumn("Vencido"); // Adding the Vencido column

            // Fill the table model with expired product data
            for (Produto produto : arrayProduto) {
                if (produto instanceof Pereciveis) {
                    // If Pereciveis, get the dataValidade and Vencido status
                    String dataValidade = ((Pereciveis) produto).getDataValidade();
                    boolean vencido = ((Pereciveis) produto).Vencido();

                    tableModel.addRow(new Object[] { produto.getCodigo(), produto.getNomeProduto(),
                            produto.getDescricao(), produto.getPreco(), dataValidade, vencido ? "Sim" : "Não" });
                }
            }

            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não existem produtos perecíveis vencidos.",
                        "Produtos Perecíveis Vencidos Não Encontrados", JOptionPane.WARNING_MESSAGE);
            } else {
                // Create a table using the table model
                JTable table = new JTable(tableModel);

                // Create a scroll pane to hold the table
                JScrollPane scrollPane = new JScrollPane(table);

                // Show the table in a dialog
                JOptionPane.showMessageDialog(null, scrollPane, "Produtos Perecíveis Vencidos",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoTodasCompras() {
        try {
            // Update the file name to "Compras.json"
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class));

            // Create a table model to hold the data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Número da Compra");
            tableModel.addColumn("Data da Compra");
            tableModel.addColumn("Valor Total");
            tableModel.addColumn("Parcelas Pagas");
            tableModel.addColumn("Parcelas Totais");

            // Fill the table model with purchase data
            for (Compra compra : arrayCompras) {
                tableModel.addRow(new Object[] { compra.getId(), compra.getDataCompra(),
                        compra.getValorTotal(), compra.getParcelasPagas(), compra.getParcelasTotais() });
            }

            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não existem compras registradas.",
                        "Compras Não Encontradas", JOptionPane.WARNING_MESSAGE);
            } else {
                // Create a table using the table model
                JTable table = new JTable(tableModel);

                // Create a scroll pane to hold the table
                JScrollPane scrollPane = new JScrollPane(table);

                // Show the table in a dialog
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

            // Search for the purchase with the specified number
            for (Compra compra : arrayCompras) {
                if (Objects.equals(compra.getId(), numero)) {
                    // Create a message to display in the dialog
                    StringBuilder message = new StringBuilder();
                    message.append("Número da Compra: ").append(compra.getId()).append("\n");
                    message.append("Data da Compra: ").append(compra.getDataCompra()).append("\n");
                    message.append("Valor Total: ").append(compra.getValorTotal()).append("\n");
                    message.append("Parcelas Pagas: ").append(compra.getParcelasPagas()).append("\n");
                    message.append("Parcelas Totais: ").append(compra.getParcelasTotais()).append("\n");

                    // Show the purchase details in a dialog
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

            // Create a table model to hold the data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Número da Compra");
            tableModel.addColumn("Data da Compra");
            tableModel.addColumn("Valor Total");
            tableModel.addColumn("Parcelas Pagas");
            tableModel.addColumn("Parcelas Totais");

            // Fill the table model with purchases that have pending payments
            for (Compra compra : arrayCompras) {
                if (compra.getParcelasTotais() > compra.getParcelasPagas()) {
                    tableModel.addRow(new Object[]{compra.getId(), compra.getDataCompra(),
                            compra.getValorTotal(), compra.getParcelasPagas(), compra.getParcelasTotais()});
                }
            }

            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não existem compras com parcelas pendentes.",
                        "Compras sem Pagamento", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Create a table using the table model
                JTable table = new JTable(tableModel);

                // Create a scroll pane to hold the table
                JScrollPane scrollPane = new JScrollPane(table);

                // Show the table in a dialog
                JOptionPane.showMessageDialog(null, scrollPane, "Relação de Compras com Pagamentos Pendentes",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoDezUltimas() {
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class));

            // Sort the array in descending order using the date of the last payment
            arrayCompras.sort((o1, o2) -> LocalDate.parse(o2.getUltimoPagamento())
                    .compareTo(LocalDate.parse(o1.getUltimoPagamento())));
            // Get the first ten items from the array or all items if less than 10
            int numComprasToShow = Math.min(10, arrayCompras.size());
            ArrayList<Compra> ultimasDez = new ArrayList<>(arrayCompras.subList(0, numComprasToShow));

            // Create a table model to hold the data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Número da Compra");
            tableModel.addColumn("Data da Compra");
            tableModel.addColumn("Valor Total");
            tableModel.addColumn("Parcelas Pagas");
            tableModel.addColumn("Parcelas Totais");

            // Fill the table model with the ten most recent purchases
            for (Compra compra : ultimasDez) {
                tableModel.addRow(new Object[]{compra.getId(), compra.getDataCompra(),
                        compra.getValorTotal(), compra.getParcelasPagas(), compra.getParcelasTotais()});
            }

            if (tableModel.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Não existem compras registradas.",
                        "Compras Não Encontradas", JOptionPane.WARNING_MESSAGE);
            } else {
                // Create a table using the table model
                JTable table = new JTable(tableModel);

                // Create a scroll pane to hold the table
                JScrollPane scrollPane = new JScrollPane(table);

                // Show the table in a dialog
                JOptionPane.showMessageDialog(null, scrollPane, "Relação das Últimas Compras",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void compraMaisCara() {
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class)); // Le o
                                                                                                          // arquivo
                                                                                                          // json e
                                                                                                          // armazena em
                                                                                                          // um array
                                                                                                          // list

            // Ordena o array do maior para o menor
            arrayCompras.sort((o1, o2) -> o2.getValorTotal().compareTo(o1.getValorTotal()));
            // Imprime o primeiro item do array
            Json.stringfy(arrayCompras.get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void compraMaisBarata() {
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class)); // Le o
                                                                                                          // arquivo
                                                                                                          // json e
                                                                                                          // armazena em
                                                                                                          // um array
                                                                                                          // list
            // Ordena o array do menor para o maior
            arrayCompras.sort((o1, o2) -> o1.getValorTotal().compareTo(o2.getValorTotal()));
            // Pega o primeiro item do array
            Json.stringfy(arrayCompras.get(0));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void relacaoMensalDoze() {
        try {
            ArrayList<Compra> arrayCompras = new ArrayList<>(Json.readAllData(fileCompra, Compra.class)); // Le o
                                                                                                          // arquivo
                                                                                                          // json e
                                                                                                          // armazena em
                                                                                                          // um array
                                                                                                          // list
            HashMap<Integer, Double> meses = new HashMap<Integer, Double>(); // Cria um hashmap chave <Mês, valor>
            int month = 0;
            for (Compra c : arrayCompras) {
                month = LocalDate.parse(c.getDataCompra()).getMonthValue();
                if (meses.containsKey(LocalDate.parse(c.getDataCompra()).getMonthValue())) { // Verifica se o mes lido
                                                                                             // já existe no hashmap
                    // Soma o valor da compra ao valor do mês correspondente
                    meses.put(month, meses.get(month) + c.getValorTotal());
                } else {
                    meses.get(month); // Adiciona o mês ao hashmap
                    // Soma o valor da compra ao valor do mês correspondente
                    meses.put(month, meses.get(month) + c.getValorTotal());
                }
            }

            Json.stringfy(meses);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
