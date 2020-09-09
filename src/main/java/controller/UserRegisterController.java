package controller;

import exceptions.InvalidInput;
import gui.UserRegisterGUI;
import service.UserService;

import javax.swing.*;

public class UserRegisterController {
    private UserService userService = new UserService();

    private UserRegisterGUI userRegisterGUI;
    public UserRegisterController(UserRegisterGUI userRegisterGUI){
        this.userRegisterGUI = userRegisterGUI;
    }

    public Boolean register(String username, String email, String password)  {

        Boolean registered = false;

        try {
            registered = userService.registerService("user", username, email, password);
        } catch (InvalidInput invalidInput) {
            JOptionPane.showMessageDialog(new JFrame(), invalidInput.getMessage(), "Register Error", JOptionPane.ERROR_MESSAGE);
        }

        if(!registered){
            JOptionPane.showMessageDialog(new JFrame(), "COULD NOT REGISTER", "Login Error", JOptionPane.ERROR_MESSAGE);
            return registered;
        }


        return  true;
    }
}
