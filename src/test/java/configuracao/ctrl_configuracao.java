/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuracao;

import helpers.mensagens.clsPSR;
import helpers.types.clsTrataJSON;
import java.util.ArrayList;
import java.util.List;
import model.entity.Configuracao;
import model.entity.Pessoa;
import model.transitorio.modElemento;

/**
 *
 * @author Rodolfo
 */
public class ctrl_configuracao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pessoa pessoa = new Pessoa();
        pessoa.setId(20);
        
        Configuracao configuracao = new Configuracao();
        configuracao.setId(20);
        configuracao.setHoraEntrada(50);
        configuracao.setHoraSaida(50);
        configuracao.setIntervalo(60);
        configuracao.setToleranciaDia(10);
        configuracao.setPessoa(pessoa);
        configuracao.setCargaHorariaDia(10);
        
        //ctrlConfiguracao c = new ctrlConfiguracao();
        //c.salvar(configuracao).forEach(s -> clsPSR.prt(s));
        
        //c.obterTodos().forEach(conf -> clsPSR.prt(conf.getPessoa().getPrimeiroNome()));

        clsTrataJSON jSON = new clsTrataJSON();
        List<modElemento> lstEmement = new ArrayList<modElemento>();       
        
        modElemento e = new modElemento();
        e.setIdElement("id");
        e.setValorElement(configuracao.getId().toString());
        lstEmement.add(e);
        e = new modElemento();
        e.setIdElement("horaEntrada");
        e.setValorElement(configuracao.getHoraEntrada().toString());
        lstEmement.add(e);
        
        clsPSR.prt(jSON.getJSON(lstEmement));        
        System.exit(0);
        
    }
    
}
