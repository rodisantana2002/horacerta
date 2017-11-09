 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;


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
import security.core.Secured;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("colaborador")
public class wsFacadePessoa extends wsFacadeGeneric<Pessoa>{
    @Context   
    SecurityContext securityContext;
    private UriInfo context;    
    
    public wsFacadePessoa(){
        super(new Pessoa());
    }

    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(Pessoa entity) {
        return super.create(entity);
    }
 
    @POST
    @Consumes({"application/json"})
    @Override
    public String update(Pessoa entity) {
        return super.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        Pessoa pessoa = new Pessoa();        
        pessoa.setId(id);
        return super.delete(pessoa);  
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public Pessoa findByID(@PathParam("id") Integer id) {
        return super.findByID(id);             
    }

    @GET
    @Path("obterbyemail/{email}")
    @Produces({"application/json, application/xml"})
    public Pessoa findByEMail(@PathParam("email") String email) {
        Predicate<Pessoa> predEMail = p -> p.getEmail().startsWith(email);        
        Pessoa pessoa = new Pessoa();
        pessoa.setEmail(email);
        if (super.findByFilter(pessoa, predEMail).isEmpty()){
            return new Pessoa();
        }
        return super.findByFilter(pessoa, predEMail).get(0);             
    }
    
    @GET
    @Path("list/")
    @Produces({"application/json"})
    @Override
    public List<Pessoa> findAll() {
        return super.findAll();  
    }        
}
