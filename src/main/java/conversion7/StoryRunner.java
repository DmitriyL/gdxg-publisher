package conversion7;

import conversion7.properties.PropertiesLoader;
import net.thucydides.jbehave.ThucydidesJUnitStories;

public class StoryRunner extends ThucydidesJUnitStories {

    public StoryRunner() {
        setProperties();
        findStoriesCalled("release_on_wikidot.story");
    }

    private void setProperties() {
        PropertiesLoader.init();

        System.setProperty("webdriver.driver", "chrome");
        System.setProperty("webdriver.chrome.driver", Constants.APP_ROOT + "\\drivers\\chromedriver.exe");
    }

}
