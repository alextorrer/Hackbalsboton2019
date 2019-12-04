/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.crud;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import model.bootstraper.EMFBootstrapper;
import model.schemas.SecurityQuestion;

/**
 *
 * @author alext
 */
public class QuestionsCRUD {

    public QuestionsCRUD() {
    }
    
    //AÒadir una pregunta nueva
    public void createQuestion(SecurityQuestion question){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(question);
            transaction.commit();
            System.out.printf("se ha a√±adido con exito");
        }
        catch(PersistenceException e) {
            transaction.rollback();
            throw e;
        }
        finally {
            manager.close();
        }
    }
    
    //Obtener una pregunta (objeto)
    public SecurityQuestion getQuestionObject(String question){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        SecurityQuestion questionObj;
        try {
            questionObj = (SecurityQuestion) manager.createQuery("from SecurityQuestion u where u.question='" + question + "'").getSingleResult();
        }
        catch(PersistenceException e) {
            throw e;
        }

        return questionObj;
    }
    
    //Obtener todas las preguntas (objetos)
    public List<SecurityQuestion> getQuestionsObjects(){
        List<SecurityQuestion> questions;
        EntityManager manager = EMFBootstrapper.openEntityManager();
        
        try{
            questions = manager.createQuery("from SecurityQuestion", SecurityQuestion.class).getResultList();
            
        }
        catch(PersistenceException ex){
            throw ex;
        }
        return questions;
    }
}

