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
    
    //Añadir una pregunta nueva
    public void createQuestion(SecurityQuestion question)throws PersistenceException{
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();

            transaction.begin();
            manager.persist(question);
            transaction.commit();
            System.out.printf("se ha añadido con exito");

    }
    
    //Obtener una pregunta (objeto)
    public SecurityQuestion getQuestionObject(String question)throws PersistenceException{
        EntityManager manager = EMFBootstrapper.openEntityManager();
        SecurityQuestion questionObj;
        
            questionObj = (SecurityQuestion) manager.createQuery("from SecurityQuestion u where u.question='" + question + "'").getSingleResult();


        return questionObj;
    }
    
    //Obtener todas las preguntas (objetos)
    public List<SecurityQuestion> getQuestionsObjects()throws PersistenceException{
        List<SecurityQuestion> questions;
        EntityManager manager = EMFBootstrapper.openEntityManager();

            questions = manager.createQuery("from SecurityQuestion", SecurityQuestion.class).getResultList();
            

        return questions;
    }
}

