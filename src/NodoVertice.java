/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zara
 */
public class NodoVertice {
    char dato;
    NodoVertice sig;
    NodoVertice ant;
    NodoArista arista;

    public NodoVertice(char d) {
        dato = d;
        sig = ant = null;
        arista = null;
    }
    public boolean insertarArista(NodoVertice direccion) {
        NodoArista nuevo = new NodoArista(direccion);
        if (nuevo == null) {
            return false;
        }

        if (arista == null) {
            arista = nuevo;
            return true;
        }
        irUltimo();
        arista.abajo = nuevo;
        nuevo.arriba = arista;
        return true;
    }

    private void irUltimo() {
        while (arista.abajo != null) {
            arista = arista.abajo;
        }
    }

    protected void irPrimero() {
        while (arista.arriba != null) {
            arista = arista.arriba;
        }
    }

    public NodoArista buscarArista(NodoVertice direccion) {
        if (arista == null) {
            return null;
        }
        irPrimero();
        for (NodoArista buscar = arista; buscar != null; buscar = buscar.abajo) {
            if (buscar.direccion == direccion) {
                return buscar;
            }
        }
        return null;
    }
     public NodoArista buscarArista(char direccion) {
        if (arista == null) {
            return null;
        }
        irPrimero();
        for (NodoArista buscar = arista; buscar != null; buscar = buscar.abajo) {
            if (buscar.direccion.dato == direccion) {
                return buscar;
            }
        }
        return null;
    }
    private boolean unaSolaArista() {
        return arista.abajo == null && arista.arriba == null;
    }

    public boolean eliminarArista(NodoVertice direccion) {
        if (arista == null) {
            return false;
        }

        NodoArista temp = buscarArista(direccion);
        if (temp == null) {
            return false;
        }

        if (temp == arista) {
            if (unaSolaArista()) {
                arista = null;
            } else {
                arista = arista.abajo;
                temp.abajo.arriba = temp.abajo = null;
            }
            return true;
        }

        if (temp.abajo == null) {
            temp.arriba.abajo = temp.arriba = null;
            return true;
        }

        temp.arriba.abajo = temp.abajo;
        temp.abajo.arriba = temp.arriba;
        temp.abajo = temp.arriba = null;
        return true;
    }

    @Override
    public String toString() {
        String res = "[" + dato + "]";
        NodoArista aux = arista;
        while (aux != null) {
            res += "->" + aux.direccion.dato;
            aux = aux.abajo;
        }
        return res;
    }

}
