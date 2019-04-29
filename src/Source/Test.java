/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import Grafo.Arista;
import Grafo.Grafo;
import Grafo.Vertice;

/**
 *
 * @author luis
 */
public class Test {

    public static Grafo g() {

        Vertice A = new Vertice("A");
        Vertice B = new Vertice("B");
        Vertice C = new Vertice("C");
        Vertice D = new Vertice("D");
        Vertice E = new Vertice("E");
        Vertice F = new Vertice("F");
        Vertice G = new Vertice("G");

        Vertice vertices[] = {A, B, C};
        Arista aristas[] = {
            new Arista(A, B, 1), new Arista(B, A, 1), new Arista(B, C, 1),
            new Arista(C, B, 1), new Arista(C, A, 1), new Arista(A, C, 1)};

        Grafo g = new Grafo(vertices, aristas);

        return g;
    }

    public static void main(String[] args) {
        g().printMatAd();
    }

}
