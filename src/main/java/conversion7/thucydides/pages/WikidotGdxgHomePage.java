package conversion7.thucydides.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.WebElementFacade;

@DefaultUrl("http://gdxg.wikidot.com")
public class WikidotGdxgHomePage extends AbstractWikidotGdxgPage {

    @FindBy(className = "login-status-sign-in")
    private WebElementFacade signInBtn;

    public void clickSignInBtn() {
        signInBtn.click();
    }
}
