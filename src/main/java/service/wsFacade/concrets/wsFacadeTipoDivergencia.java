/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

import java.util.List;
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
import model.entity.TipoDivergencia;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("tpdivergencia")
public class wsFacadeTipoDivergencia extends wsFacadeGeneric<TipoDivergencia>{
    @Context   
    private UriInfo context;  
    
    public wsFacadeTipoDivergencia() {
        super(new TipoDivergencia());
    }

    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(TipoDivergencia entity) {
        return super.create(entity);
    }
 
    @POST
    @Consumes({"application/json"})
    @Override
    public String update(TipoDivergencia entity) {
        return super.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        TipoDivergencia tpd = new TipoDivergencia();        
        tpd.setId(id);
        return super.delete(tpd);  
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public TipoDivergencia findByID(@PathParam("id") Integer id) {
        return super.findByID(id);             
    }

    @GET
    @Path("list/")
    @Produces({"application/json"})
    @Override
    public List<TipoDivergencia> findAll() {
        return super.findAll();  
    }     
}
