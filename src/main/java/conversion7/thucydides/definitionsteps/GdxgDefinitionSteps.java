package conversion7.thucydides.definitionsteps;

import conversion7.Utils;
import conversion7.domain.ReleaseData;
import conversion7.properties.Key;
import conversion7.properties.PropertiesLoader;
import conversion7.thucydides.session.Session;
import conversion7.thucydides.steps.DropboxSteps;
import conversion7.thucydides.steps.WikidotGdxgHomePageSteps;
import conversion7.thucydides.steps.WikidotGdxgReleasesPageSteps;
import conversion7.thucydides.steps.WikidotSignInPageSteps;
import net.thucydides.core.annotations.Steps;
import org.apache.log4j.Logger;
import org.fest.assertions.api.Assertions;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;

import java.util.Map;

public class GdxgDefinitionSteps {

    private static final Logger LOG = Utils.getLoggerForClass();

    @Steps
    DropboxSteps dropboxSteps;
    @Steps
    WikidotGdxgHomePageSteps wikidotGdxgHomePageSteps;
    @Steps
    WikidotSignInPageSteps wikidotSignInPageSteps;
    @Steps
    WikidotGdxgReleasesPageSteps wikidotGdxgReleasesPageSteps;

    @Given("browser maximize")
    public void browserMaximize() {
        wikidotSignInPageSteps.getDriver().manage().window().maximize();
    }

    @Given("release info: $releaseTable")
    public void releaseInfoNew(ExamplesTable releaseTable) {
        Map<String, String> releaseInfo = releaseTable.getRows().get(0);
        ReleaseData releaseData = new ReleaseData(releaseInfo.get("gameVersion"),
                releaseInfo.get("libVersion"),
                releaseInfo.get("releaseChangeLog"));
        Session.setReleaseData(releaseData);
    }

    @When("upload release on Dropbox")
    public void uploadReleaseOnDropbox() {
        ReleaseData releaseData = Session.getReleaseData();
        dropboxSteps.upload_release_on_dropbox(releaseData);
        Assertions.assertThat(releaseData.getReleaseLink()).isNotNull();
    }

    @When("add release info on Wikidot")
    public void addReleaseInfoOnWikidot() {
        wikidotSignInPageSteps.sign_in_as(PropertiesLoader.get(Key.WIKIDOT_MAIL),
                PropertiesLoader.get(Key.WIKIDOT_PASSWORD));
        wikidotGdxgReleasesPageSteps.add_release_description_on_page(Session.getReleaseData());
    }

    @Then("everybody happy")
    public void everybodyHappy() {
    }
}
