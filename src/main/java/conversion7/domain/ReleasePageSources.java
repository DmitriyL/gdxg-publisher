package conversion7.domain;

import conversion7.Utils;
import org.fest.assertions.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ReleasePageSources {

    private static final String AUTO_UPDATED_SECTION_ANCHOR = "auto-updated section";
    private static final String RELEASE_ANCHOR = "http";
    private static final String REGEX_GET_RELEASE_NAME = "release\\S*";

    private String pageContent;
    private List<String> autoContentLines;
    private String staticPageSource;

    public ReleasePageSources(String pageContent) {
        this.pageContent = pageContent;
        parseBlocks();
    }

    public String getLastReleaseName() {
        Iterator<String> iterator = autoContentLines.iterator();
        while (iterator.hasNext()) {
            String nextRow = iterator.next();
            if (nextRow.contains(RELEASE_ANCHOR)) {
                return Utils.getLastOccurrence(REGEX_GET_RELEASE_NAME, nextRow);
            }
        }
        return null;
    }

    private void parseBlocks() {
        String[] lines = pageContent.split("\n");
        StringBuilder staticPageSourceBuilder = new StringBuilder();
        int row = 0;
        for (String string : lines) {
            staticPageSourceBuilder.append(string).append("\n");
            if (string.contains(AUTO_UPDATED_SECTION_ANCHOR)) {
                break;
            }
            row++;
        }

        staticPageSource = staticPageSourceBuilder.toString();

        List<String> lastRows = new ArrayList<>();
        Assertions.assertThat(row).as(AUTO_UPDATED_SECTION_ANCHOR + " was not found").isLessThan(lines.length - 1);
        lastRows.addAll(Arrays.asList(lines).subList(row, lines.length));

        autoContentLines = lastRows;
    }

    public String generateNewSources(ReleaseData releaseData) {
        String lastReleaseName = getLastReleaseName();
        Assertions.assertThat(lastReleaseName).isNotNull();

        String rawSources = new StringBuilder(staticPageSource).append("\n")
                .append("* ").append(lastReleaseName).append("\n")
                .append("* [*").append(releaseData.getReleaseLink()).append(" version-").append(releaseData.getGameVersion()).append(" ]").append("\n")
                .append("[[collapsible hideLocation=\"both\" show=\"changelog\"]]").append("\n")
                .append(releaseData.getReleaseChangeLog()).append("\n")
                .append("[[/collapsible]]").toString();

        // format sources to avoid wikidot auto-formatting during typing
        StringBuilder newLines = new StringBuilder();
        for (String line : rawSources.split("\\n")) {
            newLines.append("@@").append(line).append("@@\n");
        }
        return newLines.toString();
    }
}
