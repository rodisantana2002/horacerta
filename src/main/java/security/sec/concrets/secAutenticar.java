/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security.sec.concrets;

import controlls.controll.concrets.ctrlUsuario;
import java.util.List;
import model.entity.Usuario;
import security.core.Isecurity;
/**
 *
 * @author Rodolfo
 */
public class secAutenticar implements Isecurity<Usuario>{
    private ctrlUsuario ctrlusuario;

    public secAutenticar(){
         ctrlusuario = new ctrlUsuario();        
    }

    @Override
    public List<String> autenticarUsuario(Usuario entity) {        
        return ctrlusuario.autenticarUsuario(entity);
    }

    @Override
    public List<String> autenticarTokenUsuario(Usuario entity) {
        return ctrlusuario.autenticarToken(entity);
    }
    
    @Override
    public void efetuarLogout(Usuario entity) {
        ctrlusuario.efetuarLogout(entity);
    }
    
    @Override
    public Usuario getUser(Usuario entity){
        //atualiza token na pessoa e retorna 
       return ctrlusuario.atualizarToken(entity);
    }
}
