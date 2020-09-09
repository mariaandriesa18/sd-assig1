package validators;

import exceptions.CustomErrorMessages;
import exceptions.InvalidInput;

import java.util.regex.*;

public class UserValidator {

    public UserValidator(){ }

    public static Boolean isEmailValid(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static Boolean isPasswordValid(String password) throws InvalidInput{
        String regex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{1,8})";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches())
        {
            return true;
        }else{
            throw new InvalidInput(CustomErrorMessages.WRONG_PASSWORD_FORMAT);
        }
    }
    public static void validCredentials(String username, String email, String password) throws InvalidInput {

        if(username.isEmpty()){
            throw new InvalidInput(CustomErrorMessages.EMPTY_USERNAME_FIELD);
        }
        if(email.isEmpty()) {
            throw new InvalidInput(CustomErrorMessages.EMPTY_EMAIL_FIELD);
        }

        if(password.isEmpty()){
            throw new InvalidInput(CustomErrorMessages.EMPTY_PASSWORD_FIELD);
        }

        if(!(email.contentEquals("admin"))){
            if(!(UserValidator.isEmailValid(email))){
                throw new InvalidInput(CustomErrorMessages.WRONG_EMAIL_FORMAT);
            }
        }

        if(!(UserValidator.isPasswordValid(password))){
            throw new InvalidInput(CustomErrorMessages.WRONG_PASSWORD_FORMAT);
        }
    }
}
