package conversion7.thucydides.session;

import conversion7.domain.ReleaseData;

public class Session {
    private static ReleaseData releaseData;

    public static ReleaseData getReleaseData() {
        return releaseData;
    }

    public static void setReleaseData(ReleaseData releaseData) {
        Session.releaseData = releaseData;
    }
}
