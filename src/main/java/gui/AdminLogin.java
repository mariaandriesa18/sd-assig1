package gui;

import controller.LoginController;

import javax.swing.*;

public class AdminLogin extends JFrame{
    private JPanel mainP;
    private JPasswordField passwordField1;
    private JButton loginBTN;
    private JLabel passLB;
    private JLabel lb1;
    private JLabel lb2;
    private JLabel lb3;
    private JLabel lb4;
    private JLabel lb5;
    private JLabel lb6;
    private LoginController loginController = new LoginController();

    //static JFrame frame = new AdminLogin();
    public AdminLogin(){

       // mainP = new JPanel();
      //  mainP.add(passLB);
      //  mainP.add(passwordField1);
       // mainP.add(loginBTN);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainP);
        this.pack();
        loginBTN.addActionListener( e ->  {
            loginController.login("admin", "admin",  new String(passwordField1.getPassword()));
            this.close();
            AdminAccountGUI adminAccountGUI = new AdminAccountGUI();

        });
    }

    public void close(){
        this.setVisible(false);
    }
    public void viewAdminLogin(){
        this.setVisible(true);
    }
}
