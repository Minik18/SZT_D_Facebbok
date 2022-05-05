package com.epam.ta.uni.stepdefinitions;

import com.epam.ta.uni.config.TestConfig;
import com.epam.ta.uni.pageobjects.HomePage;
import com.epam.ta.uni.pageobjects.SignUpPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.awaitility.Awaitility;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.time.Duration;

import static com.epam.ta.uni.config.TestConfig.PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS;

@CucumberContextConfiguration
@ContextConfiguration(classes = TestConfig.class)
public class FacebookStepDefs {

    @Autowired
    private HomePage homePage;

    @Autowired
    private SignUpPage signUpPage;

    @Given("the home page is opened")
    public void theHomePageIsOpened() {
        homePage.navigateToHomePage();
        homePage.waitForPageReadiness();
    }

    @And("the Cookie disclaimer is closed")
    public void theCookieDisclaimerIsClosed() {
        homePage.clickOnCookieDisclaimer();
    }

    @And("the Új fiók létrehozása header button is clicked")
    public void theRegistrationHeaderButtonIsClicked() {
        homePage.clickOnRegistrationButton();
        signUpPage.waitForRegPanel();
    }

    @Given("it is scrolled down")
    public void itIsScrolledDown() {
        signUpPage.scrollToTheBottomOfThePage();
        homePage.waitForPageReadiness();
    }

    /*@When("the Regisztráció button is clicked")
    public void theRegistrationButtonIsClicked() {
        signUpPage.clickOnElement("Regisztráció gomb");
    }

    @When("the email input is clicked")
    public void theEmailInputIsClicked() {
        signUpPage.clickOnElement("Email");
    }*/

    @When("^on the signup page '(.*)' is clicked$")
    public void theSignupElementIsClicked(final String name) {
        signUpPage.clickOnElement(name);
    }

    @When("^on the home page '(.*)' is clicked$")
    public void theHomePageElementIsClicked(final String name) {
        homePage.clickOnElement(name);
        homePage.waitForPageReadiness();
    }

    @And("^the '(.*)' error message of the '(?:.*)' (?:field|dropdown|radio buttons|checkbox) should be shown$")
    public void theErrorMessageShouldBeShown(final String message) {
        Awaitility.await(String.format("Element was not loaded in %s seconds", PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS))
                .atMost(Duration.ofSeconds(PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS))
                .until(() -> signUpPage.getWebDriverFromFactory().findElements(
                                By.xpath(String.format("//div[text()=\"%s\" or ./span[text()=\"%s\"]]", message, message))
                        ).size(),
                        Matchers.is(1));
    }

    @And("the Registration Panel must be visible")
    public void theRegistrationPanelMustBeVisible() {
        Awaitility.await(String.format("Element was not loaded in %s seconds", PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS))
                .atMost(Duration.ofSeconds(PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS))
                .until(() -> signUpPage.isEmailClickable(),
                        Matchers.is(true));
    }

    @Then("the Page title is {string}")
    public void thePageTitleIs(final String title) {
        Awaitility.await(String.format("Element was not loaded in %s seconds", PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS))
                .atMost(Duration.ofSeconds(PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS))
                .until(() -> homePage.getPageTitle(),
                        Matchers.is(title));
    }

    @Then("the Page title is like {string}")
    public void thePageTitleIsLike(final String regexp) {
        Awaitility.await(String.format("Element was not loaded in %s seconds", PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS))
                .atMost(Duration.ofSeconds(PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS))
                .until(() -> homePage.getPageTitle(),
                        Matchers.matchesPattern(regexp));
    }

    @When("the {string} is filled in with {string}")
    public void theFieldIsFilledWithParameter(final String field, final String content) {
        signUpPage.getInputFieldByName(field).sendKeys(content);
    }

    @When("on the home page the {string} is filled in with {string}")
    public void theHomePageFieldIsFilledWithParameter(final String field, final String content) {
        homePage.waitForPageReadiness();
        homePage.getInputFieldByName(field).sendKeys(content);
        homePage.waitForPageReadiness();
    }

    @When("the Tab button is pressed")
    public void theTabButtonIsPressed() {
        new Actions(signUpPage.getWebDriverFromFactory()).sendKeys(Keys.TAB).build().perform();
        signUpPage.waitForPageReadiness();
    }

    @When("the Enter key is pressed")
    public void theEnterKeyIsPressed() {
        new Actions(homePage.getWebDriverFromFactory()).sendKeys(Keys.ENTER).build().perform();
        homePage.waitForPageReadiness();
    }

    @When("the Down Arrow key is pressed")
    public void theDownArrowKeyIsPressed() {
        new Actions(homePage.getWebDriverFromFactory()).sendKeys(Keys.ARROW_DOWN).build().perform();
        homePage.waitForPageReadiness();
    }

    @Then("Leave Page alert is clickable")
    public void leavePageAlertIsClickable() {
        Awaitility.await(String.format("Element was not loaded in %s seconds", PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS))
                .atMost(Duration.ofSeconds(PAGE_OR_ELEMENT_LOAD_WAIT_SECONDS))
                .until(() -> homePage.goToHomePageWithAlert(),
                        Matchers.is(true));
        homePage.waitForPageReadiness();
    }
}
