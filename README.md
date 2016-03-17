Tilgungsrechner mit flexibler Konfiguration, d.h. beliebig vielen
Sondertilgungen, Auszahlungen und Monatsratenanpassungen.

ACHTUNG: Reines Lernprojekt, enthält bestimmt noch Fehler!

Beschränkungen
--------------

Alle Ereignisse in der Konfiguration finden am Monatsersten statt.

YAML Konfiguration
------------------

* Darlehensbetrag ist der Gesamtbetrag laut Vertrag (für die Berechnung des Tilgungsatz in %)
* Monatsrate -1 bedeutet "nur Zinsen, keine Tilgung"
* Tilgungszuschuss: Schuldenerlass der Bank
* Sondertilgung: Einmalige Zahlung
* Auszahlung: Auszahlung die die Schulden erhöht; alle Auszahlungen dürfen den Darlehensbetrag nicht überschreiten!
* Sollzins: Der ab nun gültige Zinssatz

Beispiel:

    months:
        "2013-12":
            # Kommentar
            auszahlung: 12123
            sollzins: 0.70
            monatsrate: -1
        "2014-01":
            auszahlung: 1686
            monatsrate: 314
        "2015-06":
            tilgungszuschuss: 2500
        "2015-08":
            sondertilgung: 10000
        "2018-01":
            # letzer Monat, danach Warnung!

TODO
----
* Ist der Hack mit Monatsrate=-1 noch notwendig?
