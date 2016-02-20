package de.lathspell.tilgungsrechner2;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.lathspell.tilgungsrechner2.formatter.Formatter;
import java.util.ArrayList;
import java.util.List;

public class Tilgungsrechner {

    private final List<Monat> monate = new ArrayList<>();

    private final ObjectMapper om = new ObjectMapper();

    public Tilgungsrechner() {
    }

    public void loop(Config config) throws Exception {
        String month = config.getFirstMonth();

        Prozess p = new Prozess();
        do {
            p.prepareNextMonth(month, config.getMonths().get(month));
            
            p.calcMonth();
            
            monate.add(om.readValue(om.writeValueAsString(p), Monat.class));
            
            month = incrementDatum(month);
        } while (p.hasDept());
    }

    public String format(Formatter formatter) {
        return formatter.format(monate);
    }

    private String incrementDatum(String old) {
        String[] parts = old.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);

        month++;
        if (month == 13) {
            month = 1;
            year++;
        }

        return String.format("%04d-%02d", year, month);
    }
}
