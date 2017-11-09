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
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class validPessoa extends validGeneric<Pessoa>{
    
    public validPessoa(){
        super();
    }
    
    public void validarCamposObrigatorios(Pessoa entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ").");                    
        }
        if (entity.getPrimeiroNome().trim().isEmpty() || entity.getPrimeiroNome()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Primeiro nome" + ").");            
        }
        if (entity.getSegundoNome().trim().isEmpty() || entity.getSegundoNome()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Segundo nome" + ").");                        
        }
        if (entity.getMatricula().trim().isEmpty() || entity.getMatricula()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Matricula" + ").");                        
        }
        if (entity.getEmail().trim().isEmpty() || entity.getEmail()==null){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Email" + ").");                        
        }
    }
    public void validarPessoaCadastrada(Pessoa entity){
        Ibusiness<Pessoa> ibusiness = new businessFactory<Pessoa>(entity).getBusiness();
        Predicate<Pessoa> predID = p -> p.getId().equals(entity.getId());
        Predicate<Pessoa> predEMail = p -> p.getEmail().equalsIgnoreCase(entity.getEmail());
        Predicate<Pessoa> predMatricula = p -> p.getMatricula().equalsIgnoreCase(entity.getMatricula());
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
        
        if (ibusiness.listarByFilter(entity, predEMail).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE + " (Email jÃ¡ vinculado a outra Pessoa).");
        }  

        if (ibusiness.listarByFilter(entity, predMatricula).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE + " (NÃºmero de MatrÃ­cula jÃ¡ vinculada a outra Pessoa).");
        }          
    }
    public void validarPessoaNaoCadastrada(Pessoa entity){
        Ibusiness<Pessoa> ibusiness = new businessFactory<Pessoa>(entity).getBusiness();
        Predicate<Pessoa> predID = p -> p.getId().equals(entity.getId());
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }          
    }    
}
