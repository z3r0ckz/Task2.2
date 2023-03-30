import org.example.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCases {

    SteamMainPage objSteamMainPage;
    SteamPrivacyPolicy objSteamPrivacyPolicy;
    SteamSearchPage objSteamSearchPage;
    JavascriptExecutor js = (JavascriptExecutor) Singleton.Driver("chrome");

    public TestCases() throws IOException {
    }

    @BeforeTest
    public void setWebpage() throws IOException {
        String url = "https://store.steampowered.com/";
        Singleton.Driver("chrome").get(url);
    }

      @Test (priority = 1)
      public void scrollAndClick() throws IOException, InterruptedException {
            System.out.println("Test 1, scroll and click on privacy policy");
            objSteamMainPage = new SteamMainPage(Singleton.Driver("chrome"));
            objSteamMainPage.scrollPrivacyPolicy(js);
            objSteamMainPage.privacyPolicyNewTab();

   }

    @Test(priority = 2)
    public void testPrivacyPolicy() throws IOException {
            System.out.println("Test 2, Language list displayed, and languages validation");
            objSteamPrivacyPolicy = new SteamPrivacyPolicy(Singleton.Driver("chrome"));
            objSteamPrivacyPolicy.switchLanguages();
            objSteamPrivacyPolicy.AllLanguagesDisplayed();
    }

    @Test(priority = 3)
    public void privacyPolicyYear() throws IOException {
        System.out.println("Test 3, validation year of the Privacy Policy");
        objSteamPrivacyPolicy = new SteamPrivacyPolicy(Singleton.Driver("chrome"));
        objSteamPrivacyPolicy.policyYear();

        Singleton.Driver("chrome").close();

    }


    @Test(priority = 4)
    public void mainPageSearchDota() throws IOException, InterruptedException {
        System.out.println("Test 4 Search the First Game");
        UtilityClass.SwitchToTab(0);
        objSteamMainPage = new SteamMainPage(Singleton.Driver("chrome"));
        objSteamSearchPage = new SteamSearchPage(Singleton.Driver("chrome"));
        objSteamMainPage.scrollUp();
        objSteamMainPage.searchFirstGame();
        objSteamSearchPage.searchBarAssert();
        objSteamSearchPage.firstElement();

    }

    @Test (priority = 5)
    public void writingGamesJson() throws IOException {
        System.out.println("test 5 Save information of Games in Json");
        objSteamSearchPage = new SteamSearchPage(Singleton.Driver("chrome"));
        String [] firstGame = objSteamSearchPage.game1WriteJson();
        String [] secondGame = objSteamSearchPage.game2WriteJson();
        UtilityClass.writingToFile(firstGame,secondGame);

    }

    @Test (priority = 6)
    public void secondSearchOfList() throws IOException {
        System.out.println("Second Search of the list");
        objSteamSearchPage = new SteamSearchPage(Singleton.Driver("chrome"));
        objSteamSearchPage.secondSearch();
        System.out.println("Search if all the stored data Match");
        objSteamSearchPage.compareArray();

    }
    @AfterClass
    public void terminateBrowser() throws IOException {
        System.out.println("End of the Test");
        Singleton.Driver("chrome").quit();
    }
}
