package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton ingresarButton;
    private JLabel passwordField;
    private JLabel userLabel;
    private JPanel LoginPanel;
    private static JPanel screen;

    public Login() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Assuming 'screen' is a CardLayout object
                CardLayout cardLayout = (CardLayout) screen.getLayout();
                cardLayout.show(screen, "SecondPanel"); // Switch to pruebaScreen
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Finansoft");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,310);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        screen = new JPanel(new CardLayout());
        Login loginPanel = new Login();
        pruebaScreen pruebaScreen= new pruebaScreen();

        // Añadir las pantallas al contenedor
        screen.add(loginPanel.LoginPanel, "LoginPanel");
        screen.add(pruebaScreen.rojo, "SecondPanel");

        // Añadir el contenedor al frame
        frame.add(screen);
        frame.setVisible(true);
    }
}
