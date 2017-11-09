/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencia;

import business.core.Ibusiness;
import business.factory.businessFactory;
import model.entity.Frequencia;
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class business_frequencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // TODO code application logic here
        try {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(4);

            Frequencia frequencia = new Frequencia();
            frequencia.setId(1);
            frequencia.setDataInicio("05/05/2015");
            frequencia.setDataTermino("06/06/2015");
            frequencia.setDescricao("Fevereiro");
            frequencia.setPessoa(pessoa);
            
            Ibusiness<Frequencia> bsFrequencia = (Ibusiness<Frequencia>) new businessFactory<Frequencia>(frequencia).getBusiness();
           // bsFrequencia.salvar(frequencia);

            for (Frequencia freq : bsFrequencia.listarAll(frequencia)) {
               // System.out.println("via bs --->" + freq.getDataInicio()+ " - " + freq.getDataTermino() + freq.getPessoa().getNomeCompleto());
            }
            //System.out.println("--->" + bsFrequencia.consultar(frequencia).getDescricao());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

}
