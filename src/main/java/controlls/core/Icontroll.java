/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls.core;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author Rodolfo
 */
public interface Icontroll<T> {
    public abstract List<String> salvar(T entity);
    public abstract List<String> deletar(T entity);
    public abstract T obter(Integer id);
    public abstract List<T> obterTodos();
    public abstract List<T> obterByFilter(T entity, Predicate<T> predicate);
}
