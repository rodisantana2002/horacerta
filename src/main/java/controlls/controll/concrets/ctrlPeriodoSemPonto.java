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
import model.entity.PeriodoSemPonto;

/**
 *
 * @author Rodolfo
 */
public class ctrlPeriodoSemPonto implements Icontroll<PeriodoSemPonto>{
    private final Ibusiness ibusiness;
    private final Ivalidator ivalidator;
    private List<String> msgs;
    private List<String> regras;

    public ctrlPeriodoSemPonto(){
        ibusiness = new businessFactory<PeriodoSemPonto>(new PeriodoSemPonto()).getBusiness();
        ivalidator = new validatorFactory<PeriodoSemPonto>(new PeriodoSemPonto()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();     
    }
    @Override
    public List<String> salvar(PeriodoSemPonto entity) {
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
    public List<String> deletar(PeriodoSemPonto entity) {
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
    public PeriodoSemPonto obter(Integer id) {
        PeriodoSemPonto psp = new PeriodoSemPonto();
        psp.setId(id);
        return (PeriodoSemPonto) ibusiness.consultar(psp);
    }

    @Override
    public List<PeriodoSemPonto> obterTodos() {
        return ibusiness.listarAll(new PeriodoSemPonto());        
    }    

    private List<String> validarDelete(PeriodoSemPonto entity){
        if(entity.getId()!=null){
            regras.add("validarPeriodoSemPontoNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(PeriodoSemPonto entity){
        regras.add("validarCamposObrigatorios");     
        if(entity.getId()==null){
            regras.add("validarPeriodoSemPontoCadastrado");
        }       
        else{
            regras.add("validarPeriodoSemPontoNaoCadastrado");
        }
        return ivalidator.validarRegras(entity, regras);                              
    } 

    @Override
    public List<PeriodoSemPonto> obterByFilter(PeriodoSemPonto entity, Predicate<PeriodoSemPonto> predicate) {
        return ibusiness.listarByFilter(entity, predicate);
    }
}
