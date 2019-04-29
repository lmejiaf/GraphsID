/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.Hashtable;

/**
 *
 * @author luis
 */
public class Test {

    public static void main(String[] args) {
        String id = "letras";
        String adyac[] = {"a", "b", "c"};

        Hashtable<String, String[]> dict = new Hashtable<>();

        dict.put(id, adyac);
        System.out.println(dict.get(id)[1]);
    }
}
