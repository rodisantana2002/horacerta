/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import helpers.mensagens.clsPSR;
import model.entity.Usuario;
import repository.core.interfaces.Irepository;
import repository.factory.repositoryFactory;

/**
 *
 * @author Rodolfo
 */
public class repoUsuario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Usuario usuario = new Usuario();
        
        Irepository<Usuario> repoUsuario = (Irepository<Usuario>) new repositoryFactory<Usuario>(usuario).getRepository();
        for (Usuario u : repoUsuario.listar(usuario)){
            clsPSR.prt("oi");
            clsPSR.prt(u.getPessoa().getPrimeiroNome());
        }
        
        System.exit(0);
        
    }
    
}
