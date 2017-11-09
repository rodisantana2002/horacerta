/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package periodosemponto;

import business.core.Ivalidator;
import business.factory.validatorFactory;
import helpers.mensagens.clsPSR;
import java.util.ArrayList;
import java.util.List;
import model.entity.PeriodoSemPonto;
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class validador_periodosemponto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pessoa pessoa = new Pessoa();
        pessoa.setId(4);
        
        PeriodoSemPonto periodoSemPonto = new PeriodoSemPonto();
        periodoSemPonto.setId(4);
        periodoSemPonto.setData("06/05/2015");
        periodoSemPonto.setDescricao("DIA DE FINADOS");
        periodoSemPonto.setPessoa(pessoa);
                
        
        ArrayList<String> lstMSG = new ArrayList<String>();
        lstMSG.add("validarDatas");        
        lstMSG.add("validarCamposObrigatorios");
        lstMSG.add("validarFrequenciaCadastrada");
        
        Ivalidator<PeriodoSemPonto> ivalidator = new validatorFactory<PeriodoSemPonto>(periodoSemPonto).getValidator();        
        List<String> lstMSGs = ivalidator.validarRegras(periodoSemPonto, lstMSG);
        
        for (String s : lstMSGs){
            clsPSR.prt(s);            
        }    
        
        System.exit(0);   
    }
    
}
