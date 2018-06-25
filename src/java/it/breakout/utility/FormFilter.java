/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.utility;

import static it.breakout.utility.Constants.*;

/**
 *
 * @author Giovanni
 */
public class FormFilter {
    
    public String filtraTesto(String input_text) {
        
        /* Rimozione caratteri speciali */
        String filtered = input_text.replaceAll("[^A-Za-z0-9]","");
        
        if(filtered != null && !filtered.isEmpty()) {
            return filtered;
        } else return DEFAULT_STRING;
    }
    
    public String filtraQuota(String input_quota) {
        
        /* Rimozione caratteri speciali e lettere*/
        String filtered = input_quota.replaceAll("[^0-9]","");
        
        if(filtered != null && !filtered.isEmpty()) {
            return filtered;
        } else return DEFAULT_STRING;
    }
    
    public Double filtraCoordinata(String input_number) {
        
        /* Rimozione caratteri speciali e lettere*/
        String filtered = input_number.replaceAll("[^0-9.,-]",""); // Solo decimali ammessi
        String dotted = filtered.replaceAll(",","."); // Solo punti ammessi
        String pattern = "^-?\\d+(,\\d+)*(\\.\\d+(e\\d+)?)?$";
        boolean check = dotted.matches(pattern); // Forma corretta del decimale
        
        if(filtered != null && !filtered.isEmpty() && check) {
            return Double.parseDouble(dotted);
        } else return DEFAULT_DOUBLE;
    }
    
    public Double filtraMisura(String input_number) {
        
        /* Rimozione caratteri speciali e lettere*/
        String filtered = input_number.replaceAll("[^0-9.,]",""); // Solo decimali ammessi
        String dotted = filtered.replaceAll(",","."); // Solo punti ammessi
        String pattern = "^?\\d+(,\\d+)*(\\.\\d+(e\\d+)?)?$";
        boolean check = dotted.matches(pattern); // Forma corretta del decimale
        
        if(filtered != null && !filtered.isEmpty() && check) {
            return Double.parseDouble(dotted);
        } else return DEFAULT_DOUBLE;
    }
    
    public String filtraCodice(String input_code) {
        
        /* Rimozione caratteri speciali */
        String filtered = input_code.replaceAll("[^A-Za-z0-9]","");
        
        if(filtered != null && !filtered.isEmpty()) {
            return filtered.toUpperCase(); // Lettere maiuscole
        } else return DEFAULT_STRING;
    }
    
    public String filtraNomeMappa(String input_nome_mappa) {
        /* Rimozione caratteri speciali e lettere*/
        String filtered = input_nome_mappa.replaceAll("[^A-Za-z0-9/_-]","");
        if(filtered != null && !filtered.isEmpty()) {
            return filtered; // Lettere maiuscole
        } else return DEFAULT_STRING;
    }
    
}