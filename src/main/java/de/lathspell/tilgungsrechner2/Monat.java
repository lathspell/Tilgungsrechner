package de.lathspell.tilgungsrechner2;

import java.math.BigDecimal;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Monat {

    protected String datum;
    protected BigDecimal auszahlung;
    protected BigDecimal darlehensbetrag;
    protected BigDecimal schuld;
    protected BigDecimal sollZins;
    protected BigDecimal monatsRate;
    protected BigDecimal monatsZins;
    protected BigDecimal monatsTilgung;
    protected BigDecimal sondertilgung;
    protected BigDecimal tilgungszuschuss;
    protected boolean neueMonatsRate;
    protected boolean zinsbindungsEnde;

}
