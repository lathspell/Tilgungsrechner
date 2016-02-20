package de.lathspell.tilgungsrechner2;

import java.math.BigDecimal;

public class Monat {

    protected String datum;
    protected BigDecimal auszahlung;
    protected BigDecimal initialeSchuld;
    protected BigDecimal schuld;
    protected BigDecimal sollZins;
    protected BigDecimal monatsRate;
    protected BigDecimal monatsZins;
    protected BigDecimal monatsTilgung;
    protected BigDecimal sondertilgung;
    protected boolean neueMonatsRate;
    protected boolean zinsbindungsEnde;

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public BigDecimal getAuszahlung() {
        return auszahlung;
    }

    public void setAuszahlung(BigDecimal auszahlung) {
        this.auszahlung = auszahlung;
    }

    public BigDecimal getInitialeSchuld() {
        return initialeSchuld;
    }

    public void setInitialeSchuld(BigDecimal initialeSchuld) {
        this.initialeSchuld = initialeSchuld;
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

    public BigDecimal getSondertilgung() {
        return sondertilgung;
    }

    public void setSondertilgung(BigDecimal sondertilgung) {
        this.sondertilgung = sondertilgung;
    }

    public boolean isNeueMonatsRate() {
        return neueMonatsRate;
    }

    public void setNeueMonatsRate(boolean neueMonatsRate) {
        this.neueMonatsRate = neueMonatsRate;
    }

    public boolean isZinsbindungsEnde() {
        return zinsbindungsEnde;
    }

    public void setZinsbindungsEnde(boolean zinsbindungsEnde) {
        this.zinsbindungsEnde = zinsbindungsEnde;
    }

}
