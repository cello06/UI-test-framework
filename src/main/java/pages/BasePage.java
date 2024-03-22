package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;

    protected Actions actions;

    protected WebDriverWait explicitlyWait;


    public BasePage(){
        driver = DriverManager.getDriver();
        actions = new Actions(driver);
        PageFactory.initElements(driver,this);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        explicitlyWait = new WebDriverWait(driver,Duration.ofSeconds(10));

    }
}
