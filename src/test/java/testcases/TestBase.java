package testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import java.awt.*;

import static drivers.DriverFactory.getNewInstance;
import static drivers.DriverHolder.getDriver;
import static drivers.DriverHolder.setDriver;
import static pages.PageBase.quitBrowser;
import static util.Utility.openBrowserNetworkTab;

public class TestBase {

    SoftAssert softAssert; //initial soft assertions

    @Parameters({"browser","url"})
    @BeforeTest
    public void setupDriver(@Optional String browser, String url) throws AWTException {

        if (browser == null)
            browser = "";

        setDriver(getNewInstance(browser));
        getDriver().get(url);
        // TODO: open browser network tab
        openBrowserNetworkTab();

    }

    @AfterTest
    public void teardown() {
        if (getDriver() != null)
            // clear cache , cookies, local storage
            quitBrowser(getDriver());
    }

}
