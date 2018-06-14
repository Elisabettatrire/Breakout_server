/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.breakout.utility;

import it.breakout.models.Nodo;
import it.breakout.models.Scala;
import it.breakout.services.Nodo_Service;
import it.breakout.services.Tronco_Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author costantino
 */
public class CamminoMinimo {
    
    /** metodo che prende in input il nodo di destinazione e il PDI di partenza
     * e restituisce il cammino minimo
     * 
     * @param destNodeId : nodo di destinazione (Punti Di Interesse o nodo normale)
     * @param startPdiId : punto di interesse di partenza
     * @return : ArrayList contenente tutti i tronchi che descrivono il cammino minimo
     */
    public static Integer[] Dijkstra_PDI (Integer destNodeId, Integer startPdiId){
        
        HashMap<Integer,Costo_e_Cammino> dijkstraTemp = new HashMap<>(); // insieme temporaneo dei cammini minimi
        HashMap<Integer,Costo_e_Cammino> dijkstraDef = new HashMap<>(); // insieme definitivo dei cammini minimi
        
        Costo_e_Cammino cost_camm_dest = new Costo_e_Cammino(); //instanzio l'oggetto contenente il costo del cammino minimo e il cammino da associare al nodo di destinazione
        cost_camm_dest.setCosto(0.0f); //Inizializzo il costo del nodo di destinazione (che è ovviamente 0)
        
        Integer nodoTempId;
        Integer[] camminoMinimo;
        
        dijkstraDef.put(destNodeId, cost_camm_dest); //Inserisco il nodo di destinazione nell'insieme definitivo
        dijkstraTemp = impostaCostoECammino(dijkstraTemp, dijkstraDef, destNodeId); //Inserisco nell'insieme temporaneo i nodi adiacenti alla destinazione 
        
        /*
        Finchè l'insieme definitivo non contiene il nodo di partenza, sposta il nodo
        di costo minimo nell'insieme definitivo e inserisce nell'insieme temporaneo 
        i nodi ad esso adiacenti, non ancora presenti nè nell'insieme temporaneo nè 
        in quello definitivo.
        */
        while(!dijkstraDef.containsKey(startPdiId)){
            nodoTempId = trovaCostoMin(dijkstraTemp);
            dijkstraDef.put(nodoTempId, dijkstraTemp.get(nodoTempId));
            dijkstraTemp.remove(nodoTempId);
            dijkstraTemp = impostaCostoECammino(dijkstraTemp, dijkstraDef, nodoTempId);
        }
        
        camminoMinimo = dijkstraDef.get(startPdiId).getCammino();
        return camminoMinimo;
    }
    
    /**metodo che prende in input il nodo di destinazione e il tronco di partenza
     * e restituisce il cammino minimo
     * 
     * @param destNodeId : nodo di destinazione (Punti Di Interesse o nodo normale)
     * @param startArcId : tronco di partenza (è di tipo Scala perchè Tronco estende Scala)
     * @return : ArrayList contenente tutti i tronchi che descrivono il cammino minimo
     */
    public static Integer[] Dijkstra_Tronco (Integer destNodeId, Integer startArcId){
        
        HashMap<Integer,Costo_e_Cammino> dijkstraTemp = new HashMap<>(); // insieme temporaneo dei cammini minimi
        HashMap<Integer,Costo_e_Cammino> dijkstraDef = new HashMap<>(); // insieme definitivo dei cammini minimi
        
        Costo_e_Cammino cost_camm_dest = new Costo_e_Cammino(); //instanzio l'oggetto contenente il costo del cammino minimo e il cammino da associare al nodo di destinazione
        cost_camm_dest.setCosto(0.0f); //Inizializzo il costo del nodo di destinazione (che è ovviamente 0)
        
        Integer nodoTempId;
        Integer[] camminoMinimo;
                
        dijkstraDef.put(destNodeId, cost_camm_dest);//Inserisco il nodo di destinazione nell'insieme definitivo
        dijkstraTemp = impostaCostoECammino(dijkstraTemp, dijkstraDef, destNodeId); //Inserisco nell'insieme temporaneo i nodi adiacenti alla destinazione 
        
        Tronco_Service troncoSrv = new Tronco_Service();
        Scala startArc = troncoSrv.findByIdGeneric(startArcId);
        
        /*
        Finchè l'insieme definitivo non contiene tutti e due i nodi del tronco di partenza, sposta il nodo
        di costo minimo nell'insieme definitivo e inserisce nell'insieme temporaneo 
        i nodi ad esso adiacenti, non ancora presenti nè nell'insieme temporaneo nè 
        in quello definitivo.
        */
        while(!dijkstraDef.containsKey(startArc.getNodiInteger()[0]) || !dijkstraDef.containsKey(startArc.getNodiInteger()[1])){
            nodoTempId = trovaCostoMin(dijkstraTemp);
            dijkstraDef.put(nodoTempId, dijkstraTemp.get(nodoTempId));
            dijkstraTemp.remove(nodoTempId);
            dijkstraTemp = impostaCostoECammino(dijkstraTemp, dijkstraDef, nodoTempId);
        }
        
        Integer startNodeId;
        /*
        calcolo il nodo del tronco con costo maggiore e lo considero come nodo di partenza
        */
        if(dijkstraDef.get(startArc.getNodiInteger()[0]).getCosto() <= dijkstraDef.get(startArc.getNodiInteger()[1]).getCosto()){
            startNodeId = startArc.getNodiInteger()[1];
        }else{
            startNodeId = startArc.getNodiInteger()[0];
        }
        
        camminoMinimo = dijkstraDef.get(startNodeId).getCammino();
        return camminoMinimo;
    }
    
    private static HashMap<Integer,Costo_e_Cammino> impostaCostoECammino(HashMap<Integer,Costo_e_Cammino> dijkstraTemp, HashMap<Integer,Costo_e_Cammino> dijkstraDef, Integer nodoId){
        
        Nodo_Service nodoSrv = new Nodo_Service();
        Tronco_Service troncoSrv = new Tronco_Service();
        
        Nodo nodo = nodoSrv.findById(nodoId);
        ArrayList<Scala> stella = new ArrayList<>();
        
        for (Integer i : nodo.getTronchi_stella_int()){
            Scala arc = troncoSrv.findByIdGeneric(i);
            stella.add(arc);
        }
        
        for (Scala i : stella){
            Integer otherNodeId;
            otherNodeId = i.otherNode(nodoId);
            if (!dijkstraTemp.containsKey(otherNodeId) && !dijkstraDef.containsKey(otherNodeId)) {
                Integer[] cammino;
                Costo_e_Cammino cost_camm = new Costo_e_Cammino();
                Float costo;
                cammino = dijkstraDef.get(nodoId).getCammino();
                cammino[cammino.length] = i.getID(); //TODO verificare se è giusto
                costo= i.getCosto_totale()+dijkstraDef.get(nodoId).getCosto();
                cost_camm.setCosto(costo);
                cost_camm.setCammino(cammino);
                dijkstraTemp.put(otherNodeId, cost_camm);
            }
        }
        return dijkstraTemp;
    }
    
    private static Integer trovaCostoMin (HashMap<Integer,Costo_e_Cammino> dijkstra){
        Integer nodo_costo_min = null;
        
        Entry<Integer, Costo_e_Cammino> min = null;
        for (Entry<Integer, Costo_e_Cammino> entry : dijkstra.entrySet()) {
            if (min == null || min.getValue().getCosto() > entry.getValue().getCosto()) {
                min = entry;
            }
        }
        if(min != null) nodo_costo_min = min.getKey();
        
        return nodo_costo_min;
    }
}

class Costo_e_Cammino {
    Float costo;
    Integer[] cammino;

    public Costo_e_Cammino() {
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public Integer[] getCammino() {
        return cammino;
    }

    public void setCammino(Integer[] cammino) {
        this.cammino = cammino;
    }
}
