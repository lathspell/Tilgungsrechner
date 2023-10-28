package de.lathspell.tilgungsrechner2.formatter;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

import lombok.extern.slf4j.Slf4j;

import de.lathspell.tilgungsrechner2.Monat;

@Slf4j
public class MeinFormatter extends InterhypFormatter {

    @Override
    protected String formatMonat(Monat zeile) {
        String output = super.formatMonat(zeile);
        if (zeile.isZinsbindungsEnde()) {
            output += ">> !!! ZINSBINDUNGSENDE !!!\n";
        }
        if (zeile.getTilgungszuschuss().compareTo(ZERO) != 0) {
            output += ">> Tilgungszuschuss: " + String.format("%,.2f", zeile.getTilgungszuschuss().doubleValue()) + "\n";
        }
        return output;
    }

    @Override
    protected String formatFooter(List<Monat> zeilen) {
        BigDecimal raten = ZERO;
        BigDecimal zinsen = ZERO;
        BigDecimal darlehen = ZERO;

        for (Monat zeile : zeilen) {
            if (zeile.getMonatsRate().compareTo(new BigDecimal(-1)) != 0) {
                raten = raten.add(zeile.getMonatsRate());
            }
            raten = raten.add(zeile.getSondertilgung());
            zinsen = zinsen.add(zeile.getMonatsZins());
            darlehen = darlehen.add(zeile.getAuszahlung());
        }

        return String.format("GESAMT: %d Monatsraten: %,.2f   davon Zinsen: %,.2f  für ein Darlehen über %,.2f\n",
                zeilen.size(), raten.doubleValue(), zinsen.doubleValue(), darlehen);
    }
}
