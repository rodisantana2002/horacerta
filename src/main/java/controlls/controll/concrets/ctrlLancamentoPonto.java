/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlls.controll.concrets;

import business.core.Ibusiness;
import business.core.Ivalidator;
import business.factory.businessFactory;
import business.factory.validatorFactory;
import controlls.core.Icontroll;
import helpers.excecoes.excMessages;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import model.entity.LancamentoPonto;

/**
 *
 * @author Rodolfo
 */
public class ctrlLancamentoPonto implements Icontroll<LancamentoPonto>{
    private Ibusiness ibusiness;
    private Ivalidator<LancamentoPonto> ivalidator;
    private List<String> msgs, regras;
    
    public ctrlLancamentoPonto(){
        ibusiness = new businessFactory<LancamentoPonto>(new LancamentoPonto()).getBusiness();
        ivalidator = new validatorFactory<LancamentoPonto>(new LancamentoPonto()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();          
    }
    
    @Override
    public List<String> salvar(LancamentoPonto entity) {
        return null;
    }

    @Override
    public List<String> deletar(LancamentoPonto entity) {
        msgs = validarDelete(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.deletar(entity)){
                msgs.add(excMessages.STR_OPERACAO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    @Override
    public LancamentoPonto obter(Integer id) {
        return null;
    }

    @Override
    public List<LancamentoPonto> obterTodos() {
        return null;
    }

    @Override
    public List<LancamentoPonto> obterByFilter(LancamentoPonto entity, Predicate<LancamentoPonto> predicate) {
        return null;
    }   
    
    private List<String> validarDelete(LancamentoPonto entity){
        if(entity.getId()!=null){
            regras.add("validarLancamentoPontoNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }       
}
