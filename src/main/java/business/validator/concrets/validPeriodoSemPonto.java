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
import helpers.formatacao.clsTrataDatas;
import java.util.function.Predicate;
import model.entity.PeriodoSemPonto;

/**
 *
 * @author Rodolfo
 */
public class validPeriodoSemPonto extends validGeneric<PeriodoSemPonto>{
    private final clsTrataDatas trataDatas;
    
    public validPeriodoSemPonto(){
        super();
        trataDatas = new clsTrataDatas();
    }
            
    public void validarDatas(PeriodoSemPonto entity){        
        if (!trataDatas.isDate(entity.getData())){
            getLstMsg().add(excMessages.STR_DATA_INVALIDA + " - (" + "Data" + ")"); 
        }        
    }
    
    public void validarCamposObrigatorios(PeriodoSemPonto entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ")");                    
        }
        if (entity.getData().toString().trim().isEmpty() || entity.getData()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Data de Início" + ")");            
        }
        if (entity.getDescricao().trim().isEmpty() || entity.getDescricao()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Descrição" + ")");                        
        }
        if ((entity.getPessoa().getId()==null) || (entity.getPessoa().getId()==0)){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Colaborador" + ")");                                    
        }
    }   
    
    public void validarPeriodoSemPontoCadastrado(PeriodoSemPonto entity){
        Ibusiness<PeriodoSemPonto> ibusiness = new businessFactory<PeriodoSemPonto>(entity).getBusiness();
        Predicate<PeriodoSemPonto> predID = p -> p.getId().equals(entity.getId());
        Predicate<PeriodoSemPonto> predData = p -> ((p.getData().equals(entity.getData())) && (p.getPessoa().getId()==entity.getPessoa().getId()));
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }                             
        if (ibusiness.listarByFilter(entity, predData).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE + " - (Data já foi utilizada para este Colaborador)");
        }                                     
    }    
    public void validarPeriodoSemPontoNaoCadastrado(PeriodoSemPonto entity){
        Ibusiness<PeriodoSemPonto> ibusiness = new businessFactory<PeriodoSemPonto>(entity).getBusiness();
        Predicate<PeriodoSemPonto> predID = p -> p.getId().equals(entity.getId());
        
        if (ibusiness.listarByFilter(entity, predID).size()==0){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }                             
    }     
}
