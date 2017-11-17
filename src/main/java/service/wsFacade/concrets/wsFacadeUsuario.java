/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

import helpers.excecoes.excMessages;
import java.util.List;
import java.util.function.Predicate;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import model.entity.Pessoa;
import model.entity.Usuario;
import security.core.Secured;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("usuario")
public class wsFacadeUsuario extends wsFacadeGeneric<Usuario>{
    @Context   
    SecurityContext securityContext;
    private UriInfo context;    
    
    public wsFacadeUsuario(){
        super(new Usuario());
    }
    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(Usuario entity) {
        return super.create(entity);
    }
    
    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        Usuario usuario = new Usuario();        
        usuario.setId(id);
        return super.delete(usuario);  
    }
    
    @POST
    @Path("validar")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    @Secured
    public Usuario validar(Usuario entity) {
        Usuario user = super.findByID(entity.getId());
        if (user!=null){
            if (entity.getSenha().equals(user.getSenha())){
                return user;                
            }
        }        
        user.setSenha("erro");
        return user;             
    }
      
    @POST
    @Path("alterarsenha")
    @Consumes({"application/json"})
    @Secured
    public String alterarSenha(Usuario entity){
        String msg = super.update(entity);
        return msg;
    }           
    
    @POST
    @Path("isusuario")
    @Consumes({"application/json"})
    public String isUsuario(Usuario usuario) {
        Predicate<Usuario> predEMail = p -> p.getPessoa().getEmail().equals(usuario.getPessoa().getEmail());        
        if(super.findByFilter(usuario, predEMail).isEmpty()){
            return "0";
        }
        return "1";             
    }    
}
