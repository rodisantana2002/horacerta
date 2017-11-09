/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TipoDivergencia;

import controlls.controll.concrets.ctrlTipoDivergencia;
import helpers.mensagens.clsPSR;
import model.entity.TipoDivergencia;
import model.enumeration.TipoSaldo;

/**
 *
 * @author Rodolfo
 */
public class crtl_tipodivergencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        TipoDivergencia tipoDivergencia = new TipoDivergencia();
        tipoDivergencia.setId(7);
        tipoDivergencia.setDescricao("ATRASO");
        tipoDivergencia.setTipoSaldo(TipoSaldo.NEGATIVO);
        
        ctrlTipoDivergencia td = new ctrlTipoDivergencia();
        td.salvar(tipoDivergencia);
        
        
        td.obterTodos().forEach(tpd -> clsPSR.prt(tpd.getDescricao() + " " + tpd.getTipoSaldo()));        
        System.exit(0);                              
    }    
}
