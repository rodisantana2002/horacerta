/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.Usuario;

/**
 *
 * @author Rodolfo
 */
public class bsUsuario extends bsGeneric<Usuario>{

    public bsUsuario(){
        super(new Usuario());
    }
    
    public bsUsuario(Usuario entity) {
        super(entity);
    }    
}
