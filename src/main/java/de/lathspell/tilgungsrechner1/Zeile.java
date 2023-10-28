package de.lathspell.tilgungsrechner1;

import java.math.BigDecimal;

public class Zeile {

    private String datum;
    private BigDecimal schuld;
    private BigDecimal sollZins;
    private BigDecimal monatsRate;
    private BigDecimal monatsZins;
    private BigDecimal monatsTilgung;

    public Zeile(BigDecimal schuld, BigDecimal sollZins, BigDecimal monatsRate, String datum) {
        this.schuld = schuld;
        this.sollZins = sollZins;
        this.monatsRate = monatsRate;
        this.datum = datum;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public BigDecimal getSchuld() {
        return schuld;
    }

    public void setSchuld(BigDecimal schuld) {
        this.schuld = schuld;
    }

    public BigDecimal getSollZins() {
        return sollZins;
    }

    public void setSollZins(BigDecimal sollZins) {
        this.sollZins = sollZins;
    }

    public BigDecimal getMonatsRate() {
        return monatsRate;
    }

    public void setMonatsRate(BigDecimal monatsRate) {
        this.monatsRate = monatsRate;
    }

    public BigDecimal getMonatsZins() {
        return monatsZins;
    }

    public void setMonatsZins(BigDecimal monatsZins) {
        this.monatsZins = monatsZins;
    }

    public BigDecimal getMonatsTilgung() {
        return monatsTilgung;
    }

    public void setMonatsTilgung(BigDecimal monatsTilgung) {
        this.monatsTilgung = monatsTilgung;
    }

}
