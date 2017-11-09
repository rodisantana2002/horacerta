package pessoa;


import model.entity.Pessoa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import repository.core.conn.repoConnection;
import repository.core.interfaces.Irepository;
import repository.core.repo.concrets.repoPessoa;
import repository.factory.repositoryFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rodolfo
 */
public class repositorio_pessoa {

    /**
     * @param args the command line arguments
     */
    
    //private static Configuration cfg;
    //private static SessionFactory sessionFactory;
 
    
    public static void main(String[] args) {
        // TODO code application logic here
        try {        
        Pessoa pessoa = new Pessoa();                         
        pessoa.setId(4);
        //pessoa.setNome("Rodolfo Santana");
        //repoPessoa repoPessoa = new repoPessoa();
        Irepository<Pessoa> repoPessoa = (Irepository<Pessoa>) new repositoryFactory<Pessoa>(pessoa).getRepository();

        for (Pessoa pessoa1 : repoPessoa.listar(pessoa)){ 
            System.out.println("via repo --->" + pessoa1.getPrimeiroNome());
        }
        
        System.out.println("--->" + repoPessoa.obterById(pessoa, pessoa.getId()).getPrimeiroNome());
        
        //repoPessoa.salvar(pessoa);
        //repoPessoa.remover(pessoa);
        
//        Session session =null;
//        Transaction transaction = null;

//     
//        session = repoConnection.getInstance().getSession();        
//        transaction = session.beginTransaction();   
//        


//        //System.out.println("session : "+ session.toString());
//        session.save(pessoa);
//        transaction.commit();
//        transaction=null;
        }
        catch (Exception ex){ ex.printStackTrace();}
        System.exit(0);
    }

    
}
