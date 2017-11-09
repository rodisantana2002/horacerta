/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.factory;

import helpers.factoryClassGeneric.concretClassFactory;
import java.io.Serializable;
import repository.core.interfaces.Irepository;

/**
 *
 * @author Rodolfo
 */
public class repositoryFactory<T> implements Serializable{
    private T entity;
    private Irepository<T> iRepository;
    private concretClassFactory concretClassFactory;
    
    public repositoryFactory(T entity){
        this.entity = entity;
        initObject();
    }
    
    //regra que estabelece a convençao para a definição do nome da classe que será utilizada para persistir os dados
    //1ª parte - dinânica - será verificado qual o tipo de persistencia setado na configuração do sistema
    //os tipos permitidos serão definidos pelo enumeraor (enumTypePersist)
    //2ª parte - fixa     - será convencionado o prefixo para o nome da classe como sendo o valor: "repo"
    //3ª parte - dinâmica - será obtido pelo nome da classe da entidade que esta sendo persistida   
    private void initObject() {
       concretClassFactory = new concretClassFactory(entity);
    }

    //o sistema identifica o tipo de persistenca e assim produz a instancia da classe para que os dados sejam persistidos 
    public Irepository<T> getRepository() {
        iRepository = (Irepository<T>) concretClassFactory.getFactoryRepository();
        return iRepository;
    }    
}