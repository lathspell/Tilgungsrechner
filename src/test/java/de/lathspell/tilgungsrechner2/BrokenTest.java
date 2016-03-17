package de.lathspell.tilgungsrechner2;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import de.lathspell.tilgungsrechner.TestHelper;

public class BrokenTest {

    @Test
    public void broken1() throws Exception {
        TestHelper helper = new TestHelper(BrokenTest.class, "broken1");
        Config config = helper.loadConfig();

        Exception caught = null;
        try {
            Tilgungsrechner tr = new Tilgungsrechner();
            tr.loop(config);
        } catch (Exception e) {
            caught = e;
        }
        assertNotNull(caught);
        assertEquals("Schuld 69627.82 übersteigt Darlehensbetrag 60000!", caught.getMessage());
    }

}
