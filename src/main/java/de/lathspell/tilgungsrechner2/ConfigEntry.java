package de.lathspell.tilgungsrechner2;

import java.math.BigDecimal;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ConfigEntry {

    private BigDecimal auszahlung;
    private BigDecimal sondertilgung;
    private BigDecimal sollzins;
    private BigDecimal monatsrate;
    private String zinsbindung;
}
