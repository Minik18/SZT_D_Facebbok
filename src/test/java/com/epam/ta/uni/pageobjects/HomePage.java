package com.epam.ta.uni.pageobjects;

import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import com.epam.ta.uni.factory.WebDriverFactory;

import java.util.Map;

@Component
public class HomePage extends CommonPageObject {
    private static final String HOME_PAGE_URL = "https://www.facebook.com/";

    @FindBy(css = "a[data-testid=\"open-registration-form-button\"]")
    private WebElement registrationButton;

    @FindBy(css = "button[data-cookiebanner='accept_button']")
    private WebElement cookieDisclaimer;

    @FindBy(css = "button[name=login]")
    private WebElement loginButton;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "pass")
    private WebElement passwordInput;

    @FindBy(css = "input[aria-label='Keresés a Facebookon']")
    private WebElement searchInput;

    @FindBy(css = "div[role=\"banner\"] > div:nth-child(2) > div > div > div > div > div > label")
    private WebElement searchLabel;

    @FindBy(css = "div[role='article']:nth-of-type(1) a[role='presentation']")
    private WebElement firstResult;

    @FindBy(css = "form[data-testid='royal_login_form'] > div > a")
    private WebElement recoverPassword;

    @FindBy(xpath = "//a[@href=\"https://www.facebook.com/pages/?category=top&ref=bookmarks\"]")
    private WebElement profiles;

    @FindBy(css = "a[aria-label=\"Új oldal létrehozása\"]")
    private WebElement newProfileLink;

    @FindBy(css = "label[aria-label='Oldal neve (kötelező megadni)'] input")
    private WebElement pageNameInput;

    @FindBy(css = "label[aria-label='Kategória (kötelező megadni)'] input")
    private WebElement pageCategoryInput;

    @FindBy(css = "div[aria-label='Tovább']")
    private WebElement alertContinueButton;


    public HomePage(final WebDriverFactory factory) {
        super(factory);
    }

    public void navigateToHomePage() {
        navigateToUrl(HOME_PAGE_URL);
    }

    private final Map<String, WebElement> inputFieldsMap = Map.ofEntries(
            Map.entry("Email", emailInput),
            Map.entry("Password", passwordInput),
            Map.entry("Regisztráció", registrationButton),
            Map.entry("Bejelentkezés", loginButton),
            Map.entry("Keresés a Facebookon", searchInput),
            Map.entry("Keresés ikon", searchLabel),
            Map.entry("Első találat", firstResult),
            Map.entry("Elfelejtett jelszó", recoverPassword),
            Map.entry("Oldalak", profiles),
            Map.entry("Új oldal létrehozása", newProfileLink),
            Map.entry("Oldal neve", pageNameInput),
            Map.entry("Kategória", pageCategoryInput)
    );

    public WebElement getInputFieldByName(final String name) {
        return inputFieldsMap.get(name);
    }

    public void clickOnElement(final String name) {
        clickOnElement(inputFieldsMap.get(name));
    }

    public void clickOnRegistrationButton() {
        waitForElementToBeClickable(registrationButton);
        registrationButton.click();
        waitForPageReadiness();
    }

    public void clickOnCookieDisclaimer() {
        waitForElementToBeClickable(cookieDisclaimer);
        clickWithJsExecutor(cookieDisclaimer);
    }

    public Object goToHomePageWithAlert() {
//        try {
//            navigateToUrl(HOME_PAGE_URL);
//        }
//        catch(UnhandledAlertException ex) {
//            new WebDriverWait(getWebDriverFromFactory(), 15).until(ExpectedConditions.alertIsPresent());
//            getWebDriverFromFactory().switchTo().alert().accept();
//        }
//        waitForPageReadiness();
//        navigateToUrl(HOME_PAGE_URL);
//        waitForPageReadiness();
        getWebDriverFromFactory().navigate().back();
        waitForPageReadiness();
        waitForElementToBeClickable(alertContinueButton);
        clickWithJsExecutor(alertContinueButton);
        waitForPageReadiness();
        navigateToUrl(HOME_PAGE_URL);
        return true;
    }
}
