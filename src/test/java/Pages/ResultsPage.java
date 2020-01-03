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

public class ResultsPage {
    private AndroidDriver<AndroidElement> driver;
    private FluentWait<MobileDriver> wait;

    public ResultsPage(AndroidDriver driver){
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

    private By results = new MobileBy.ByAndroidUIAutomator("new UiSelector().resourceId(\"pl.otomoto:id/recyclerView\")");

    public boolean isEmpty(){
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(results));
            return true;
        } catch (Exception e){
            return false;
        }
    }

}
