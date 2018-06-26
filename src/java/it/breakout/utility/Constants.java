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
public class Constants {
    
    /* Parametri del database */
    public static final String DB_URL = "jdbc:derby://localhost:1527/breakout2";
    public static final String DB_USR = "app";
    public static final String DB_PSW = "app";
    
    /* Parametri per il filtro form */
    public static final String DEFAULT_STRING = "%empty%";
    public static final Double DEFAULT_DOUBLE = -1e-10;
    
    /* Parametri PDI */
    public static final String TIPO_AULA = "Aula";
    public static final String TIPO_PDR = "Punto di Ritrovo";
    public static final String TIPO_INV = "invariato";
}
