package conversion7.properties;

import conversion7.Utils;
import org.apache.log4j.Logger;
import org.fest.assertions.api.Assertions;
import org.fest.assertions.api.Fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoader {

    private static final Logger LOG = Utils.getLoggerForClass();


    public static void init() {
        Properties properties = new Properties();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("private.properties");
            properties.load(fileInputStream);

            for (Map.Entry<Object, Object> propEntry : properties.entrySet()) {
                System.setProperty(propEntry.getKey().toString(), propEntry.getValue().toString());
            }

        } catch (IOException e) {
            Fail.fail("Init properties: " + e.getMessage());
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String get(Key key) {
        String value = System.getProperty(key.toString());
        LOG.info("getProperty: " + key + " = " + value);
        Assertions.assertThat(value).isNotNull();
        return value;
    }

    public static int getI(Key key) {
        return Integer.parseInt(get(key));
    }

}
