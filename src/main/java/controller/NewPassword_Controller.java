

package controller;

import controller.exceptions.EmptyException;
import controller.exceptions.NoMatchException;
import controller.exceptions.OlderPasswordException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import model.crud.UserCRUD;
import model.schemas.User;
import view.NewPassword;

/**
 *
 * @author alext
 */
public class NewPassword_Controller {
    
    public void createNewPassword(NewPassword view, User user) throws EmptyException, OlderPasswordException, NoMatchException {
        
        
        UserCRUD model = new UserCRUD();
        
        char[] password;
        password = view.getPassword();
        
        char[] confirmPassword;
        confirmPassword = view.getConfirmPassword();
     
        Map<String, String> data = new HashMap<>();
        data.put("password", String.valueOf(password));
        data.put("confirm_password", String.valueOf(confirmPassword));
     
        if(validCompleteness(data) > 0){
            
            throw new EmptyException();
            
        }else if(!data.get("password").equals(data.get("confirm_password"))){
            
            throw new NoMatchException();
            
        }else if(user.getPassword().equals(data.get("password"))){
            
            throw new OlderPasswordException();
            
        }else{
            
            try{
                
                model.updateUserPassword(user, data.get("password"));
                
            }
            catch(Exception ex){
                showError(ex,view);
            }
        }
     
    }
    
    public void showError(Exception ex, NewPassword view){
        if(ex instanceof EmptyException){
            JOptionPane.showMessageDialog(
                    view, "You must fill every text field" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof OlderPasswordException){
            JOptionPane.showMessageDialog(
                    view, "You must enter a new password" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof NoMatchException){
            JOptionPane.showMessageDialog(
                    view, "Passwords dont match" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else{
            JOptionPane.showMessageDialog(
                    view, "Unexpected error", "ERROR", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    
    public void showSuccess(NewPassword view){
        JOptionPane.showMessageDialog(
                    view, "New password created" , "Updated", JOptionPane.INFORMATION_MESSAGE);
        
        view.returnToLogin();
    }
    
     //Validate that there is no empty information
    public int validCompleteness(Map<String,String> data){
        int isComplete = 0;
        Set<String> keys = data.keySet();
        for(String key: keys){
            if(data.get(key).isBlank()){ //Checks that there is no empty information
                isComplete++;
            }
        }

        return isComplete;
    }
}
