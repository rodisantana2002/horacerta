/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TipoDivergencia;

import business.core.Ivalidator;
import business.factory.validatorFactory;
import helpers.mensagens.clsPSR;
import java.util.ArrayList;
import java.util.List;
import model.entity.TipoDivergencia;

/**
 *
 * @author Rodolfo
 */
public class validador_tipodivergencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TipoDivergencia entity = new TipoDivergencia();
        entity.setId(null);
        entity.setDescricao(null);
        entity.setTipoSaldo(null);
                
        
        ArrayList<String> lstMSG = new ArrayList<String>();
        lstMSG.add("validarCamposObrigatorios");
        lstMSG.add("validarTipoDivergenciaCadastrada");
        
        Ivalidator<TipoDivergencia> ivalidator = new validatorFactory<TipoDivergencia>(entity).getValidator();        
        List<String> lstMSGs = ivalidator.validarRegras(entity, lstMSG);
        
        for (String s : lstMSGs){
            clsPSR.prt(s);            
        }    
        
        System.exit(0);        
    }        
}
