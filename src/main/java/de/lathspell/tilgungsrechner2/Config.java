package de.lathspell.tilgungsrechner2;

import java.util.Iterator;
import java.util.Map;

public class Config {

    private Map<String, ConfigEntry> months;

    public String getFirstMonth() {
        Iterator<String> configIterator = getMonths().keySet().iterator();
        if (!configIterator.hasNext()) {
            throw new RuntimeException("YML hat keinen Monat definiert!");
        }
        return configIterator.next();
    }

    public Map<String, ConfigEntry> getMonths() {
        return months;
    }

    public void setMonths(Map<String, ConfigEntry> months) {
        this.months = months;
    }

}
