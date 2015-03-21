package conversion7.thucydides.steps;

import conversion7.thucydides.pages.WikidotGdxgHomePage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class WikidotGdxgHomePageSteps extends ScenarioSteps {

    WikidotGdxgHomePage wikidotGdxgHomePage;

    @Step
    public void init_sign_in() {
        open_page();
        wikidotGdxgHomePage.clickSignInBtn();
        wikidotGdxgHomePage.waitFor(2).second();
    }

    @Step
    public void open_page() {
        wikidotGdxgHomePage.open();
    }
}
