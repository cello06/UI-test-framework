package step_definitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.BrowserUtils;
import utils.ConfigManager;
import utils.DriverManager;

public class Hooks {
    private static final Logger LOGGER = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        LOGGER.info("::::::::::::::::::::TEST STARTED::::::::::::::::::::::::::");
        LOGGER.info("Scenario Name ->>>>>>>>>>  " + scenario.getName());
        LOGGER.info("Browser Type  ->>>>>>>>>> " + ConfigManager.getProperty("browser"));
        DriverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot screenshot = (TakesScreenshot) DriverManager.getDriver();
            byte[] image = screenshot.getScreenshotAs(OutputType.BYTES);
            String pathOfCopiedImage = BrowserUtils.takeScreenshot(scenario.getName());
            scenario.attach(image, "image/png", scenario.getName());
            LOGGER.trace("Copy of screenshot is copied -> " + pathOfCopiedImage);
            LOGGER.error(":::::::::::::Test Scenario Failed ->>>>>>>>> " + scenario.getName() + ":::");
        } else {
            LOGGER.info("::::::::::::::::::::TEST PASSED::::::::::::::::::::::::::");
        }
        DriverManager.killDriver();
    }
}
