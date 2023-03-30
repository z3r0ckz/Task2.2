package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import java.io.IOException;

public class SteamSearchPage {

    public SteamSearchPage(WebDriver driver) throws IOException {

    }

    //Locators
    WebElement GameTitle1 = UtilityClass.waitFor("//a[contains(@class, 'search_result_row')][1]//span[@class='title']");
    WebElement GameTitle2 = UtilityClass.waitFor("//a[contains(@class, 'search_result_row')][2]//span[@class='title']");

    WebElement GameReview1 = UtilityClass.waitFor("//a[contains(@class, 'search_result_row')][1]//span[contains(@class,'search_review_summary')]");
    WebElement GameReview2 = UtilityClass.waitFor("//a[contains(@class, 'search_result_row')][2]//span[contains(@class,'search_review_summary')]");

    String gameReview1 = GameReview1.getAttribute("data-tooltip-html");
    String gameReview2 = GameReview2.getAttribute("data-tooltip-html");

    WebElement GamePrice1 = UtilityClass.waitFor("//a[contains(@class, 'search_result_row')][1]//div[contains(@class,'search_price_discount')]");
    WebElement GamePrice2 = UtilityClass.waitFor("//a[contains(@class, 'search_result_row')][2]//div[contains(@class,'search_price_discount')]");

    String gamePrice1 = GamePrice1.getAttribute("data-price-final");
    String gamePrice2 = GamePrice2.getAttribute("data-price-final");


    WebElement GameReleaseDate1 = UtilityClass.waitFor("//a[contains(@class, 'search_result_row')][1]//div[contains(@class,'search_released')]");
    WebElement GameReleaseDate2 = UtilityClass.waitFor("//a[contains(@class, 'search_result_row')][2]//div[contains(@class,'search_released')]");

    String gameRelease1 = GameReleaseDate1.getText();
    String gameRelease2 = GameReleaseDate2.getText();
    By gameBarSearch = By.id("term");
    public void searchBarAssert() throws IOException {
        System.out.println("Search box on result name contains searched name");
        String gameJson = UtilityClass.readGameSearch();
        String searchBar = Singleton.Driver("chrome").findElement(gameBarSearch).getAttribute("value");
        Assert.assertEquals(searchBar, gameJson);
    }

    public void firstElement() throws IOException {
        System.out.println("The first name is equal to searched name");
        String gameJson = UtilityClass.readGameSearch();
        String firstElement = Singleton.Driver("chrome").findElement((By) GameTitle1).getText();
        Assert.assertEquals(firstElement, gameJson);
    }

    public String[] game1WriteJson() throws IOException {


        String firstName = Singleton.Driver("chrome").findElement((By) GameTitle1).getText();
        String price1 = Singleton.Driver("chrome").findElement(By.id(gamePrice1)).getText();
        String review1 = Singleton.Driver("chrome").findElement(By.id(gameReview1)).getText();
        String release1 = Singleton.Driver("chrome").findElement(By.id(gameRelease1)).getText();
        return new String[]{firstName, price1,review1,release1 };

    }
    public String[] game2WriteJson() throws IOException {

        String secondName = Singleton.Driver("chrome").findElement((By) GameTitle2).getText();
        String price2 = Singleton.Driver("chrome").findElement(By.id(gamePrice2)).getText();
        String review2 = Singleton.Driver("chrome").findElement(By.id(gameReview2)).getText();
        String release2 = Singleton.Driver("chrome").findElement(By.id(gameRelease2)).getText();
        return new String[]{secondName, price2, review2,release2 };

    }

    public void secondSearch() throws IOException {
        System.out.println("Search the second name received from the result list");
        String secondSearch = Singleton.Driver("chrome").findElement((By) GameTitle2).getText();
        Singleton.Driver("chrome").findElement(gameBarSearch).clear();
        Singleton.Driver("chrome").findElement(gameBarSearch).sendKeys(secondSearch, Keys.ENTER);
        String textBox = Singleton.Driver("chrome").findElement(gameBarSearch).getAttribute("value");
        Assert.assertEquals(textBox,"Dota 2 Player Profiles");
        System.out.println("Search box on the result page contains searched name");
    }

    public void compareArray() throws IOException {
        String [] game1 = UtilityClass.readGamesJson(0);
        String [] game2 = UtilityClass.readGamesJson(1);
        String [] game1Fields = game1WriteJson();
        String [] game2Fields = game2WriteJson();

        for (int i=0; i< game1.length; i++){
            Assert.assertEquals(game1[i],game1Fields[i]);
            System.out.println("Actual: "+game1 [i]+" Expected:" +game1Fields[i]);
        }

        for (int i=0; i< game2.length; i++){
            Assert.assertEquals(game2[i],game2Fields[i]);
            System.out.println("Actual: "+game2 [i]+" Expected:" +game2Fields[i]);
        }
    }

}











