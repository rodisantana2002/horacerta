/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.abstracts;

import controlls.core.Icontroll;
import helpers.factoryClassGeneric.concretClassFactory;
import java.util.List;
import java.util.function.Predicate;
import service.core.IwsFacade;

/**
 *
 * @author Rodolfo
 */
public abstract class wsFacadeGeneric<T> implements IwsFacade<T>{
    private final Icontroll<T> icontroll;
    private final concretClassFactory<T> classFactory;
    
    public wsFacadeGeneric(T entity){
        classFactory = new concretClassFactory<T>(entity);        
        icontroll = (Icontroll<T>) classFactory.getControll();
    }

    @Override
    public String create(T entity) {        
        String strList="";
        List<String> lstMSG = icontroll.salvar(entity);
        
        for (String str : lstMSG){
            strList += str + "";
        }
        return strList;
    }

    @Override
    public String update(T entity) {
        String strList="";
        List<String> lstMSG = icontroll.salvar(entity);
        
        for (String str : lstMSG){
            strList += str + "";
        }
        return strList;       
    }
    
    public String delete(T entity) {
        String strList="";
        List<String> lstMSG = icontroll.deletar(entity);
        
        for (String str : lstMSG){
            strList += str + "";
        }
        return strList;
    }

    @Override
    public T findByID(Integer id) {
        return icontroll.obter(id);
    }
    
    @Override
    public List<T> findAll() {
        return icontroll.obterTodos();
    }   
    
    @Override
    public List<T> findByFilter(T entity, Predicate<T> predicate){
        return icontroll.obterByFilter(entity, predicate);
    }
}
