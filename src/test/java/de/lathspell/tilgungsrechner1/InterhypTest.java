package de.lathspell.tilgungsrechner1;

import java.math.BigDecimal;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class InterhypTest {

    @Test
    public void test1() throws Exception {
        Tilgungsrechner tr = new Tilgungsrechner(new BigDecimal(120000), new BigDecimal(2.09), new BigDecimal(800), "2016-04");
        tr.loop();
        
        String ist = tr.format(new InterhypFormatter());
        System.out.println("test1:\n"+ist);
        String soll = IOUtils.toString(ClassLoader.getSystemResourceAsStream("de\\lathspell\\tilgungsrechner1\\test1.ok"), "UTF-8");
        
        String actualCleaned = ist.replaceAll("[\\t ]+", " ");
        String expectedCleaned = soll.replaceAll("[\\t ]+", " ");
        assertEquals(expectedCleaned, actualCleaned);
    }
}
