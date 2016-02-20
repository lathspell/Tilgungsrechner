package de.lathspell.tilgungsrechner1;

import java.math.BigDecimal;
import java.util.List;

public class MeinFormatter implements Formatter {

    @Override
    public String format(List<Zeile> zeilen) {
        String output = printStart(zeilen.get(0));
        for (Zeile zeile : zeilen.subList(1, zeilen.size())) {
            output += printStatus(zeile);
        }
        return output;
    }

    private String printStart(Zeile zeile) {
        return String.format("%s Auszahlung von %9.2f€\n", zeile.getDatum(), zeile.getSchuld());
    }

    private String printStatus(Zeile zeile) {
        return String.format("%s Monatsrate: %7.2f abzgl. Monatszins: %7.2f (%5.2f%%) bleibt Tilgung: %7.2f => Schuld: %s€\n",
                zeile.getDatum(),
                zeile.getMonatsRate(), zeile.getMonatsZins().doubleValue(), zeile.getSollZins().multiply(new BigDecimal(100)).doubleValue(),
                zeile.getMonatsTilgung().doubleValue(),
                zeile.getSchuld().toPlainString());
    }

}
