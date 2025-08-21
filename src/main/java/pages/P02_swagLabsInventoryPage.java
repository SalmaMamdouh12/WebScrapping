package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class P02_swagLabsInventoryPage {

    WebDriver driver;

    //constructor
    public P02_swagLabsInventoryPage(WebDriver driver){
        this.driver= driver;
    }

    //export locators
    private final By allProducts = By.className("inventory_item");
    private final By allProductNames = By.className("inventory_item_name");
    private final By allProductDesc = By.className("inventory_item_desc");
    private final By allProductPrices =  By.className("inventory_item_price");
    private final By allImages = By.cssSelector(".inventory_item_img img");

    //methods for locators
    public List<WebElement> getAllProducts() {
        return driver.findElements(allProducts);
    }

    public String getProductName(WebElement product) {
        return product.findElement(allProductNames).getText();
    }

    public String getProductDesc(WebElement product) {
        return product.findElement(allProductDesc).getText();
    }

    public String getProductPrice(WebElement product) {
        return product.findElement(allProductPrices).getText();
    }

    public String getProductImageURL(WebElement product) {
        return product.findElement(allImages).getAttribute("src");
    }

}
