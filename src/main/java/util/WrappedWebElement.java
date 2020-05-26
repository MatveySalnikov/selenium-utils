package util;

import org.openqa.selenium.*;
import java.util.List;
import static util.WebDriverUtils.*;

public class WrappedWebElement implements WebElement {
    private WebElement element;
    private WebDriver driver;
    private By by;

    public WrappedWebElement(WebElement element, WebDriver driver, By by){
        this.element = element;
        this.driver = driver;
        this.by = by;
    }

    public void click() {
        try{
            this.element.click();
        }catch(StaleElementReferenceException e){
            this.element = getClickableElement(driver, by);
            this.element.click();
        }
    }

    public void submit() {
        try{
            this.element.submit();
        }catch(StaleElementReferenceException e){
            this.element = getElement(driver, by);
            this.element.submit();
        }
    }

    public void sendKeys(CharSequence... charSequences) {
        try{
            this.element.sendKeys(charSequences);
        }catch(StaleElementReferenceException e){
            this.element = getClickableElement(driver, by);
            this.element.sendKeys(charSequences);
        }
    }

    public void clear() {
        try{
            this.element.clear();
        }catch(StaleElementReferenceException e){
            this.element = getClickableElement(driver, by);
            this.element.clear();
        }
    }

    public String getTagName() {
        try{
            return this.element.getTagName();
        }catch(StaleElementReferenceException e){
            this.element = getElement(driver, by);
            return this.element.getTagName();
        }
    }

    public String getAttribute(String s) {
        try{
            return this.element.getAttribute(s);
        }catch(StaleElementReferenceException e){
            this.element = getElement(driver, by);
            return this.element.getAttribute(s);
        }
    }

    public boolean isSelected() {
        try{
            return this.element.isSelected();
        }catch(StaleElementReferenceException e){
            this.element = getClickableElement(driver, by);
            return this.element.isSelected();
        }
    }

    public boolean isEnabled() {
        try{
            return this.element.isEnabled();
        }catch(StaleElementReferenceException e){
            this.element = getElement(driver, by);
            return this.element.isEnabled();
        }
    }

    public String getText() {
        try{
            return this.element.getText();
        }catch(StaleElementReferenceException e){
            this.element = getVisibleElement(driver, this.by);
            return this.element.getText();
        }
    }

    public List<WebElement> findElements(By by) {
        try{
            return this.element.findElements(by);
        }catch(StaleElementReferenceException e){
            this.element = getElement(driver, this.by);
            return this.element.findElements(by);
        }
    }

    public WebElement findElement(By by) {
        try{
            return this.element.findElement(by);
        }catch(StaleElementReferenceException e){
            this.element = getElement(driver, by);
            return this.element.findElement(by);
        }
    }

    public boolean isDisplayed() {
        try{
            return this.element.isDisplayed();
        }catch(StaleElementReferenceException e){
            this.element = getElement(driver, by);
            return this.element.isDisplayed();
        }
    }

    public Point getLocation() {
        try{
            return this.element.getLocation();
        }catch(StaleElementReferenceException e){
            this.element = getElement(driver, by);
            return this.element.getLocation();
        }
    }

    public Dimension getSize() {
        try{
            return this.element.getSize();
        }catch(StaleElementReferenceException e){
            this.element = getElement(driver, by);
            return this.element.getSize();
        }
    }

    public Rectangle getRect() {
        try{
            return this.element.getRect();
        }catch(StaleElementReferenceException e){
            this.element = getElement(driver, by);
            return this.element.getRect();
        }
    }

    public String getCssValue(String s) {
        try{
            return this.element.getCssValue(s);
        }catch(StaleElementReferenceException e){
            this.element = getElement(driver, by);
            return this.element.getCssValue(s);
        }
    }

    public <T> T getScreenshotAs(OutputType<T> outputType) throws WebDriverException {
        try{
            return this.element.getScreenshotAs(outputType);
        }catch(StaleElementReferenceException e){
            this.element = getElement(driver, by);
            return this.element.getScreenshotAs(outputType);
        }
    }
}
