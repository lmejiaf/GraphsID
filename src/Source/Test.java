/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Source;

import Grafo.Arista;
import Grafo.Grafo;
import Grafo.Vertice;
import java.util.Scanner;

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

        Vertice vertices[] = {A, B, C, D, E, F};
        Arista aristas[] = {
            new Arista(A, B, 1), new Arista(B, A, 1), new Arista(B, C, 1),
            new Arista(C, B, 1), new Arista(C, A, 1), new Arista(A, C, 1),
            new Arista(C, D, 1), new Arista(D, C, 1), new Arista(D, E, 1),
            new Arista(E, D, 1), new Arista(A, D, 1), new Arista(D, A, 1),
            new Arista(C, F, 1), new Arista(F, C, 1), new Arista(B, E, 1),
            new Arista(E, B, 1)
        };

        Grafo g = new Grafo(vertices, aristas);

        return g;
    }

    public static void main(String[] args) {
//        System.out.println(g().generarArista(1, 3));
        Scanner leer = new Scanner(System.in);
        System.out.println("Digite un K menor o igual(<=) al conjunto de aristas del grafo");
        int k = leer.nextInt();

        g().MMM(k);
    }

}
