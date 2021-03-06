package tests;

import Pages.MainPage;
import Pages.PartsPage;
import Pages.ResultsPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class PartsTest {

    private AndroidDriver<AndroidElement> driver;

    @BeforeMethod
    public void beforeMethod() throws MalformedURLException {
        //TO DO
        //Set of capabilities should be in separate file
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Mi A2 Lite");
        caps.setCapability("udid", "d1c696c80605");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "pl.otomoto");
        caps.setCapability("appActivity", "com.fixeads.verticals.cars.startup.view.activities.StartupActivity");
        caps.setCapability("browserName", "");

        driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
    }

    @AfterMethod
    public void afterMethod(){
        //TO DO
        //capture screenshot if failed
        System.out.println("afterMethod");
    }

    @AfterSuite
    public void afterSuite(){
        //TO DO
        //Generate report
        System.out.println("afterSuite");
        driver.quit();
    }

    @Test
    public void ifRimsForDeliveryMercedesAvailable(){
        MainPage mainPage = new MainPage(driver);

        PartsPage parts = mainPage.goToPartsPage();
        parts.selectMoreFilters();
        parts.chooseSubcategory("deliveryVehicleParts");
        parts.chooseCarMake("Mercedes-benz"); //for test 'Acerbi'
        parts.choosePartCategory("rims");

        ResultsPage results = parts.searchResults();
        Assert.assertFalse(results.isEmpty(),"Result of search should not be empty");
    }
}
