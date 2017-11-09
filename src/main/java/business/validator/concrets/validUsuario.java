/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.validator.concrets;

import business.core.Ibusiness;
import business.factory.businessFactory;
import business.validator.abstracts.validGeneric;
import java.util.ArrayList;
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
    
    public void validarAutenticacaoUsuario(Usuario entity){
        Ibusiness<Usuario> ibusiness = new businessFactory<Usuario>(entity).getBusiness();
        Predicate<Usuario> predEMail = p -> (p.getPessoa().getEmail().equalsIgnoreCase(entity.getPessoa().getEmail()));

        ArrayList<Usuario> lst = (ArrayList<Usuario>) ibusiness.listarByFilter(entity, predEMail);        
        if (lst.isEmpty()){            
            getLstMsg().add("Email não localizado!");
        }  
        else{       
            if (lst.get(0).getSenha()==null || !lst.get(0).getSenha().equals(entity.getSenha())){
                getLstMsg().add("Senha Incorreta!");
            }
        }                 
    }
    
    public void validarTokenUsuario(Usuario entity){
        Ibusiness<Usuario> ibusiness = new businessFactory<Usuario>(entity).getBusiness();
        Predicate<Usuario> predToken = p -> (p.getPessoa().getEmail().equalsIgnoreCase(entity.getPessoa().getEmail()) && p.getToken().equalsIgnoreCase(entity.getToken()));

        if (ibusiness.listarByFilter(entity, predToken).isEmpty()){
            getLstMsg().add("O Token inválido ou expirado!");                    
        }
    }    
}
