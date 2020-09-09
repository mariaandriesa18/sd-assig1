package gui;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegularUserLoginGUI extends JFrame {
    private JPanel userLogPanel;
    private JLabel loginTitleLB;
    private JPanel logPanel;
    private JLabel usernameLB;
    private JTextField usernameTF;
    private JLabel passLB;
    private JPasswordField passwordField;
    private JButton newHereRegisterButton;
    private JButton loginButton;

    private LoginController loginController = new LoginController();
    private UserRegisterGUI userRegisterGUI;
    private UserAccountGUI userAccountGUI;

    public RegularUserLoginGUI() {
        this.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2 - 200, dim.height / 2 - this.getSize().height / 2 - 200);
        this.setSize(new Dimension(600, 600));
        this.add(userLogPanel);
        logPanel.setPreferredSize(new Dimension(300, 300));
        this.add(logPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        loginButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  closePanel();
                  userAccountGUI = new UserAccountGUI(usernameTF.getText());
               }
        });

        newHereRegisterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closePanel();
                userRegisterGUI = new UserRegisterGUI();
            }

        });
    }

    public void closePanel(){
        this.setVisible(false);

      }

}