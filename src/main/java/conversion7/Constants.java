package conversion7;

import org.apache.log4j.Logger;

import java.io.File;

public class Constants {

    private static final Logger LOG = Utils.getLoggerForClass();

    public static final String APP_ROOT;
    public static final String RESOURCES_ROOT;
    public static final String DROPBOX_ROOT_FOLDER = "/test-deploy";


    static {
        File appRoot = new File("");
        APP_ROOT = appRoot.getAbsolutePath();
        LOG.info("appRoot: " + APP_ROOT);
        RESOURCES_ROOT = APP_ROOT + "\\src\\main\\resources";
    }
}
