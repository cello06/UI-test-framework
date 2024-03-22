package step_definitions;

import org.openqa.selenium.WebDriver;
import pages.BasePage;
import utils.DriverManager;
import utils.Pages;

public abstract class BaseStep {

    protected WebDriver driver ;

    protected Pages pages;


    public BaseStep(){
        driver = DriverManager.getDriver();
        pages = new Pages();
    }
}
