/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.wsFacade.concrets;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import model.entity.LancamentoPonto;
import service.wsFacade.abstracts.wsFacadeGeneric;

/**
 *
 * @author Rodolfo
 */
@Path("lancponto")
public class wsFacadeLancamentoPonto extends wsFacadeGeneric<LancamentoPonto>{
    @Context   
    private UriInfo context;    
    
    public wsFacadeLancamentoPonto(){
        super(new LancamentoPonto());
    }
    
    @DELETE
    @Path("{id}")
    @Override
    public String delete(@PathParam("id") Integer id) {
        LancamentoPonto lancamentoPonto = new LancamentoPonto();
        lancamentoPonto.setId(id);
        return super.delete(lancamentoPonto);  
    }      
    
}
