package org.POO_AC2;

import java.io.IOException;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.POO_AC2.dominio.cliente.Endereco;
import org.POO_AC2.dominio.cliente.PF;
import org.POO_AC2.dominio.cliente.PJ;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.GridLayout;

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
                for (int i = 0; i < opcoes.length; i++) {
                    if (group.getElements().nextElement().isSelected()) {

                        switch (i) {
                            case 0:// cadastro PF || PJ
                                cadastroDoCliente();
                                break;
                            case 1:
                                // Código para "Deletar Cliente pelo CPF ou CNPJ"
                                JPanel delecaoClientePanel = new JPanel(new GridLayout(1, 1));
                                delecaoClientePanel.add(new JLabel("CPF/CNPJ: "));
                                JTextField cField = new JTextField();
                                delecaoClientePanel.add(cField);
                                int delecaoClientePagina = JOptionPane.showConfirmDialog(
                                    null,
                                    delecaoClientePanel,
                                    "Deleção de Cliente",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null);
                                // delecaoCliente();
                                break;
                            case 2:
                                // Código para "Deletar Cliente pelo nome"
                                break;
                            case 3:
                                // Código para "Cadastro de produtos"
                                break;
                            case 4:
                                // Código para "Efetuação de uma compra"
                                break;
                            case 5:
                                // Código para "Atualização da situação de pagamentos de uma compra"
                                break;
                            case 6:
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

    public static void cadastroDoCliente() {

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

                            cadastroPFPanel.add(new JLabel("Data de Cadastro:"));
                            JTextField dataCadastroField = new JTextField();
                            cadastroPFPanel.add(dataCadastroField);

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
                                String dataCadastro = dataCadastroField.getText();
                                String cpf = cpfField.getText();
                                int qntParcelasMax = Integer.parseInt(qntParcelasMaxField.getText());
                                // com os dados coletados, cria um novo pf, que agora pode ser adicionado no
                                // json
                                PF pf = new PF(nome, endereco, dataCadastro, cpf, qntParcelasMax);

                                // precisa salvar no json agora
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

                            cadastroPJPanel.add(new JLabel("Data de Cadastro:"));
                            JTextField dataCadastroEmpresaField = new JTextField();
                            cadastroPJPanel.add(dataCadastroEmpresaField);

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
                                String dataCadastro = dataCadastroEmpresaField.getText();
                                String cnpj = cpfEmpresaField.getText();
                                String razaoSocial = razaoSocialField.getText();
                                int qntParcelasMax = Integer.parseInt(qntParcelasMaxEmpresaField.getText());

                                // com os dados coletados, cria um novo pf, que agora pode ser adicionado no
                                // json
                                PJ pj = new PJ(nome, endereco, dataCadastro, cnpj, razaoSocial, qntParcelasMax);

                                // precisa salvar no json agora
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

    public static void delecaoCliente(String codigo) {
    }
}