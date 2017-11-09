/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.Frequencia;

/**
 *
 * @author Rodolfo
 */
public class bsFrequencia extends bsGeneric<Frequencia>{
    public bsFrequencia(Frequencia frequencia){
        super(frequencia);
    }
    public bsFrequencia(){
        super(new Frequencia());
    }
}
