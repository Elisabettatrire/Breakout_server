/*
 * Questa classe contiene tutte le variabili d'ambiente necessarie per la
 * connessione con il DB e per il corretto funzionamento degli URL
 */
package it.breakout.utility;

/**
 *
 * @author Giovanni
 */
public class EnvVariables {
    
    /* Parametri del database */
    public static final String DB_URL = "jdbc:derby://localhost:1527/breakout4";
    public static final String DB_USR = "app";
    public static final String DB_PSW = "app";
    
    /* URL predefiniti */
    public static final String URL_HOME = "/welcome.jsp";
    public static final String URL_MAPPE = "/DBAccess";
    public static final String URL_PIANO = "ObjectAccess?obj=piano&nm=";
    public static final String URL_GRAFO = "ObjectAccess?obj=grafo&nm=";
    public static final String URL_BEACON = "ObjectAccess?obj=beacon&nm=";
    
    /* Parametri per il filtro form */
    public static final String DEFAULT_STRING = "%empty%";
    public static final Double DEFAULT_DOUBLE = -1e-10;
    
}
