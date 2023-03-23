import org.example.*;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCases {

    SteamMainPage objSteamMainPage;
    SteamSearchPage objSteamSearchPage;
    SteamPrivacyPolicy objSteamPrivacyPolicy;
    SteamGamePage objSteamGamePage;

    @BeforeTest
    public void setWebpage(){
        String url = "https://store.steampowered.com/";
        Singleton.Driver().get(url);
    }

      @Test (priority = 1)
      public void scrollAndClick(){
            System.out.println("Test 1, scroll and click on privacy policy");
            objSteamMainPage = new SteamMainPage(Singleton.Driver());
            objSteamMainPage.scrollAndPrivacyPolicy();

   }

    @Test(priority = 2)
    public void testPrivacyPolicy(){
            System.out.println("kjfksjhdf";)
            System.out.println("Test 2, Language list displayed, and languages validation");
            objSteamPrivacyPolicy = new SteamPrivacyPolicy(Singleton.Driver());
            objSteamPrivacyPolicy.switchLanguages();
            //objSteamPrivacyPolicy.AllLanguagesDisplayed();
    }

    @Test(priority = 3)
    public void privacyPolicyYear(){
        System.out.println("Test 3, validation year of the Privacy Policy");
        objSteamPrivacyPolicy = new SteamPrivacyPolicy(Singleton.Driver());
        objSteamPrivacyPolicy.policyYear();

    }


    @Test(priority = 4)
    public void mainPageSearchDota() throws IOException {
        System.out.println("Start of the test 4");
        objSteamMainPage = new SteamMainPage(Singleton.Driver());
        objSteamMainPage.activePage();
        objSteamMainPage.dotaSearch();
        objSteamSearchPage = new SteamSearchPage(Singleton.Driver());
        objSteamSearchPage.searchPageField();
        objSteamGamePage = new SteamGamePage(Singleton.Driver());
        objSteamGamePage.addElementsToJSON();

    }

    @Test (priority = 5)
    public void secondSearchOfList() throws IOException, ParseException {
        System.out.println("Start of the test 5");
        objSteamSearchPage = new SteamSearchPage(Singleton.Driver());
        objSteamSearchPage.searchSecondGame();
        objSteamGamePage = new SteamGamePage(Singleton.Driver());
        objSteamGamePage.JsonReader();

    }
    @AfterTest
    public void terminateBrowser(){
        Singleton.Driver().quit();
    }
}
