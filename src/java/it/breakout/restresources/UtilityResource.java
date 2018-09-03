/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.restresources;

import it.breakout.models.Beacon;
import it.breakout.models.Modifica;
import it.breakout.models.Utente;
import it.breakout.services.BeaconService;
import it.breakout.services.ModificaService;
import it.breakout.services.NotificaService;
import it.breakout.services.UtenteService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    
    @PUT
    @Path("setposition")
    @Consumes(MediaType.APPLICATION_JSON)
    public void setPosition(Utente utente){
        UtenteService utenteService = new UtenteService();
        BeaconService beaconSrv = new BeaconService();

        Utente utenteLocal = utenteService.findByUser(utente.getUsername());
        
        if(utenteLocal.getID_beacon() != null){
            Beacon oldPosition = beaconSrv.findByID(utenteLocal.getID_beacon());
            oldPosition.setInd_NCD(oldPosition.getInd_NCD() - 1);
            beaconSrv.updateNCD(oldPosition);
        }
        
        utenteService.updatePosition(utente);
        if(utente.getID_beacon() != null){
            Beacon newPosition = beaconSrv.findByID(utente.getID_beacon());
            newPosition.setInd_NCD(newPosition.getInd_NCD() + 1);
            beaconSrv.updateNCD(newPosition);
        }
    }
    
    @PUT
    @Path("beaconvalues")
    @Consumes(MediaType.APPLICATION_JSON)
    public void setBeaconValues(){
    }
    
    @POST
    @Path("checkusername")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String checkUsername(Utente utente){
        UtenteService utenteService = new UtenteService();
        Utente utenteTrovato = utenteService.findByUser(utente.getUsername());
        
        String flag;
        
        if(utenteTrovato.getID_utente()!=null){
            flag = "true";
        }else{
            flag="false";
        }
        
        return flag;
    }
    
    @GET
    @Path("emergency")
    @Produces(MediaType.APPLICATION_JSON)
    public String checkEmergency(){
        NotificaService notificaSrv = new NotificaService();
        
        return notificaSrv.retrieveLastState();
    }
    
}
