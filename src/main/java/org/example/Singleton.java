package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;


public class Singleton {
    private static WebDriver driver;

    public static WebDriver Driver() {

        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("lang=en-GB");
            options.addArguments("--incognito");
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability(ChromeOptions.CAPABILITY, options);
            options.merge(cap);
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

        }

        return driver;
    }
    public static void SwitchToTab(int tabIndex)
    {
        Set<String> windowHandles = Singleton.Driver().getWindowHandles();
        String tabToSwitch = new ArrayList<>(windowHandles).get(tabIndex);
        Singleton.Driver().switchTo().window(tabToSwitch);
    }

    public static int getActualIndexTab(){
        int currentWindowIndex = -1;
        String currentWindowHandle = Singleton.Driver().getWindowHandle();

        Set<String> windowHandles = Singleton.Driver().getWindowHandles();
        int i = 0;
        for (String windowHandle : windowHandles) {
            if (windowHandle.equals(currentWindowHandle)) {
                currentWindowIndex = i;
                break;
            }
            i++;
        }
        return currentWindowIndex;
    }
    public static  WebElement waitFor(String locator)
    {
        int timeoutInSeconds = 3;
        Duration durationTimeout = Duration.ofSeconds(timeoutInSeconds);
        WebDriverWait wait = new WebDriverWait(Singleton.Driver(),durationTimeout);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        return element;
    }
    public static WebElement FindByXPath(String locator)
    {
        return Singleton.Driver().findElement(By.xpath(locator));
    }

}

