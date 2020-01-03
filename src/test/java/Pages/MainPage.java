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

public class MainPage{

    private AndroidDriver<AndroidElement> driver;
    private FluentWait<MobileDriver> wait;

    public MainPage(AndroidDriver driver){
        this.driver = driver;
        this.wait = new FluentWait<>((MobileDriver) driver)
                .pollingEvery(Duration.ofMillis(50))
                .withTimeout(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NullPointerException.class)
                .ignoring(ClassCastException.class)
        ;;
    }

    private By partsButtonSelector = new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"pl.otomoto:id/tab_category_text_view\").text(\"CZĘŚCI\")");

    public PartsPage goToPartsPage(){
        wait.until(ExpectedConditions.presenceOfElementLocated(partsButtonSelector));
        driver.findElement(partsButtonSelector).click();
        return new PartsPage(driver);
    }

}
