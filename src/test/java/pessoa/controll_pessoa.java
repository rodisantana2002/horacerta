/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pessoa;

import controlls.controll.concrets.ctrlUsuario;
import model.entity.Pessoa;
import model.entity.Usuario;

/**
 *
 * @author Rodolfo
 */
public class controll_pessoa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pessoa pessoa = new Pessoa();                         
        pessoa.setId(20);
        pessoa.setPrimeiroNome("Vera");
        pessoa.setSegundoNome("Verao");
        pessoa.setMatricula("006666");
        pessoa.setEmail("rodolfo");
        
        Usuario usuario = new Usuario();
        usuario.setSenha("Teste");
        usuario.setToken("djk343c575l8fbr6tueer1t20h");
        usuario.setPessoa(pessoa);
        
        ctrlUsuario cpessoa = new ctrlUsuario();
        //cpessoa.autenticarUsuario(usuario).forEach(e -> clsPSR.prt(e));       
        //clsPSR.prt(cpessoa.atualizarToken(usuario).getToken());
        cpessoa.efetuarLogout(usuario);
        
       // cpessoa.autenticarToken(pessoa).forEach(e -> clsPSR.prt(e));     ;

        
        
        System.exit(0);
    }   
}
