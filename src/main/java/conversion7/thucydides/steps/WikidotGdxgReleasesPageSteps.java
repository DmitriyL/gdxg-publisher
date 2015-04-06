package conversion7.thucydides.steps;

import conversion7.domain.ReleaseData;
import conversion7.domain.ReleasePageSources;
import conversion7.thucydides.pages.WikidotGdxgReleasesPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

public class WikidotGdxgReleasesPageSteps extends ScenarioSteps {

    WikidotGdxgReleasesPage wikidotGdxgReleasesPage;

    @Step
    public void add_release_description_on_page(ReleaseData releaseData) {
        wikidotGdxgReleasesPage.open();
        // TODO force remove Page lock conflict and edit original version
        wikidotGdxgReleasesPage.clickEditButton();

        ReleasePageSources releasePageSources = new ReleasePageSources(wikidotGdxgReleasesPage.getEditPageContentAreaText());
        wikidotGdxgReleasesPage.typeTextToEditPageContentArea(releasePageSources.generateNewSources(releaseData));

        wikidotGdxgReleasesPage.clickEditSaveBtn();
    }


}
