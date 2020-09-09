package controller;

import exceptions.InvalidInput;
import gui.AdminAccountGUI;
import gui.AdminLogin;
import gui.RegularUserLoginGUI;
import service.UserService;

import javax.swing.*;

public class LoginController {

    private UserService userService = new UserService();
    private AdminLogin adminLog ;
    private RegularUserLoginGUI regularUserLoginGUI;
    public LoginController(){}


    public Boolean login(String userType,String username, String password){

        Boolean loggedIn = false;
        try{
            loggedIn = userService.loginService(userType,username, password);
        }catch(InvalidInput e){
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
        }

        if(!loggedIn){
            JOptionPane.showMessageDialog(new JFrame(), "COULDN'T LOG IN", "Login Error", JOptionPane.ERROR_MESSAGE);
            return loggedIn;
        }

        return true;

    }



}
