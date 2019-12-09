/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.crud;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import model.bootstraper.EMFBootstrapper;
import model.schemas.User;


/**
 *
 * @author joses
 */
public class UserCRUD {
    
    public void createUser(User user) throws PersistenceException{
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();

            transaction.begin();
            manager.persist(user);
            transaction.commit();
            System.out.printf("se ha añadido con exito");

    }
    

    public User getUser(String email)throws PersistenceException{
        EntityManager manager = EMFBootstrapper.openEntityManager();
        User user;

        user = (User) manager.createQuery("from User u where u.Email='" + email + "'").getSingleResult();
        return user;
      
    }
    
    public void deleteUser(String email)throws PersistenceException{
        String delims = "[,]";
        String[] tokens = email.split(delims);


        for(int i = 0; i < tokens.length; i++){
            User user = getUser(tokens[i]);
            EntityManager manager = EMFBootstrapper.openEntityManager();
            EntityTransaction transaction = manager.getTransaction();

                transaction.begin();
                manager.remove(user);
                transaction.commit();
                System.out.printf("se ha eliminado con exito");

        }
    }
    
    
    //Actualizar la contraseña del usuario
    public void updateUserPassword(User user, String password)throws PersistenceException{
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        int id = user.getId();

            transaction.begin();
            Query query = manager.createQuery("update User set Password = '" + password + "' where Id = '" + id + "' " );
            query.executeUpdate();
            transaction.commit();


    }

}
