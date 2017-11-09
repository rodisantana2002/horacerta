/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registroponto;

import business.core.Ivalidator;
import business.factory.validatorFactory;
import helpers.mensagens.clsPSR;
import java.util.ArrayList;
import java.util.List;
import model.entity.Frequencia;
import model.entity.Pessoa;
import model.entity.RegistroPonto;

/**
 *
 * @author Rodolfo
 */
public class validator_registroponto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pessoa pessoa = new Pessoa();
        pessoa.setId(4);
        
        Frequencia frequencia = new Frequencia();
        frequencia.setId(5);
        
        RegistroPonto registroPonto = new RegistroPonto();
        registroPonto.setId(null);
        registroPonto.setDataRegistro("05/05/2015");
        registroPonto.setPessoa(pessoa);
        registroPonto.setFrequencia(frequencia);
        
        ArrayList<String> lstMSG = new ArrayList<String>();
        lstMSG.add("validarDatas");        
        lstMSG.add("validarCamposObrigatorios");
        lstMSG.add("validarRegistroPontoCadastrado");
        lstMSG.add("validarIsDataSemPonto");
        lstMSG.add("validarIsIntervaloFrequencia");
        
        Ivalidator<RegistroPonto> ivalidator = new validatorFactory<RegistroPonto>(registroPonto).getValidator();        
        List<String> lstMSGs = ivalidator.validarRegras(registroPonto, lstMSG);
        
        for (String s : lstMSGs){
            clsPSR.prt(s);            
        }    
        
        System.exit(0);                
    }   
}
