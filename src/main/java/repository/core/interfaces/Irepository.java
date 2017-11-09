/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core.interfaces;

import java.util.List;

/**
 *
 * @author Rodolfo
 */
public interface Irepository<T> {
    public abstract T salvar(T entity);
    public abstract boolean remover(T entity);
    public abstract T obterById(T entity, Integer id);
    public abstract List<T> listar(T entity);
}
