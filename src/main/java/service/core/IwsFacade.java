/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.core;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author Rodolfo
 */
public interface IwsFacade<T> {
    public abstract String create(T entity);
    public abstract String update(T entity);
    public abstract String delete(Integer entity);
    public abstract T findByID(Integer id);    
    public abstract List<T> findAll();
    public abstract List<T> findByFilter(T entity, Predicate<T> predicate);
}
