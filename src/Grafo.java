
import static javax.swing.JOptionPane.showMessageDialog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zara
 */

public class Grafo {
    
    NodoVertice vertice;

    public Grafo() {
        vertice = null;
    }

    public boolean insertarVertice(char dato) {
        NodoVertice nuevo = new NodoVertice(dato);
        if (nuevo == null)return false;
        if (vertice == null) {
            vertice = nuevo;
            return true;
        }
        irUltimo();
        vertice.sig = nuevo;
        nuevo.ant = vertice;
        return true;
    }

    private void irUltimo() {
        while (vertice.sig != null) {
            vertice = vertice.sig;
        }
    }

    public void irPrimero() {
        while (vertice.ant != null) {
            vertice = vertice.ant;
        }
    }

    NodoVertice buscarVertice(char dato) {
        if (vertice == null) {
            return null;
        }
        irPrimero();
        for (NodoVertice buscar = vertice; buscar != null; buscar = buscar.sig) {
            if (buscar.dato == dato) {
                return buscar;
            }
        }
        return null;
    }

    public boolean insertarArista(char origen, char destino) {
        NodoVertice nodoOrigen = buscarVertice(origen);
        NodoVertice nodoDestino = buscarVertice(destino);

        if (nodoOrigen == null || nodoDestino == null) {
            showMessageDialog(null, "false");
            return false;
        }
        return nodoOrigen.insertarArista(nodoDestino);
    }

    private boolean unSoloVertice() {
        return vertice.ant == null && vertice.sig == null;
    }

    public boolean eliminarVertice(char dato) {
        if (vertice == null) {
            return false;
        }

        NodoVertice t = buscarVertice(dato);
        if (t == null) {
            return false;
        }

        if (t.arista != null) {
            return false;
        }

        quitarAristasDeOtrosVertices(t);

        if (t == vertice)
        {
            if (unSoloVertice()) {
                vertice = null;
            } else {
                vertice = t.sig;
                t.sig.ant = t.sig = null;

            }
            return true;
        }

        if (t.sig == null) //Temp está en el último
        {
            t.ant.sig = null;
            t.ant = null;
            return true;
        }

        //Temp está en medio
        t.ant.sig = t.sig;
        t.sig.ant = t.ant;
        t.sig = t.ant = null;
        return true;

    }

    private void quitarAristasDeOtrosVertices(NodoVertice NodoEliminar) {
        irPrimero();
        for (NodoVertice buscar = vertice; buscar != null; buscar = buscar.sig) {
            buscar.eliminarArista(NodoEliminar);
        }
    }

    public boolean eliminarArista(char origen, char destino) {
        NodoVertice nodoOrigen = buscarVertice(origen);
        NodoVertice nodoDestino = buscarVertice(destino);
        if (nodoOrigen == null || nodoDestino == null) {
            return false;
        }
        return nodoOrigen.eliminarArista(nodoDestino);
    }
    
    
     public int contarVertices() {
        if (vertice == null) {
            return 0;
        }
        int i = 0;
        irPrimero();
        NodoVertice a = vertice;
        while (a!= null) {
            i++;
            a = a.sig;
        }
        return i;
    }
    public String ListaAdyacencia(){
        irPrimero();
        String cad="";
        for(NodoVertice z=vertice; z!=null; z=z.sig){
            cad=cad+z.dato+" | ";
            if(z.arista != null){
                z.irPrimero();
                for(NodoArista k=z.arista; k!=null;k=k.abajo){
                    cad=cad+" ==> "+k.direccion.dato;
                }
            }
            cad=cad+"\n";
        }return cad;
    } 

    public String matrizAdyacencia() {
        if (vertice == null) {return null;}
        String cad = "";
        int x = contarVertices();
        irPrimero();
        NodoVertice z = vertice;
       
        for(NodoVertice i=vertice; i!=null; i=i.sig){
            cad = " "+cad + i.dato + "  ";
        } cad = cad + "\n";
        for(int r=0; r<x; r++){
            for(int c=0; c<x; c++){
                if(c==0)cad = cad + z.dato+" ";
                cad = cad;
                if(vertice.buscarArista(z.dato)== null){
                    cad = cad + "0 |";
                }else{
                    cad = cad + "1 |";
                } if(vertice.sig!=null)vertice = vertice.sig;
            }//primer for
            irPrimero();
            z = z.sig;
            cad = cad + "\n";
        }//segundo for
        return cad;
    } 
}
