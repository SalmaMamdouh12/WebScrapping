package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class P01_swagLabsLoginPage {

    //Driver Declaration
    WebDriver driver;

    //Constructor to Driver Initialization
    public P01_swagLabsLoginPage(WebDriver driver) {
       this.driver=driver;
    }

    // Locators
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginButton = By.id("login-button");

    //action methods
    public P01_swagLabsLoginPage fillUsername(String username){
        driver.findElement(this.username).sendKeys(username);
        return this;
    }
    public P01_swagLabsLoginPage fillPassword(String password){
        driver.findElement(this.password).sendKeys(password);
        return this;
    }
    public P01_swagLabsLoginPage clickLoginButton(){
        driver.findElement(this.loginButton).click();
        return this;
    }

}