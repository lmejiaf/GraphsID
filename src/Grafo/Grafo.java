/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.math.BigInteger;
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
        String[][] mat = matAdString();
        int k = 0;
        for (int i = mat.length - 1; i > 0; i--) {
            String[] col = new String[mat.length];
            for (int j = 1; j < mat.length; j++) {
                if (mat[j][i] != null && mat[j][i].compareTo("1") == 0) {
                    col[k] = generarArista(j, i);
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

    // combinatoria entre aristas adyacentes
    public void combinar() {
        ArrayList<String[]> listas = generarListas();
//        ArrayList<Boolean[]> li = new ArrayList<>();
        ArrayList<String[]> matchings = new ArrayList<>();
        ArrayList<String> endpoints = new ArrayList<>();

        String tag = "";
        String[] principal = listas.get(0);

        
    }
    //comb

}

//    static BigInteger combinatoria(BigInteger n, BigInteger k) {
//        BigInteger fn = BigInteger.ONE;
//        BigInteger fn_k = BigInteger.ONE;
//        BigInteger fk = BigInteger.ONE;
//        BigInteger res = BigInteger.ONE;
//        if (n.compareTo(k) == 0) {
//            return BigInteger.ONE;
//        }
//        for (BigInteger i = BigInteger.ONE; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
//            fn = fn.multiply(i);
//        }
//        for (BigInteger i = BigInteger.ONE; i.compareTo(n.subtract(k)) <= 0; i = i.add(BigInteger.ONE)) {
//            fn_k = fn_k.multiply(i);
//        }
//        for (BigInteger i = BigInteger.ONE; i.compareTo(k) <= 0; i = i.add(BigInteger.ONE)) {
//            fk = fk.multiply(i);
//        }
//
//        res = fn.divide(fk.multiply(fn_k));
//        if (res.toString().length() > 5) {
//            res = new BigInteger(res.toString().substring(0, 5));
//        }
//        return res;
//    }}
