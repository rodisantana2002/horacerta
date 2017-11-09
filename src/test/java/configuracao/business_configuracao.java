/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuracao;

import business.core.Ibusiness;
import business.factory.businessFactory;
import model.entity.Configuracao;
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class business_configuracao {
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(4);
        
        Configuracao configuracao = new Configuracao();        
        configuracao.setId(null);
        configuracao.setHoraEntrada(120);
        configuracao.setHoraSaida(180);
        configuracao.setIntervalo(60);
        configuracao.setToleranciaDia(10);
        configuracao.setCargaHorariaDia(configuracao.getHoraEntrada() + configuracao.getHoraSaida());
        configuracao.setPessoa(pessoa);                
        
        try{
            Ibusiness<Configuracao> bsConfiguracao = (Ibusiness<Configuracao>) new businessFactory<Configuracao>(configuracao).getBusiness();
            bsConfiguracao.salvar(configuracao);

            for (Configuracao configuracao1 : bsConfiguracao.listarAll(configuracao)) {
                //System.out.println("via bs --->" + configuracao1.getPessoa().getNomeCompleto());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }       
        System.exit(0);                
    }    
}
