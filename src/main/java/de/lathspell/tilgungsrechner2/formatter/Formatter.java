package de.lathspell.tilgungsrechner2.formatter;

import de.lathspell.tilgungsrechner2.Monat;
import java.util.List;

public abstract class Formatter {

    public String format(List<Monat> zeilen) {
        String output = formatHeader(zeilen);
        output = zeilen.stream().map((zeile) -> formatMonat(zeile)).reduce(output, String::concat);
        output += formatFooter(zeilen);
        return output;
    }

    protected abstract String formatHeader(List<Monat> zeilen);

    protected abstract String formatMonat(Monat zeilen);

    protected abstract String formatFooter(List<Monat> zeilen);

}
