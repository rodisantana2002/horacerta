/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import model.entity.Usuario;
import security.sec.concrets.secAutenticar;

/**
 *
 * @author Rodolfo
 */
@Path("autentication")
public class wsFacadeSecurityAutenticar {
    @Context   
    private UriInfo context;
    private secAutenticar autenticar;

    public wsFacadeSecurityAutenticar(){
        autenticar = new secAutenticar();        
    }
    
    @POST
    @Path("login")    
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response autenticarUsuario(Usuario entity){
        try{
            if(autenticar.autenticarUsuario(entity).isEmpty()){
                return Response.ok(autenticar.getUser(entity)).build();
            }        
            else{
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }        
        }
        catch(Exception ex){
            return Response.serverError().build();
        }
    }    
    
    @POST
    @Path("logout")    
    @Consumes({"application/json"})
    public void efetuarLogout(Usuario entity){
        autenticar.efetuarLogout(entity);
    }        

    @POST
    @Path("esquecisenha")
    @Consumes({"application/json"})
    public String enviarSenha(Usuario usuario){        
        return autenticar.enviarSenha(usuario.getPessoa().getEmail());
    }   
}
