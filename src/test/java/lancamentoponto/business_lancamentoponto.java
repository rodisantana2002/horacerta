/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lancamentoponto;

import business.core.Ibusiness;
import business.factory.businessFactory;
import model.entity.LancamentoPonto;
import model.entity.RegistroPonto;
import model.enumeration.TipoRegistro;

/**
 *
 * @author Rodolfo
 */
public class business_lancamentoponto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {           
            RegistroPonto registroPonto = new RegistroPonto();
            registroPonto.setId(3);
            Ibusiness<RegistroPonto> bsRegistroPonto = (Ibusiness<RegistroPonto>) new businessFactory<RegistroPonto>(registroPonto).getBusiness();
            registroPonto = bsRegistroPonto.consultar(registroPonto);
            
            LancamentoPonto lancamentoPonto = new LancamentoPonto();
            lancamentoPonto.setId(null);
            lancamentoPonto.setHoraLancamento(888);
            lancamentoPonto.setRegistroPonto(registroPonto);
            lancamentoPonto.setTipoRegistro(TipoRegistro.ENTRADA);
                       
            Ibusiness<LancamentoPonto> bsLancamentoPonto = (Ibusiness<LancamentoPonto>) new businessFactory<LancamentoPonto>(lancamentoPonto).getBusiness();
            bsLancamentoPonto.salvar(lancamentoPonto);

            for (LancamentoPonto regLancPonto : bsLancamentoPonto.listarAll(lancamentoPonto)) {
               // System.out.println("via bs --->" + regLancPonto.getRegistroPonto().getDataRegistro() + " - " + regLancPonto.getHoraLancamento() + " - " + regLancPonto.getTipoRegistro());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);   
    }    
}
