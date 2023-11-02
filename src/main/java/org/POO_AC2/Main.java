package org.POO_AC2;




import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;



// 1. Cadastros de Clientes;
// 2. Deletar cliente pelo CPF ou CNPJ;
// 3. Deletar cliente pelo nome;
// 4. Cadastro de Produtos;
// 5. Efetuação de uma compra;
// 6. Atualização da situação de pagamento de uma compra;
// 7. Relatórios;
// (a) Relação de todos os Clientes que possuem o nome iniciado por uma determinada
// sequência de caracteres;
// (b) Relação de todos os Produtos;
// (c) Busca de um produto pelo nome;
// (d) Relação de produtos que são perecíveis e que estão com a data de validade vencida;
// (e) Relação de todas as compras;
// (f) Busca de uma compra pelo número;
// (g) Relação de todas as compras que não foram pagas ainda;
// (h) Relação das 10 últimas compras pagas;
// (i) Apresentação das informações da compra mais cara;
// (j) Apresentação das informações da compra mais barata;
// (k) Relação do valor total de compras feitas em cada mês nos últimos 12 meses.
// 8. Sair (termina o sistema)
//JOptionPane

public class Main{

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
        String mensagem = "Por favor, digite o numero do menu para onde deseja navegar.";
        String titulo = "Organizacoes Tabajara";
        ImageIcon icone = new ImageIcon("assets\\menu.jfif");
        // JOptionPane.showOptionDialog(parentComponent, message, title,
        //       optionType, messageType, icon, options, initialValue);
        JOptionPane.showOptionDialog(null, mensagem, titulo,
        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE , null, opcoes, null);


    }
}