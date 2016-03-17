package de.lathspell.tilgungsrechner1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class InterhypFormatter implements Formatter {

    @Override
    public String format(List<Zeile> zeilen) {
        String output = printStart(zeilen.get(0));
        for (Zeile zeile : zeilen.subList(1, zeilen.size())) {
            output += printStatus(zeile);
        }
        return output;
    }

    private String printStart(Zeile zeile) {
        return "";
    }

    private String printStatus(Zeile zeile) {
        String parts[]= zeile.getDatum().split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), 1);
        
        return String.format("%s %7.2f %7.2f %7.2f %,8.2f\n",
                date.format(DateTimeFormatter.ofPattern("LLL uuuu")).replaceAll("Mrz", "Mär"),
                zeile.getMonatsRate(),
                zeile.getMonatsZins().doubleValue(),
                zeile.getMonatsTilgung().doubleValue(),
                zeile.getSchuld().doubleValue());
    }
}
