
package controller;

//recibe,valida y envia los parametros para agregar un usuario

import controller.exceptions.AlreadyRegisteredException;
import controller.exceptions.EmptyException;
import controller.exceptions.NoMatchException;
import controller.exceptions.NotEmailException;
import model.crud.UserCRUD;
import model.schemas.User;
import view.Register;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;
import model.crud.QuestionsCRUD;
import model.schemas.SecurityQuestion;

public class add_user 
{
    
    UserCRUD model;
    
    public add_user(){
        this.model = new UserCRUD();
    }

    public void nuevo_usuario(Register view) throws EmptyException, NotEmailException,AlreadyRegisteredException,NoMatchException{   
     User user = new User();
 
     Map<String, String> data = new HashMap<>();
     data.put("nombre", view.getNombre());
     data.put("correo", view.getCorreo());
     data.put("contrasena", view.getContrasena());
     data.put("conf_contrasena", view.getConfcontrasena());
     data.put("question", view.getSelectedQuestion());
     data.put("answer", view.getAnswer());
     
     if (validCompleteness(data)>0){
         
            throw new EmptyException();
            
        }else if(!data.get("correo").contains("@")){
            
            throw new NotEmailException();
            
        }else if(validExistingEmail(data.get("correo"), view)){
            
            throw new AlreadyRegisteredException();
            
        }else if(!data.get("contrasena").equals(data.get("conf_contrasena"))){
            
            throw new NoMatchException();
        }
        else{
            
            try{
                
                user.setName(data.get("nombre"));
                user.setEmail(data.get("correo"));
                user.setPassword(data.get("contrasena"));
                user.setQuestion(new QuestionsCRUD().getQuestionObject(data.get("question")));
                user.setAnswer(data.get("answer"));
                
                model.createUser(user);
                
            }catch(Exception ex){
                showError(ex, view);
            }

        }

    }
 
    //JOptionPane si todo salió bien
    public void showSuccess(Register view){
        JOptionPane.showMessageDialog(
                    view, "Registered successfully" , "Registered", JOptionPane.INFORMATION_MESSAGE);
        
        view.returnToLogin();
    }
  
 
    //Obtener de model la lista con las preguntas
    public List<SecurityQuestion> getQuestionsFromController(){
        return new QuestionsCRUD().getQuestionsObjects();
    }
    
    //Display an OptionPane in the view with the error
    public void showError(Exception ex, Register view){
        if(ex instanceof EmptyException){
            JOptionPane.showMessageDialog(
                    view, "You must fill every text field" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof NotEmailException){
            JOptionPane.showMessageDialog(
                    view, "Please enter a valid email" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof AlreadyRegisteredException){
            JOptionPane.showMessageDialog(
                    view, "Email already registered" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof NoMatchException){
            JOptionPane.showMessageDialog(
                    view, "Passwords dont match" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof NoResultException){
            
        }
        else{
            JOptionPane.showMessageDialog(
                    view, "Unexpected error", "ERROR", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
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
    
    public boolean validExistingEmail(String email, Register view){
        boolean exists = false;
        User user;
        
        try{
            
            user = model.getUser(email);
            if(user!=null){
                exists = true;
            }
            
        }catch(Exception ex){
            showError(ex, view);
        }
        
        return exists;
    }
}       
