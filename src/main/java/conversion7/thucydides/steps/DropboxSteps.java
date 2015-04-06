package conversion7.thucydides.steps;

import conversion7.Constants;
import conversion7.DropboxServiceFacade;
import conversion7.Utils;
import conversion7.domain.ReleaseData;
import conversion7.properties.Key;
import conversion7.properties.PropertiesLoader;
import conversion7.thucydides.pages.DropboxAppAuthPage;
import conversion7.thucydides.pages.DropboxSignInPage;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.apache.log4j.Logger;
import org.fest.assertions.api.Assertions;
import org.fest.assertions.api.Fail;

public class DropboxSteps extends ScenarioSteps {

    private static final Logger LOG = Utils.getLoggerForClass();

    DropboxAppAuthPage dropboxAppAuthPage;
    DropboxSignInPage dropboxSignInPage;

    @Step
    public void upload_release_on_dropbox(ReleaseData releaseData) {
        init_dropbox_client();
        upload_files(releaseData);
    }

    @Step
    public void init_dropbox_client() {
        if (DropboxServiceFacade.isClientInitialized()) {
            LOG.debug("Client already initialized.");
            return;
        }

        try {
            String authUrl = DropboxServiceFacade.getAuthUrl();
            dropboxAppAuthPage.getDriver().get(authUrl);

            login_on_dropbox();
            dropboxAppAuthPage.clickAllowAccessBtn();
            String authCode = dropboxAppAuthPage.getTextAuthCodeLabel();

            DropboxServiceFacade.initClient(authCode);
        } catch (Exception e) {
            Fail.fail(e.getMessage(), e);
        }

        Assertions.assertThat(DropboxServiceFacade.isClientInitialized()).isTrue();
    }

    @Step
    public void upload_files(ReleaseData releaseData) {
        try {
            ReleaseData prevReleaseData = new ReleaseData(
                    DropboxServiceFacade.getFolderFiles(Constants.DROPBOX_ROOT_FOLDER));

            if (releaseData.getGameVersion().equals(prevReleaseData.getGameVersion())) {
                Fail.fail("Release already exist: " + releaseData.getGameVersion());
            }
            String curReleaseLibVersion = releaseData.getLibVersion();
            if (curReleaseLibVersion == null && prevReleaseData.getLibVersion() == null) {
                Fail.fail("No lib provided in previous and new releases!");
            }

            String prevReleaseDataGameFileName = prevReleaseData.getGameFileName();
            if (prevReleaseDataGameFileName != null) {
                // delete old game version
                DropboxServiceFacade.deleteFile(Constants.DROPBOX_ROOT_FOLDER, prevReleaseDataGameFileName);
            }
            DropboxServiceFacade.uploadFile(Constants.DROPBOX_ROOT_FOLDER, releaseData.getGameFileName());

            if (curReleaseLibVersion != null && !curReleaseLibVersion.isEmpty()) {
                String prevReleaseLibVersion = prevReleaseData.getLibVersion();
                if (!curReleaseLibVersion.equals(prevReleaseLibVersion)) {
                    if (prevReleaseLibVersion != null) {
                        // delete old lib if new lib will be added
                        DropboxServiceFacade.deleteFile(Constants.DROPBOX_ROOT_FOLDER, prevReleaseData.getLibFileName());
                    }
                    DropboxServiceFacade.uploadFile(Constants.DROPBOX_ROOT_FOLDER, releaseData.getLibFileName());
                }
            }

            String shareableUrl = DropboxServiceFacade.createShareableUrl(Constants.DROPBOX_ROOT_FOLDER);
            releaseData.setReleaseLink(shareableUrl);
        } catch (Exception e) {
            Fail.fail(e.getMessage(), e);
        }
    }

    @Step
    public void login_on_dropbox() {
        dropboxSignInPage.typeTextToLoginInput(PropertiesLoader.get(Key.DROPBOX_MAIL));
        dropboxSignInPage.typeTextToPasswordInput(PropertiesLoader.get(Key.DROPBOX_PASSWORD));
        dropboxSignInPage.clickSubmitLoginBtn();
    }


}
