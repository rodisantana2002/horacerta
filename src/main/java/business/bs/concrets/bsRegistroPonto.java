/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.RegistroPonto;

/**
 *
 * @author Rodolfo
 */
public class bsRegistroPonto extends bsGeneric<RegistroPonto>{
    public bsRegistroPonto(){
        super(new RegistroPonto());
    }
    public bsRegistroPonto(RegistroPonto registroPonto){
        super(registroPonto);
    }
}
