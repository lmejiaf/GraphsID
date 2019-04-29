/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author luis
 */
public class Arista {

    private Vertice inicio;
    private Vertice fin;
    private int peso;
   

    public Arista(Vertice inicio, Vertice fin, int peso) {
        this.inicio = inicio;
        this.fin = fin;
        this.peso = peso;
    }

    public Vertice getInicio() {
        return inicio;
    }

    public Vertice getFin() {
        return fin;
    }

    public int getPeso() {
        return peso;
    }

   

    public String getName() {
        return inicio.getId() + fin.getId();
    }

    public void setInicio(Vertice inicio) {
        this.inicio = inicio;
    }

    public void setFin(Vertice fin) {
        this.fin = fin;
    }

}
