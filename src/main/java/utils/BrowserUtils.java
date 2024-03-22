package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;


public class BrowserUtils {
    public static String takeScreenshot(String name) {

        name = new Date().toString()
                .replace(" ", "_")
                .replace(":", "-")
                + "_" + name;

        String pathOfFileToCopyScreenshot = System.getProperty("user.dir") + "/src/test-output/screenshots/" + name + ".png";
        TakesScreenshot screenshot = (TakesScreenshot) DriverManager.getDriver();
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File(pathOfFileToCopyScreenshot);

        try {
            FileUtils.copyFile(source, destination);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathOfFileToCopyScreenshot;
    }
}
