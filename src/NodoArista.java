/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zara
 */
public class NodoArista {
    NodoArista arriba;
    NodoArista abajo;
    NodoVertice direccion;
    
    public NodoArista (NodoVertice direccion){
        this.direccion = direccion;
        arriba = null;
        abajo = null;
    }
}
