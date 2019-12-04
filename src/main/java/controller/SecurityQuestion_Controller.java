
package controller;

import controller.exceptions.EmptyException;
import controller.exceptions.NotEmailException;
import controller.exceptions.WrongAnswerException;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;
import model.crud.QuestionsCRUD;
import model.crud.UserCRUD;
import model.schemas.SecurityQuestion;
import model.schemas.User;
import view.SecurityQuestion_UI;

/**
 *
 * @author alext
 */
public class SecurityQuestion_Controller {
    
    UserCRUD userModel;
    QuestionsCRUD questionModel;
    
    //Obtener usuario de modelo (por email) y obtener de ese usuario su respectiva pregunta de seguridad
    public String getQuestionByEmail(String email, SecurityQuestion_UI view) throws EmptyException, NotEmailException{
        
        userModel = new UserCRUD();
        questionModel = new QuestionsCRUD();
        
        User user;
        SecurityQuestion question;
        String userQuestion = "";
        
        if(email.isBlank()){
            
            throw new EmptyException();
            
        }else if(!email.contains("@")){
        
            throw new NotEmailException();
        
        }else{
            
            try{
                user = userModel.getUser(email);
                question = questionModel.getQuestionObject(user.getSecurityQuestion().getQuestion());
                userQuestion = question.getQuestion();
            }
            catch(Exception e){
                showError(e,view);
            }

        }
        
        return userQuestion;
    }
    
    
    //Valida que la respuesta a la pregunta sea la almacenada por el usuario
    public void validateAnswer(String answer, String email, SecurityQuestion_UI view) throws WrongAnswerException{
        
        userModel = new UserCRUD();
        questionModel = new QuestionsCRUD();
        
        User user;
        
        try{
            
            user = userModel.getUser(email);
            
            if(answer.equals(user.getAnswer())){
                view.moveOn(user);
            }else{
                throw new WrongAnswerException();
            }
            
        }catch(Exception ex){
            showError(ex, view);
        }
    }
    
    
    
    public void showError(Exception ex, SecurityQuestion_UI view){
        if(ex instanceof EmptyException){
            JOptionPane.showMessageDialog(
                    view, "You must provide an email" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof NotEmailException){
            JOptionPane.showMessageDialog(
                    view, "Invalid email" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof NoResultException){
            JOptionPane.showMessageDialog(
                    view, "Unregistered email" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof WrongAnswerException){
            JOptionPane.showMessageDialog(
                    view, "Wrong answer" , "ERROR", JOptionPane.ERROR_MESSAGE);
        }else if(ex instanceof NullPointerException){
            
        }
        else{
            JOptionPane.showMessageDialog(
                    view, "Unexpected error", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
