package de.lathspell.tilgungsrechner2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Prozess extends Monat {

    private static final Logger log = LoggerFactory.getLogger(Prozess.class);

    Prozess() {
        super();
        schuld = BigDecimal.ZERO;
    }

    void prepareNextMonth(String datum, ConfigEntry currentConfig) {
        this.datum = datum;
        auszahlung = BigDecimal.ZERO;
        sondertilgung = BigDecimal.ZERO;
        neueMonatsRate = false;
        zinsbindungsEnde = false;
        if (currentConfig == null) {
            return;
        }
        if (currentConfig.getAuszahlung() != null) {
            auszahlung = currentConfig.getAuszahlung();
            schuld = schuld.add(currentConfig.getAuszahlung());
        }
        if (initialeSchuld == null && monatsRate != null && monatsRate.equals(new BigDecimal(-1)) && currentConfig.getMonatsrate() != null) {
            initialeSchuld = BigDecimal.ZERO.add(schuld);
        }
        if (currentConfig.getMonatsrate() != null) {
            if (monatsRate != null) {
                neueMonatsRate = true;
            }
            monatsRate = currentConfig.getMonatsrate();
        }
        if (currentConfig.getSollzins() != null) {
            sollZins = currentConfig.getSollzins();
        }
        if (currentConfig.getSondertilgung() != null) {
            sondertilgung = currentConfig.getSondertilgung();
        }
        if (currentConfig.getZinsbindung() != null) {
            zinsbindungsEnde = true;
        }
    }

    void calcMonth() {
        // Vor Zinsberechnung da: "Sondertilgung 10.000,00 EUR nach Periode Dez 2019"
        schuld = schuld.subtract(sondertilgung);

        // Zinsen für aktuellen Monat berechnen
        monatsZins = schuld.multiply(sollZins).divide(new BigDecimal(12 * 100), 2, RoundingMode.HALF_UP); // 12 Monate und 100 wegen %
        if (monatsZins.compareTo(monatsRate) > 0 && !monatsRate.equals(new BigDecimal(-1))) {
            throw new RuntimeException(datum + " Monatsrate " + monatsRate + "€ < Monatszins " + monatsZins + "€ !");
        }
        // Letzter Monat hat geringere Rate um keine negative Schuld zu bekommen.
        if (schuld.add(monatsZins).compareTo(monatsRate) < 0) {
            monatsRate = schuld.add(monatsZins);
        }

        // Monatsrate um Monatszins verringern und Tilgung berechnen
        monatsTilgung = monatsRate.equals(new BigDecimal(-1)) ? BigDecimal.ZERO : monatsRate.subtract(monatsZins);
        schuld = schuld.subtract(monatsTilgung);
    }

    boolean hasDept() {
        return (schuld.signum() == 1);
    }

}
