/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
    boolean visitado[];

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
        visitado = new boolean[matAd.length];

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

        }

        return dict;
    }
    //generar matriz etiquetada con los id de cada vertice

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

        return mat;
    }

    //generar posiciones dada las coordenadas(idx,idy)
    private int[] generarPos(String idx, String idy) {

        String mat[][] = etiquetacion();

        String etiquetas[] = mat[0];

        int pos[] = new int[2];

        for (int i = 0; i < etiquetas.length; i++) {
            String etiqueta = etiquetas[i];

            if (etiqueta != null && etiqueta.compareTo(idx) == 0) {
                pos[0] = i;
            }
            if (etiqueta != null && etiqueta.compareTo(idy) == 0) {
                pos[1] = i;
            }

        }
        return pos;
    }

    // generar la matriz String de adyacencia con la definicion del diccionario
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

    //reducir la matriz del grafo por medio de la diagonal principal
    public int[][] reducir() {
        int mat[][] = matAd;
        for (int i = 0; i < mat.length; i++) {
            for (int j = i; j < mat.length; j++) {
                mat[j][i] = 0;
            }
        }
        return mat;
    }

    //generar la posicion dados los indices
    public String generarArista(int x, int y) {
        String[] etiqueta = etiquetacion()[0];
        String indice = etiqueta[x] + "," + etiqueta[y];
        return indice;
    }

    //extraer las listas para la futura combinatoria
    public ArrayList<String[]> generarListas() {
        ArrayList<String[]> listas = new ArrayList<>();
        int[][] mat = reducir();
        int k = 0;
        for (int i = mat.length - 1; i >= 0; i--) {
            String[] col = new String[mat.length];
            for (int j = 0; j < mat.length; j++) {
                if (mat[j][i] == 1) {
                    col[k] = generarArista(j + 1, i + 1);
                    k++;
                }
            }
            listas.add(col);
            k = 0;
        }
//        for (String[] strings : listas) {
//            for (String string : strings) {
//                System.out.println(string);
//            }
//            System.out.println("");
//        }
        return listas;
    }

    //crear el vector de todas las aristas aptas para combinatoria
    public String[] aristasReducidas() {

        ArrayList<String[]> l = generarListas();
        String[] edgesToMatch = new String[aristas.length / 2];
        int i = 0;
        for (String[] strings : l) {
            for (String string : strings) {
                if (string != null) {
                    edgesToMatch[i] = string;
//                    System.out.println(edgesToMatch[i]);
                    i++;
                }
            }
        }

        return edgesToMatch;
    }

    public void Perm2(String[] elem, String act, int n, int r, ArrayList<String> MaxMatchs) {
        if (n == 0) {

            String[] a = act.split(";");
            ArrayList<String> endpoints = new ArrayList<>();

            for (int i = 0; i < a.length; i++) {
                String matchs = a[i];
                if (!endpoints.contains(matchs.split(",")[0]) && !endpoints.contains(matchs.split(",")[1])) {
                    endpoints.add(matchs.split(",")[0]);
                    endpoints.add(matchs.split(",")[1]);

                }
            }
            int inicio = 0;
            String MM = "";
            for (int i = 0; i < endpoints.size(); i = i + 2) {

                String edge = endpoints.get(i) + "," + endpoints.get(i + 1);
                MM += edge + ";";
            }
            MM = MM.trim();
            MM = MM.substring(0, MM.length() - 1);

            if (!MM.isEmpty()) {
//                System.out.println(MM);
                MaxMatchs.add(MM);
            }

        } else {
            for (int i = 0; i < r; i++) {
//                ArrayList<String> endpoints = new ArrayList<>();
                if (!act.contains(elem[i])) { // Controla que no haya repeticiones
                    Perm2(elem, act + elem[i] + ";", n - 1, r, MaxMatchs);
                }
            }
        }
    }

    public ArrayList<String[]> maximalMatchings() {

        String aristas[] = aristasReducidas();
////        ArrayList<String> ariReduct = new ArrayList();
//        for (String arista : aristas) {
//            ariReduct.add(arista);
//            System.out.println(arista);
//        }
        System.out.println("............................");
        ArrayList<String> PosibleMaxMatchs = new ArrayList();
        ArrayList<String[]> MaxMatchs = new ArrayList();

        Perm2(aristas, "", aristas.length, aristas.length, PosibleMaxMatchs);
        Collections.sort(PosibleMaxMatchs);
        Set<String> hs = new HashSet<>();
        hs.addAll(PosibleMaxMatchs);
        PosibleMaxMatchs.clear();
        PosibleMaxMatchs.addAll(hs);
        Collections.sort(PosibleMaxMatchs);

        //filtración de resultados para saber si son Maximal
        for (String PosibleMaxMatch : PosibleMaxMatchs) {
            String[] mm = PosibleMaxMatch.split(";");
            if (comparar(mm) == true) {
                MaxMatchs.add(mm);
            }
        }
        return MaxMatchs;
    }

    //comparar endpoints de matching con endpoints del grafo
    public boolean comparar(String[] matching) {
        ArrayList<String> endPointsMatching = new ArrayList<>();
        String aristas[] = aristasReducidas();

        for (int i = 0; i < matching.length; i++) {
            String Ei = matching[i].split(",")[0];
            String Ef = matching[i].split(",")[1];
            endPointsMatching.add(Ei);
            endPointsMatching.add(Ef);
        }
        for (int i = 0; i < endPointsMatching.size(); i++) {
            for (int j = 0; j < aristas.length; j++) {
                if (!endPointsMatching.contains(aristas[j].split(",")[0]) && !endPointsMatching.contains(aristas[j].split(",")[1])) {
                    return false;
                }
            }
        }

        return true;
    }

    //cuales de los Maximal Matching son MMM(Grafo g, numero de aristas k):
    public void MMM(int k) {
        boolean existe = false;
        if (k <= aristas.length / 2) {
            ArrayList<String[]> matchs = maximalMatchings();
            for (String[] match : matchs) {
                if (match.length <= k) {
                    existe = true;
                    for (String edge : match) {
                        System.out.print(edge + " ");
                    }
                    System.out.println("");
                }
            }
            if (existe == false) {
                System.out.println("No existe un Maximal Matching con una cantidad de aristas menor o igual a " + k);
            }
        } else {
            System.out.println("el entero k dado no es válido");
        }
    }

}
