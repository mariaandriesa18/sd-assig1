package start;

import entity.Document;
import entity.Request;
import entity.Residence;
import entity.User;
import gui.MainGUI;
import repository.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ApplicationStart {

    public static void main(String[] args) {

       /*UserRepo ur = new UserRepo();
        User user = new User();
        user.setUser_id(UUID.randomUUID().toString());
        user.setUser_name("admin");
        user.setEmail("admin");
        user.setUser_type("admin");
        user.setPassword("Admin12@");
        ur.insertEntry(user);
*/
      // UserRepo ur = new UserRepo();

      // List<String> emails = ur.queryForAllWith("email");

      // for(String u : emails){
      //     System.out.println("email : "+ u );
     //  }

        MainGUI mainView = new MainGUI();
    }
}
