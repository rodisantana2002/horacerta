/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class bsPessoa extends bsGeneric<Pessoa>{

    public bsPessoa(){
        super(new Pessoa());
    }
    
    public bsPessoa(Pessoa entity) {
        super(entity);
    }
    
}
