package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class SteamPrivacyPolicy {
    By languagePulldownList = By.id("language_pulldown");
    By policyYear = By.xpath("//i[contains(text(), '2023')]");
    By languageList = By.xpath("//div[@id='languages']/a[contains(@href,'{0}')]");


    public SteamPrivacyPolicy(WebDriver driver){

    }
    public void switchLanguages() throws IOException {
        //Language List is Displayed
        Singleton.Driver("chrome").findElement(languagePulldownList).click();
        assertTrue(Singleton.Driver("chrome").findElement(languagePulldownList).isDisplayed());
        System.out.println("Validate that the Language list is displayed");
        //Hide the List
        Singleton.Driver("chrome").findElement(languagePulldownList).click();

        System.out.println("Check if the windows open in the next tab");
        assertEquals(UtilityClass.getActualIndexTab(),1);
    }

    public void AllLanguagesDisplayed() throws IOException {
        System.out.println("Validate that all the languages are displayed");
        List<WebElement> flagList = Singleton.Driver("chrome").findElements(languageList);
        for (int i = 0; i < flagList.size() ; i++) {
            flagList.get(i).getAttribute("a");
            System.out.println("link revisado" + flagList.get(i).getText());
            String[] list = UtilityClass.languageList();
            //For assert
            assertEquals(list[i], flagList.get(i).getText());
        }



    }

    public void policyYear() throws IOException {
        String signedYear = Singleton.Driver("chrome").findElement(policyYear).getText();
        System.out.println(signedYear);
            assertEquals("Revision Date: January 1, 2023",signedYear);
        }
    }

