/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package periodosemponto;

import business.core.Ibusiness;
import business.factory.businessFactory;
import java.util.function.Predicate;
import model.entity.PeriodoSemPonto;
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class business_periodosemponto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {        
            Pessoa pessoa = new Pessoa();
            pessoa.setId(4);
            
            PeriodoSemPonto periodoSemPonto = new PeriodoSemPonto();                         
            periodoSemPonto.setId(1);
            periodoSemPonto.setData("06/05/2015");
            periodoSemPonto.setDescricao("Dia do bosta");
            periodoSemPonto.setPessoa(pessoa);

            Ibusiness<PeriodoSemPonto> bsPeriodoSP = (Ibusiness<PeriodoSemPonto>) new businessFactory<PeriodoSemPonto>(periodoSemPonto).getBusiness();
            //bsPeriodoSP.salvar(periodoSemPonto);

            Predicate<PeriodoSemPonto> predPeriodoSP = p -> p.getPessoa().getPrimeiroNome().contains("L");
            //bsPeriodoSP.listarByFilter(periodoSemPonto, predPeriodoSP).forEach(e -> System.out.println("via bs --->" + e.getPessoa().getNomeCompleto()));
                    
        }
        catch (Exception ex){ ex.printStackTrace();}
        System.exit(0);        
    }    
}
