package de.lathspell.tilgungsrechner2;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

import de.lathspell.tilgungsrechner.TestHelper;
import de.lathspell.tilgungsrechner2.formatter.InterhypFormatter;

@RunWith(Parameterized.class)
public class InterhypTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"test1"}, {"test2"}, {"test3"}, {"test4"}, {"test5"}
        });
    }

    @Parameterized.Parameter
    public String name;

    @Test
    public void test1() throws Exception {
        TestHelper helper = new TestHelper(InterhypTest.class, name);
        Config config = helper.loadConfig();

        Tilgungsrechner tr = new Tilgungsrechner();
        tr.loop(config);

        String ist = tr.format(new InterhypFormatter());
        helper.writeOutput(ist);
        String soll = helper.loadOk();

        String actualCleaned = ist.replaceAll("[\\t ]+", " ").replaceAll("\r", "");
        String expectedCleaned = soll.replaceAll("[\\t ]+", " ").replaceAll("\r", "");
        assertEquals(expectedCleaned, actualCleaned);
    }

}
