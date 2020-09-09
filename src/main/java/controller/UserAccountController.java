package controller;

import entity.User;
import gui.AdminAccountGUI;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class UserAccountController {

    private UserService userService = new UserService();

    public UserAccountController(){}


    public List<String[]> fetchTableConfiguredUsers(){
        ArrayList<User> userList = userService.fetchAllUsers();
        List<String[]> tableData = new ArrayList<>();
        for(User us : userList){
            String[] userRow = new String[3];
            userRow[0] = String.valueOf(us.getUser_id());
            userRow[1] = String.valueOf(us.getUser_name());
            userRow[2] = String.valueOf(us.getEmail());
            tableData.add(userRow);
        }

        return tableData;
    }

    public ArrayList<User> fetchAllUsers(){
        return userService.fetchAllUsers();
    }

    public User getUserByID(String id){
        return userService.getUserById(id);
    }

    public User getUserByUserName(String username){
        return userService.getUserByUserName(username);
    }
}
