/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

import controlls.controll.concrets.ctrlUsuario;
import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import model.entity.Usuario;
import security.core.Secured;

/**
 *
 * @author Rodolfo
 */
@Secured
@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class AuthenticationFilter implements ContainerRequestFilter {    
    ctrlUsuario ctrlUsuario;
    
    public AuthenticationFilter(){
        ctrlUsuario = new ctrlUsuario();
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorizationHeaderToken = requestContext.getHeaderString("token");
        String authorizationHeaderEmail = requestContext.getHeaderString("email");

        if (authorizationHeaderToken == null || authorizationHeaderEmail==null) {
            throw new NotAuthorizedException("A requisicao deve possuir um email ou token para autenticacao");
        } 
                    
        Usuario usuario = new Usuario();
        usuario.getPessoa().setEmail(authorizationHeaderEmail);
        usuario.setToken(authorizationHeaderToken);
        
        try{
            if(!ctrlUsuario.autenticarToken(usuario).isEmpty()){
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
        }
        catch(Exception ex){
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    } 
}
