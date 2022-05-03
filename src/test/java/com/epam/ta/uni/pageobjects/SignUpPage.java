package com.epam.ta.uni.pageobjects;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.stereotype.Component;

import com.epam.ta.uni.factory.WebDriverFactory;

@Component
public class SignUpPage extends CommonPageObject {

    @FindBy(css = "#reg_form_box button")
    private WebElement registrationButton;

    @FindBy(css = "input[name=reg_email__]")
    private WebElement emailInput;

    @FindBy(css = "input[name=lastname]")
    private WebElement lastNameInput;

    @FindBy(id = "confirm")
    private WebElement confirmEmailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "year")
    private WebElement yearInput;

    @FindBy(id = "day")
    private WebElement dayInput;

    private final Map<String, WebElement> inputFieldsMap = Map.of(
        "Email", emailInput,
        "Vezetéknév", lastNameInput,
        "Create password.", passwordInput,
        "YYYY", yearInput,
        "DD", dayInput,
            "Regisztráció", registrationButton
    );

    public SignUpPage(final WebDriverFactory factory) {
        super(factory);
    }

    public void clickOnRegistrationButton() {
        clickOnElement(registrationButton);
    }


    public void clickOnElement(final String name) {
        clickOnElement(inputFieldsMap.get(name));
    }

    public WebElement getInputFieldByName(final String name) {
        return inputFieldsMap.get(name);
    }

    public void waitForRegPanel() {
        waitForElementToBeClickable(emailInput);
    }

    public boolean isEmailClickable() {
        return isElementClickable(emailInput);
    }
}
