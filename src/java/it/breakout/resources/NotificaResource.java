/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.resources;

import java.sql.Connection;

import it.breakout.services.NotificaService;

/**
 *
 * @author Giovanni
 */
public class NotificaResource {
    
    NotificaService notifica_service = new NotificaService();
    Connection conn = null;
    
    public void startEmergency() {
        notifica_service.startEmergency();
    }
    
    public void abortEmergency() {
        notifica_service.abortEmergency();
    }
    
    public String retrieveLastState() {
        return notifica_service.retrieveLastState();
    }
    
}
