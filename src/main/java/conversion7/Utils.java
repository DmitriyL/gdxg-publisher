package conversion7;

import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static Logger getLoggerForClass() {
        return Logger.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }

    public static String getNthOccurrence(int index, String pattern, String input) {
        Matcher m = Pattern.compile(pattern).matcher(input);

        int i = 0;
        while (m.find()) {
            if (i == index) {
                return m.group();
            }
            i++;
        }
        return null;
    }

    public static String getLastOccurrence(String pattern, String input) {
        Matcher m = Pattern.compile(pattern).matcher(input);

        String lastFound = null;
        while (m.find()) {
            lastFound = m.group();
        }
        return lastFound;
    }


}
