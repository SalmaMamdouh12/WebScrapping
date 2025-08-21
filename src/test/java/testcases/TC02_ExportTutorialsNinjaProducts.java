package testcases;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.P03_tutorialsNinjaLoginPage;
import util.Utility;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static drivers.DriverHolder.getDriver;

public class TC02_ExportTutorialsNinjaProducts extends TestBase {

    String siteURL;
    String filePath = Utility.getTodayFileName();
    P03_tutorialsNinjaLoginPage p03TutorialsNinjaLoginPage;

    @Test(priority = 1, description = "Scrape and append TutorialsNinja products to the same CSV")
    public void extractTutorialsNinjaProductData_P() throws InterruptedException, IOException {

        p03TutorialsNinjaLoginPage = new P03_tutorialsNinjaLoginPage(getDriver());

        // Navigate to the base product list page
        p03TutorialsNinjaLoginPage.showAllProductList();

        // Append mode: open existing file, skip header
        Utility.initCSVForAppend(filePath);
        Utility.writeEmptyLine(); // Adds a blank line before second dataset

        List<WebElement> sidebarLinks = p03TutorialsNinjaLoginPage.getSidebarCategoryElements();
        int categoryCount = sidebarLinks.size();

        for (int i = 0; i < categoryCount; i++) {
            // Re-navigate to main product list
            p03TutorialsNinjaLoginPage.showAllProductList();
            Thread.sleep(2000);

            // Re-fetch to avoid stale element reference
            sidebarLinks = p03TutorialsNinjaLoginPage.getSidebarCategoryElements();
            WebElement category = sidebarLinks.get(i);
            String categoryName = category.getText().trim();

            System.out.println("Opening category: " + categoryName);
            category.click();
            Thread.sleep(2000);

            List<WebElement> products = p03TutorialsNinjaLoginPage.getAllProducts();
            siteURL = getDriver().getCurrentUrl();

            if (products.size() == 0) {
                System.out.println("No products found in category: " + categoryName);
            }

            for (WebElement product : products) {
                List<String> row = new ArrayList<>();
                row.add(p03TutorialsNinjaLoginPage.getProductTitle(product));
                row.add(p03TutorialsNinjaLoginPage.getProductDescription(product));
                row.add(p03TutorialsNinjaLoginPage.getProductPrice(product));
                row.add(p03TutorialsNinjaLoginPage.getProductImageURL(product));
                row.add(siteURL);

                Utility.writeCSVRow(row);
            }
        }
        Utility.writeTotalPriceRow();
        Utility.closeCSV();
        System.out.println("TutorialsNinja products appended to CSV.");
    }

}