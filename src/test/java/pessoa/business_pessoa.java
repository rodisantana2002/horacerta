package pessoa;


import business.core.Ibusiness;
import business.factory.businessFactory;
import java.util.function.Predicate;
import model.entity.Pessoa;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rodolfo
 */
public class business_pessoa {

    /**
     * @param args the command line arguments
     */
    
    //private static Configuration cfg;
    //private static SessionFactory sessionFactory;
 
    
    public static void main(String[] args) {
        // TODO code application logic here
        try {        
            Pessoa pessoa = new Pessoa();                         
            pessoa.setId(34);
            pessoa.setPrimeiroNome("Rui");
            pessoa.setSegundoNome("Salete");
            pessoa.setMatricula("009303");
            pessoa.setEmail("inessalte2002@gmail.com");

            //bsPessoa bsPessoa = new bsPessoa();
            Ibusiness<Pessoa> bsPessoa = (Ibusiness<Pessoa>) new businessFactory<Pessoa>(pessoa).getBusiness();
            bsPessoa.salvar(pessoa);
            
            
            Predicate<Pessoa> predPessoa = p -> p.getPrimeiroNome().contains("aaa");

            for (Pessoa pessoa1 : bsPessoa.listarByFilter(pessoa, predPessoa)){ 
                System.out.println("via bs --->" + pessoa1.getPrimeiroNome() + " - " + pessoa1.getSegundoNome());
            }        
            System.out.println("--->" + bsPessoa.consultar(pessoa).getPrimeiroNome());
        }
        catch (Exception ex){ ex.printStackTrace();}                      
        System.exit(0);
    }   
}
