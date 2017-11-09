package business.bs.abstracts;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import business.core.Ibusiness;
import helpers.types.clsConstants;
import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import repository.core.interfaces.Irepository;
import repository.factory.repositoryFactory;

/**
 *
 * @author Rodolfo
 */
public abstract class bsGeneric<T> implements Ibusiness<T> {
    private Irepository<T> iRepository;

    public bsGeneric(T entity) {
        iRepository = (Irepository<T>) new repositoryFactory(entity).getRepository();
    }
    
    @Override
    public T salvar(T entity) {
        return iRepository.salvar(entity);        
    }

    @Override
    public boolean deletar(T entity) {
        return iRepository.remover(entity);
    }

    @Override
    public T consultar(T entity) {
        try {
            Method mt = entity.getClass().getDeclaredMethod(clsConstants.METODO_ID);            
            return iRepository.obterById(entity, (Integer) mt.invoke(entity));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<T> listarAll(T entity) {
        return iRepository.listar(entity);
    }    
    
    @Override
    public List<T> listarByFilter(T entity, Predicate<T> predicate){       
        return iRepository.listar(entity).stream().filter(predicate).collect(Collectors.toList());
    }        
}
