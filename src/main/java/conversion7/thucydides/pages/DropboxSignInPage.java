package conversion7.thucydides.pages;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;

public class DropboxSignInPage extends PageObject {

    @FindBy(xpath = ".//input[@name='login_email']")
    private WebElementFacade loginInput;

    @FindBy(xpath = ".//input[@name='login_password']")
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
