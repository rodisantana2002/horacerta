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
import model.entity.LancamentoPonto;

/**
 *
 * @author Rodolfo
 */
public class validLancamentoPonto extends validGeneric<LancamentoPonto>{
    public validLancamentoPonto(){
        super();
    }
    public void validarCamposObrigatorios(LancamentoPonto entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ")");                    
        }
        if (entity.getHoraLancamento()==null || entity.getHoraLancamento()<=0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Hora do Lançcamento" + ")");            
        }        
        if (entity.getRegistroPonto().getId()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Registro Ponto" + ")");                                    
        }
        if (entity.getRegistroPonto().getId()==0){
           getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Registro Ponto" + ")");                                    
        }                
        if (entity.getTipoRegistro()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Tipo Registro" + ")");                                    
        }
    }     
    public void validarLancamentoPontoCadastrado(LancamentoPonto entity){
        Ibusiness<LancamentoPonto> ibusiness = new businessFactory<LancamentoPonto>(entity).getBusiness();
        Predicate<LancamentoPonto> predID = p -> p.getId().equals(entity.getId());
        Predicate<LancamentoPonto> predHoraLancamento = p -> (p.getHoraLancamento().equals(entity.getHoraLancamento())) && 
                                                             (p.getRegistroPonto().getId()==entity.getRegistroPonto().getId());
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }                             
        if (ibusiness.listarByFilter(entity, predHoraLancamento).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE + " - (O lançamento já foi informado para o Regsitro de Ponto)");
        }                                     
    }   
    public void validarLancamentoPontoNaoCadastrado(LancamentoPonto entity){
        Ibusiness<LancamentoPonto> ibusiness = new businessFactory<LancamentoPonto>(entity).getBusiness();
        Predicate<LancamentoPonto> predID = p -> p.getId().equals(entity.getId());
        
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }                             
    }      
}
