package conversion7.thucydides.steps;

import conversion7.thucydides.pages.WikidotSignInPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class WikidotSignInPageSteps extends ScenarioSteps {

    WikidotSignInPage wikidotSignInPage;

    @Step
    public void sign_in_as(String login, String password) {
        wikidotSignInPage.open();
        wikidotSignInPage.typeTextToLoginInput(login);
        wikidotSignInPage.typeTextToPasswordInput(password);
        wikidotSignInPage.clickSubmitLoginBtn();
        wikidotSignInPage.waitFor(3).second();
    }
}
