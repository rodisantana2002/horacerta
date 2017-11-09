/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencia;

import controlls.controll.concrets.ctrlFrequencia;
import helpers.formatacao.clsTrataDatas;
import helpers.mensagens.clsPSR;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import model.entity.Frequencia;
import model.entity.Pessoa;

/**
 *
 * @author Rodolfo
 */
public class ctrl_frequencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pessoa pessoa = new Pessoa();
        pessoa.setId(20);
        
//        List<Frequencia> lstFrequencia = new ArrayList<Frequencia>();              
//        Frequencia frequencia = new Frequencia();
//        frequencia.setId(01);
//        frequencia.setDataInicio("03/02/2016");
//        frequencia.setDataTermino("02/03/2016");
//        frequencia.setDescricao("Fevereiro/2016");
//        frequencia.setPessoa(pessoa);
//        lstFrequencia.add(frequencia);
//        
//        frequencia = new Frequencia();
//        frequencia.setId(02);
//        frequencia.setDataInicio("03/01/2016");
//        frequencia.setDataTermino("18/02/2016");
//        frequencia.setDescricao("Janeiro/2016");
//        frequencia.setPessoa(pessoa);
//        lstFrequencia.add(frequencia);
//
//        frequencia = new Frequencia();
//        frequencia.setId(03);
//        frequencia.setDataInicio("03/05/2016");
//        frequencia.setDataTermino("24/06/2016");
//        frequencia.setDescricao("Maio/2016");
//        frequencia.setPessoa(pessoa);
//        lstFrequencia.add(frequencia);
//        
//        frequencia = new Frequencia();
//        frequencia.setId(04);
//        frequencia.setDataInicio("03/06/2016");
//        frequencia.setDataTermino("16/07/2016");
//        frequencia.setDescricao("Junho/2016");
//        frequencia.setPessoa(pessoa);
//        lstFrequencia.add(frequencia);
//        
//        frequencia = new Frequencia();
//        frequencia.setId(05);
//        frequencia.setDataInicio("03/07/2016");
//        frequencia.setDataTermino("05/08/2016");
//        frequencia.setDescricao("Agosto/2016");
//        frequencia.setPessoa(pessoa);
//        lstFrequencia.add(frequencia);
//        
//        frequencia = new Frequencia();
//        frequencia.setId(06);
//        frequencia.setDataInicio("03/09/2016");
//        frequencia.setDataTermino("15/10/2016");
//        frequencia.setDescricao("Setembro/2016");
//        frequencia.setPessoa(pessoa);
//        lstFrequencia.add(frequencia);
//        
//        frequencia = new Frequencia();
//        frequencia.setId(07);
//        frequencia.setDataInicio("03/03/2016");
//        frequencia.setDataTermino("06/04/2016");
//        frequencia.setDescricao("Março/2016");
//        frequencia.setPessoa(pessoa);
//        lstFrequencia.add(frequencia);
//        
//        frequencia = new Frequencia();
//        frequencia.setId(8);
//        frequencia.setDataInicio("03/04/2016");
//        frequencia.setDataTermino("05/05/2016");
//        frequencia.setDescricao("Abril/2016");
//        frequencia.setPessoa(pessoa);
//        lstFrequencia.add(frequencia);
//        
//        frequencia = new Frequencia();
//        frequencia.setId(9);
//        frequencia.setDataInicio("03/10/2016");
//        frequencia.setDataTermino("10/11/2016");
//        frequencia.setDescricao("Outubro/2016");
//        frequencia.setPessoa(pessoa);
//        lstFrequencia.add(frequencia);
//        
//        frequencia = new Frequencia();
//        frequencia.setId(10);
//        frequencia.setDataInicio("03/11/2015");
//        frequencia.setDataTermino("22/12/2015");
//        frequencia.setDescricao("Dexembro/2015");
//        frequencia.setPessoa(pessoa);
//        lstFrequencia.add(frequencia);
//
//        frequencia = new Frequencia();
//        frequencia.setId(11);
//        frequencia.setDataInicio("05/07/2016");
//        frequencia.setDataTermino("02/08/2016");
//        frequencia.setDescricao("Julho/2016");
//        frequencia.setPessoa(pessoa);
//        lstFrequencia.add(frequencia);        
//        
//        Collections.sort(lstFrequencia, frequencia);
//        Collections.reverse(lstFrequencia);
        
        clsTrataDatas trataDatas = new clsTrataDatas();
        
        String data = "12/12/2016";
        
       Predicate<Frequencia> predPeriodo = p -> (  //data de registro dentro do periodo informado
                                                    (((trataDatas.isDateMenor(p.getDataInicio(), trataDatas.parseDataEsp("2016-12-12"))) 
                                                                 || p.getDataInicio().equalsIgnoreCase(data)) 
                                                        &&
                                                     ((trataDatas.isDateMaior(p.getDataTermino(), trataDatas.parseDataEsp("2016-12-12")) 
                                                                 || p.getDataTermino().equalsIgnoreCase(data)))));        

        ctrlFrequencia c = new ctrlFrequencia();
        Frequencia frequencia = new Frequencia();
        frequencia.setDataInicio(data);
        frequencia.setDataTermino(data);
        List<Frequencia> lstFreq = c.obterByFilter(frequencia, predPeriodo);        
        
//        for (Frequencia frequencia1 : lstFrequencia){
//            clsPSR.prt(frequencia1.getDataTermino());                   
//        }
        
      //  ctrlFrequencia c = new ctrlFrequencia();
//      c.salvar(frequencia).forEach(s -> clsPSR.prt(s));
        
        //lstFreq.forEach(conf -> clsPSR.prt(conf.getDescricao() + " --> " + conf.getDataTermino()));
        
        System.exit(0);                
    }
    
}
