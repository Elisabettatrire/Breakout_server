/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package restresources;

import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import models.Beacon;
import models.Mappa;
import models.Nodo;
import models.Pdi;
import models.Piano;
import models.Scala;
import models.Tronco;
import services.Beacon_Service;
import services.Mappa_Service;
import services.Nodo_Service;
import services.Piano_Service;
import services.Tronco_Service;
/**
 *
 * @author costantino
 */
@Path("/maps")
public class Mappe_resource {
    
    @GET
    @Path("/getmaps")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Mappa> getMaps() {
        Set<Mappa> maps=null;
        maps = Mappa_Service.findAll();
        
        return maps;
    }
    
    @GET
    @Path("/getnodes")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Nodo> getNodes() {
        Set<Nodo> nodes=null;
        nodes = Nodo_Service.findAllNodes();
        
        return nodes;
    }
    
    @GET
    @Path("/getarcs")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Tronco> getArcs() {
        Set<Tronco> arcs=null;
        arcs = Tronco_Service.findAllArcs();
        
        return arcs;
    }
    
    @GET
    @Path("/getstairs")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Scala> getStairs() {
        Set<Scala> stairs=null;
        stairs = Tronco_Service.findAllStairs();
        
        return stairs;
    }
    
    @GET
    @Path("/getfloors")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Piano> getFloors() {
        Set<Piano> floors=null;
        floors = Piano_Service.findAll();
        
        return floors;
    }
    
    @GET
    @Path("/getbeacons")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Beacon> getBeacons() {
        Set<Beacon> beacons=null;
        beacons = Beacon_Service.findAll();
        
        return beacons;
    }
    
    @GET
    @Path("/getpdis")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Pdi> getPdis() {
        Set<Pdi> pdis=null;
        pdis = Nodo_Service.findAllPdis();
        
        return pdis;
    }
}
