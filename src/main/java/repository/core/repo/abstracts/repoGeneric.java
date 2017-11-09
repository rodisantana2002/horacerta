/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core.repo.abstracts;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.core.conn.repoConnection;
import repository.core.interfaces.Irepository;

/**
 *
 * @author Rodolfo
 * @param <T>
 */
public abstract class repoGeneric<T> implements Irepository<T>{
    private Session session;
    private Transaction transaction;

    public repoGeneric(){
        session = null;
        transaction = null;        
    }
    
    @Override
    public T salvar(T entity) {
        try {
            session = repoConnection.getInstance().getSession();        
            transaction = session.beginTransaction();   
            session.saveOrUpdate(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }    
    }

    @Override
    public boolean remover(T entity) {
        try {
            session = repoConnection.getInstance().getSession();        
            transaction = session.beginTransaction();   
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }           
        return true;
    }

    @Override
    public T obterById(T entity, Integer id) {
        try {
            session = repoConnection.getInstance().getSession();        
            return (T) session.get(entity.getClass(), id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }           
    }

    @Override
    public List<T> listar(T entity) {
        try {
            session = repoConnection.getInstance().getSession();        
            Criteria criteria = session.createCriteria(entity.getClass());
            return criteria.list();                
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<T>();
        }           
    }    
}
