/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package it.breakout.restresources;

import it.breakout.models.Beacon;
import it.breakout.models.Mappa;
import it.breakout.models.Pdi;
import it.breakout.models.Piano;
import it.breakout.models.Tronco;
import it.breakout.services.BeaconService;
import it.breakout.services.MappaService;
import it.breakout.services.NodoService;
import it.breakout.services.PianoService;
import it.breakout.services.TroncoService;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 *
 * @author costantino
 */
@Path("maps")
public class MappeResource {
    
    @GET
    @Path("getmaps")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Mappa> getMaps() {
        ArrayList<Mappa> maps = new ArrayList<>();
        MappaService mappaSrv = new MappaService();
        
        maps = mappaSrv.findAll();
        
        return maps;
    }
    
    
    @GET
    @Path("beacon")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Beacon> getBeacons() {
        ArrayList<Beacon> beacons = new ArrayList<>();
        BeaconService beaconSrv = new BeaconService();
        
        beacons = beaconSrv.findAll();
        
        return beacons;
    }
    
    @GET
    @Path("arcs")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Tronco> getArcs() {
        ArrayList<Tronco> tronchi = new ArrayList<>();
        TroncoService troncoSrv = new TroncoService();
        
        tronchi = troncoSrv.findAll();
        
        return tronchi;
    }
    
    @GET
    @Path("nodes")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Pdi> getNodes() {
        ArrayList<Pdi> nodi = new ArrayList<>();
        NodoService nodoSrv = new NodoService();
        
        nodi = nodoSrv.findAll();
        
        return nodi;
    }
    
    @GET
    @Path("floors")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Piano> getFloors() {
        ArrayList<Piano> piani = new ArrayList<>();
        PianoService pianoSrv = new PianoService();
        
        piani = pianoSrv.findAll();
        
        return piani;
    }   
    
}
