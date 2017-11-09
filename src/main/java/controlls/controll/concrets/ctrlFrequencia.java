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
import model.entity.Frequencia;

/**
 *
 * @author Rodolfo
 */
public class ctrlFrequencia implements Icontroll<Frequencia>{
    private Ibusiness ibusiness;
    private Ivalidator<Frequencia> ivalidator;
    private List<String> msgs, regras;

    public ctrlFrequencia(){
        ibusiness = new businessFactory<Frequencia>(new Frequencia()).getBusiness();
        ivalidator = new validatorFactory<Frequencia>(new Frequencia()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();        
    }

    @Override
    public List<String> salvar(Frequencia entity) {
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
    public List<String> deletar(Frequencia entity) {
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
    public Frequencia obter(Integer id) {
        Frequencia c = new Frequencia();
        c.setId(id);
        return (Frequencia) ibusiness.consultar(c);
    }

    @Override
    public List<Frequencia> obterTodos() {
        return ibusiness.listarAll(new Frequencia());        
    }    

    private List<String> validarDelete(Frequencia entity){
        if(entity.getId()!=null){
            regras.add("validarFrequenciaNaoCadastrada");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(Frequencia entity){
        regras.add("validarCamposObrigatorios");     
        if(entity.getId()==null){
            regras.add("validarFrequenciaCadastrada");
        }       
        else{
            regras.add("validarFrequenciaNaoCadastrada");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    @Override
    public List<Frequencia> obterByFilter(Frequencia entity, Predicate<Frequencia> predicate) {
        return ibusiness.listarByFilter(entity, predicate);
    }
}
