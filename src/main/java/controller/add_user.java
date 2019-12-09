
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
     data.put("contrasena", String.valueOf(view.getContrasena()));
     data.put("conf_contrasena", String.valueOf(view.getConfcontrasena()));
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
                view.showError(ex);
            }

        }

    }

 
    //Obtener de model la lista con las preguntas
    public List<SecurityQuestion> getQuestionsFromController(){
        return new QuestionsCRUD().getQuestionsObjects();
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
            view.showError(ex);
        }
        
        return exists;
    }
}       
