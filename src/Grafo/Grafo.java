/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author luis
 */
public class Grafo {

    private Vertice vertices[];
    private Arista aristas[];
    private int[][] matAd;
    public Grafo(Vertice vertices[], Arista aristas[]) {
        this.vertices = vertices;
        this.aristas = aristas;
        for (Vertice vertice : vertices) {
            for (Arista arista : aristas) {
                if (arista.getInicio() == vertice) {
                    vertice.addArista(arista);
                }
            }
        }
        this.matAd = generarMatrizDeAdyacencia();
    }

    //generar diccionario de adyacencia
    private TreeMap<String, ArrayList<String>> crearDiccionario() {
        Vertice verAd;
        String id;
        ArrayList<String> adyac = new ArrayList<>();
        TreeMap<String, ArrayList<String>> dict = new TreeMap<>();

        for (Vertice vertice : vertices) {

            //se guardan bien la lista de vertices
            dict.put(vertice.getId(), new ArrayList<>());

        }
        ArrayList<String> ad = new ArrayList<>();
        for (Vertice vertice : vertices) {

            vertice.getAristas().forEach((arista) -> {
                ad.add(arista.getFin().getId());
            });
            dict.get(vertice.getId()).addAll(ad);
            ad.clear();

//            //se comprobó que los vertices quedaron bien guardados y aristas bien relacionadas
//            System.out.println("resultado de vertices:");
//            for (Arista arista : vertice.getAristas()) {
//                System.out.println(vertice.getId() + "-->" + arista.getFin().getId());
//            }
        }
//                                    System.out.println("Break for debug");

        //warning: hay problema con el diccionario, se esan repitiendo los elementos
//        System.out.println("------------");
//        System.out.println("resultados del diccionario: ");
//        for (String key : dict.keySet()) {
//            System.out.println(dict.get(key).size());
//        }
//        for (String key : dict.keySet()) {
//            System.out.println(key+": ");
//            for (String elem :  dict.get(key)) {
//                System.out.print(elem);
//            }
//            System.out.println("");
//        }
        return dict;
    }

//    //generar matriz etiquetada con los id de cada vertice
    private String[][] etiquetacion() {
        TreeMap<String, ArrayList<String>> dict = crearDiccionario();
        Set<String> Ids = dict.keySet();
        String mat[][] = new String[Ids.size() + 1][Ids.size() + 1];
        int k = 1;
        for (String Id : Ids) {
            mat[0][k] = Id;
            mat[k][0] = Id;
            k++;

        }
//        for (String[] strings : mat) {
//            for (int i = 0; i < strings.length; i++) {
//                if (strings[i] == null) {
//                    System.out.print(" 0 ");
//                } else {
//                    System.out.print(" " + strings[i] + " ");
//                }
//
//            }
//            System.out.println("");
//        }

        return mat;
    }

//    //generar posiciones dada las coordenadas(idx,idy)
    private int[] generarPos(String idx, String idy) {

        String mat[][] = etiquetacion();
        // warning:  hacer el cambio en las aristas de cada vertice para que el fin no sea el inicio
//        System.out.println(idx+", "+idy);
        String etiquetas[] = mat[0];
//        System.out.println("etiquetas: ");
//        for (int i = 0; i < etiquetas.length; i++) {
//            System.out.print(etiquetas[i]+" ");
//        }
//        System.out.println("");
        int pos[] = new int[2];
//        System.out.println("entrada: " + "(" + idx + ", " + idy + ")");
        for (int i = 0; i < etiquetas.length; i++) {
            String etiqueta = etiquetas[i];

            if (etiqueta != null && etiqueta.compareTo(idx) == 0) {
                pos[0] = i;
            }
            if (etiqueta != null && etiqueta.compareTo(idy) == 0) {
                pos[1] = i;
            }
//            System.out.println("salida: " + pos[0] + ", " + pos[1]);

        }
        return pos;
    }

//    // generar la matriz String de adyacencia con la definicion del diccionario
    private String[][] matAdString() {
        int pos[];
        int px, py;
        String mat[][] = etiquetacion();

        TreeMap<String, ArrayList<String>> dict = crearDiccionario();
        for (Entry<String, ArrayList<String>> entry : dict.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                String idx = entry.getKey();
                String idy = entry.getValue().get(i);
                pos = generarPos(idx, idy);
                px = pos[0];
                py = pos[1];
                mat[px][py] = "1";
            }
        }
//        for (int i = 0; i < mat.length; i++) {
//            for (int j = 0; j < mat.length; j++) {
//                System.out.print("[" + mat[i][j] + "]");
//            }
//            System.out.println("");
//        }
        return mat;
    }

//    //quitar etiquetas y pasar la matriz String a número
    private int[][] generarMatrizDeAdyacencia() {

        int adyacencia[][] = new int[vertices.length][vertices.length];

        String mat[][] = matAdString();

        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat.length; j++) {
                if (mat[i][j] == null || mat[i][j].compareTo("0") == 0) {
                    adyacencia[i - 1][j - 1] = 0;
                } else {
                    adyacencia[i - 1][j - 1] = 1;
                }
            }

        }
//        for (int i = 0; i < adyacencia.length; i++) {
//            for (int j = 0; j < adyacencia.length; j++) {
//                System.out.print("[" + adyacencia[i][j] + "]");
//            }
//            System.out.println("");
//        }

        return adyacencia;
    }

    //imprimir matriz
    public void printMatAd() {
        int[][] mat = matAd;
        for (int[] mat1 : mat) {
            for (int j = 0; j < mat.length; j++) {
                System.out.print("[ " + mat1[j] + " ]");
            }
            System.out.println("");
        }
    }
    
}
