package de.lathspell.tilgungsrechner2.formatter;

import de.lathspell.tilgungsrechner2.Monat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class InterhypFormatter extends Formatter {

    @Override
    protected String formatHeader(List<Monat> zeilen) {
        return "Tilgungsplan\n"
                + "Periode 	Rate (EUR) 	Zinsanteil (EUR) 	Tilgungsanteil (EUR) 	Restschuld am Periodenende (EUR)\n";
    }

    @Override
    protected String formatMonat(Monat zeile) {
        String parts[] = zeile.getDatum().split("-");
        LocalDate date = LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), 1);
        String monthString = date.format(DateTimeFormatter.ofPattern("LLL uuuu")).replaceAll("Mrz", "Mär");

        String meldung = "";
        if (zeile.getSondertilgung() != null && zeile.getSondertilgung().signum() != 0) {
            meldung += String.format(">> Sondertilgung %,.2f EUR nach Periode %s\n",
                    zeile.getSondertilgung().doubleValue(),
                    prevMonth(zeile.getDatum()));
        }
        if (zeile.isNeueMonatsRate()) {
            meldung += String.format(">> Neuer Tilgungssatz %,.2f %% ab %s (neue Rate %,.2f)\n",
                    getChangedRate(zeile.getMonatsRate(), zeile.getInitialeSchuld(), zeile.getSollZins()),
                    monthString,
                    zeile.getMonatsRate()
            );
        }

        return meldung + String.format("%s %,16.2f %,18.2f %,22.2f %,25.2f\n",
                monthString,
                zeile.getMonatsRate().equals(new BigDecimal(-1)) ? zeile.getMonatsZins() : zeile.getMonatsRate(),
                zeile.getMonatsZins().doubleValue(),
                zeile.getMonatsTilgung().doubleValue(),
                zeile.getSchuld().doubleValue());
    }

    @Override
    protected String formatFooter(List<Monat> zeilen) {
        return "";
    }

    private double getChangedRate(BigDecimal monatsRate, BigDecimal initialeSchuld, BigDecimal sollZins) {
        double rate = (monatsRate.doubleValue() - (initialeSchuld.doubleValue() * sollZins.doubleValue() / 100 / 12)) * 12 / initialeSchuld.doubleValue();
        return rate * 100;
    }

    private String prevMonth(String curMonth) {
        String parts[] = curMonth.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        month--;
        if (month == 0) {
            month = 12;
            year--;
        }
        LocalDate date = LocalDate.of(year, month, 1);
        return date.format(DateTimeFormatter.ofPattern("LLL uuuu", Locale.GERMANY)).replaceFirst("Mrz", "Mär");
    }
}
