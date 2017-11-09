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
import model.entity.RegistroPonto;

/**
 *
 * @author Rodolfo
 */
public class ctrlRegistroPonto implements Icontroll<RegistroPonto>{
    private Ibusiness ibusiness;
    private Ivalidator<RegistroPonto> ivalidator;
    private List<String> msgs, regras;

    public ctrlRegistroPonto(){
        ibusiness = new businessFactory<RegistroPonto>(new RegistroPonto()).getBusiness();
        ivalidator = new validatorFactory<RegistroPonto>(new RegistroPonto()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();        
    }

    @Override
    public List<String> salvar(RegistroPonto entity) {
        msgs = validar(entity);        
        if (msgs.isEmpty()){            
            if (ibusiness.salvar(entity)!=null){
                msgs.add(excMessages.STR_OPERACAO_SUCESSO);            
                return msgs;
            }
        }
        msgs.add(excMessages.STR_OPERACAO_INSUCESSO);
        return msgs;
    }

    @Override
    public List<String> deletar(RegistroPonto entity) {
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
    public RegistroPonto obter(Integer id) {
        RegistroPonto c = new RegistroPonto();
        c.setId(id);
        return (RegistroPonto) ibusiness.consultar(c);
    }

    @Override
    public List<RegistroPonto> obterTodos() {
        return ibusiness.listarAll(new RegistroPonto());        
    }    

    private List<String> validarDelete(RegistroPonto entity){
        if(entity.getId()!=null){
            regras.add("validarRegistroPontoNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(RegistroPonto entity){
        regras.add("validarDatas");        
        regras.add("validarCamposObrigatorios");     
        
        if(entity.getId()==null){
            regras.add("validarRegistroPontoCadastrado");
            regras.add("validarIsDataSemPonto");
            regras.add("validarIsIntervaloFrequencia");                    
        }       
        else{
            regras.add("validarRegistroPontoNaoCadastrado");
            regras.add("validarLancamentoPontoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }            

    @Override
    public List<RegistroPonto> obterByFilter(RegistroPonto entity, Predicate<RegistroPonto> predicate) {
        return ibusiness.listarByFilter(entity, predicate);
    }
}
