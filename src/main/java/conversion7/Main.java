package conversion7;

public class Main {

    private static final String REGEX_GET_RELEASE_NAME = "release\\S*";


    public static void main(String[] args) {
        testPageSource();
    }

    private static void testPageSource() {
        // create @@line1@@\n@@line2@@
        String lines = "line1\nline2";

        StringBuilder newLines = new StringBuilder();
        for (String line : lines.split("\\n")) {
            newLines.append("@@").append(line).append("@@\n");
        }

        System.out.println(newLines.toString());
    }

    private static void testNthOccurrence() {
        String nthOccurrence = Utils.getNthOccurrence(1, REGEX_GET_RELEASE_NAME, "* [*https://www.dropbox.com/s/8nzvyshxl6oeckf/release-0.02-no-libs.zip?dl=0 release-0.02-no-libs ]");
        System.out.println(nthOccurrence);
    }


}
