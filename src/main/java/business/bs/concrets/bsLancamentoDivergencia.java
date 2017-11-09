/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.LancamentoDivergencia;

/**
 *
 * @author Rodolfo
 */
public class bsLancamentoDivergencia extends bsGeneric<LancamentoDivergencia>{
    public bsLancamentoDivergencia(){
        super(new LancamentoDivergencia());
    }
    public bsLancamentoDivergencia(LancamentoDivergencia lancamentoDivergencia){
        super(lancamentoDivergencia);
    }
}
