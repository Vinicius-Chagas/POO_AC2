package org.POO_AC2;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.POO_AC2.dominio.cliente.Cliente;
import org.POO_AC2.dominio.cliente.Endereco;
import org.POO_AC2.dominio.cliente.PF;
import org.POO_AC2.dominio.cliente.PJ;
import org.POO_AC2.dominio.produto.Pereciveis;
import org.POO_AC2.dominio.produto.Produto;
import org.POO_AC2.dominio.recursos.Json.Json;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.GridLayout;
import java.util.Iterator;

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
                                // Código para "Efetuação de uma compra"
                                break;
                            case "[6] Atualizacao da situacao de pagamentos de uma compra":
                                // Código para "Atualização da situação de pagamentos de uma compra"
                                break;
                            case "[7] Relatorios":
                                // Código para "Relatórios"
                                break;
                        }
                        break;
                    }
                }
                if (!opcaoSelecionada) {
                    JOptionPane.showMessageDialog(null, "Nenhuma opção selecionada. Se quiser sair, selecione o botão Cancelar.", titulo,
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
        if(!fileCliente.createNewFile()){
             arrayCliente.addAll(Json.readAllData(fileCliente,Cliente.class));
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
                                PF pf = new PF(nome, endereco, cpf, qntParcelasMax);

                                arrayCliente.add(pf);
                                Json.writeAllData(arrayCliente,fileCliente);
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
                                PJ pj = new PJ(nome, endereco,  cnpj, razaoSocial, qntParcelasMax);

                                arrayCliente.add(pj);
                                Json.writeAllData(arrayCliente,fileCliente);
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
        if(!fileCliente.createNewFile()){
            arrayCliente.addAll(Json.readAllData(fileCliente,Cliente.class));
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
                                while (iterator.hasNext()){
                                    Object obj = iterator.next();
                                    if(obj instanceof PF pf) {
                                        if(pf.getCpf().compareTo(cpf) == 0) {
                                            iterator.remove();
                                             Json.writeAllData(arrayCliente, fileCliente);
                                             JOptionPane.showMessageDialog(null, "Exclusão de pessoa física efetuada com sucesso.", titulo,
                                             JOptionPane.WARNING_MESSAGE, null);
                                        }else{
                                             JOptionPane.showMessageDialog(null, "Nenhum cliente com este CPF encontrado, favor verificar se os dados foram digitados corretamente.", titulo,
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
                                while (iterator.hasNext()){
                                    Object obj = iterator.next();
                                    if(obj instanceof PJ pj) {
                                        if(pj.getCnpj().compareTo(cnpj) == 0) {
                                            iterator.remove();
                                             Json.writeAllData(arrayCliente, fileCliente);
                                        JOptionPane.showMessageDialog(null, "Exclusão de pessoa jurídica efetuada com sucesso.", titulo,
                                        JOptionPane.WARNING_MESSAGE, null);
                                        }else{
                                             JOptionPane.showMessageDialog(null, "Nenhum cliente com este CNPJ encontrado, favor verificar se os dados foram digitados corretamente.", titulo,
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
        if(!fileCliente.createNewFile()){
            arrayCliente.addAll(Json.readAllData(fileCliente,Cliente.class));
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
        if(!fileProduto.createNewFile() && fileProduto.length()>0){
            arrayProduto.addAll(Json.readAllData(fileProduto,Produto.class));
        }

        if(!arrayProduto.isEmpty()) {
            codigo = arrayProduto.get(arrayProduto.size() - 1).getCodigo() + 1;
        }
        else {
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
                                double preco = Double.parseDouble(precoField.getText().replace(",","."));

                                // com os dados coletados, cria um novo produto, que agora pode ser adicionado no
                                // json
                                Produto prod = new Produto(codigo,nome,descricao,preco);

                                arrayProduto.add(prod);
                                Json.writeAllData(arrayProduto,fileProduto);
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
                                double preco = Double.parseDouble(precoPerecivelField.getText().replace(",","."));
                                String validade = validadePerecivelField.getText();

                                // com os dados coletados, cria um novo produto, que agora pode ser adicionado no
                                // json
                                Produto prod = new Pereciveis(codigo,nome,descricao,preco,validade);

                                arrayProduto.add(prod);
                                Json.writeAllData(arrayProduto,fileProduto);
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
}