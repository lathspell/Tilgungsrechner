package de.lathspell.tilgungsrechner2;

import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
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

}
