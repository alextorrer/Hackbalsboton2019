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
    
    public void createUser(User user){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            manager.persist(user);
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
    

    public User getUser(String email){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        User user = new User();
        try {
            user = (User) manager.createQuery("from User u where u.Email='" + email + "'").getSingleResult();
        }
        catch(PersistenceException e) {
            throw e;
        }

        return user;
    }
    
    public void deleteUser(String email){
        String delims = "[,]";
        String[] tokens = email.split(delims);


        for(int i = 0; i < tokens.length; i++){
            User user = getUser(tokens[i]);
            EntityManager manager = EMFBootstrapper.openEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            try {
                transaction.begin();
                manager.remove(user);
                transaction.commit();
                System.out.printf("se ha eliminado con exito");
            }
            catch(PersistenceException e) {
                transaction.rollback();
                throw e;
            }
            finally {
                manager.close();
            }
        }
    }
    
    
    //Actualizar la contraseÒa del usuario
    public void updateUserPassword(User user, String password){
        EntityManager manager = EMFBootstrapper.openEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        int id = user.getId();
        
        try {
            transaction.begin();
            Query query = manager.createQuery("update User set Password = '" + password + "' where Id = '" + id + "' " );
            query.executeUpdate();
            transaction.commit();
        }
        catch(PersistenceException e) {
            transaction.rollback();
            throw e;
            
        }finally {
            manager.close();
        }

    }

}
