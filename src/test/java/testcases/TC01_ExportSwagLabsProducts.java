package testcases;

import drivers.DriverHolder;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_swagLabsLoginPage;
import pages.P02_swagLabsInventoryPage;
import util.Utility;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static drivers.DriverHolder.getDriver;

public class TC01_ExportSwagLabsProducts extends TestBase {

    P02_swagLabsInventoryPage p02_swagLabsInventoryPage;
    String username = "standard_user";
    String password = "secret_sauce";
    String filePath = Utility.getTodayFileName();
    String siteURL;

    @Test(priority = 1, description = "Product Details Scrapping for Swag Labs")
    public void extractSwagLabsProductData_P() throws IOException, InterruptedException {

        // Initialize CSV with dynamic filename
        Utility.initCSV(filePath, false);

        p02_swagLabsInventoryPage = new P02_swagLabsInventoryPage(getDriver());

        new P01_swagLabsLoginPage(getDriver()).fillUsername(username).fillPassword(password).clickLoginButton();

        // Now get the actual page URL after login
        siteURL = DriverHolder.getDriver().getCurrentUrl();
        System.out.println("Captured site URL: " + siteURL);

        List<WebElement> products = p02_swagLabsInventoryPage.getAllProducts();

        Utility.writeCSVHeader(new String[]{"Name", "Description", "Price", "ImageURL", "SiteURL"});

        for (WebElement product : products) {
            List<String> row = new ArrayList<>();
            row.add(p02_swagLabsInventoryPage.getProductName(product));
            row.add(p02_swagLabsInventoryPage.getProductDesc(product));
            row.add(p02_swagLabsInventoryPage.getProductPrice(product));
            row.add(p02_swagLabsInventoryPage.getProductImageURL(product));
            row.add(siteURL);

            Utility.writeCSVRow(row);
        }
        // Append Total Price row
        Utility.writeTotalPriceRow();
        Utility.closeCSV();

        List<String> lines = Files.readAllLines(Paths.get(filePath));
        Assert.assertEquals(lines.size(), products.size() + 5); // +2 for header and date-time
    }
}