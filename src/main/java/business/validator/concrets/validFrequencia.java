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
import model.entity.Frequencia;

/**
 *
 * @author Rodolfo
 */
public class validFrequencia extends validGeneric<Frequencia>{
    private final clsTrataDatas trataDatas;
    
    public validFrequencia(){
        super();
        trataDatas = new clsTrataDatas();                
    }
    
    public void validarDatas(Frequencia entity){        
        if (!trataDatas.isDate(entity.getDataInicio())){
            getLstMsg().add(excMessages.STR_DATA_INVALIDA + " - (" + "Data Início" + ")"); 
        }        
        if (!trataDatas.isDate(entity.getDataTermino())){
            getLstMsg().add(excMessages.STR_DATA_INVALIDA + " - (" + "Data Término" + ")"); 
        }               
        if(trataDatas.isDateMaior(trataDatas.parseDataBraIso(entity.getDataInicio()), trataDatas.parseDataBraIso(entity.getDataTermino()))){
            getLstMsg().add(excMessages.STR_DATA_PERIODO);             
        }
    }
    public void validarCamposObrigatorios(Frequencia entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ")");                    
        }
        if (entity.getDataInicio().toString().trim().isEmpty() || entity.getDataInicio()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Data de Início" + ")");            
        }
        if (entity.getDataTermino().toString().trim().isEmpty() || entity.getDataTermino()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Data de Término" + ")");                        
        }
        if (entity.getDescricao().trim().isEmpty() || entity.getDescricao()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Descrição" + ")");                        
        }
        if (entity.getPessoa().getId()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Colaborador" + ")");                                    
        }
        if (entity.getPessoa().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Colaborador" + ")");                                    
        }        
    }
    public void validarFrequenciaCadastrada(Frequencia entity){
        Ibusiness<Frequencia> ibusiness = new businessFactory<Frequencia>(entity).getBusiness();
        Predicate<Frequencia> predID = p -> p.getId().equals(entity.getId());
        Predicate<Frequencia> predPeriodo = p -> ((  //somente a data de inicio dentro de um periodo cadastrado
                                                    (((trataDatas.isDateMenor(trataDatas.parseDataBraIso(p.getDataInicio()), trataDatas.parseDataBraIso(entity.getDataInicio()))) 
                                                                 || p.getDataInicio().equalsIgnoreCase(entity.getDataInicio())) 
                                                        &&
                                                     ((trataDatas.isDateMaior(trataDatas.parseDataBraIso(p.getDataTermino()), trataDatas.parseDataBraIso(entity.getDataInicio()))) 
                                                                 || p.getDataTermino().equalsIgnoreCase(entity.getDataInicio())))  
                                                  || //somente data de termino dentro de um periodo cadastrado
                                                    (((trataDatas.isDateMenor(trataDatas.parseDataBraIso(p.getDataInicio()), trataDatas.parseDataBraIso(entity.getDataTermino()))) 
                                                                 || p.getDataInicio().equalsIgnoreCase(entity.getDataTermino())) 
                                                        &&
                                                     ((trataDatas.isDateMaior(trataDatas.parseDataBraIso(p.getDataTermino()), trataDatas.parseDataBraIso(entity.getDataTermino()))) 
                                                                 || p.getDataTermino().equalsIgnoreCase(entity.getDataTermino())))                                                                     
                                                  || //periodo interno já cadastrado
                                                    (((trataDatas.isDateMenor(trataDatas.parseDataBraIso(p.getDataInicio()), trataDatas.parseDataBraIso(entity.getDataInicio()))) 
                                                                 || p.getDataInicio().equalsIgnoreCase(entity.getDataInicio())) 
                                                        &&
                                                     ((trataDatas.isDateMaior(trataDatas.parseDataBraIso(p.getDataTermino()), trataDatas.parseDataBraIso(entity.getDataTermino()))) 
                                                                 || p.getDataTermino().equalsIgnoreCase(entity.getDataTermino())))                                                                                                                                 
                                                  ||  //periodo externo maior que um periodo cadastrado
                                                    (((trataDatas.isDateMaior(trataDatas.parseDataBraIso(p.getDataInicio()), trataDatas.parseDataBraIso(entity.getDataInicio()))) 
                                                                 || p.getDataInicio().equalsIgnoreCase(entity.getDataInicio())) 
                                                        &&
                                                     ((trataDatas.isDateMenor(trataDatas.parseDataBraIso(p.getDataTermino()), trataDatas.parseDataBraIso(entity.getDataTermino()))) 
                                                                 || p.getDataTermino().equalsIgnoreCase(entity.getDataTermino())))                                                                                                                                                 
                                                   )          
                                                    && (p.getPessoa().getId()==entity.getPessoa().getId())
                                                 );
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }                             
        if (ibusiness.listarByFilter(entity, predPeriodo).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE + " - (Periodo já foi utilizadoa para este Colaborador)");
        }                                     
    }
    public void validarFrequenciaNaoCadastrada(Frequencia entity){
        Ibusiness<Frequencia> ibusiness = new businessFactory<Frequencia>(entity).getBusiness();
        Predicate<Frequencia> predID = p -> p.getId().equals(entity.getId());
       
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }  
    }         
}
