/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

import helpers.formatacao.clsTrataDatas;
import helpers.mensagens.clsPSR;
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
import javax.ws.rs.core.UriInfo;
import model.entity.Pessoa;
import model.entity.RegistroPonto;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("regponto")
public class wsFacadeRegistroPonto extends wsFacadeGeneric<RegistroPonto>{
    @Context   
    private UriInfo context;    
    private clsTrataDatas trataDatas;
    
    public wsFacadeRegistroPonto(){
        super(new RegistroPonto());
        trataDatas = new clsTrataDatas();
    }

    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(RegistroPonto entity) {
        return super.create(entity);
    }
 
    @POST
    @Consumes({"application/json"})
    @Override
    public String update(RegistroPonto entity) {
        return super.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        RegistroPonto regponto = new RegistroPonto();        
        regponto.setId(id);
        return super.delete(regponto);  
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public RegistroPonto findByID(@PathParam("id") Integer id) {
        return super.findByID(id);             
    }

    @GET
    @Path("list/")
    @Produces({"application/json"})
    @Override
    public List<RegistroPonto> findAll() {
        return super.findAll();  
    }      
    
    @GET
    @Path("colaborador/{id}/list/")
    @Produces({"application/json"})
    public List<RegistroPonto> findAllByColaborador(@PathParam("id") Integer id) {
        Predicate<RegistroPonto> predData = p -> (p.getPessoa().getId().equals(id));        
        
        RegistroPonto registroPonto = new RegistroPonto();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        registroPonto.setPessoa(pessoa);
        
        return super.findByFilter(registroPonto, predData);             
    }    

    @GET
    @Path("colaborador/{id}/obterbydata/{data}")
    @Produces({"application/json"})
    public RegistroPonto findByColaboradorByData(@PathParam("id") Integer id, @PathParam("data") String data) {
        Predicate<RegistroPonto> predData = p -> ((p.getDataRegistro().equals(trataDatas.parseDataEsp(data))) 
                                                  &&
                                                  (p.getPessoa().getId().equals(id)));        
        
        RegistroPonto registroPonto = new RegistroPonto();
        registroPonto.setDataRegistro(trataDatas.parseDataEsp(data));
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        registroPonto.setPessoa(pessoa);
        
        if (super.findByFilter(registroPonto, predData).isEmpty()){
            return null;
        }
        return super.findByFilter(registroPonto, predData).get(0);             
    }    
}
