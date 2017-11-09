package business.validator.abstracts;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import business.core.Ivalidator;
import helpers.actions.clsTrataActionsDinamicas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodolfo
 */
public abstract class validGeneric<T> implements Ivalidator<T>{
    private List<String> lstMsg;
    private clsTrataActionsDinamicas actionsDinamicas;
    
    public validGeneric(){
        lstMsg = new ArrayList<String>();
    }
    
    public List<String> getLstMsg() {
        return lstMsg;
    }

    public void setLstMsg(List<String> lstMsg) {
        this.lstMsg = lstMsg;
    }
    
    ///Metodos que implementam o padrao COMMAND
    /// os comandos/metodos são implementados nas classes conretas, mas
    /// a chamada é definida e utilizada nas classes usuárias da mesma.
    /// a classe executora é a super classe abstrata a qual as demais 
    ///classes concretas extendem
    @Override
    public List<String> validarRegras(T entity, String operacao) {
        actionsDinamicas = new clsTrataActionsDinamicas(this, operacao, entity);
        actionsDinamicas.executarMetodoDinamico();
        return lstMsg;
    }

    @Override
    public List<String> validarRegras(T entity, List<String> operacao) {
        for (String strOperacao : operacao){
            actionsDinamicas = new clsTrataActionsDinamicas(this, strOperacao, entity);            
            actionsDinamicas.executarMetodoDinamico();
        }
        return lstMsg;
    }          
}
