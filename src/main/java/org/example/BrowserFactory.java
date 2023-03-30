package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;

public class BrowserFactory {
    public static WebDriver createWebDriver(String browserType) throws IOException {
        if (browserType.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();

            //Pasarle el sting the config de la utility class
            options.addArguments(UtilityClass.readChromeBrowserConfig());
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(options);
        } else if (browserType.equalsIgnoreCase("firefox")) {
            FirefoxOptions optionsFx = new FirefoxOptions();
            optionsFx.addArguments(UtilityClass.readFirefoxConfig());
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver(optionsFx);
        } else if (browserType.equalsIgnoreCase("edge")) {
            // configuración de Edge
            return new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Tipo de navegador no soportado: " + browserType);
        }
    }
}
