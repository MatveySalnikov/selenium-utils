package util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static util.Util.myWait;

public class WebDriverUtils {
    private static final int timeout = 15;

    public static WebElement getElement(WebDriver driver, By by){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        waitPageComplete(wait);
        return new WrappedWebElement(wait.until(ExpectedConditions.presenceOfElementLocated(by)), driver, by);
    }

    public static WebElement getVisibleElement(WebDriver driver, By by){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        waitPageComplete(wait);
        return new WrappedWebElement(wait.until(ExpectedConditions.visibilityOfElementLocated(by)), driver, by);
    }

    public static WebElement getClickableElement(WebDriver driver, By by){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        waitPageComplete(wait);
        return new WrappedWebElement(wait.until(ExpectedConditions.elementToBeClickable(by)), driver, by);
    }

    public static List<WebElement> getVisibleElements(WebDriver driver, By by){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        waitPageComplete(wait);
        List<WebElement> elements = new LinkedList<>();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by))
                .forEach(element -> elements.add(new WrappedWebElement(element, driver, by)));
        return elements;
    }

    public static List<WebElement> getVisibleElements(WebDriver driver, By by, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        waitPageComplete(wait);
        List<WebElement> elements = new ArrayList<>();
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by))
                    .forEach(element -> elements.add(new WrappedWebElement(element, driver, by)));
        }finally {
            return elements;
        }
    }

    public static List<WebElement> getExistingElements(WebDriver driver, By by){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        waitPageComplete(wait);
        List<WebElement> elements = new ArrayList<>();
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by))
                    .forEach(element -> elements.add(new WrappedWebElement(element, driver, by)));
        }finally {
            return elements;
        }
    }

    public static boolean isElementPresent(WebDriver driver, By by){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        waitPageComplete(wait);
        List<WebElement> elements = new ArrayList<>();
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by))
                    .forEach(element -> elements.add(new WrappedWebElement(element, driver, by)));
        }finally {
            return elements.size() == 1;
        }
    }

    public static void waitUntilElementIsInvisibility(WebDriver driver, By by, int timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        waitPageComplete(wait);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void scrollToElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public static boolean isVideoPlaying(WebDriver driver, WebElement element, int seconds){
        for(int i = 0; i < seconds; i++){
            String value = getElementProperty(driver, element, "paused");
            if(value.equals("false")){
                return true;
            }
            myWait(seconds * 1000);
        }
        return false;
    }

    public static String getElementProperty(WebDriver driver, WebElement element, String propertyName){
        String script = String.format("return arguments[0].%s", propertyName);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script, element).toString();
    }

    public static String getAlertMessage(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        if (alert != null){
            alert = driver.switchTo().alert();
            String message = alert.getText();
            alert.accept();
            driver.switchTo().defaultContent();
            return message;
        }
        return null;
    }

    public static void switchContextToFrame(WebDriver driver, WebElement element){
        driver.switchTo().frame(element);
    }

    public static void switchContextToDefault(WebDriver driver){
        driver.switchTo().defaultContent();
    }

    private static void waitPageComplete(WebDriverWait wait){
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}
