/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroponto;

import business.core.Ibusiness;
import business.factory.businessFactory;
import helpers.mensagens.clsPSR;
import model.entity.Frequencia;
import model.entity.LancamentoDivergencia;
import model.entity.Pessoa;
import model.entity.RegistroPonto;

/**
 *
 * @author Rodolfo
 */
public class business_registroponto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(4);

            Frequencia frequencia = new Frequencia();
            frequencia.setId(5);
            
            RegistroPonto registroPonto = new RegistroPonto();
            registroPonto.setId(12);
            registroPonto.setDataRegistro("06/05/2015");
            registroPonto.setPessoa(pessoa);
            registroPonto.setFrequencia(frequencia);

//            LancamentoPonto lancamentoPonto = new LancamentoPonto();
//            lancamentoPonto.setId(null);
//            lancamentoPonto.setHoraLancamento(780);
//            lancamentoPonto.setRegistroPonto(registroPonto);
//            lancamentoPonto.setTipoRegistro(TipoRegistro.SAIDA);           
//            registroPonto.getLancamentoPonto().add(lancamentoPonto);
            
            LancamentoDivergencia lancamentoDivergencia = new LancamentoDivergencia();
            lancamentoDivergencia.setId(null);
            lancamentoDivergencia.setDescricao("Falta");
            lancamentoDivergencia.setRegistroPonto(registroPonto);
            registroPonto.getLancamentoDivergencia().add(lancamentoDivergencia);
            
            Ibusiness<RegistroPonto> bsRegistroPonto = (Ibusiness<RegistroPonto>) new businessFactory<RegistroPonto>(registroPonto).getBusiness();
            //bsRegistroPonto.salvar(registroPonto);

            
            for (RegistroPonto regPonto : bsRegistroPonto.listarAll(registroPonto)) {
               // System.out.println("via bs --->" + regPonto.getDataRegistro() + " - " + regPonto.getPessoa().getNomeCompleto());
                regPonto.getLancamentoPonto().forEach(l -> clsPSR.prt(String.valueOf(l.getId()) + " " +  String.valueOf(l.getHoraLancamento())));
                regPonto.getLancamentoDivergencia().forEach(d -> clsPSR.prt(d.getDescricao()));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }    
}
