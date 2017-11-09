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
import model.entity.Configuracao;
/**
 *
 * @author Rodolfo
 */
public class validConfiguracao extends validGeneric<Configuracao>{
   
    public validConfiguracao(){
        super();
    }

    public void validarHorario(Configuracao entity){        
        if(entity.getHoraEntrada()>=entity.getHoraSaida()){
            getLstMsg().add(excMessages.STR_HORA_PERIODO_INVALIDO);             
        }
        if(entity.getCargaHorariaDia()>600){
            getLstMsg().add(excMessages.STR_HORA_CARGA_HORARIA_INVALIDA);             
        }
        if(entity.getIntervalo()>120){
            getLstMsg().add(excMessages.STR_HORA_INTERVALO_MAIOR_INVALIDO);             
        }        
        if(entity.getIntervalo()<60){
            getLstMsg().add(excMessages.STR_HORA_INTERVALO_MENOR_INVALIDO);             
        }                
    }    
    public void validarCamposObrigatorios(Configuracao entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ")");                    
        }
        if(entity.getHoraEntrada()<=0 || entity.getHoraEntrada()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Horário de Entrada" + ")");                        
        }        
        if(entity.getHoraSaida()<=0 || entity.getHoraSaida()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Horário de Saída" + ")");                        
        }        
        if(entity.getIntervalo()<=0 || entity.getIntervalo()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Intervalo de Descanso ou Almoço" + ")");                        
        }        
        if(entity.getCargaHorariaDia()<=0 || entity.getCargaHorariaDia()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Carga Horária Diária" + ")");                        
        }                
        if (entity.getPessoa().getId()==null || entity.getPessoa().getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Colaborador" + ")");                                    
        }
    }
    public void validarConfiguracaoCadastrada(Configuracao entity){
        Ibusiness<Configuracao> ibusiness = new businessFactory<Configuracao>(entity).getBusiness();
        Predicate<Configuracao> predID = p -> p.getId()==entity.getId();
        Predicate<Configuracao> predColaborador = p -> p.getPessoa().getId().equals(entity.getPessoa().getId());
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
        
        if (ibusiness.listarByFilter(entity, predColaborador).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE + " (Configuração já foi vinculada a outro Colaborador)");
        }  
    }    
    public void validarConfiguracaoNaoCadastrada(Configuracao entity){
        Ibusiness<Configuracao> ibusiness = new businessFactory<Configuracao>(entity).getBusiness();
        Predicate<Configuracao> predColaborador = p -> (p.getPessoa().getId().equals(entity.getPessoa().getId()));
        
        if (ibusiness.listarByFilter(entity, predColaborador).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }  
    }       
}
