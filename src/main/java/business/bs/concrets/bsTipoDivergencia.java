/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.TipoDivergencia;

/**
 *
 * @author Rodolfo
 */
public class bsTipoDivergencia extends bsGeneric<TipoDivergencia> {
    public bsTipoDivergencia(){
        super(new TipoDivergencia());
    }
    
    public bsTipoDivergencia(TipoDivergencia tipoDivergencia){
        super(tipoDivergencia);
    }            
}

