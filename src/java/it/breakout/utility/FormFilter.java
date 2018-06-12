/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.utility;

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
        } else return "empty";
    }
    
    public String filtraQuota(String input_quota) {
        
        /* Rimozione caratteri speciali e lettere*/
        String filtered = input_quota.replaceAll("[^0-9]","");
        
        if(filtered != null && !filtered.isEmpty()) {
            return filtered;
        } else return "empty";
    }
    
    public int filtraNumero(String input_number) {
        
        /* Rimozione caratteri speciali e lettere*/
        String filtered = input_number.replaceAll("[^0-9]","");
        
        if(filtered != null && !filtered.isEmpty()) {
            return Integer.parseInt(filtered);
        } else return -1;
    }
    
    public String filtraDescrizione(String input_description) {
        
        /* Rimozione spazi iniziali e finali */
        String trimmed = input_description.trim();
        String filtered = trimmed.replaceAll("[^A-Za-z0-9]", "");
        /*FINIRE*/
        return filtered;
        
    }
    
    
    public String codeFilter(String input_code) {
        
        /* Rimozione caratteri speciali */
        String filtered = input_code.replaceAll("[^A-Za-z0-9]","");
        
        if(filtered != null && !filtered.isEmpty()) {
            return filtered.toUpperCase(); // Lettere maiuscole
        } else return "empty";
    }
}
