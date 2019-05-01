/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conjuntos;

/**
 *
 * @author luis
 */
import java.util.ArrayList;
//import java.util.Arrays;

public class Conjunto {

    private static ArrayList<String> union(ArrayList<String> primero, ArrayList<String> segundo) {
        ArrayList<String> retVal = new ArrayList<String>(primero);
        for (String worte : segundo) {
            if (!primero.contains(worte)) {
                retVal.add(worte);
            }
        }
        return retVal;
    }

    private static ArrayList<String> interseccion(ArrayList<String> a, ArrayList<String> b) {
        ArrayList<String> c = new ArrayList<String>();
        ArrayList<String> iter = a.size() > b.size() ? a : b;
        for (String elem : iter) {
            if (a.contains(elem) && b.contains(elem)) {
                c.add(elem);
            }
        }
        return c;
    }

    private static ArrayList<String> diferencia(ArrayList<String> a, ArrayList<String> b) {
        ArrayList<String> c = new ArrayList<String>();
        for (String elem : a) {
            if (!b.contains(elem)) {
                c.add(elem);
            }
        }
        return c;
    }
    

}
