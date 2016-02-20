package de.lathspell.tilgungsrechner2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.assertNotNull;

public class TestHelper {

    public static Config loadConfig(Class clazz, String filename) throws IOException {
        String ymlConfig = loadFile(clazz, filename);
        Config config = new ObjectMapper(new YAMLFactory()).readValue(ymlConfig, Config.class);
        assertNotNull(config);
        return config;
    }

    public static String loadFile(Class clazz, String filename) throws IOException {
        String fileAndPath = clazz.getPackage().getName().replaceAll("\\.", "\\\\") + "\\" + filename;
        System.out.println(fileAndPath);
        String contents = IOUtils.toString(ClassLoader.getSystemResourceAsStream(fileAndPath), "UTF-8");
        assertNotNull(contents);
        return contents;
    }
}
