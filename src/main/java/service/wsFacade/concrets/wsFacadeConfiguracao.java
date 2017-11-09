/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

import helpers.formatacao.clsTrataHorario;
import helpers.types.clsTrataJSON;
import java.util.ArrayList;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import model.entity.Configuracao;
import model.entity.Pessoa;
import model.transitorio.modElemento;
import org.json.JSONObject;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("configuracao")
public class wsFacadeConfiguracao extends wsFacadeGeneric<Configuracao> {

    @Context
    private UriInfo context;
    private clsTrataHorario trataHorario;
    private clsTrataJSON trataJSON;
    private JSONObject jSONObject;

    public wsFacadeConfiguracao() {
        super(new Configuracao());
        trataHorario = new clsTrataHorario();
        trataJSON = new clsTrataJSON();        
    }

    @PUT
    @Path("add/")
    @Consumes({"application/json"})
    @Override
    public String create(Configuracao entity) {
        return super.create(entity);
    }

    @POST
    @Consumes({"application/json"})
    @Override
    public String update(Configuracao entity) {
        return super.update(entity);
    }

    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        Configuracao cfg = new Configuracao();
        cfg.setId(id);
        return super.delete(cfg);
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    @Override
    public Configuracao findByID(@PathParam("id") Integer id) {
        return super.findByID(id);
    }

    @GET
    @Path("colaborador/{id}")
    @Produces("application/json")
    public Configuracao findByColaborador(@PathParam("id") Integer id) {
        Predicate<Configuracao> predColaborador = p -> p.getPessoa().getId().equals(id);        
        Configuracao configuracao = new Configuracao();
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);        
        configuracao.setPessoa(pessoa);
        
        return super.findByFilter(configuracao, predColaborador).get(0);
    }

    @GET
    @Path("list/")
    @Produces({"application/json"})
    @Override
    public List<Configuracao> findAll() {
        return super.findAll();
    }
/*
//        String strResponse = getJSON(id);        
//        
//        if (strResponse.isEmpty()) {
//            return Response.status(Response.Status.NOT_FOUND).build();
//        }        
//        jSONObject = new JSONObject(strResponse);
//        return Response.ok(jSONObject.toString()).build();
    
    private String getJSON(Integer id) {
        Predicate<Configuracao> predID = p -> p.getPessoa().getId().equals(id);
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);

        Configuracao configuracao = new Configuracao();
        configuracao.setPessoa(pessoa);        
        List<Configuracao> lstConfig = super.findByFilter(configuracao, predID);              
        
        if(lstConfig.isEmpty()){
            return "";
        }
        
        Configuracao conf = lstConfig.get(0);        
        List<modElemento> lstElements = new ArrayList<modElemento>();       
        modElemento elemento = new modElemento();
        elemento.setIdElement("id");
        elemento.setValorElement(String.valueOf(conf.getId()));
        lstElements.add(elemento);
        //----
        elemento = new modElemento();
        elemento.setIdElement("horaEntrada");
        elemento.setValorElement(trataHorario.converterHora(conf.getHoraEntrada()).getHorario());
        lstElements.add(elemento);
        //----
        elemento = new modElemento();
        elemento.setIdElement("horaSaida");
        elemento.setValorElement(trataHorario.converterHora(conf.getHoraSaida()).getHorario());
        lstElements.add(elemento);
        //----
        elemento = new modElemento();
        elemento.setIdElement("cargaHorariaDia");
        elemento.setValorElement(trataHorario.converterHora(conf.getCargaHorariaDia()).getHorario());
        lstElements.add(elemento);
        //----
        elemento = new modElemento();
        elemento.setIdElement("intervalo");
        elemento.setValorElement(trataHorario.converterHora(conf.getIntervalo()).getHorario());
        lstElements.add(elemento);
        //----
        elemento = new modElemento();
        elemento.setIdElement("toleranciaDia");
        elemento.setValorElement(trataHorario.converterHora(conf.getToleranciaDia()).getHorario());
        lstElements.add(elemento);
        
        return trataJSON.getJSON(lstElements);        
    }
*/
}
