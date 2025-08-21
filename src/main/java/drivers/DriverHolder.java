package drivers;

import org.openqa.selenium.WebDriver;

public class DriverHolder {

    //Webdriver defined as thread local
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    //Getter method to return thread local driver
    public static synchronized WebDriver getDriver() {
        return driver.get();
    }

    //Setter to set driver
    public static void setDriver(WebDriver driver) {
        DriverHolder.driver.set(driver);
    }

}