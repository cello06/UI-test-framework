package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverManager {

    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);

    private static final ThreadLocal<WebDriver> THREAD_LOCAL = new ThreadLocal<>();

    public static WebDriver getDriver() {
        String browser = ConfigManager.getProperty("browser");

        if (THREAD_LOCAL.get() == null) {
            switch (browser.toLowerCase()) {
                case "edge":
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--start-maximized");
                    edgeOptions.addArguments("--ignore-certificate-error");
                    THREAD_LOCAL.set(new EdgeDriver(edgeOptions));
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--width-1920");
                    firefoxOptions.addArguments("--height-1080");
                    THREAD_LOCAL.set(new FirefoxDriver(firefoxOptions));
                    break;

                case "chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--ignore-certificate-error");
                    THREAD_LOCAL.set(new ChromeDriver(chromeOptions));
                    break;
                default:
                    LOGGER.error("There is no such browser");
            }
        }
        return THREAD_LOCAL.get();
    }

    public static void killDriver() {
        if (THREAD_LOCAL.get() != null) {
            THREAD_LOCAL.get().quit();
            THREAD_LOCAL.remove();
            LOGGER.info("The DRIVER is killed!");
        }
    }
}
