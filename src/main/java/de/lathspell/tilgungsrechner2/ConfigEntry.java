package de.lathspell.tilgungsrechner2;

import java.math.BigDecimal;

public class ConfigEntry {

    private BigDecimal auszahlung;
    private BigDecimal sondertilgung;
    private BigDecimal sollzins;
    private BigDecimal monatsrate;
    private String zinsbindung;

    public BigDecimal getAuszahlung() {
        return auszahlung;
    }

    public void setAuszahlung(BigDecimal auszahlung) {
        this.auszahlung = auszahlung;
    }

    public BigDecimal getSondertilgung() {
        return sondertilgung;
    }

    public void setSondertilgung(BigDecimal sondertilgung) {
        this.sondertilgung = sondertilgung;
    }

    public BigDecimal getSollzins() {
        return sollzins;
    }

    public void setSollzins(BigDecimal sollzins) {
        this.sollzins = sollzins;
    }

    public BigDecimal getMonatsrate() {
        return monatsrate;
    }

    public void setMonatsrate(BigDecimal monatsrate) {
        this.monatsrate = monatsrate;
    }

    public String getZinsbindung() {
        return zinsbindung;
    }

    public void setZinsbindung(String zinsbindung) {
        this.zinsbindung = zinsbindung;
    }

}
