package model.test;

import java.util.ArrayList;
import model.crud.HouseCRUD;
import model.crud.UserCRUD;
import model.schemas.House;
import model.schemas.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import model.crud.QuestionsCRUD;
import model.schemas.SecurityQuestion;

public class TestUser {

    public static void main(String[] args){
        
        /*SecurityQuestion quest1 = new SecurityQuestion("Nombre de la mascota de tu infancia");
        SecurityQuestion quest2 = new SecurityQuestion("Ciudad de nacimiento");
        SecurityQuestion quest3 = new SecurityQuestion("Apellido de solter@");
        SecurityQuestion quest4 = new SecurityQuestion("Primer empleo");
        
        
        
        qcrud.createQuestion(quest1);
        qcrud.createQuestion(quest2);
        qcrud.createQuestion(quest3);
        qcrud.createQuestion(quest4);*/
        
        /*QuestionsCRUD qcrud = new QuestionsCRUD();
        User user = new User();
            user.setName("Rodrigo");
            user.setEmail("rodrigo@correo.com");
            user.setPassword("pass");
            user.setQuestion(qcrud.getQuestion("Nombre de la mascota de tu infancia"));
            user.setAnswer("Dani");
        new UserCRUD().createUser(user);*/
        
        /*QuestionsCRUD qcrud = new QuestionsCRUD();
        List<SecurityQuestion> questions;
        questions = qcrud.getQuestions();
        for(int i=0;i<4;i++)
        {
            System.out.println(questions.get(i).getQuestion());
        }*/
        
        UserCRUD model = new UserCRUD();
        User user;
        String newPassword = "nueva2";
        user = model.getUser("rodrigo@correo.com");
        model.updateUserPassword(user, newPassword);
    }
    private static EntityManagerFactory emf;
    private static EntityManager manager;

    public static void test() {
        emf = Persistence.createEntityManagerFactory("DomoticaModel");
        manager = emf.createEntityManager();


        List<User> userList = manager.createQuery("from User").getResultList();
        for (User u:userList) {
            System.out.println(u.toString());
        }

    }
}
