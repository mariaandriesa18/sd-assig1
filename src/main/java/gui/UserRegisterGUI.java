package gui;

import controller.LoginController;
import controller.UserRegisterController;

import javax.swing.*;
import java.awt.*;

public class UserRegisterGUI extends JFrame{
    private JPanel regPanel;
    private JLabel userRegTitleLb;
    private JPanel regFieldsPanel;
    private JLabel usernameLB;
    private JTextField usernameTF;
    private JLabel emailLB;
    private JTextField emailTF;
    private JLabel passLB;
    private JPasswordField passwordField;
    private JLabel lb1;
    private JLabel lb2;
    private JLabel lb3;
    private JLabel lb4;
    private JLabel lb5;
    private JLabel lb6;
    private JButton registerButton;

    private UserRegisterController userRegisterController = new UserRegisterController(this);

    public UserRegisterGUI(){

        this.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2 - 300, dim.height / 2 - this.getSize().height / 2 - 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(regPanel);
        this.pack();

        registerButton.addActionListener( e ->  {
            userRegisterController.register(new String(usernameTF.getText()),new String(emailTF.getText()),
                    new String(passwordField.getPassword()));
            this.closePanel();
            RegularUserLoginGUI userLoginGUI = new RegularUserLoginGUI();
        });
    }

    public void closePanel() {this.setVisible(false);}
    /*public static void main (String[] args) {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new UserRegisterGUI());
        frame.pack();

    }*/
}
