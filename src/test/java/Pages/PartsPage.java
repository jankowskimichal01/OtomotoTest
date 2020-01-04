package Pages;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class PartsPage {

    private AndroidDriver<AndroidElement> driver;
    private FluentWait<MobileDriver> wait;

    public PartsPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>((MobileDriver) driver)
                .pollingEvery(Duration.ofMillis(50))
                .withTimeout(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NullPointerException.class)
                .ignoring(ClassCastException.class)
        ;
    }

    private static final By moreFiltersSelector = new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"pl.otomoto:id/show_more_filters\")");
    private static final By subcategoryChooser = new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"pl.otomoto:id/subcategoryChooser\")");
    private static final By deliveryVehiclePartsSubCategory = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"Części do pojazdów dostawczych\")");
    private static final By carMake = new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"pl.otomoto:id/chooserBtn\")");
    private static final By carMakeSearch = new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"pl.otomoto:id/filter\")");
    private static final By getCarMakeSearchResult = new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"android:id/text1\").index(0)");
    private static final By partCategory = new MobileBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector()"
            + ".resourceId(\"pl.otomoto:id/search_scroll_view\")).scrollIntoView("
            + "new UiSelector().text(\"Rodzaj części\"));");
    private static final By selectRims = new MobileBy.ByAndroidUIAutomator("new UiSelector().text(\"Felgi\")");
    private static final By searchResults = new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"pl.otomoto:id/mag_icon\")");

    public ResultsPage searchResults() {
        clickElement(searchResults);
        return new ResultsPage(driver);
    }

    public boolean checkIfElementExists(By selector) {
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AndroidElement getElementIfExists(By selector) {
        if(!checkIfElementExists(selector)) {
            //should be added to report
            System.out.println("Element " + selector + "cannot be clicked");
            return null;
        }
        else {
            return driver.findElement(selector);
        }
    }

    public void clickElement(By selector) {
        getElementIfExists(selector).click();
    }

    public void selectMoreFilters() {
        clickElement(moreFiltersSelector);
    }

    public void chooseSubcategory(String subcategoryName) {
        clickElement(subcategoryChooser);
        switch(subcategoryName) {
            case "deliveryVehicleParts":
                clickElement(deliveryVehiclePartsSubCategory);
            default:
                throw new IllegalArgumentException("Unsupported subcategory");
        }
    }

    public void chooseCarMake(String make) {
        clickElement(carMake);
        getElementIfExists(carMakeSearch).sendKeys(make);
        clickElement(getCarMakeSearchResult);
    }

    public void choosePartCategory(String category) {
        driver.findElement(partCategory).click();
        switch(category){
            case "rims":
                clickElement(selectRims);
            default:
                throw new IllegalArgumentException("Unsupported part category");
        }
    }
}
