/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

import helpers.formatacao.clsTrataDatas;
import java.util.ArrayList;
import java.util.Collections;
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
import model.entity.Frequencia;
import model.entity.Pessoa;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("frequencia")
public class wsFacadeFrequencia extends wsFacadeGeneric<Frequencia>{
    @Context   
    private UriInfo context;  
    private final clsTrataDatas trataDatas;
    
    public wsFacadeFrequencia() {
        super(new Frequencia());
        trataDatas = new clsTrataDatas();         
    }

    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(Frequencia entity) {
        return super.create(entity);
    }
 
    @POST
    @Consumes({"application/json"})
    @Override
    public String update(Frequencia entity) {
        return super.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        Frequencia cfg = new Frequencia();        
        cfg.setId(id);
        return super.delete(cfg);  
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public Frequencia findByID(@PathParam("id") Integer id) {
        return super.findByID(id);             
    }

    @GET
    @Path("list/")
    @Produces({"application/json"})
    @Override
    public List<Frequencia> findAll() {
        return super.findAll();  
    }     
    
    @GET
    @Path("colaborador/{id}")
    @Produces("application/json")
    public List<Frequencia> findByColaborador(@PathParam("id") Integer id) {        
        Predicate<Frequencia> predID = p -> p.getPessoa().getId().equals(id);        
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);

        Frequencia frequencia = new Frequencia();
        frequencia.setPessoa(pessoa);        
        List<Frequencia> lstFreq = super.findByFilter(frequencia, predID);  
        
        Collections.sort(lstFreq, frequencia);
        Collections.reverse(lstFreq);
        
        if (lstFreq.isEmpty()) {
            return new ArrayList<Frequencia>();
        }        
        return lstFreq;        
    }   
    
   @GET
   @Path("colaborador/{id}/obterbydata/{data}")
   @Produces({"application/json"})
   public List<Frequencia> findByColaboradorByData(@PathParam("id") Integer id, @PathParam("data") String data){        
        Predicate<Frequencia> predPeriodo = p-> (((trataDatas.isDateMenor(trataDatas.parseDataBraIso(p.getDataInicio()),data) || p.getDataInicio().equalsIgnoreCase(trataDatas.parseDataEsp(data)))
                                                &&    
                                                (trataDatas.isDateMaior(trataDatas.parseDataBraIso(p.getDataTermino()),data) || p.getDataTermino().equalsIgnoreCase(trataDatas.parseDataEsp(data))))
                                                && p.getPessoa().getId().equals(id));
        
        Frequencia frequencia = new Frequencia();        
        frequencia.setDataInicio(data);
        frequencia.setDataTermino(data);
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        frequencia.setPessoa(pessoa);
        List<Frequencia> lstFreq = super.findByFilter(frequencia, predPeriodo);

        return lstFreq;
   } 
}
