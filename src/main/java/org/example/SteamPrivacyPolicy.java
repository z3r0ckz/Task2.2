package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class SteamPrivacyPolicy {
   By languagePulldownList = By.id("language_pulldown");
    By policyYear = By.xpath("//i[contains(text(), '2023')]");
    By homeText = By.linkText("Home");


    public SteamPrivacyPolicy(WebDriver driver){
        PageFactory.initElements(Singleton.Driver(), this);
    }
    public void switchLanguages(){
        //Language List is Displayed
        Singleton.Driver().findElement(languagePulldownList).click();
        assertTrue(Singleton.Driver().findElement(languagePulldownList).isDisplayed());
        System.out.println("Validate that the Language list is displayed");
        //Hide the List
        Singleton.Driver().findElement(languagePulldownList).click();

        System.out.println("Check if the windows open in the next tab");
        assertEquals(Singleton.getActualIndexTab(),1);
    }

    public void AllLanguagesDisplayed()
    {
        System.out.println("Validate that all the languages are displayed");
        WebElement languages =
                (WebElement) Singleton.Driver().findElements(By.xpath("//div[@id='languages']")
        );
        assertTrue(languages.isDisplayed());
    }

    public void policyYear(){
        String signedYear = Singleton.Driver().findElement(policyYear).getText();
        System.out.println(signedYear);
            assertEquals("Revision Date: January 1, 2023",signedYear);
        }
    }

