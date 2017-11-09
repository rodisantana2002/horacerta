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
import model.entity.Configuracao;

/**
 *
 * @author Rodolfo
 */
public class ctrlConfiguracao implements Icontroll<Configuracao>{
    private Ibusiness ibusiness;
    private Ivalidator<Configuracao> ivalidator;
    private List<String> msgs, regras;

    public ctrlConfiguracao(){
        ibusiness = new businessFactory<Configuracao>(new Configuracao()).getBusiness();
        ivalidator = new validatorFactory<Configuracao>(new Configuracao()).getValidator();
        msgs = new ArrayList<String>();
        regras = new ArrayList<String>();        
    }

    @Override
    public List<String> salvar(Configuracao entity) {
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
    public List<String> deletar(Configuracao entity) {
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
    public Configuracao obter(Integer id) {
        Configuracao c = new Configuracao();
        c.setId(id);
        return (Configuracao) ibusiness.consultar(c);
    }

    @Override
    public List<Configuracao> obterTodos() {
        return ibusiness.listarAll(new Configuracao());        
    }    

    private List<String> validarDelete(Configuracao entity){
        if(entity.getId()!=null){
            regras.add("validarConfiguracaoNaoCadastrada");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }    

    private List<String> validar(Configuracao entity){
        regras.add("validarCamposObrigatorios");  
        regras.add("validarHorario");  
        if(entity.getId()==null){
            regras.add("validarConfiguracaoCadastrada");
        }       
        else{
            regras.add("validarConfiguracaoNaoCadastrada");
        }
        return ivalidator.validarRegras(entity, regras);                              
    }        

    @Override
    public List<Configuracao> obterByFilter(Configuracao entity, Predicate<Configuracao> predicate) {
        return ibusiness.listarByFilter(entity, predicate);
    }
}