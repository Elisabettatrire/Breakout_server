/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.restresources;

import it.breakout.models.Utente;
import it.breakout.services.UtenteService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author costantino
 */
@Path("user")
public class UserResource {
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String login(Utente utente){
        UtenteService utenteService = new UtenteService();
        Utente utenteTrovato = utenteService.findByUserAndPass(utente.getUsername(), utente.getPassword());
        
        String flag;
        
        if(utenteTrovato.getID_utente() != null){
            utenteService.updateIsOnline(utenteTrovato);
            flag = "true";
        }else{
            flag = "false";
        }
        
        return flag;
    }
    
    @POST
    @Path("logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String logout(Utente utente){
        UtenteService utenteService = new UtenteService();
        Utente utenteTrovato = utenteService.findByUser(utente.getUsername());
        
        String flag;
        
        if(utenteTrovato.getID_utente() != null){
            utenteService.updateIsNotOnline(utenteTrovato);
            flag = "true";
        }else{
            flag = "false";
        }
        
        return flag;
    }
    
    
    
}
