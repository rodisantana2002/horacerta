/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.PeriodoSemPonto;

/**
 *
 * @author Rodolfo
 */
public class bsPeriodoSemPonto extends bsGeneric<PeriodoSemPonto> {    
    public bsPeriodoSemPonto(){
        super(new PeriodoSemPonto());
    }
    
    public bsPeriodoSemPonto(PeriodoSemPonto periodoSemPonto){
        super(periodoSemPonto);
    }
}
