package business.factory;

import helpers.factoryClassGeneric.concretClassFactory;
import business.core.Ivalidator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rodolfo
 */
public class validatorFactory<T> {
    private Ivalidator<T> ivalidator;
    private T entity;
    private concretClassFactory concretClassFactory;
    
    public validatorFactory(T entity){
        this.entity = entity;
        initObject();
    }
    
    //regra que estabelece a convençao para a definição do nome da classe que será utilizada para a camada de validação de negócios
    //1ª parte - fixa     - será convencionado o prefixo para o nome da classe como sendo o valor: "valid"
    //2ª parte - dinâmica - será obtido pelo nome da classe da entidade que esta sendo validada
    private void initObject() {
        concretClassFactory = new concretClassFactory(entity);
    }

    //o sistema identifica a classe e assim produz a sua instancia 
    public Ivalidator getValidator() {
        ivalidator = (Ivalidator<T>) concretClassFactory.getFactoryValidator();        
        return ivalidator;
    }     
}
