/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security.core;

import java.util.List;

/**
 *
 * @author Rodolfo
 */
public interface Isecurity<T> {    
    public List<String> autenticarUsuario(T entity);
    public List<String> autenticarTokenUsuario(T entity);
    public void efetuarLogout(T entity);
    public T getUser(T entity);
}
