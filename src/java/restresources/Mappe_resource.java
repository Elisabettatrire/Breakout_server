/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restresources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
/**
 *
 * @author costantino
 */
@Path("/maps")
public class Mappe_resource {

    @GET
    @Path("/getit")
    @Produces("text/plain")
    public String getIt() {
      return "Got it!";
    }
          
        
      
}
