package org.example;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.json.simple.*;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.internal.IContainer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

public class SteamGamePage {

    public SteamGamePage(WebDriver driver) {
        PageFactory.initElements(Singleton.Driver(), this);
    }

    //Locators
    WebElement GameTitle1 = Singleton.waitFor("//a[contains(@class, 'search_result_row')][1]//span[@class='title']");
    WebElement GameTitle2 = Singleton.waitFor("//a[contains(@class, 'search_result_row')][2]//span[@class='title']");

    String gameTitle1 = GameTitle1.getText();
    String gameTitle2 = GameTitle2.getText();

    WebElement GameReview1 = Singleton.waitFor("//a[contains(@class, 'search_result_row')][1]//span[contains(@class,'search_review_summary')]");
    WebElement GameReview2 = Singleton.waitFor("//a[contains(@class, 'search_result_row')][2]//span[contains(@class,'search_review_summary')]");

    String gameReview1 = GameReview1.getAttribute("data-tooltip-html");
    String gameReview2 = GameReview2.getAttribute("data-tooltip-html");

    WebElement GamePrice1 = Singleton.waitFor("//a[contains(@class, 'search_result_row')][1]//div[contains(@class,'search_price_discount')]");
    WebElement GamePrice2 = Singleton.waitFor("//a[contains(@class, 'search_result_row')][2]//div[contains(@class,'search_price_discount')]");

    String gamePrice1 = GamePrice1.getAttribute("data-price-final");
    String gamePrice2 = GamePrice2.getAttribute("data-price-final");


    WebElement GameReleaseDate1 = Singleton.waitFor("//a[contains(@class, 'search_result_row')][1]//div[contains(@class,'search_released')]");
    WebElement GameReleaseDate2 = Singleton.waitFor("//a[contains(@class, 'search_result_row')][2]//div[contains(@class,'search_released')]");

    String gameRelease1 = GameReleaseDate1.getText();
    String gameRelease2 = GameReleaseDate2.getText();


    @SuppressWarnings("unchecked")
    public void addElementsToJSON() throws IOException {
        System.out.println("Start to add Elements on the Json File of GameList");
        // Variables to store the text of the elements and then pass them to the JSON
        // object
        // Array of JSON to write to the file
        JSONArray gamesArr = new JSONArray();
        for (int i = 0; i < 2; i++) {

                JSONObject jsonObj = new JSONObject();
                jsonObj.put("Title", gameTitle1);
                jsonObj.put("ReleaseDate", gameRelease1);
                jsonObj.put("reviewSummary", gameReview1);
                jsonObj.put("Price", gamePrice1);

                gamesArr.add(jsonObj);
                //jsonObj.put("Platforms")

        }
        // Write the array of JSON objects to the file, flush the writer and close it
        try (FileWriter writer = new FileWriter("src/JsonFiles/gameList.json", true)) {
            writer.write(gamesArr.toJSONString());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            System.out.println("Exception Handle" + e);
        }

    }

    public void JsonReader() throws IOException, ParseException {
        System.out.println("Start the JsonReader");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/JsonFiles/gameList.json"));
        JSONArray gamesArr = (JSONArray) obj;

        {
            for (Object gameObj:
                 gamesArr) {
                JSONObject game = (JSONObject) gameObj;
                String title = (String) game.get("Title");
                String releaseDate = (String) game.get("ReleaseDate");
                String reviewSummary = (String) game.get("reviewSummary");
                double price = (double) game.get("Price");

                //Assertion of values on the Json file
                Object[] expectedValues = { title };
                Object[] actualValues = new Object[1];
                actualValues[0] = title;
                assertArrayEquals("Games values should match", expectedValues, actualValues);
                System.out.println("Result list contains 2 Stored Items from previous Search");
            }
        }
    }
}











