/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanalaskuri;

/**
 *
 * @author Antero Oikkonen
 */
public class Sanat implements Comparable<Sanat> {

    private String sana;
    private int lukumaara;

    public Sanat(String sana, int lukumaara) {
        this.sana = sana;
        this.lukumaara = lukumaara;
    }

    public String getSana() {
        return sana;
    }

    public void setSana(String sana) {
        this.sana = sana;
    }

    public int getLukumaara() {
        return lukumaara;
    }

    public void setLukumaara(int lukumaara) {
        this.lukumaara = lukumaara;
    }

    public void lisaaLukumaaraa() {
        lukumaara += 1;
    }

    @Override
    public int compareTo(Sanat o) {
        if (o.getLukumaara() > this.lukumaara) {
            return 1;
        } else if (o.getLukumaara() == this.lukumaara) {
            return sana.compareTo(o.getSana());
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Sanat) {
            return sana.equals(((Sanat) obj).getSana());
        }
        return false;
    }

    @Override
    public String toString() {
        return "["+sana+", "+lukumaara+"]";
    }
}
