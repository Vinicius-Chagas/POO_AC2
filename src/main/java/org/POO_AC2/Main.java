package org.POO_AC2;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.GridLayout;

public class Main {
    public static void main(String[] args) throws IOException {
        String[] opcoes = {
            "[1] Cadastros de Clientes",
            "[2] Deletar Cliente pelo CPF ou CNPJ", 
            "[3] Deletar Cliente pelo nome", 
            "[4] Cadastro de produtos",
            "[5] Efetuacao de uma compra", 
            "[6] Atualizacao da situacao de pagamentos de uma compra", 
            "[7] Relatorios",
            "[8] Sair"
        };
        String mensagem = "Por favor, escolha uma opção:";
        String titulo = "Organizacoes Tabajara";
        //ImageIcon icone = new ImageIcon("assets\\menu.jfif"); //icone ficou muito grande
        
        JPanel panel = new JPanel(new GridLayout(opcoes.length, 1));

        ButtonGroup group = new ButtonGroup();
        
        for (String opcao : opcoes) {
            JRadioButton radioButton = new JRadioButton(opcao);
            panel.add(radioButton);
            group.add(radioButton);
        }
        
        int resultado = JOptionPane.showConfirmDialog(null, panel, mensagem, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        
        if (resultado == JOptionPane.OK_OPTION) {
            for (int i = 0; i < opcoes.length; i++) {
                if (group.getElements().nextElement().isSelected()) {
                    switch(i) {
                        case 0:
                        String[] tipoCliente = {"Pessoa Física", "Pessoa Jurídica"};
                        JPanel tipoPanel = new JPanel(new GridLayout(tipoCliente.length, 1));
                        ButtonGroup tipoGroup = new ButtonGroup();
                        
                        for (String tipo : tipoCliente) {
                            JRadioButton tipoRadioButton = new JRadioButton(tipo);
                            tipoPanel.add(tipoRadioButton);
                            tipoGroup.add(tipoRadioButton);
                        }
                        
                        int tipoResultado = JOptionPane.showConfirmDialog(
                            null,
                            tipoPanel, 
                            "Por favor, escolha o tipo de cliente:", 
                            JOptionPane.OK_CANCEL_OPTION, 
                            JOptionPane.PLAIN_MESSAGE, 
                            null
                            );
                        
                       if (tipoResultado == JOptionPane.OK_OPTION) {
    Enumeration<AbstractButton> elements = tipoGroup.getElements();
    int j = 0;
    while (elements.hasMoreElements()) {
        AbstractButton button = elements.nextElement();
        if (button.isSelected()) {
            switch(j) {
                case 0:
                    JOptionPane.showMessageDialog(null, "Você selecionou: pessoa física" , titulo, JOptionPane.INFORMATION_MESSAGE, null);
                    break;
                case 1:
                    JOptionPane.showMessageDialog(null, "Você selecionou: pessoa Jurídica" , titulo, JOptionPane.INFORMATION_MESSAGE, null);
                    break;
            }
            break;
        }
        j++;
    }
} else {
    JOptionPane.showMessageDialog(null, "Nenhum tipo de cliente selecionado.", titulo, JOptionPane.WARNING_MESSAGE, null);
}
                        case 1:
                            // Código para "Deletar Cliente pelo CPF ou CNPJ"
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
                        case 7:
                            // Código para "Sair"
                            System.exit(0);
                    }
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma opção selecionada.", titulo, JOptionPane.WARNING_MESSAGE, null);
        }
    }
}
