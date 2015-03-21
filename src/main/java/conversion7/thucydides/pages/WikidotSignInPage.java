package conversion7.thucydides.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.WebElementFacade;

@DefaultUrl("https://www.wikidot.com/default--flow/login__LoginPopupScreen?originSiteId=722035&openerUri=http://gdxg.wikidot.com")
public class WikidotSignInPage extends AbstractWikidotGdxgPage {

    @FindBy(name = "login")
    private WebElementFacade loginInput;

    @FindBy(name = "password")
    private WebElementFacade passwordInput;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElementFacade submitLoginBtn;

    public void clickSubmitLoginBtn() {
        submitLoginBtn.click();
    }

    public void typeTextToLoginInput(final String text) {
        loginInput.type(text);
    }

    public void typeTextToPasswordInput(final String text) {
        passwordInput.type(text);
    }
}
