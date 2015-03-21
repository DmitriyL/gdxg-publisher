package conversion7;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxClient;
import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.DbxWriteMode;
import conversion7.properties.Key;
import conversion7.properties.PropertiesLoader;
import org.apache.log4j.Logger;
import org.fest.assertions.api.Fail;

import java.io.File;
import java.io.FileInputStream;
import java.util.Locale;

public class DropboxServiceFacade {

    private static final Logger LOG = Utils.getLoggerForClass();

    private static DbxRequestConfig config;
    private static DbxWebAuthNoRedirect webAuth;
    private static DbxClient client;

    public static String getAuthUrl() {
        // Get your app key and secret from the Dropbox developers website.
        final String APP_KEY = PropertiesLoader.get(Key.APP_KEY);
        final String APP_SECRET = PropertiesLoader.get(Key.APP_SECRET);

        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        config = new DbxRequestConfig(
                "JavaTutorial/1.0", Locale.getDefault().toString());
        webAuth = new DbxWebAuthNoRedirect(config, appInfo);

        return webAuth.start();
    }

    public static void initClient(String authCode) throws Exception {
        DbxAuthFinish authFinish = webAuth.finish(authCode);
        String accessToken = authFinish.accessToken;
        client = new DbxClient(config, accessToken);
        LOG.info("Linked account: " + client.getAccountInfo().displayName);
    }

    public static void deleteFile(String path, String name) {
        String fullPath = path + "/" + name;
        LOG.info("deleteFile: " + fullPath);
        try {
            client.delete(fullPath);
        } catch (DbxException e) {
            Fail.fail(e.getMessage(), e);
        }
    }

    public static String uploadFile(String path, String name) throws Exception {
        String fullPath = path + "/" + name;
        LOG.info("uploadFile: " + fullPath);
        File inputFile = new File(name);
        try (FileInputStream inputStream = new FileInputStream(inputFile)) {
            DbxEntry.File uploadedFile = client.uploadFile(fullPath,
                    DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println("Uploaded: " + uploadedFile.toString());
        }

        String shareableUrl = client.createShareableUrl(fullPath);
        LOG.info("shareableUrl: " + shareableUrl);
        return shareableUrl;
    }

    public static DbxEntry.WithChildren getFolderFiles(String folder) throws Exception {
        LOG.info("getFolderFiles:" + folder);
        DbxEntry.WithChildren listing = client.getMetadataWithChildren(folder);
        if (listing == null) {
            LOG.info("No files in the path");
            return null;
        }
        LOG.info("Files in the path:");
        for (DbxEntry child : listing.children) {
            System.out.println("	" + child.name + ": " + child.toString());
        }
        return listing;
    }

    public static boolean isClientInitialized() {
        return client != null;
    }

    public static String createShareableUrl(String path) throws DbxException {
        LOG.info("createShareableUrl: " + path);
        return client.createShareableUrl(path);
    }
}
