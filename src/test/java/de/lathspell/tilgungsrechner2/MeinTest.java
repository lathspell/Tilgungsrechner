package de.lathspell.tilgungsrechner2;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

import de.lathspell.tilgungsrechner.TestHelper;
import de.lathspell.tilgungsrechner2.formatter.MeinFormatter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MeinTest {

    @Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"mein1"}, {"mein2"}, {"mein3"}, {"mein4"}
        });
    }

    @Parameterized.Parameter
    public String name;

    @Test
    public void test1() throws Exception {
        TestHelper helper = new TestHelper(MeinTest.class, name);
        Config config = helper.loadConfig();

        Tilgungsrechner tr = new Tilgungsrechner();
        tr.loop(config);

        String ist = tr.format(new MeinFormatter());
        helper.writeOutput(ist);
        String soll = helper.loadOk();

        String actualCleaned = ist.replaceAll("[\\t ]+", " ");
        String expectedCleaned = soll.replaceAll("[\\t ]+", " ");
        assertEquals(expectedCleaned, actualCleaned);
    }

}
