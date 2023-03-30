package org.example;

import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class Singleton {

    private static WebDriver driver;
    public static WebDriver Driver(String browserName) throws IOException {

        if (driver == null) {
            driver = BrowserFactory.createWebDriver(browserName);
            driver.manage().window().maximize();
        }

        return driver;
    }
}

