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
import model.entity.TipoDivergencia;

/**
 *
 * @author Rodolfo
 */
public class validTipoDivergencia extends validGeneric<TipoDivergencia>{
    public validTipoDivergencia(){
        super();
    }
    
    public void validarCamposObrigatorios(TipoDivergencia entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ")");                    
        }
        if (entity.getDescricao()==null || entity.getDescricao().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Descrição" + ")");                        
        }
        if (entity.getTipoSaldo()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Tipo Saldo" + ")");                        
        }
    }
    public void validarTipoDivergenciaCadastrada(TipoDivergencia entity){
        Ibusiness<TipoDivergencia> ibusiness = new businessFactory<TipoDivergencia>(entity).getBusiness();
        Predicate<TipoDivergencia> predID = p -> p.getId().equals(entity.getId());
        Predicate<TipoDivergencia> predDescricao = p -> p.getDescricao().equalsIgnoreCase(entity.getDescricao());

        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
        
        if (ibusiness.listarByFilter(entity, predDescricao).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE + " (Descrição já foi vinculada a outro Tipo de Divergência)");
        }  
    }    
    public void validarTipoDivergenciaNaoCadastrada(TipoDivergencia entity){
        Ibusiness<TipoDivergencia> ibusiness = new businessFactory<TipoDivergencia>(entity).getBusiness();
        Predicate<TipoDivergencia> predID = p -> p.getId().equals(entity.getId());
       
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }  
    }    
}
