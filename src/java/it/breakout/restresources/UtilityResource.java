/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.restresources;

import it.breakout.models.Modifica;
import it.breakout.models.Utente;
import it.breakout.services.ModificaService;
import it.breakout.services.UtenteService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
@Path("utility")
public class UtilityResource {
    
    @GET
    @Path("testconnection")
    @Produces(MediaType.APPLICATION_JSON)
    public String testConnection(){
        return "true";
    }
    
    @POST
    @Path("registraUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String registra(Utente utente){
        UtenteService utenteService = new UtenteService();
        
        return utenteService.insertUtente(utente).toString();
    }
    
    @POST
    @Path("checkmodifiche")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String checkModifiche(Modifica modifica){
        ModificaService modificaService = new ModificaService();
        String flag;
        Timestamp dataServer = new Timestamp(modificaService.findLast().getData());
        Timestamp dataApp = new Timestamp(modifica.getData());
        if(dataServer.compareTo(dataApp)<=0){
            flag= "false";
        }else{
            flag="true";
        }

        return flag;
    }
    
    @POST
    @Path("getmodifiche")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Modifica> getModifiche(Modifica modifica){
        ModificaService modificaService = new ModificaService();
        ArrayList<Modifica> listaModifiche = new ArrayList<>();
        Timestamp dataApp = new Timestamp(modifica.getData());
        
        listaModifiche = modificaService.findAllAfterDate(dataApp);

        return listaModifiche;
    }
    
    @GET
	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Utente> test(){
            ArrayList<Utente> gegia = new ArrayList<>();
		Utente value = new Utente();
		value.setID_utente(5);
		value.setID_beacon(9);
		value.setNome("Mario");
		value.setCognome("Rossi");
                value.setEmail("fifo@vavva.con");
                value.setUsername("nonno");
                value.setPassword("nanni");
                value.setIs_online(true);
		
                gegia.add(value);
                
                Utente value1 = new Utente();
		value1.setID_utente(4);
		value1.setID_beacon(6);
		value1.setNome("Marino");
		value1.setCognome("Rotti");
                value1.setEmail("vavva@fifo.con");
                value1.setUsername("ninna");
                value1.setPassword("nanna");
                value1.setIs_online(false);
                
                gegia.add(value1);
                
		return gegia;
	}
    
    
    
}
