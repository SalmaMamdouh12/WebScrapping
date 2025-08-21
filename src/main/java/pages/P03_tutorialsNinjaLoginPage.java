package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class P03_tutorialsNinjaLoginPage {

    // Driver Declaration
    WebDriver driver;

    // Constructor
    public P03_tutorialsNinjaLoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By deskTopCategory = By.xpath("(//nav/div/ul/li/a)[1]");
    private final By innerAllDeskTop = By.className("see-all");
    private final By allProducts = By.cssSelector(".product-layout");
    private final By productTitle = By.xpath(".//h4/a");
    private final By productDescription = By.cssSelector(".caption > p");
    private final By productPrice = By.cssSelector(".price");
    private final By productImage = By.cssSelector(".image img");
    private final By leftSidebarCategoryLinks = By.cssSelector("div.list-group a");

    // Method to open all products page
    public void showAllProductList() throws InterruptedException {
        driver.findElement(deskTopCategory).click();
        Thread.sleep(3000); // Ideally use WebDriverWait
        driver.findElement(innerAllDeskTop).click();
    }

    // Get all products on current page
    public List<WebElement> getAllProducts() {
        return driver.findElements(allProducts);
    }

    // Get product info
    public String getProductTitle(WebElement product) {
        return product.findElement(productTitle).getText();
    }

    public String getProductDescription(WebElement product) {
        return product.findElement(productDescription).getText();
    }

    public String getProductPrice(WebElement product) {
        return product.findElement(productPrice).getText();
    }

    public String getProductImageURL(WebElement product) {
        return product.findElement(productImage).getAttribute("src");
    }

    // Sidebar category links
    public List<WebElement> getSidebarCategoryElements() {
        return driver.findElements(leftSidebarCategoryLinks);
    }

    public String getSiteURL() {
        return driver.getCurrentUrl();
    }
}