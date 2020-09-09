package service;

import entity.User;
import exceptions.CustomErrorMessages;
import exceptions.InvalidInput;
import repository.UserRepo;
import validators.UserValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserService {

    public UserService(){ }

    private UserRepo urepo = new UserRepo();



    public Boolean registerService(String userType, String username, String email, String password) throws InvalidInput {

        try {
            UserValidator.validCredentials(username,email, password);
        }catch(InvalidInput e){
            throw new InvalidInput(e.getMessage());
        }

        List<String> usernames = urepo.queryForAllWith("user_name");
        if(usernames.contains(username)){
            throw new InvalidInput("User Name" + CustomErrorMessages.ALREADY_REGISTERED);
        }

        List<String> emails = urepo.queryForAllWith("email");
        if(emails.contains(email)){
            throw new InvalidInput("Email"+ CustomErrorMessages.ALREADY_REGISTERED);
        }

        User newUser = new User();
        newUser.setUser_id(UUID.randomUUID().toString());
        newUser.setUser_type(userType);
        newUser.setUser_name(username);
        newUser.setEmail(email);
        newUser.setPassword(password);

        urepo.insertEntry(newUser);
        return true;
    }

    public Boolean loginService(String userType,String username, String password) throws InvalidInput {
        try{
            UserValidator.isPasswordValid(password);
        }catch(InvalidInput e){
            throw new InvalidInput(e.getMessage());
        }

        List<User> users = urepo.queryForAllWithUserType(userType);

        for(User u: users){
            if(u.getUser_name().contentEquals(username)) {
                if (u.getPassword().contentEquals(password)) {
                    return true;
                } else {
                    throw new InvalidInput(CustomErrorMessages.INCORRECT_PASSWORD);
                }
            }
        }

        return false;
    }

    public ArrayList<User> fetchAllUsers(){
        return new ArrayList<User>(urepo.queryForAllWithUserType("user"));
    }

    public User getUserById(String id){
        return urepo.findByString(id);
    }

    public User getUserByUserName(String username){
        return urepo.findByUsername(username);
    }
}
