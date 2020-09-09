package gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame{

    private JPanel mainPanel;
    private JButton ADMINButton;
    private JButton CITIZENButton;


    public MainGUI(){
        initialize();
    }

    public void  initialize(){

        this.setVisible(true);
        this.setLocationRelativeTo(null);

        mainPanel = new JPanel();
        mainPanel.add(ADMINButton);
        mainPanel.add(CITIZENButton);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ADMINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AdminLogin adlog = new AdminLogin();
                MainGUI.super.setVisible(false);
            }
        });
        CITIZENButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RegularUserLoginGUI regularUserLoginGUI = new RegularUserLoginGUI();
                MainGUI.super.setVisible(false);
            }
        });
        this.setContentPane(mainPanel);
        this.pack();
    }



}

