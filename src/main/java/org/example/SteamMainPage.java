package org.example;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.IOException;
import java.util.ArrayList;


import static org.testng.AssertJUnit.assertEquals;


public class SteamMainPage {

    By searchField = By.id("store_nav_search_term");
    By privacyPolicy = By.xpath("//a[contains(@href,'https://store.steampowered.com/privacy_agreement/?snr=1_44_44_')]");
    WebElement linkElementPP = Singleton.Driver("chrome").findElement(privacyPolicy);

    public SteamMainPage(WebDriver driver) throws IOException {

    }

   public void scrollUp() throws IOException, InterruptedException {
       Thread.sleep(2000);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Singleton.Driver("chrome");
        jsExecutor.executeScript("window.scrollBy(0, -document.body.scrollHeight)");

    }

    public void scrollPrivacyPolicy (@NotNull JavascriptExecutor js) throws IOException, InterruptedException {
        Thread.sleep(1000);
        WebElement prPo = Singleton.Driver("chrome").findElement(privacyPolicy);
        js.executeScript("arguments[0].scrollIntoView()", prPo);
    }

    public void privacyPolicyNewTab() throws  IOException {
        //Open Privacy Policy in a new tab
        Actions action = new Actions(Singleton.Driver("chrome"));
        action.keyDown(Keys.CONTROL).moveToElement(linkElementPP).click().perform();
        ArrayList <String> tabs = new ArrayList<>(Singleton.Driver("chrome").getWindowHandles());
        try{
            Singleton.Driver("chrome").switchTo().window(tabs.get(1));
            //Get header
            String getHeaderText = UtilityClass.getHeader();
            System.out.println(getHeaderText);
            assertEquals ("Privacy Policy Agreement",getHeaderText);
        }
        catch (NoSuchWindowException e){
            System.out.println("No such windows exception Handle");
        }

    }


    public void searchFirstGame() throws IOException, InterruptedException {
        System.out.println("First Game Search");
        String expectedUrl = "https://store.steampowered.com/search/?term=Dota+2";
        String gameJson = UtilityClass.readGameSearch();
        Thread.sleep(1000);
        Singleton.Driver("chrome").findElement(searchField).sendKeys("");
        Singleton.Driver("chrome").findElement(searchField).sendKeys(gameJson);
        Singleton.Driver("chrome").findElement(searchField).submit();
        String currentUrl = Singleton.Driver("chrome").getCurrentUrl();
        Assert.assertEquals(currentUrl,expectedUrl);
    }

}

