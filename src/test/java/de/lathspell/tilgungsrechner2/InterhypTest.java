package de.lathspell.tilgungsrechner2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import de.lathspell.tilgungsrechner.TestHelper;
import de.lathspell.tilgungsrechner2.formatter.InterhypFormatter;

public class InterhypTest {
    
    @Test
    public void test1() throws Exception {
        TestHelper helper = new TestHelper(InterhypTest.class, "test1");
        Config config = helper.loadConfig();
        
        Tilgungsrechner tr = new Tilgungsrechner();
        tr.loop(config);

        String ist = tr.format(new InterhypFormatter());
        helper.writeOutput(ist);
        String soll = helper.loadOk();

        String actualCleaned = ist.replaceAll("[\\t ]+", " ");
        String expectedCleaned = soll.replaceAll("[\\t ]+", " ");
        assertEquals(expectedCleaned, actualCleaned);
    }

    @Test
    public void test2() throws Exception {
        TestHelper helper = new TestHelper(InterhypTest.class, "test2");
        Config config = helper.loadConfig();

        Tilgungsrechner tr = new Tilgungsrechner();
        tr.loop(config);

        String ist = tr.format(new InterhypFormatter());
        helper.writeOutput(ist);
        String soll = helper.loadOk();

        String actualCleaned = ist.replaceAll("[\\t ]+", " ");
        String expectedCleaned = soll.replaceAll("[\\t ]+", " ");
        assertEquals(expectedCleaned, actualCleaned);
    }

}
