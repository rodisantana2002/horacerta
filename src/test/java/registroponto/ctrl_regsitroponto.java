/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroponto;

import controlls.controll.concrets.ctrlRegistroPonto;
import helpers.formatacao.clsTrataDatas;
import helpers.mensagens.clsPSR;
import model.entity.Frequencia;
import model.entity.LancamentoDivergencia;
import model.entity.LancamentoPonto;
import model.entity.Pessoa;
import model.entity.RegistroPonto;
import model.enumeration.TipoRegistro;

/**
 *
 * @author Rodolfo
 */
public class ctrl_regsitroponto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pessoa pessoa = new Pessoa();
        pessoa.setId(20);
        clsTrataDatas trataDatas = new clsTrataDatas();
        
        Frequencia frequencia = new Frequencia();
        frequencia.setId(35);
        
        RegistroPonto registroPonto = new RegistroPonto();
        registroPonto.setId(null);
        registroPonto.setDataRegistro("04/12/2018");
        registroPonto.setPessoa(pessoa);
        registroPonto.setFrequencia(frequencia);
                
        
            LancamentoPonto lancamentoPonto = new LancamentoPonto();
            lancamentoPonto.setId(null);
            lancamentoPonto.setHoraLancamento(780);
            lancamentoPonto.setRegistroPonto(registroPonto);
            lancamentoPonto.setTipoRegistro(TipoRegistro.ENTRADA);           
            registroPonto.getLancamentoPonto().add(lancamentoPonto);

            lancamentoPonto = new LancamentoPonto();
            lancamentoPonto.setId(null);
            lancamentoPonto.setHoraLancamento(990);
            lancamentoPonto.setRegistroPonto(registroPonto);
            lancamentoPonto.setTipoRegistro(TipoRegistro.SAIDA);           
            registroPonto.getLancamentoPonto().add(lancamentoPonto);
            
            LancamentoDivergencia lancamentoDivergencia = new LancamentoDivergencia();
            lancamentoDivergencia.setId(null);
            lancamentoDivergencia.setDescricao("Falta");
            lancamentoDivergencia.setRegistroPonto(registroPonto);
            registroPonto.getLancamentoDivergencia().add(lancamentoDivergencia);
               
        //clsPSR.prt(trataDatas.parseDataEsp(registroPonto.getDataRegistro()));

        ctrlRegistroPonto regponto = new ctrlRegistroPonto();
        regponto.salvar(registroPonto).forEach(e -> clsPSR.prt(e));
        //regponto.deletar(pessoa).forEach(e -> clsPSR.prt(e));
        System.exit(0);            
    }
    
}
