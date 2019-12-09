package controller;

import controller.exceptions.EmptyException;
import model.crud.UserCRUD;
import model.schemas.User;
import view.Login;

import java.util.*;
import javax.persistence.NoResultException;

public class Login_Controller {


    //Validates the input and send it to the model
    public void LoginUser(Login view) throws EmptyException {

        User user;
        UserCRUD model = new UserCRUD();

        Map<String, String> data = new HashMap<>();
        data.put("email", view.getEmailText());
        data.put("password", String.valueOf(view.getPasswordText()));

        if (!validCompleteness(data))
        {
            throw new EmptyException();
        }
        else
        {
            try{
                user = model.getUser(data.get("email"));
                validateLogin(data, user, view);
            }catch(Exception ex){
                view.showError(ex);
            }

        }
    }

    //Verify the password
    public void validateLogin(Map<String,String> data, User user, Login view){
        if(user.getPassword().equals(data.get("password"))){
            view.EnterHome();
        }else{
             throw new NoResultException();
        }
        
    }

    //Validate that there is no empty information
    public boolean validCompleteness(Map<String,String> data){
        boolean isComplete = false;
        Set<String> keys = data.keySet();
        for(String key: keys){
            if(!data.get(key).isEmpty()){ //Checks that there is no empty information
                isComplete = true;
            }
        }

        return isComplete;
    }
    
    
}


