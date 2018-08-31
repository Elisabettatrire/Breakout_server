/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.models;

import javax.enterprise.context.ApplicationScoped;

import it.breakout.services.NotificaService;

/**
 *
 * @author Giovanni
 */
@ApplicationScoped
public class Notifica {
    
    private String ID_Notifica;
    private String is_emergenza;

    public String getID_Notifica() {
        return ID_Notifica;
    }

    public void setID_Notifica(String ID_Notifica) {
        this.ID_Notifica = ID_Notifica;
    }

    public String getIs_emergenza() {
        return is_emergenza;
    }

    public void setIs_emergenza(String is_emergenza) {
        this.is_emergenza = is_emergenza;
    }
    
    public String retrieveLastState() {
        
        NotificaService notificaService = new NotificaService();
        this.setIs_emergenza(notificaService.retrieveLastState());
        return this.getIs_emergenza();
    }
    
}
