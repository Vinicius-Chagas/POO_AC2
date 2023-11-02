package org.POO_AC2;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
        ImageIcon icone = new ImageIcon("assets\\menu.jfif");
        
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
                    JOptionPane.showMessageDialog(null, "Você selecionou: " + opcoes[i], titulo, JOptionPane.INFORMATION_MESSAGE, null);
                    break;
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nenhuma opção selecionada.", titulo, JOptionPane.WARNING_MESSAGE, null);
        }
    }
}
