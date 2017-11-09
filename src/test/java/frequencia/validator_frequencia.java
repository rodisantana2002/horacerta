/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencia;

import business.core.Ivalidator;
import business.factory.validatorFactory;
import helpers.mensagens.clsPSR;
import java.util.ArrayList;
import java.util.List;
import model.entity.Frequencia;
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class validator_frequencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pessoa pessoa = new Pessoa();
        pessoa.setId(5);
        
        Frequencia frequencia = new Frequencia();
        frequencia.setId(45);
        frequencia.setDataInicio("05/06/2015");
        frequencia.setDataTermino("06/06/2015");
        frequencia.setDescricao("4");
        frequencia.setPessoa(pessoa);
                
        
        ArrayList<String> lstMSG = new ArrayList<String>();
        lstMSG.add("validarDatas");        
        lstMSG.add("validarCamposObrigatorios");
        lstMSG.add("validarFrequenciaCadastrada");
        
        Ivalidator<Frequencia> ivalidator = new validatorFactory<Frequencia>(frequencia).getValidator();        
        List<String> lstMSGs = ivalidator.validarRegras(frequencia, lstMSG);
        
        for (String s : lstMSGs){
            clsPSR.prt(s);            
        }    
        
        System.exit(0);        
    }    
}
