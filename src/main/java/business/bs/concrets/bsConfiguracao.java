/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.bs.concrets;

import business.bs.abstracts.bsGeneric;
import model.entity.Configuracao;

/**
 *
 * @author Rodolfo
 */
public class bsConfiguracao extends bsGeneric<Configuracao>{
    public bsConfiguracao(){
        super(new Configuracao());
    }
    
    public bsConfiguracao(Configuracao configuracao){
        super(configuracao);
    }
}
