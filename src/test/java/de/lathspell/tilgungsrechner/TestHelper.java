package de.lathspell.tilgungsrechner;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.io.FileUtils;

import static org.junit.Assert.assertNotNull;

import de.lathspell.tilgungsrechner2.Config;

public class TestHelper {

    private final String resourcesDir = "src/test/resources/";
    private final String basename;
    private final File dir;

    public TestHelper(Class clazz, String basename) {
        this.basename = basename;
        this.dir = new File(resourcesDir + clazz.getPackage().getName().replaceAll("\\.", "/"));
    }

    public Config loadConfig() throws IOException {
        String ymlConfig = loadFile(".yml");
        Config config = new ObjectMapper(new YAMLFactory()).readValue(ymlConfig, Config.class);
        assertNotNull(config);
        return config;
    }

    public String loadOk() throws IOException {
        return loadFile(".ok");
    }

    public String loadFile(String ext) throws IOException {
        File f = new File(dir, basename + ext);
        // System.out.println(f);
        String contents = FileUtils.readFileToString(f, "UTF-8");
        assertNotNull(contents);
        return contents;
    }

    public void writeOutput(String ist) throws IOException {
        writeOutput(ist, false);
    }

    public void writeOutput(String ist, boolean withConfig) throws IOException {
        assertNotNull(ist);
        FileUtils.write(new File(dir, basename + ".out"),
                (withConfig ? ("=== " + basename + " ===\n\n" + loadFile(".yml") + "\n\n") : ("# " + basename + "\n")) + ist,
                "UTF-8");
        // System.out.println("# " + basename + ":\n" + ist);
    }
}
