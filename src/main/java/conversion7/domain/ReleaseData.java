package conversion7.domain;

import com.dropbox.core.DbxEntry;
import conversion7.Utils;
import org.apache.log4j.Logger;
import org.fest.assertions.api.Assertions;

public class ReleaseData {

    private static final Logger LOG = Utils.getLoggerForClass();

    public static final String ARCHIVE_EXT = ".zip";
    public static final String GAME_ARCHIVE_PREFIX = "game-";
    public static final String LIB_ARCHIVE_PREFIX = "lib-";

    private String gameVersion;
    private String libVersion;
    private String releaseChangeLog;
    private String releaseLink;

    public ReleaseData(String gameVersion, String libVersion, String releaseChangeLog) {
        this.gameVersion = gameVersion;
        this.libVersion = libVersion;

        StringBuilder changesBuilder = new StringBuilder();
        for (String changeLine : releaseChangeLog.split("\\\\n")) {
            changesBuilder.append(changeLine).append("\n");
        }

        this.releaseChangeLog = changesBuilder.toString();
    }

    /** Existing release */
    public ReleaseData(DbxEntry.WithChildren folderFiles) {
        if (folderFiles == null) {
            LOG.info("ReleaseData is empty");
            return;
        }

        for (DbxEntry child : folderFiles.children) {
            if (child.name.contains(GAME_ARCHIVE_PREFIX)) {
                Assertions.assertThat(gameVersion).isNull();
                gameVersion = child.name.replaceAll(GAME_ARCHIVE_PREFIX, "").replaceAll(ARCHIVE_EXT, "");
            }
            if (child.name.contains(LIB_ARCHIVE_PREFIX)) {
                Assertions.assertThat(libVersion).isNull();
                libVersion = child.name.replaceAll(LIB_ARCHIVE_PREFIX, "").replaceAll(ARCHIVE_EXT, "");
            }
        }
    }

    public void setReleaseLink(String releaseLink) {
        this.releaseLink = releaseLink;
    }

    public String getReleaseLink() {
        return releaseLink;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public String getLibVersion() {
        return libVersion;
    }

    public String getReleaseChangeLog() {
        return releaseChangeLog;
    }

    public String getGameFileName() {
        if (getGameVersion() == null) {
            return null;
        }
        return GAME_ARCHIVE_PREFIX + getGameVersion() + ARCHIVE_EXT;
    }

    public String getLibFileName() {
        if (getLibVersion() == null) {
            return null;
        }
        return LIB_ARCHIVE_PREFIX + getLibVersion() + ARCHIVE_EXT;
    }
}
