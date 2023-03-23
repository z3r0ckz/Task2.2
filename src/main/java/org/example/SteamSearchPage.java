package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertEquals;

public class SteamSearchPage {


    By searchBox = By.id("term");
    public SteamSearchPage(WebDriver driver){
            PageFactory.initElements(Singleton.Driver(),this);
        }

    public static By positionList(int i){

        return By.xpath("//a[contains(@class, 'search_result_row')]["+i+"]//span[@class='title']");
    }
    public void searchPageField() {
        Singleton.waitFor(String.valueOf(searchBox));
        String textBox = Singleton.Driver().findElement(searchBox).getAttribute("value");
        String searchPos = Singleton.Driver().findElement(positionList(1)).getText();
        assertEquals(textBox,"Dota 2");
        System.out.println("Search box on the result name contains searched name: "+textBox);
        assertEquals(searchPos,"Dota 2");
        System.out.println("The first name is equal to searched name: "+searchPos);


    }
    public void searchSecondGame(){
        System.out.println("Search the second name received from the result list");
        String searchPos = Singleton.Driver().findElement(positionList(2)).getText();
        System.out.println(searchPos);
        Singleton.Driver().findElement(searchBox).clear();
        Singleton.Driver().findElement(searchBox).sendKeys(searchPos, Keys.ENTER);
        String textBox = Singleton.Driver().findElement(searchBox).getAttribute("value");
        assertEquals(textBox,"Dota 2 Player Profiles");
        System.out.println("Search box on the result page contains searched name");
    }

    }





