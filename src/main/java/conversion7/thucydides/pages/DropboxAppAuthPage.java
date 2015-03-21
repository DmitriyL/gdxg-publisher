package conversion7.thucydides.pages;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;

public class DropboxAppAuthPage extends PageObject {

    @FindBy(name = "allow_access")
    private WebElementFacade allowAccessBtn;
    @FindBy(id = "auth-code")
    private WebElementFacade authCodeLabel;

    public void clickAllowAccessBtn() {
        allowAccessBtn.click();
    }

    public String getTextAuthCodeLabel() {
        return authCodeLabel.getText();
    }

}
