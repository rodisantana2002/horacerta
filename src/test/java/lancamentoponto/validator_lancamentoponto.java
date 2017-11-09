/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lancamentoponto;

import business.core.Ibusiness;
import business.core.Ivalidator;
import business.factory.businessFactory;
import business.factory.validatorFactory;
import helpers.mensagens.clsPSR;
import java.util.ArrayList;
import java.util.List;
import model.entity.LancamentoPonto;
import model.entity.RegistroPonto;

/**
 *
 * @author Rodolfo
 */
public class validator_lancamentoponto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RegistroPonto registroPonto = new RegistroPonto();
        registroPonto.setId(2);
        Ibusiness<RegistroPonto> bsRegistroPonto = (Ibusiness<RegistroPonto>) new businessFactory<RegistroPonto>(registroPonto).getBusiness();
        registroPonto = bsRegistroPonto.consultar(registroPonto);
        
        LancamentoPonto lancamentoPonto = new LancamentoPonto();
        lancamentoPonto.setId(null);
        lancamentoPonto.setHoraLancamento(560);
        lancamentoPonto.setRegistroPonto(registroPonto);
        lancamentoPonto.setTipoRegistro(null);        
        
        ArrayList<String> lstMSG = new ArrayList<String>();
        lstMSG.add("validarCamposObrigatorios");
        lstMSG.add("validarLancamentoPontoCadastrado");
        
        Ivalidator<LancamentoPonto> ivalidator = new validatorFactory<LancamentoPonto>(lancamentoPonto).getValidator();        
        List<String> lstMSGs = ivalidator.validarRegras(lancamentoPonto, lstMSG);
        
        for (String s : lstMSGs){
            clsPSR.prt(s);            
        }    
        
        System.exit(0);          
    }    
}
