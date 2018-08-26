/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.resources;

import it.breakout.models.Modifica;
import it.breakout.services.ModificaService;

/**
 *
 * @author Giovanni
 */
public class ModificaResource {
    
    ModificaService modifica_service = new ModificaService();
    
    public void insert(Modifica modifica) {
        modifica_service.insert(modifica);
    }
    
}
