package de.lathspell.tilgungsrechner1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Tilgungsrechner {

    private int maxMonate = 30 * 12;

    private final List<Zeile> zeilen = new ArrayList<>();
    private Zeile zeile;

    public static void main(String[] args) {
        Tilgungsrechner tr = new Tilgungsrechner(new BigDecimal(120000), new BigDecimal(2.09), new BigDecimal(800), "2016-04");
        tr.loop();
        System.out.println(tr.format(new MeinFormatter()));
    }

    public Tilgungsrechner(BigDecimal schuld, BigDecimal sollZinsWert, BigDecimal monatsRate, String datum) {
        zeile = new Zeile(schuld, sollZinsWert.divide(new BigDecimal(100), 4, RoundingMode.HALF_UP), monatsRate, datum);
        zeilen.add(zeile); // Titelzeile
        zeilen.add(zeile);
    }

    public void loop() {
        while (zeile.getSchuld().doubleValue() > 0) {
         
            // Zinsen für aktuellen Monat berechnen
            zeile.setMonatsZins(zeile.getSchuld().multiply(zeile.getSollZins()).divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
            if (zeile.getMonatsZins().compareTo(zeile.getMonatsRate()) > 0) {
                throw new RuntimeException(zeile.getDatum() + " Monatsrate " + zeile.getMonatsRate() + "€ < Monatszins " + zeile.getMonatsZins() + "€ !");
            }
            // Letzter Monat
            if (zeile.getSchuld().add(zeile.getMonatsZins()).compareTo(zeile.getMonatsRate()) < 0) {
                zeile.setMonatsRate(zeile.getSchuld().add(zeile.getMonatsZins()));
            }

            // Monatsrate um Monatszins verringern und Tilgung berechnen
            zeile.setMonatsTilgung(zeile.getMonatsRate().subtract(zeile.getMonatsZins()));
            zeile.setSchuld(zeile.getSchuld().subtract(zeile.getMonatsTilgung()));

            
            zeile = new Zeile(zeile.getSchuld(), zeile.getSollZins(), zeile.getMonatsRate(), zeile.getDatum());
            zeile.setDatum(incrementDatum(zeile.getDatum()));
            zeilen.add(zeile);
            
            checkMaxMonate();
        }
        zeilen.remove(zeilen.size()-1);
    }

    private String incrementDatum(String old) {
        String[] parts = old.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        month++;
        if (month == 13) {
            month = 1;
            year++;
        }

        return String.format("%04d-%02d", year, month);
    }

    private void checkMaxMonate() {
        if (zeilen.size() > maxMonate) {
            throw new RuntimeException("Vertrag läuft zu lange!");
        }
    }

    public String format(Formatter formatter) {
        return formatter.format(zeilen);
    }

}
