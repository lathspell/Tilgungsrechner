package de.lathspell.tilgungsrechner1;

import java.math.BigDecimal;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import de.lathspell.tilgungsrechner.TestHelper;

public class InterhypTest {

    @Test
    public void test1() throws Exception {
        TestHelper helper = new TestHelper(InterhypTest.class, "test1");

        Tilgungsrechner tr = new Tilgungsrechner(new BigDecimal(120000), new BigDecimal(2.09), new BigDecimal(800), "2016-04");
        tr.loop();

        String ist = tr.format(new InterhypFormatter());
        helper.writeOutput(ist);
        String soll = helper.loadOk();

        String actualCleaned = ist.replaceAll("[\\t ]+", " ");
        String expectedCleaned = soll.replaceAll("[\\t ]+", " ");
        assertEquals(expectedCleaned, actualCleaned);
    }
}
