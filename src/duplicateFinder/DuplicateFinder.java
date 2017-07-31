/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanalaskuri;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *
 * @author Antero Oikkonen
 */
public class Sanalaskuri {

    //private Hashtable<String,Integer> sanalista = new Hashtable<String,Integer>();
    private List<Sanat> sanalista = new ArrayList();

    public void add(String sana) {
        Sanat s = new Sanat(sana, 1);
        int indeksi = 0;
        if (sanalista.contains(s)) {
            indeksi = sanalista.indexOf(s);
            sanalista.get(indeksi).lisaaLukumaaraa();
        } else {
            sanalista.add(s);
        }
    }

    public List getEsiintymat() {
        Collections.sort(sanalista, (s1, s2) -> s1.compareTo(s2));
        return sanalista;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Sanalaskuri laskuri = new Sanalaskuri();
        laskuri.add("pulla");
        laskuri.add("kakku");
        laskuri.add("pulla");
        laskuri.add("leipa");
        laskuri.add("pipari");
        laskuri.add("pipari");
        laskuri.add("pipari");
        laskuri.add("pulla");
        laskuri.add("kahvi");
        laskuri.add("kakku");

        System.out.println(laskuri.getEsiintymat());
        laskuri = new Sanalaskuri();

        String f = "sanat.txt";

        try {
            List<String> sanat = new ArrayList<>();

            Scanner scanner = new Scanner(new File(f));

            while(scanner.hasNext()){
                sanat.add(scanner.next());
            }
            
            sanat.stream().forEach(laskuri::add);
          
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(laskuri.getEsiintymat());
        
        
    }

}
