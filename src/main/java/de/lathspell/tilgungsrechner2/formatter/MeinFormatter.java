package de.lathspell.tilgungsrechner2.formatter;

import de.lathspell.tilgungsrechner2.Monat;
import java.math.BigDecimal;
import java.util.List;

public class MeinFormatter extends InterhypFormatter {

    @Override
    protected String formatMonat(Monat zeile) {
        String output = super.formatMonat(zeile);
        if (zeile.isZinsbindungsEnde()) {
            output += ">> !!! ZINSBINDUNGSENDE !!!\n";
        }
        return output;
    }

    @Override
    protected String formatFooter(List<Monat> zeilen) {
        BigDecimal raten = BigDecimal.ZERO;
        BigDecimal zinsen = BigDecimal.ZERO;
        BigDecimal darlehen = BigDecimal.ZERO;

        for (Monat zeile : zeilen) {
            raten = raten.add(zeile.getMonatsRate()).add(zeile.getSondertilgung());
            zinsen = zinsen.add(zeile.getMonatsZins());
            darlehen = darlehen.add(zeile.getAuszahlung());
        }

        return String.format("GESAMT: Monatsraten: %,.2f   davon Zinsen: %,.2f  für ein Darlehen über %,.2f\n",
                raten.doubleValue(), zinsen.doubleValue(), darlehen);
    }
}
