/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package periodosemponto;

import controlls.controll.concrets.ctrlPeriodoSemPonto;
import helpers.mensagens.clsPSR;
import model.entity.PeriodoSemPonto;
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class ctrl_periodosemponto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pessoa pessoa = new Pessoa();
        pessoa.setId(20);
        
        
        PeriodoSemPonto periodoSemPonto = new PeriodoSemPonto();
        periodoSemPonto.setId(null);
        periodoSemPonto.setDescricao("Natal");
        periodoSemPonto.setData("25/12/2016");
        periodoSemPonto.setPessoa(pessoa);
                
        ctrlPeriodoSemPonto c = new ctrlPeriodoSemPonto();
        c.salvar(periodoSemPonto).forEach(s -> clsPSR.prt(s));
        
        //c.obterTodos().forEach(conf -> clsPSR.prt(conf.getPessoa().getNomeCompleto()));
        
        System.exit(0);        
    }
    
}
