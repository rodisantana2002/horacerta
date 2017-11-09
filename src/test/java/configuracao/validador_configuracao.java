/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuracao;

import business.core.Ivalidator;
import business.factory.validatorFactory;
import helpers.mensagens.clsPSR;
import java.util.ArrayList;
import java.util.List;
import model.entity.Configuracao;
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class validador_configuracao {
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(4);
        
        Configuracao configuracao = new Configuracao();        
        configuracao.setId(null);
        configuracao.setHoraEntrada(0);
        configuracao.setHoraSaida(0);
        configuracao.setIntervalo(0);
        configuracao.setToleranciaDia(0);
        configuracao.setCargaHorariaDia(configuracao.getHoraEntrada() + configuracao.getHoraSaida());
        configuracao.setPessoa(pessoa);                
        
        ArrayList<String> lstMSG = new ArrayList<String>();
        lstMSG.add("validarHorario");        
        lstMSG.add("validarCamposObrigatorios");
        lstMSG.add("validarConfiguracaoCadastrada");
        
        Ivalidator<Configuracao> ivalidator = new validatorFactory<Configuracao>(configuracao).getValidator();        
        List<String> lstMSGs = ivalidator.validarRegras(configuracao, lstMSG);
                
        for (String s : lstMSGs){
            clsPSR.prt(s);            
        }    
        
        System.exit(0);                
    }    
}
