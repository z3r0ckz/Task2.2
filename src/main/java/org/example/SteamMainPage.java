package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;


public class SteamMainPage {

    By searchBar = By.id("store_nav_search_term");
    By searchButton = By.id("store_search_link");
    @FindBy(linkText = "Privacy Policy")
    WebElement privacyPolicyText;

    @FindBy (id = "store_nav_search_term")
    WebElement id2;


    public SteamMainPage(WebDriver driver){
        PageFactory.initElements(Singleton.Driver(),this);
    }
    public String getHeader(){
        String getHeaderText;
        getHeaderText = Singleton.Driver().getTitle();


        return getHeaderText;
    }

   public void scrollUp(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) Singleton.Driver();
        jsExecutor.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
    }
    public void scrollAndPrivacyPolicy (){
        JavascriptExecutor js = (JavascriptExecutor) Singleton.Driver();
        js.executeScript("arguments[0].scrollIntoView();", privacyPolicyText);
        //Open Privacy Policy in a new tab
        Actions action = new Actions(Singleton.Driver());
        action.keyDown(Keys.CONTROL).moveToElement(privacyPolicyText).click().perform();
        ArrayList <String> tabs = new ArrayList<>(Singleton.Driver().getWindowHandles());
       try{
           Singleton.Driver().switchTo().window(tabs.get(1));
           //Get header
           String getHeaderText = getHeader();
           System.out.println(getHeaderText);
           assertEquals ("Privacy Policy Agreement",getHeaderText);
        }
        catch (NoSuchWindowException e){
            System.out.println("No such windows exception Handle");
        }
    }
    public void activePage(){
        if (Singleton.getActualIndexTab() != 0){
            Singleton.SwitchToTab(0);
            scrollUp();
            System.out.println("Do the Scroll UP");
            System.out.println(Singleton.getActualIndexTab());
            Singleton.Driver().switchTo().defaultContent();
            Singleton.Driver().switchTo().activeElement().click();
        }

    }
    public void dotaSearch() {
        System.out.println("Dota 2 Search");
        System.out.println("Input search");
        Singleton.Driver().switchTo().defaultContent();
        Singleton.Driver().findElement(searchBar).sendKeys("Dota 2",Keys.ENTER);
        System.out.println("Click search button");
        //Singleton.Driver().findElement(searchButton).click();
        String currentUrl = Singleton.Driver().getCurrentUrl();
        System.out.println("validate that we are on the correct page");
        assertEquals("https://store.steampowered.com/search/?term=Dota+2",currentUrl);
    }

}

