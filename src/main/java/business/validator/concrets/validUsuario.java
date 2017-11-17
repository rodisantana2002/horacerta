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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import model.entity.Usuario;

/**
 *
 * @author Rodolfo
 */
public class validUsuario extends validGeneric<Usuario>{
    
    public validUsuario(){
        super();
    }
        
    public void validarCamposObrigatorios(Usuario entity){
        if (entity.getId()!=null && entity.getId()==0){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Identificador" + ").");                    
        }
        if (entity.getPessoa().getPrimeiroNome()==null || entity.getPessoa().getPrimeiroNome().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Primeiro nome" + ").");            
        }
        if (entity.getPessoa().getSegundoNome()==null || entity.getPessoa().getSegundoNome().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Segundo nome" + ").");                        
        }
        if (entity.getPessoa().getEmail()==null || entity.getPessoa().getEmail().trim().isEmpty()){
            getLstMsg().add(excMessages.STR_DADOS_OBRIGATORIOS + " - (" + "Email" + ").");                        
        }
    }
    
    public void validarRegistroCadastrado(Usuario entity){
        Ibusiness<Usuario> ibusiness = new businessFactory<Usuario>(entity).getBusiness();
        Predicate<Usuario> predID = p -> p.getId().equals(entity.getId());
        Predicate<Usuario> predEMail = p -> p.getPessoa().getEmail().equalsIgnoreCase(entity.getPessoa().getEmail());
        
        if (ibusiness.listarByFilter(entity, predID).size()>0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE);
        }  
        
        List<Usuario> lstUsuarios = ibusiness.listarByFilter(entity, predEMail);
        if (!lstUsuarios.isEmpty() && lstUsuarios.get(0).getId()!=null && lstUsuarios.get(0).getId()!=0){
            getLstMsg().add(excMessages.STR_REG_JA_EXISTE + " (Email ja foi vinculado a outro Usuário).");
        }  
    }
    
    public void validarRegistroNaoCadastrado(Usuario entity){
        Ibusiness<Usuario> ibusiness = new businessFactory<Usuario>(entity).getBusiness();
        Predicate<Usuario> predID = p -> p.getId().equals(entity.getId());
        if (ibusiness.listarByFilter(entity, predID).isEmpty()){
            getLstMsg().add(excMessages.STR_REG_NAO_EXISTE);
        }          
    }         
    
    public void validarAutenticacaoUsuario(Usuario entity){
        Ibusiness<Usuario> ibusiness = new businessFactory<Usuario>(entity).getBusiness();
        Predicate<Usuario> predEMail = p -> ((p.getPessoa().getEmail().equalsIgnoreCase(entity.getPessoa().getEmail())));

        ArrayList<Usuario> lst = (ArrayList<Usuario>) ibusiness.listarByFilter(entity, predEMail);        
        if (lst.isEmpty()){            
            getLstMsg().add("Usuário não localizado!");
        }  
        else{       
            if (lst.get(0).getSenha()==null || !lst.get(0).getSenha().equals(entity.getSenha())){
                getLstMsg().add("Senha Incorreta!");
            }
        }                 
    }
    
    public void validarTokenUsuario(Usuario entity){
        Ibusiness<Usuario> ibusiness = new businessFactory<Usuario>(entity).getBusiness();
        Predicate<Usuario> predToken = p -> (p.getPessoa().getEmail().equalsIgnoreCase(entity.getPessoa().getEmail()) && 
                                             p.getToken().equalsIgnoreCase(entity.getToken()));

        if (ibusiness.listarByFilter(entity, predToken).isEmpty()){
            getLstMsg().add("O Token inválido ou expirado!");                    
        }
    }     
}
