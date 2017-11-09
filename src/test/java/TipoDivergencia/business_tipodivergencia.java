/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TipoDivergencia;

import business.core.Ibusiness;
import business.factory.businessFactory;
import model.entity.TipoDivergencia;
import model.enumeration.TipoSaldo;

/**
 *
 * @author Rodolfo
 */
public class business_tipodivergencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            TipoDivergencia tipoDivergencia = new TipoDivergencia();
            tipoDivergencia.setId(null);
            tipoDivergencia.setDescricao("ATESTADO");
            tipoDivergencia.setTipoSaldo(TipoSaldo.NEGATIVO);
            
            Ibusiness<TipoDivergencia> bsTipoDivergencia = (Ibusiness<TipoDivergencia>) new businessFactory<TipoDivergencia>(tipoDivergencia).getBusiness();
            bsTipoDivergencia.salvar(tipoDivergencia);

            for (TipoDivergencia tipoDivergencias : bsTipoDivergencia.listarAll(tipoDivergencia)) {
                System.out.println("via bs --->" + tipoDivergencias.getDescricao());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);        
    }    
}
