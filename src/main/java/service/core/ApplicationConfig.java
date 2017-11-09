/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.core;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Rodolfo
 */
@javax.ws.rs.ApplicationPath("services")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.wsFacade.concrets.AuthenticationFilter.class);
        resources.add(service.wsFacade.concrets.wsFacadeConfiguracao.class);
        resources.add(service.wsFacade.concrets.wsFacadeFrequencia.class);
        resources.add(service.wsFacade.concrets.wsFacadeLancamentoPonto.class);
        resources.add(service.wsFacade.concrets.wsFacadePeriodoSemPonto.class);
        resources.add(service.wsFacade.concrets.wsFacadePessoa.class);
        resources.add(service.wsFacade.concrets.wsFacadeRegistroPonto.class);
        resources.add(service.wsFacade.concrets.wsFacadeSecurityAutenticar.class);
        resources.add(service.wsFacade.concrets.wsFacadeTipoDivergencia.class);
        resources.add(service.wsFacade.concrets.wsFacadeUsuario.class);
    }    
}
