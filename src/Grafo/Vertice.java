/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luis
 */
public class Vertice {

    private String id;
    private List<Arista> aristas = new ArrayList<>();
    private int grado;

    public Vertice(String id) {
        this.id = id;
    }

    

    public String getId() {
        return id;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public void addArista(Arista arista) {

        aristas.add(arista);
    }

    

}
