package com.epam.ta.uni.pageobjects;

import static com.epam.ta.uni.config.TestConfig.PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.ta.uni.factory.WebDriverFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonPageObject {

    private WebDriverFactory factory;

    public CommonPageObject(final WebDriverFactory factory) {
        PageFactory.initElements(factory.getWebDriver(), this);
        this.factory = factory;
    }

    public WebDriver getWebDriverFromFactory() {
        return factory.getWebDriver();
    }

    public void waitForElementToBeClickable(final WebElement webElement) {
        try {
            new WebDriverWait(getWebDriverFromFactory(), PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS).until(
                ExpectedConditions.elementToBeClickable(webElement)
            );
        } catch (NoSuchElementException exception) {
            throw new RuntimeException("Element is not clickable!");
        }
    }

    public boolean isElementClickable(final WebElement webElement) {
        try {
            new WebDriverWait(getWebDriverFromFactory(), 1).until(
                    ExpectedConditions.elementToBeClickable(webElement)
            );
        } catch (NoSuchElementException exception) {
            return false;
        }
        return true;
    }

    public void clickOnElement(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
        waitForPageReadiness();
    }

    public void scrollToTheBottomOfThePage() {
        //waitForSeconds(1);
        ((JavascriptExecutor) getWebDriverFromFactory()).executeScript("window.scrollBy(0, 500)");
    }

    public void clickWithJsExecutor(final WebElement webElement) {
        ((JavascriptExecutor) getWebDriverFromFactory()).executeScript("arguments[0].click();", webElement);
    }

    public void waitForPageReadiness() {
        new WebDriverWait(getWebDriverFromFactory(), PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS).until(
            driver ->
                String.valueOf(
                    ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")
                )
        );
    }

    protected void navigateToUrl(final String url) {
        getWebDriverFromFactory().get(url);
        waitForPageReadiness();
    }

    public String getPageTitle() {
        return getWebDriverFromFactory().getTitle();
    }

    public void waitForSeconds(int seconds) {
        /*WebDriverWait w = new WebDriverWait(getWebDriverFromFactory(), seconds);
        w.until(
                driver -> String.valueOf(1==2)
        );*/
        //getWebDriverFromFactory().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
