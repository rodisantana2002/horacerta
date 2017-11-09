/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.validator.concrets;

import business.core.Ibusiness;
import business.factory.businessFactory;
import business.validator.abstracts.validGeneric;
import helpers.excecoes.excMessages;
import java.util.function.Predicate;
import model.entity.LancamentoDivergencia;

/**
 *
 * @author Rodolfo
 */
public class validLancamentoDivergencia extends validGeneric<LancamentoDivergencia>{
    public validLancamentoDivergencia(){
        super();
    }
    public void validarCamposObrigatorios(LancamentoDivergencia entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ")");                    
        }
        if (entity.getDescricao()==null || entity.getDescricao().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Descrição" + ")");            
        }        
        if (entity.getRegistroPonto().getId()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Registro Ponto" + ")");                                    
        }
        if (entity.getRegistroPonto().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Registro Ponto" + ")");                                    
        }                
    }     
    public void validarLancamentoPontoCadastrado(LancamentoDivergencia entity){
        Ibusiness<LancamentoDivergencia> ibusiness = new businessFactory<LancamentoDivergencia>(entity).getBusiness();
        Predicate<LancamentoDivergencia> predID = p -> p.getId().equals(entity.getId());

        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }                             
    }    
}
