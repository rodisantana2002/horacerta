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
import model.entity.TipoDivergencia;

/**
 *
 * @author Rodolfo
 */
public class ctrlTipoDivergencia implements Icontroll<TipoDivergencia>{
    private final Ibusiness ibusiness;
    private final Ivalidator ivalidator;
    private List<String> msgs;
    private List<String> regras;

    public ctrlTipoDivergencia(){
        ibusiness = new businessFactory<TipoDivergencia>(new TipoDivergencia()).getBusiness();
        ivalidator = new validatorFactory<TipoDivergencia>(new TipoDivergencia()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();     
    }
    @Override
    public List<String> salvar(TipoDivergencia entity) {
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
    public List<String> deletar(TipoDivergencia entity) {
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
    public TipoDivergencia obter(Integer id) {
        TipoDivergencia td = new TipoDivergencia();
        td.setId(id);
        return (TipoDivergencia) ibusiness.consultar(td);
    }

    @Override
    public List<TipoDivergencia> obterTodos() {
        return ibusiness.listarAll(new TipoDivergencia());        
    }    

    private List<String> validarDelete(TipoDivergencia entity){
        if(entity.getId()!=null){
            regras.add("validarTipoDivergenciaNaoCadastrada");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(TipoDivergencia entity){
        regras.add("validarCamposObrigatorios");     
        if(entity.getId()==null){
            regras.add("validarTipoDivergenciaCadastrada");
        }       
        else{
            regras.add("validarTipoDivergenciaNaoCadastrada");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }   

    @Override
    public List<TipoDivergencia> obterByFilter(TipoDivergencia entity, Predicate<TipoDivergencia> predicate) {
        return ibusiness.listarByFilter(entity, predicate);
    }
}
