package de.lathspell.tilgungsrechner2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.math.BigDecimal.ZERO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Prozess extends Monat {

    Prozess() {
        super();
        schuld = ZERO;
    }

    void prepareNextMonth(String datum, ConfigEntry currentConfig) {
        log.debug("prepareNextMonth: " + datum);
        this.datum = datum;
        auszahlung = ZERO;
        sondertilgung = ZERO;
        neueMonatsRate = false;
        zinsbindungsEnde = false;
        if (currentConfig == null) {
            return;
        }
        if (currentConfig.getAuszahlung() != null) {
            auszahlung = currentConfig.getAuszahlung();
            schuld = schuld.add(currentConfig.getAuszahlung());
        }
        if (initialeSchuld == null && (monatsRate == null || monatsRate.equals(new BigDecimal(-1))) && currentConfig.getMonatsrate() != null) {
            log.debug("Initiale Schuld := " + schuld);
            initialeSchuld = ZERO.add(schuld);
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
        monatsTilgung = monatsRate.equals(new BigDecimal(-1)) ? ZERO : monatsRate.subtract(monatsZins);
        schuld = schuld.subtract(monatsTilgung);
    }

    boolean hasDept() {
        return (schuld.signum() == 1);
    }

}
