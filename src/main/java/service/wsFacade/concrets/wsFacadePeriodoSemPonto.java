/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

import helpers.formatacao.clsTrataDatas;
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
import model.entity.PeriodoSemPonto;
import model.entity.Pessoa;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("periodosemponto")
public class wsFacadePeriodoSemPonto extends wsFacadeGeneric<PeriodoSemPonto>{
    @Context   
    private UriInfo context;  
    private final clsTrataDatas trataDatas;
    
    public wsFacadePeriodoSemPonto() {
        super(new PeriodoSemPonto());
        trataDatas = new clsTrataDatas();        
    }

    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(PeriodoSemPonto entity) {
        return super.create(entity);
    }
 
    @POST
    @Consumes({"application/json"})
    @Override
    public String update(PeriodoSemPonto entity) {
        return super.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        PeriodoSemPonto cfg = new PeriodoSemPonto();        
        cfg.setId(id);
        return super.delete(cfg);  
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public PeriodoSemPonto findByID(@PathParam("id") Integer id) {
        return super.findByID(id);             
    }

    @GET
    @Path("list/")
    @Produces({"application/json"})
    @Override
    public List<PeriodoSemPonto> findAll() {
        return super.findAll();  
    }  

    
    @GET
    @Path("colaborador/{id}/list/")
    @Produces({"application/json"})
    public List<PeriodoSemPonto> findAllByColaborador(@PathParam("id") Integer id) {
        Predicate<PeriodoSemPonto> predData = p -> p.getPessoa().getId().equals(id);        
        PeriodoSemPonto periodoSemPonto = new PeriodoSemPonto();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);        
        periodoSemPonto.setPessoa(pessoa);
        
        return super.findByFilter(periodoSemPonto, predData);
    }    
    
    @GET
    @Path("colaborador/{id}/validardata/{data}")
    @Produces({"text/plain"})
    public String findByColaboradorByData(@PathParam("id") Integer id, @PathParam("data") String data) {
        Predicate<PeriodoSemPonto> predData = p -> (p.getData().equals(trataDatas.parseDataEsp(data))) && p.getPessoa().getId().equals(id);        
        PeriodoSemPonto periodoSemPonto = new PeriodoSemPonto();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        
        periodoSemPonto.setData(trataDatas.parseDataEsp(data));
        periodoSemPonto.setPessoa(pessoa);
        if (super.findByFilter(periodoSemPonto, predData).isEmpty()){
            return "Hoje temos expediente, registre seu ponto";
        }
        return "Hoje não temos expediente, aproveite!";             
    }    
}
