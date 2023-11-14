package org.POO_AC2;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.*;

import javax.swing.*;

import org.POO_AC2.dominio.cliente.Cliente;
import org.POO_AC2.dominio.cliente.Endereco;
import org.POO_AC2.dominio.cliente.PF;
import org.POO_AC2.dominio.cliente.PJ;
import org.POO_AC2.dominio.compra.Compra;
import org.POO_AC2.dominio.compra.ItemCompra;
import org.POO_AC2.dominio.produto.Pereciveis;
import org.POO_AC2.dominio.produto.Produto;
import org.POO_AC2.dominio.recursos.Json.Json;
import org.POO_AC2.dominio.recursos.relatorios.Relatorios;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import java.util.concurrent.atomic.AtomicLong;

public class Main {

    static String titulo = "Organizacoes Tabajara";

    public static void main(String[] args) throws IOException {
        boolean continuar = true;
        do {
            String[] opcoes = {
                    "[1] Cadastros de Clientes",
                    "[2] Deletar Cliente pelo CPF ou CNPJ",
                    "[3] Deletar Cliente pelo nome",
                    "[4] Cadastro de produtos",
                    "[5] Efetuacao de uma compra",
                    "[6] Atualizacao da situacao de pagamentos de uma compra",
                    "[7] Relatorios",
                    "[8] sair"
            };
            String mensagem = "Por favor, escolha uma opção:";
            // ImageIcon icone = new ImageIcon("assets\\menu.jfif"); //icone ficou muito
            // grande

            JPanel panel = new JPanel(new GridLayout(opcoes.length, 1));

            ButtonGroup group = new ButtonGroup();

            for (String opcao : opcoes) {
                JRadioButton radioButton = new JRadioButton(opcao);
                panel.add(radioButton);
                group.add(radioButton);
            }

            int resultado = JOptionPane.showConfirmDialog(null, panel, mensagem, JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null);

            if (resultado == JOptionPane.OK_OPTION) {
                boolean opcaoSelecionada = false;
                Enumeration<AbstractButton> elements = group.getElements();
                while (elements.hasMoreElements()) {
                    AbstractButton button = elements.nextElement();

                    if (button.isSelected()) {
                        opcaoSelecionada = true;
                        String buttonText = button.getText();

                        switch (buttonText) {
                            case "[1] Cadastros de Clientes":// cadastro PF || PJ
                                cadastroDoCliente();
                                break;
                            case "[2] Deletar Cliente pelo CPF ou CNPJ":
                                delecaoClientePorChave();
                                break;
                            case "[3] Deletar Cliente pelo nome":
                                delecaoClientePorNome();
                                break;
                            case "[4] Cadastro de produtos":
                                cadastroProduto();
                                break;
                            case "[5] Efetuacao de uma compra":
                                novaCompra();
                                break;
                            case "[6] Atualizacao da situacao de pagamentos de uma compra":
                                atualizarPagamentoCompra();
                                break;
                            case "[7] Relatorios":
                                menuRelatorios();
                                break;
                            case "[8] sair":
                                System.exit(0);
                                break;
                        }
                        break;
                    }
                }
                if (!opcaoSelecionada) {
                    JOptionPane.showMessageDialog(null,
                            "Nenhuma opção selecionada. Se quiser sair, selecione o botão Cancelar.", titulo,
                            JOptionPane.WARNING_MESSAGE, null);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Você escolheu sair, volte sempre!", titulo,
                        JOptionPane.WARNING_MESSAGE, null);
                continuar = false;
            }
        } while (continuar);// tentativa de do while
    }

    public static void cadastroDoCliente() throws IOException {
        File fileCliente = new File("Cliente.json");
        ArrayList<Cliente> arrayCliente = new ArrayList<>();
        if (!fileCliente.createNewFile()) {
            arrayCliente.addAll(Json.readAllData(fileCliente, Cliente.class));
        }

        Long codigo;
        if (!arrayCliente.isEmpty()) {
            codigo = arrayCliente.get(arrayCliente.size() - 1).getId() + 1;
        } else {
            codigo = 1L;
        }

        // opções do tipo de cadastro
        String[] tipoCliente = { "Pessoa Física", "Pessoa Jurídica" };
        // JPanel para melhor exibição e UX.
        JPanel tipoPanel = new JPanel(new GridLayout(tipoCliente.length, 1));
        ButtonGroup tipoGroup = new ButtonGroup();
        // criação dos botões de PF e PJ
        for (String tipo : tipoCliente) {
            JRadioButton tipoRadioButton = new JRadioButton(tipo);
            tipoPanel.add(tipoRadioButton);
            tipoGroup.add(tipoRadioButton);
        }
        // JOptionPane -> Tela de escolha do cliente
        int tipoResultado = JOptionPane.showConfirmDialog(
                null,
                tipoPanel,
                "Por favor, escolha o tipo de cliente:",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null);
        if (tipoResultado == JOptionPane.OK_OPTION) {
            Enumeration<AbstractButton> elements = tipoGroup.getElements();
            int j = 0;
            while (elements.hasMoreElements()) {
                AbstractButton button = elements.nextElement();
                if (button.isSelected()) {
                    switch (j) {
                        case 0:// caso pessoa física
                               // cria o JPanel para exibição dos campos e coleta de dados.
                            JPanel cadastroPFPanel = new JPanel(new GridLayout(10, 2));

                            // campos para dados da pessoa física
                            cadastroPFPanel.add(new JLabel("Nome:"));
                            JTextField nomeField = new JTextField();
                            cadastroPFPanel.add(nomeField);

                            cadastroPFPanel.add(new JLabel("Rua:"));
                            JTextField ruaField = new JTextField();
                            cadastroPFPanel.add(ruaField);

                            cadastroPFPanel.add(new JLabel("Número:"));
                            JTextField numeroField = new JTextField();
                            cadastroPFPanel.add(numeroField);

                            cadastroPFPanel.add(new JLabel("Bairro:"));
                            JTextField bairroField = new JTextField();
                            cadastroPFPanel.add(bairroField);

                            cadastroPFPanel.add(new JLabel("CEP:"));
                            JTextField cepField = new JTextField();
                            cadastroPFPanel.add(cepField);

                            cadastroPFPanel.add(new JLabel("Cidade:"));
                            JTextField cidadeField = new JTextField();
                            cadastroPFPanel.add(cidadeField);

                            cadastroPFPanel.add(new JLabel("Estado:"));
                            JTextField estadoField = new JTextField();
                            cadastroPFPanel.add(estadoField);

                            cadastroPFPanel.add(new JLabel("CPF:"));
                            JTextField cpfField = new JTextField();
                            cadastroPFPanel.add(cpfField);

                            cadastroPFPanel.add(new JLabel("Quantidade Máxima de Parcelas:"));
                            JTextField qntParcelasMaxField = new JTextField();
                            cadastroPFPanel.add(qntParcelasMaxField);
                            // local de exibição com o JOptionPane
                            int cadastroPFResultado = JOptionPane.showConfirmDialog(
                                    null,
                                    cadastroPFPanel,
                                    "Cadastro de Pessoa Física",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null);
                            // se clicar em ok, os dados são coletados
                            if (cadastroPFResultado == JOptionPane.OK_OPTION) {
                                String nome = nomeField.getText();
                                String rua = ruaField.getText();
                                int numero = Integer.parseInt(numeroField.getText());
                                String bairro = bairroField.getText();
                                int cep = Integer.parseInt(cepField.getText());
                                String cidade = cidadeField.getText();
                                String estado = estadoField.getText();
                                Endereco endereco = new Endereco(rua, numero, bairro, cep, cidade, estado);
                                String cpf = cpfField.getText();
                                int qntParcelasMax = Integer.parseInt(qntParcelasMaxField.getText());
                                // com os dados coletados, cria um novo pf, que agora pode ser adicionado no
                                // json
                                PF pf = new PF(nome, endereco, cpf, qntParcelasMax, codigo);

                                arrayCliente.add(pf);
                                Json.writeAllData(arrayCliente, fileCliente);
                            } else {// se não clicou em ok, cadastro é cancelado
                                JOptionPane.showMessageDialog(null, "Cadastro de Pessoa Física cancelado.", titulo,
                                        JOptionPane.WARNING_MESSAGE, null);
                            }
                            break;// fim do cadastro de pessoa física
                        case 1:// começo cadastro pessoa jurídica
                               // cria o JPanel para exibição dos campos e coleta de dados.
                            JPanel cadastroPJPanel = new JPanel(new GridLayout(11, 2));

                            // campos para dados da pessoa física
                            cadastroPJPanel.add(new JLabel("Nome:"));
                            JTextField nomeEmpresaField = new JTextField();
                            cadastroPJPanel.add(nomeEmpresaField);

                            cadastroPJPanel.add(new JLabel("Rua:"));
                            JTextField ruaEmpresaField = new JTextField();
                            cadastroPJPanel.add(ruaEmpresaField);

                            cadastroPJPanel.add(new JLabel("Número:"));
                            JTextField numeroEmpresaField = new JTextField();
                            cadastroPJPanel.add(numeroEmpresaField);

                            cadastroPJPanel.add(new JLabel("Bairro:"));
                            JTextField bairroEmpresaField = new JTextField();
                            cadastroPJPanel.add(bairroEmpresaField);

                            cadastroPJPanel.add(new JLabel("CEP:"));
                            JTextField cepEmpresaField = new JTextField();
                            cadastroPJPanel.add(cepEmpresaField);

                            cadastroPJPanel.add(new JLabel("Cidade:"));
                            JTextField cidadeEmpresaField = new JTextField();
                            cadastroPJPanel.add(cidadeEmpresaField);

                            cadastroPJPanel.add(new JLabel("Estado:"));
                            JTextField estadoEmpresaField = new JTextField();
                            cadastroPJPanel.add(estadoEmpresaField);

                            cadastroPJPanel.add(new JLabel("CNPJ:"));
                            JTextField cpfEmpresaField = new JTextField();
                            cadastroPJPanel.add(cpfEmpresaField);
                            cadastroPJPanel.add(new JLabel("Razão Social:"));
                            JTextField razaoSocialField = new JTextField();
                            cadastroPJPanel.add(razaoSocialField);

                            cadastroPJPanel.add(new JLabel("Quantidade Máxima de Parcelas:"));
                            JTextField qntParcelasMaxEmpresaField = new JTextField();
                            cadastroPJPanel.add(qntParcelasMaxEmpresaField);
                            // local de exibição com o JOptionPane
                            int cadastroPJResultado = JOptionPane.showConfirmDialog(
                                    null,
                                    cadastroPJPanel,
                                    "Cadastro de Pessoa Jurídica",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null);
                            // se clicar em ok, os dados são coletados
                            if (cadastroPJResultado == JOptionPane.OK_OPTION) {
                                String nome = nomeEmpresaField.getText();
                                String rua = ruaEmpresaField.getText();
                                int numero = Integer.parseInt(numeroEmpresaField.getText());
                                String bairro = bairroEmpresaField.getText();
                                int cep = Integer.parseInt(cepEmpresaField.getText());
                                String cidade = cidadeEmpresaField.getText();
                                String estado = estadoEmpresaField.getText();
                                Endereco endereco = new Endereco(rua, numero, bairro, cep, cidade, estado);
                                String cnpj = cpfEmpresaField.getText();
                                String razaoSocial = razaoSocialField.getText();
                                int qntParcelasMax = Integer.parseInt(qntParcelasMaxEmpresaField.getText());

                                // com os dados coletados, cria um novo pf, que agora pode ser adicionado no
                                // json
                                PJ pj = new PJ(nome, endereco, cnpj, razaoSocial, qntParcelasMax, codigo);

                                arrayCliente.add(pj);
                                Json.writeAllData(arrayCliente, fileCliente);
                            } else {// se não clicou em ok, cadastro é cancelado
                                JOptionPane.showMessageDialog(null, "Cadastro de Pessoa Jurídica cancelado.", titulo,
                                        JOptionPane.WARNING_MESSAGE, null);
                            }
                    }
                    break;
                }
                j++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum tipo de cliente selecionado.", titulo,
                    JOptionPane.WARNING_MESSAGE, null);
        }
    }

    public static void delecaoClientePorChave() throws IOException {
        File fileCliente = new File("Cliente.json");
        ArrayList<Cliente> arrayCliente = new ArrayList<>();
        if (!fileCliente.createNewFile()) {
            arrayCliente.addAll(Json.readAllData(fileCliente, Cliente.class));
        }

        // opções do tipo de cadastro
        String[] tipoCliente = { "Pessoa Física", "Pessoa Jurídica" };
        // JPanel para melhor exibição e UX.
        JPanel tipoPanel = new JPanel(new GridLayout(tipoCliente.length, 1));
        ButtonGroup tipoGroup = new ButtonGroup();
        // criação dos botões de PF e PJ
        for (String tipo : tipoCliente) {
            JRadioButton tipoRadioButton = new JRadioButton(tipo);
            tipoPanel.add(tipoRadioButton);
            tipoGroup.add(tipoRadioButton);
        }
        // JOptionPane -> Tela de escolha do cliente
        int tipoResultado = JOptionPane.showConfirmDialog(
                null,
                tipoPanel,
                "Por favor, escolha o tipo de cliente:",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null);
        if (tipoResultado == JOptionPane.OK_OPTION) {
            Enumeration<AbstractButton> elements = tipoGroup.getElements();
            int j = 0;
            while (elements.hasMoreElements()) {
                AbstractButton button = elements.nextElement();
                if (button.isSelected()) {
                    switch (j) {
                        case 0:// caso pessoa física
                               // cria o JPanel para exibição dos campos e coleta de dados.
                            JPanel exclusaoPFPanel = new JPanel(new GridLayout(3, 2));

                            // campos para dados da pessoa física
                            exclusaoPFPanel.add(new JLabel("CPF:"));
                            JTextField cpfField = new JTextField();
                            exclusaoPFPanel.add(cpfField);

                            // local de exibição com o JOptionPane
                            int exclusaoPFResultado = JOptionPane.showConfirmDialog(
                                    null,
                                    exclusaoPFPanel,
                                    "Cadastro de Pessoa Física",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null);
                            // se clicar em ok, os dados são coletados
                            if (exclusaoPFResultado == JOptionPane.OK_OPTION) {
                                String cpf = cpfField.getText();

                                Iterator<Cliente> iterator = arrayCliente.iterator();
                                while (iterator.hasNext()) {
                                    Object obj = iterator.next();
                                    if (obj instanceof PF pf) {
                                        if (pf.getCpf().compareTo(cpf) == 0) {
                                            iterator.remove();
                                            Json.writeAllData(arrayCliente, fileCliente);
                                            JOptionPane.showMessageDialog(null,
                                                    "Exclusão de pessoa física efetuada com sucesso.", titulo,
                                                    JOptionPane.WARNING_MESSAGE, null);
                                        } else {
                                            JOptionPane.showMessageDialog(null,
                                                    "Nenhum cliente com este CPF encontrado, favor verificar se os dados foram digitados corretamente.",
                                                    titulo,
                                                    JOptionPane.WARNING_MESSAGE, null);
                                            delecaoClientePorChave();
                                        }
                                    }
                                }

                            } else {// se não clicou em ok, cadastro é cancelado
                                JOptionPane.showMessageDialog(null, "Exclusão de pessoa física cancelada.", titulo,
                                        JOptionPane.WARNING_MESSAGE, null);
                            }
                            break;// fim do cadastro de pessoa física
                        case 1:// começo cadastro pessoa jurídica
                               // cria o JPanel para exibição dos campos e coleta de dados.
                            JPanel exclusaoPJPanel = new JPanel(new GridLayout(10, 2));

                            // campos para dados da pessoa física
                            exclusaoPJPanel.add(new JLabel("CNPJ:"));
                            JTextField cnpjField = new JTextField();
                            exclusaoPJPanel.add(cnpjField);

                            // local de exibição com o JOptionPane
                            int exclusaoPJResultado = JOptionPane.showConfirmDialog(
                                    null,
                                    exclusaoPJPanel,
                                    "Cadastro de Pessoa Física",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null);
                            // se clicar em ok, os dados são coletados
                            if (exclusaoPJResultado == JOptionPane.OK_OPTION) {
                                String cnpj = cnpjField.getText();
                                Iterator<Cliente> iterator = arrayCliente.iterator();
                                while (iterator.hasNext()) {
                                    Object obj = iterator.next();
                                    if (obj instanceof PJ pj) {
                                        if (pj.getCnpj().compareTo(cnpj) == 0) {
                                            iterator.remove();
                                            Json.writeAllData(arrayCliente, fileCliente);
                                            JOptionPane.showMessageDialog(null,
                                                    "Exclusão de pessoa jurídica efetuada com sucesso.", titulo,
                                                    JOptionPane.WARNING_MESSAGE, null);
                                        } else {
                                            JOptionPane.showMessageDialog(null,
                                                    "Nenhum cliente com este CNPJ encontrado, favor verificar se os dados foram digitados corretamente.",
                                                    titulo,
                                                    JOptionPane.WARNING_MESSAGE, null);
                                            delecaoClientePorChave();
                                        }
                                    }
                                }

                            } else {// se não clicou em ok, cadastro é cancelado
                                JOptionPane.showMessageDialog(null, "Exclusão de pessoa juridica cancelada.", titulo,
                                        JOptionPane.WARNING_MESSAGE, null);
                            }
                    }
                    break;
                }
                j++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum tipo de cliente selecionado.", titulo,
                    JOptionPane.WARNING_MESSAGE, null);
        }

    }

    public static void delecaoClientePorNome() throws IOException {
        File fileCliente = new File("Cliente.json");
        ArrayList<Cliente> arrayCliente = new ArrayList<>();
        if (!fileCliente.createNewFile()) {
            arrayCliente.addAll(Json.readAllData(fileCliente, Cliente.class));
        }

        // cria o JPanel para exibição dos campos e coleta de dados.
        JPanel exclusaoClientePanel = new JPanel(new GridLayout(3, 2));

        // campos para dados do cliente
        exclusaoClientePanel.add(new JLabel("Nome:"));
        JTextField nomeField = new JTextField();
        exclusaoClientePanel.add(nomeField);

        // local de exibição com o JOptionPane
        int exclusaoClienteResultado = JOptionPane.showConfirmDialog(
                null,
                exclusaoClientePanel,
                "Cadastro de Pessoa Física",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null);
        // se clicar em ok, os dados são coletados
        if (exclusaoClienteResultado == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();

            arrayCliente.removeIf(cliente -> cliente.getNome().compareTo(nome) == 0);

            Json.writeAllData(arrayCliente, fileCliente);

        } else {// se não clicou em ok, é cancelado
            JOptionPane.showMessageDialog(null, "Exclusão de cliente cancelada.", titulo,
                    JOptionPane.WARNING_MESSAGE, null);
        }

    }

    public static void cadastroProduto() throws IOException {
        File fileProduto = new File("Produto.json");
        ArrayList<Produto> arrayProduto = new ArrayList<>();
        Long codigo;
        if (!fileProduto.createNewFile() && fileProduto.length() > 0) {
            arrayProduto.addAll(Json.readAllData(fileProduto, Produto.class));
        }

        if (!arrayProduto.isEmpty()) {
            codigo = arrayProduto.get(arrayProduto.size() - 1).getCodigo() + 1;
        } else {
            codigo = 1L;
        }

        // opções do tipo de cadastro
        String[] tipoCliente = { "Produto comum", "Produto perecivel" };
        // JPanel para melhor exibição e UX.
        JPanel tipoPanel = new JPanel(new GridLayout(tipoCliente.length, 1));
        ButtonGroup tipoGroup = new ButtonGroup();
        // criação dos botões de PF e PJ
        for (String tipo : tipoCliente) {
            JRadioButton tipoRadioButton = new JRadioButton(tipo);
            tipoPanel.add(tipoRadioButton);
            tipoGroup.add(tipoRadioButton);
        }
        // JOptionPane -> Tela de escolha do cliente
        int tipoResultado = JOptionPane.showConfirmDialog(
                null,
                tipoPanel,
                "Por favor, escolha o tipo de produto:",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null);
        if (tipoResultado == JOptionPane.OK_OPTION) {
            Enumeration<AbstractButton> elements = tipoGroup.getElements();
            int j = 0;
            while (elements.hasMoreElements()) {
                AbstractButton button = elements.nextElement();
                if (button.isSelected()) {
                    switch (j) {
                        case 0:// caso Produto comum
                               // cria o JPanel para exibição dos campos e coleta de dados.
                            JPanel cadastroProdutoPanel = new JPanel(new GridLayout(10, 2));

                            // campos para dados da pessoa física
                            cadastroProdutoPanel.add(new JLabel("Codigo:"));
                            JTextField codigoField = new JTextField();
                            codigoField.setText(codigo.toString());
                            codigoField.setEnabled(false);
                            codigoField.setDisabledTextColor(Color.BLACK);
                            cadastroProdutoPanel.add(codigoField);

                            cadastroProdutoPanel.add(new JLabel("Nome:"));
                            JTextField nomeField = new JTextField();
                            cadastroProdutoPanel.add(nomeField);

                            cadastroProdutoPanel.add(new JLabel("Descrição:"));
                            JTextField descricaoField = new JTextField();
                            cadastroProdutoPanel.add(descricaoField);

                            cadastroProdutoPanel.add(new JLabel("Preço:"));
                            JTextField precoField = new JTextField();
                            cadastroProdutoPanel.add(precoField);

                            // local de exibição com o JOptionPane
                            int cadastroProdutoResultado = JOptionPane.showConfirmDialog(
                                    null,
                                    cadastroProdutoPanel,
                                    "Cadastro de Produto",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null);
                            // se clicar em ok, os dados são coletados
                            if (cadastroProdutoResultado == JOptionPane.OK_OPTION) {
                                String nome = nomeField.getText();
                                String descricao = descricaoField.getText();
                                double preco = Double.parseDouble(precoField.getText().replace(",", "."));

                                // com os dados coletados, cria um novo produto, que agora pode ser adicionado
                                // no
                                // json
                                Produto prod = new Produto(codigo, nome, descricao, preco);

                                arrayProduto.add(prod);
                                Json.writeAllData(arrayProduto, fileProduto);
                            } else {// se não clicou em ok, cadastro é cancelado
                                JOptionPane.showMessageDialog(null, "Cadastro de Produto cancelado.", titulo,
                                        JOptionPane.WARNING_MESSAGE, null);
                            }
                            break;// fim do cadastro de produto
                        case 1:/// caso Produto perecivel
                               // cria o JPanel para exibição dos campos e coleta de dados.
                            JPanel cadastroProdutoPerecivelPanel = new JPanel(new GridLayout(11, 2));

                            // campos para dados da pessoa física
                            cadastroProdutoPerecivelPanel.add(new JLabel("Codigo:"));
                            JTextField codigoPerecivelField = new JTextField();
                            codigoPerecivelField.setText(codigo.toString());
                            cadastroProdutoPerecivelPanel.add(codigoPerecivelField);

                            cadastroProdutoPerecivelPanel.add(new JLabel("Nome:"));
                            JTextField nomePerecivelField = new JTextField();
                            cadastroProdutoPerecivelPanel.add(nomePerecivelField);

                            cadastroProdutoPerecivelPanel.add(new JLabel("Descrição:"));
                            JTextField descricaoPerecivelField = new JTextField();
                            cadastroProdutoPerecivelPanel.add(descricaoPerecivelField);

                            cadastroProdutoPerecivelPanel.add(new JLabel("Preço:"));
                            JTextField precoPerecivelField = new JTextField();
                            cadastroProdutoPerecivelPanel.add(precoPerecivelField);

                            cadastroProdutoPerecivelPanel.add(new JLabel("Data de validade:"));
                            JTextField validadePerecivelField = new JTextField();
                            cadastroProdutoPerecivelPanel.add(validadePerecivelField);

                            // local de exibição com o JOptionPane
                            int cadastroProdutoPerecivelResultado = JOptionPane.showConfirmDialog(
                                    null,
                                    cadastroProdutoPerecivelPanel,
                                    "Cadastro de produto perecivel",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null);
                            // se clicar em ok, os dados são coletados
                            if (cadastroProdutoPerecivelResultado == JOptionPane.OK_OPTION) {
                                String nome = nomePerecivelField.getText();
                                String descricao = descricaoPerecivelField.getText();
                                double preco = Double.parseDouble(precoPerecivelField.getText().replace(",", "."));
                                String validade = validadePerecivelField.getText();

                                // com os dados coletados, cria um novo produto, que agora pode ser adicionado
                                // no
                                // json
                                Produto prod = new Pereciveis(codigo, nome, descricao, preco, validade);

                                arrayProduto.add(prod);
                                Json.writeAllData(arrayProduto, fileProduto);
                            } else {// se não clicou em ok, cadastro é cancelado
                                JOptionPane.showMessageDialog(null, "Cadastro de Produto perecivel cancelado.", titulo,
                                        JOptionPane.WARNING_MESSAGE, null);
                            }
                            break;// fim do cadastro de produto
                    }
                    break;
                }
                j++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum tipo de cliente selecionado.", titulo,
                    JOptionPane.WARNING_MESSAGE, null);
        }
    }

    public static void novaCompra() throws IOException {

        File fileCompra = new File("Compras.json");
        ArrayList<Compra> arrayCompra = new ArrayList<>();
        File fileCliente = new File("Cliente.json");
        ArrayList<Cliente> arrayCliente = new ArrayList<>();
        File fileProduto = new File("Produto.json");
        ArrayList<Produto> arrayProduto = new ArrayList<>();

        if (!fileCompra.createNewFile() && fileCompra.length() > 0) {
            arrayCompra.addAll(Json.readAllData(fileCompra, Compra.class));
        }
        if (!fileCliente.createNewFile() && fileCliente.length() > 0) {
            arrayCliente.addAll(Json.readAllData(fileCliente, Cliente.class));
        }
        if (!fileProduto.createNewFile() && fileProduto.length() > 0) {
            arrayProduto.addAll(Json.readAllData(fileProduto, Produto.class));
        }

        long codigo;

        if (!arrayCompra.isEmpty()) {
            codigo = arrayCompra.get(arrayCompra.size() - 1).getId() + 1;
        } else {
            codigo = 1L;
        }

        JPanel cadastroDeCompra = new JPanel(new GridLayout(7, 2));

        cadastroDeCompra.add(new JLabel("Compra número:"));
        JTextField idField = new JTextField(Long.toString(codigo));
        idField.setEnabled(false);
        idField.setDisabledTextColor(Color.BLACK);
        cadastroDeCompra.add(idField);

        cadastroDeCompra.add(new JLabel("Data da compra:"));
        JTextField dataField = new JTextField(LocalDateTime.now().toString());
        dataField.setEnabled(false);
        dataField.setDisabledTextColor(Color.BLACK);
        cadastroDeCompra.add(dataField);

        cadastroDeCompra.add(new JLabel("Cliente:"));
        String[] arrayStringClientes = arrayCliente.stream().map(c -> c.getId().toString() + " - " + c.getNome())
                .toArray(String[]::new);
        JComboBox<String> clienteCombobox = new JComboBox<>(arrayStringClientes);
        cadastroDeCompra.add(clienteCombobox);

        cadastroDeCompra.add(new JLabel("Itens da compra:"));
        JButton selectItemsButton = new JButton("Selecionar Itens");
        cadastroDeCompra.add(selectItemsButton);

        ArrayList<ItemCompra> arrayItemsCompra = new ArrayList<>();
        cadastroDeCompra.add(new JLabel("Itens selecionados:"));
        JTextArea selectedItemsTextArea = new JTextArea();
        int initialTextAreaHeight = selectedItemsTextArea.getPreferredSize().height;
        selectedItemsTextArea.setPreferredSize(
                new Dimension(selectedItemsTextArea.getPreferredSize().width, initialTextAreaHeight * 3));
        selectedItemsTextArea.setEditable(false);
        cadastroDeCompra.add(new JScrollPane(selectedItemsTextArea));

        selectItemsButton.addActionListener(e -> {
            try {
                ItemCompra selectedItem = selectItemsAndQuantity(arrayProduto, cadastroDeCompra);
                arrayItemsCompra.add(selectedItem);

                // Update the JTextArea to display the selected items
                selectedItemsTextArea
                        .append("Cód.: " + selectedItem.codigoProduto + " | Quantidade: " + selectedItem.qtde + "\n");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        cadastroDeCompra.add(new JLabel("Parcelas totais:"));
        JTextField parcelasField = new JTextField();
        cadastroDeCompra.add(parcelasField);

        // Dá exception quando não digita quantidade e clicka em OK, mas não encerra o
        // app
        int SelecaoDeItensResultado = JOptionPane.showConfirmDialog(
                null,
                cadastroDeCompra,
                "Seleção de itens",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null);

        String[] s = arrayStringClientes[clienteCombobox.getSelectedIndex()].split("-");
        Long idCliente = Long.parseLong(s[0].trim());
        String dataCompra = dataField.getText();

        // Tem que fazer verificação de se o número de parcelas digitado não é maior que
        // o número de parcelas liberado para o cliente
        Compra c = new Compra(dataCompra, codigo, idCliente, arrayItemsCompra,
                Integer.parseInt(parcelasField.getText()));
        arrayCompra.add(c);
        Json.writeAllData(arrayCompra, fileCompra);
        JOptionPane.showMessageDialog(null,
                "Compra efetuada com sucesso.\nCódigo da compra para futuras atualizações: " + codigo,
                "Compra Efetuada",
                JOptionPane.INFORMATION_MESSAGE);

    }

    private static ItemCompra selectItemsAndQuantity(ArrayList<Produto> produtos, JComponent parentComponent)
            throws IOException {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(parentComponent), "Seleção de Produtos",
                Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLayout(new BorderLayout());

        AtomicReference<Long> id = null;

        String[] arrayStringProdutos = produtos.stream().map(c -> c.getCodigo() + " - " + c.getNomeProduto())
                .toArray(String[]::new);
        JList<String> itensList = new JList<>(arrayStringProdutos);
        itensList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane(itensList);
        dialog.add(listScroller, BorderLayout.CENTER);

        JPanel quantityPanel = new JPanel(new GridLayout(2, 2));
        quantityPanel.add(new JLabel("Quantidade:"));
        JTextField quantityField = new JTextField();
        quantityPanel.add(quantityField);

        JButton confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(confirmEvent -> {
            int[] selectedIndices = itensList.getSelectedIndices();
            for (int index : selectedIndices) {
                Produto selectedProduto = produtos.get(index);
                int quantity = Integer.parseInt(quantityField.getText());
                JOptionPane.showMessageDialog(null, "Produto selecionado: " + selectedProduto.getNomeProduto() +
                        "\nQuantidade: " + quantity, "Seleção de Produtos", JOptionPane.INFORMATION_MESSAGE);
            }
            dialog.dispose();
        });
        quantityPanel.add(confirmButton);

        dialog.add(quantityPanel, BorderLayout.SOUTH);

        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(parentComponent);
        dialog.setVisible(true);
        String[] s = itensList.getSelectedValue().split("-");

        return new ItemCompra(Integer.parseInt(quantityField.getText()), Long.parseLong(s[0].trim()));
    }

    public static void listarCompras(ArrayList<Compra> compras) {
        // Sort the compras in reverse order based on id
        Collections.sort(compras, (c1, c2) -> Long.compare(c2.getId(), c1.getId()));

        // Create a 2D array to hold data for the table
        Object[][] data = new Object[compras.size()][2];

        // Populate the data array with compra information
        for (int i = 0; i < compras.size(); i++) {
            Compra compra = compras.get(i);
            data[i][0] = compra.getId();
            data[i][1] = compra.getCliente().getNome(); // Adjust this based on your Compra structure
        }

        // Define column names
        String[] columnNames = { "ID", "Buyer" };

        // Create a JTable with the data and column names
        JTable table = new JTable(data, columnNames);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Put the table in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);

        // Show the table in a JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Compras", JOptionPane.PLAIN_MESSAGE);
    }

    public static void atualizarPagamentoCompra() throws IOException {
        File fileCompra = new File("Compras.json");
        ArrayList<Compra> arrayCompra = new ArrayList<>();

        if (!fileCompra.createNewFile() && fileCompra.length() > 0) {
            arrayCompra.addAll(Json.readAllData(fileCompra, Compra.class));
        }

        AtomicLong codigoCompra = new AtomicLong(); // Usa AtomicLong para mutabilidade

        while (true) {
            JPanel pagamentoPanel = new JPanel(new GridLayout(4, 2));

            // Campos para dados da pessoa física
            pagamentoPanel.add(new JLabel("Deseja ver as compras?"));

            JButton listarComprasButton = new JButton("Listar Compras");
            pagamentoPanel.add(listarComprasButton);

            pagamentoPanel.add(new JLabel("Código da compra:"));
            JTextField codigoField = new JTextField();
            if (codigoCompra.get() != 0) {
                codigoField.setText(Long.toString(codigoCompra.get())); // Define o valor anterior de codigoCompra se
                                                                        // não for 0
            }
            pagamentoPanel.add(codigoField);

            pagamentoPanel.add(new JLabel("Número de parcelas pagas:"));
            JTextField parcelasPagasField = new JTextField();
            pagamentoPanel.add(parcelasPagasField);

            // Adiciona um listener para o botão de listar compras
            listarComprasButton.addActionListener(e -> listarCompras(arrayCompra));

            // Local de exibição com o JOptionPane
            int resultado = JOptionPane.showConfirmDialog(
                    null,
                    pagamentoPanel,
                    "Pagamento de parcela",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null);

            // Se clicar em OK, os dados são coletados
            if (resultado == JOptionPane.OK_OPTION) {
                try {
                    // Obtém valores do formulário
                    long codigoCompraInput = Long.parseLong(codigoField.getText());
                    int parcelasPagasInput = Integer.parseInt(parcelasPagasField.getText());

                    // Validação se o número de parcelas pagas é menor ou igual ao número existente
                    Compra compraSelecionada = null;
                    for (Compra compra : arrayCompra) {
                        if (compra.getId() == codigoCompraInput) {
                            compraSelecionada = compra;
                            break;
                        }
                    }

                    if (compraSelecionada != null) {
                        int parcelasTotais = compraSelecionada.getParcelasTotais();
                        int parcelasPagasExistente = compraSelecionada.getParcelasPagas();

                        if (parcelasPagasInput <= parcelasPagasExistente) {
                            // Mensagem de aviso sobre parcelas pagas menor ou igual
                            int confirmacao = JOptionPane.showConfirmDialog(
                                    null,
                                    "O número de parcelas pagas é menor ou igual ao número existente de parcelas. Realmente deseja realizar essa operação?",
                                    "Aviso",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.WARNING_MESSAGE);

                            if (confirmacao == JOptionPane.OK_OPTION) {
                                // Continua com a operação
                                compraSelecionada.setParcelasPagas(parcelasPagasInput);
                                Json.writeAllData(arrayCompra, fileCompra);
                                JOptionPane.showMessageDialog(null,
                                        "Operação realizada com sucesso!",
                                        "Sucesso",
                                        JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                        } else if (parcelasPagasInput > parcelasTotais) {
                            // Mensagem de aviso sobre número de parcelas pagas maior que o total
                            JOptionPane.showMessageDialog(null,
                                    "Número de parcelas pagas maior do que número de parcelas totais (número de parcelas totais: "
                                            + parcelasTotais + ").\n\n Abortando operação.",
                                    "Aviso",
                                    JOptionPane.WARNING_MESSAGE);
                        } else {
                            // Operação normal
                            compraSelecionada.setParcelasPagas(parcelasPagasInput);
                            Json.writeAllData(arrayCompra, fileCompra);
                            JOptionPane.showMessageDialog(null,
                                    "Operação realizada com sucesso!",
                                    "Sucesso",
                                    JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                    } else {
                        // Mensagem de erro se a compra não for encontrada
                        JOptionPane.showMessageDialog(null,
                                "Compra não encontrada. Verifique o código da compra.",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    // Trata exceção de formato inválido
                    JOptionPane.showMessageDialog(null,
                            "Por favor, insira valores válidos para o código da compra e número de parcelas pagas.",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // O usuário cancelou a operação
                JOptionPane.showMessageDialog(null,
                        "Operação de atualização de parcelas cancelada",
                        "Cancelado",
                        JOptionPane.INFORMATION_MESSAGE);
                break; // Sai do loop
            }
        }
    }

    public static void menuRelatorios() {
        boolean continuar = true;
        do {
            String[] opcoes = {
                    "[a] Relação de todos os Clientes que possuem o nome iniciado por uma determinada sequência de caracteres;",
                    "[b] Relação de todos os Produtos;",
                    "[c] Busca de um produto pelo nome;",
                    "[d] Relação de produtos que são perecíveis e que estão com a data de validade vencida;",
                    "[e] Relação de todas as compras;",
                    "[f] Busca de uma compra pelo número;",
                    "[g] Relação de todas as compras que não foram pagas ainda;",
                    "[h] Relação das 10 últimas compras pagas;",
                    "[i] Apresentação das informações da compra mais cara;",
                    "[j] Apresentação das informações da compra mais barata;",
                    "[k] Relação do valor total de compras feitas em cada mês nos últimos 12 meses.",
                    "[0] Voltar para o menu principal"
            };
            String mensagem = "Por favor, escolha uma opção:";
            JPanel panel = new JPanel(new GridLayout(opcoes.length, 1));
            ButtonGroup group = new ButtonGroup();

            for (String opcao : opcoes) {
                JRadioButton radioButton = new JRadioButton(opcao);
                panel.add(radioButton);
                group.add(radioButton);
            }

            int resultado = JOptionPane.showConfirmDialog(null, panel, mensagem, JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE, null);

            if (resultado == JOptionPane.OK_OPTION) {
                boolean opcaoSelecionada = false;
                Enumeration<AbstractButton> elements = group.getElements();
                while (elements.hasMoreElements()) {
                    AbstractButton button = elements.nextElement();

                    if (button.isSelected()) {
                        opcaoSelecionada = true;
                        String buttonText = button.getText();

                        switch (buttonText.charAt(1)) {
                            case 'a':
                                // Relação de todos os Clientes que possuem o nome iniciado por uma determinada sequência de caracteres;
                                String nomeCliente = JOptionPane.showInputDialog("Digite a sequência de caracteres:");
                                new Relatorios().procurarClientePorNome(nomeCliente);
                                break;

                            case 'b':
                                // Relação de todos os Produtos;
                                new Relatorios().relacaoTodosProdutos();
                                break;

                            case 'c':
                                // Busca de um produto pelo nome;
                                String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto:");
                                new Relatorios().buscaProdutoNome(nomeProduto);
                                break;

                            case 'd':
                                // Relação de produtos que são perecíveis e que estão com a data de validade
                                // vencida;
                                new Relatorios().buscaVencidos();
                                break;

                            case 'e':
                                // Relação de todas as compras;
                                new Relatorios().relacaoTodasCompras();
                                break;

                            case 'f':
                                // Busca de uma compra pelo número;
                                Long numeroCompra = Long
                                        .parseLong(JOptionPane.showInputDialog("Digite o número da compra:"));
                                new Relatorios().buscaCompraNumero(numeroCompra);
                                break;

                            case 'g':
                                // Relação de todas as compras que não foram pagas ainda;
                                new Relatorios().relacaoPendencias();
                                break;

                            case 'h':
                                // Relação das 10 últimas compras pagas;
                                new Relatorios().relacaoDezUltimas();
                                break;

                            case 'i':
                                // Apresentação das informações da compra mais cara;
                                new Relatorios().compraMaisCara();
                                break;

                            case 'j':
                                // Apresentação das informações da compra mais barata;
                                new Relatorios().compraMaisBarata();
                                break;

                            case 'k':
                                // Relação do valor total de compras feitas em cada mês nos últimos 12 meses.
                                new Relatorios().relacaoMensalDoze();
                                break;
                            case '0':
                                // Voltar para o menu principal
                                return;
                        }
                        break;
                    }
                }
                if (!opcaoSelecionada) {
                    JOptionPane.showMessageDialog(null,
                            "Nenhuma opção selecionada. Se quiser sair, selecione o botão Cancelar.",
                            titulo, JOptionPane.WARNING_MESSAGE, null);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Você escolheu retornar ao menu principal.", titulo,
                        JOptionPane.WARNING_MESSAGE, null);
                continuar = false;
            }
        } while (continuar);
    }

}