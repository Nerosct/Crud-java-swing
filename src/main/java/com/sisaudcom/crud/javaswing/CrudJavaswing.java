package com.sisaudcom.crud.javaswing;

import com.sisaudcom.crud.javaswing.connection.InicializadorBanco;
import com.sisaudcom.crud.javaswing.view.TelaLogin;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author neros
 */
public class CrudJavaswing {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("FlatLaf indisponível, usando Look and Feel padrão.");
        }

        // Inicializa o banco de dados
        try {
            InicializadorBanco.inicializar();

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Não foi possível inicializar o banco de dados.\n"
                    + "Verifique se o PostgreSQL está em execução.",
                    "Erro de conexão",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // Abre a tela de login na thread correta da interface
        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}
