/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoa;

import business.core.Ivalidator;
import business.factory.validatorFactory;
import helpers.mensagens.clsPSR;
import java.util.ArrayList;
import java.util.List;
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class validator_pessoa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // TODO code application logic here
        
        Pessoa pessoa = new Pessoa();
        pessoa.setId(4);///teste colocando valores e removendo valores
        pessoa.setPrimeiroNome("Rodolfo"); ///teste colocando valores e removendo valores
        pessoa.setSegundoNome("");
        pessoa.setMatricula("009303");
        pessoa.setEmail("rodisantana2002@gmail.com");
        
        ArrayList<String> lstMSG = new ArrayList<String>();
        lstMSG.add("validarCamposObrigatorios");
        lstMSG.add("validarPessoaCadastrada");
        
        Ivalidator<Pessoa> ivalidator = new validatorFactory<Pessoa>(pessoa).getValidator();        
        List<String> lstMSGs = ivalidator.validarRegras(pessoa, lstMSG);
        
        for (String s : lstMSGs){
            clsPSR.prt(s);            
        }    
        
        System.exit(0);
    }    
}
