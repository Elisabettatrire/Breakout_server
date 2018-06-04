/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package it.breakout.restresources;

import it.breakout.models.Mappa;
import it.breakout.services.Mappa_Service;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
        
        return maps;
    }
}
