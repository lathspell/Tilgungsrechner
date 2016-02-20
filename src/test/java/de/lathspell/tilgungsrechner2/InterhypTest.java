package de.lathspell.tilgungsrechner2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.lathspell.tilgungsrechner2.formatter.InterhypFormatter;
import org.apache.commons.io.IOUtils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class InterhypTest {

    @Test
    public void test1() throws Exception {
        Config config = TestHelper.loadConfig(InterhypTest.class, "test1.yml");
        
        Tilgungsrechner tr = new Tilgungsrechner();
        tr.loop(config);

        String ist = tr.format(new InterhypFormatter());
        System.out.println("test1:\n" + ist);
        String soll = TestHelper.loadFile(InterhypTest.class, "test1.ok");

        String actualCleaned = ist.replaceAll("[\\t ]+", " ");
        String expectedCleaned = soll.replaceAll("[\\t ]+", " ");
        assertEquals(expectedCleaned, actualCleaned);
    }

    @Test
    public void test2() throws Exception {
        String ymlConfig = IOUtils.toString(ClassLoader.getSystemResourceAsStream("de\\lathspell\\tilgungsrechner2\\test2.yml"), "UTF-8");
        Config config = new ObjectMapper(new YAMLFactory()).readValue(ymlConfig, Config.class);

        Tilgungsrechner tr = new Tilgungsrechner();
        tr.loop(config);

        String ist = tr.format(new InterhypFormatter());
        System.out.println("test2:\n" + ist);
        String soll = IOUtils.toString(ClassLoader.getSystemResourceAsStream("de\\lathspell\\tilgungsrechner2\\test2.ok"), "UTF-8");

        String actualCleaned = ist.replaceAll("[\\t ]+", " ");
        String expectedCleaned = soll.replaceAll("[\\t ]+", " ");
        assertEquals(expectedCleaned, actualCleaned);
    }

}
