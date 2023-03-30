package org.example;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;


public class UtilityClass {

    //Read the browser configuration
    public static String @NotNull [] readChromeBrowserConfig() throws IOException {
        String browserConfig = new String(Files.readAllBytes(Paths.get("src/JsonFiles/chromeConfig.json")));
        JSONArray jsonArray = new JSONArray(browserConfig);
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        String browserMode = jsonObject.getString("browserMode");
        String language = jsonObject.getString("language");
        String remoteAllow = jsonObject.getString("remoteAllow");

        return new String[]{browserMode,language,remoteAllow};
    }

    //Read Firefox options
    public static String @NotNull [] readFirefoxConfig() throws IOException {
        String browserConfig = new String(Files.readAllBytes(Paths.get("src/JsonFiles/chromeConfig.json")));
        JSONArray jsonArray = new JSONArray(browserConfig);
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        String browserMode = jsonObject.getString("browserMode");
        String language = jsonObject.getString("language");
        String remoteAllow = jsonObject.getString("remoteAllow");

        return new String[]{browserMode,language,remoteAllow};
    }
    //Read the search game
    public static String readGameSearch() throws IOException {
        String gameSearch = new String(Files.readAllBytes(Paths.get("src/JsonFiles/gameSearch.json")));
        JSONArray jsonArray = new JSONArray(gameSearch);
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        return jsonObject.getString("Game");
    }

    //Read the gameList that we obtain form the web
    public static String @NotNull [] readGamesJson(int index) throws IOException, JSONException {
        String data = new String(Files.readAllBytes(Paths.get("src/JsonFiles/gameList.json")));

        JSONArray jsonArray = new JSONArray(data);

        JSONObject jsonObject = jsonArray.getJSONObject(index);

        String name = jsonObject.getString("name");
        String reviews = jsonObject.getString("reviews");
        String date = jsonObject.getString("release data");
        String price = jsonObject.getString("price");

        return new String[]{name,price,reviews,date};

    }

    public static String[] languageList() throws IOException, JSONException {
        String data = new String(Files.readAllBytes(Paths.get("src/JsonFiles/languagesList.json")));

        JSONArray jsonArray = new JSONArray(data);

        JSONObject jsonObject = jsonArray.getJSONObject(0);

        String english = jsonObject.getString("english");
        String spanish = jsonObject.getString("spanish");
        String french = jsonObject.getString("french");
        String german = jsonObject.getString("german");
        String italian = jsonObject.getString("italian");
        String russian = jsonObject.getString("russian");
        String japanese = jsonObject.getString("japanese");
        String portuguese = jsonObject.getString("portuguese");
        String brazilian = jsonObject.getString("brazilian");
        return new String[]{english,spanish,french,german,italian,
        russian,japanese,portuguese,brazilian};

    }



    public static void writingToFile(String @NotNull [] firstGame, String @NotNull [] secondGame) throws JSONException{
        JSONArray gamesArr = new JSONArray();
        JSONObject jsonObj1 = new JSONObject();
        jsonObj1.put("Title", firstGame[0]);
        jsonObj1.put("Price", firstGame[1]);
        jsonObj1.put("reviewSummary", firstGame[2]);
        jsonObj1.put("ReleaseDate", firstGame[3] );

        JSONObject jsonObj2 = new JSONObject();
        jsonObj2.put("Title", secondGame[0]);
        jsonObj2.put("Price", secondGame[1]);
        jsonObj2.put("reviewSummary", secondGame[2]);
        jsonObj2.put("ReleaseDate", secondGame[3] );

        gamesArr.putAll(jsonObj1);
        gamesArr.putAll(jsonObj2);

        // Write the array of JSON objects to the file, flush the writer and close it
        try (FileWriter writer = new FileWriter("src/JsonFiles/gameList.json", true)) {
            writer.write(gamesArr.put(jsonObj1.length(),jsonObj2).length());
            writer.flush();
        } catch (Exception e) {
            System.out.println("Exception Handle" + e);
        }

    }


    public static void SwitchToTab(int tabIndex) throws IOException {
        Set<String> windowHandles = Singleton.Driver("chrome").getWindowHandles();
        String tabToSwitch = new ArrayList<>(windowHandles).get(tabIndex);
        Singleton.Driver("chrome").switchTo().window(tabToSwitch);
    }

    public static int getActualIndexTab() throws IOException {
        int currentWindowIndex = -1;
        String currentWindowHandle = Singleton.Driver("chrome").getWindowHandle();

        Set<String> windowHandles = Singleton.Driver("chrome").getWindowHandles();
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
    public static WebElement waitFor(String locator) throws IOException {
        int timeoutInSeconds = 3;
        Duration durationTimeout = Duration.ofSeconds(timeoutInSeconds);
        WebDriverWait wait = new WebDriverWait(Singleton.Driver("chrome"),durationTimeout);
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
    }

    public static String getHeader() throws IOException {
        String getHeaderText;
        getHeaderText = Singleton.Driver("chrome").getTitle();


        return getHeaderText;
    }


}
