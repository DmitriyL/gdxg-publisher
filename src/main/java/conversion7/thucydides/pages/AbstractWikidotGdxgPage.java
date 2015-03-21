package conversion7.thucydides.pages;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;

public class AbstractWikidotGdxgPage extends PageObject {

    @FindBy(id = "edit-button")
    private WebElementFacade editButton;

    @FindBy(id = "edit-page-textarea")
    private WebElementFacade editPageContentArea;

    @FindBy(id = "edit-save-button")
    private WebElementFacade editSaveBtn;

    public void clickEditButton() {
        editButton.click();
    }

    public void clickEditSaveBtn() {
        editSaveBtn.click();
    }

    public String getEditPageContentAreaText() {
        return editPageContentArea.getText();
    }

    public void typeTextToEditPageContentArea(final String text) {
        editPageContentArea.type(text);
        editPageContentArea.sendKeys(Keys.BACK_SPACE);

        String[] lines = text.split("\\n");
        for (int i = 0; i < lines.length; i++) {
            editPageContentArea.sendKeys(Keys.BACK_SPACE);
            editPageContentArea.sendKeys(Keys.BACK_SPACE);
            editPageContentArea.sendKeys(Keys.HOME);
            editPageContentArea.sendKeys(Keys.DELETE);
            editPageContentArea.sendKeys(Keys.DELETE);

            editPageContentArea.sendKeys(Keys.ARROW_UP);
            editPageContentArea.sendKeys(Keys.END);
        }

    }


}
