/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.LancamentoPonto;

/**
 *
 * @author Rodolfo
 */
public class bsLancamentoPonto extends bsGeneric<LancamentoPonto>{
    public bsLancamentoPonto(){
        super(new LancamentoPonto());
    }
    public bsLancamentoPonto(LancamentoPonto lancamentoPonto){
        super(lancamentoPonto);
    }
}
