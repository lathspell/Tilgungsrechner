package de.lathspell.tilgungsrechner2;

import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConfigEntry {

    /** Vertraglich vereinbarter Betrag zur Erreichnung der Tilungssätze in %. */
    private BigDecimal darlehensbetrag;
    private BigDecimal auszahlung;
    /** Von uns geleistete Zahlung. */
    private BigDecimal sondertilgung;
    /** Bankseitiger Schuldenerlass. */
    private BigDecimal tilgungszuschuss;
    private BigDecimal sollzins;
    private BigDecimal monatsrate;
    private String zinsbindung;
}
