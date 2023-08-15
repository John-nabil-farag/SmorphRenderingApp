package testCases;

import com.google.gson.JsonObject;
import com.mashape.unirest.http.exceptions.UnirestException;
import helpers.JsonReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SignUpPage;
import pages.ThankYouPage;

import java.time.Duration;

public class SignUpTest extends BaseTest{

    JsonObject credentials;
    LoginPage loginPage;
    SignUpPage signUpPage;
    ThankYouPage thankYouPage;


    @BeforeClass
    public void initializePages()
    {
        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        thankYouPage = new ThankYouPage(driver);
    }

    @BeforeClass
    public void readTestData()
    {
        //Read the data from json
        JsonReader jsonReader = new JsonReader();
        credentials = jsonReader.readJsonFileToJsonObject("src/main/resources/Data/signUpData.json");
    }

    @BeforeMethod
    public void navigateToSignUpPage()
    {
        driver.get("https://qa4.speechmorphing.com/RenderingApp/");
        loginPage.notSignedUpYet.click();
    }

    @Test
    public void checkImplementedDesignIsMatching()
    {
        Assert.assertEquals(driver.getTitle(), "SpeechMorphing Rendering App Signup Page");
        Boolean p = (Boolean)((JavascriptExecutor)driver).executeScript("return arguments[0].complete" + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", signUpPage.smorphLogo);
        Assert.assertEquals(signUpPage.appVersion.getText(), "2.0");
        Assert.assertEquals(signUpPage.userGuide.getText(), "User Guide");
        Assert.assertEquals(signUpPage.pageTitle.getText(), "SIGN UP");
        Assert.assertEquals(signUpPage.firstName.getAttribute("placeholder"), "First Name");
        Assert.assertEquals(signUpPage.lastName.getAttribute("placeholder"),  "Last Name");
        Assert.assertEquals(signUpPage.emailAddress.getAttribute("placeholder"), "Email Address");
        Assert.assertEquals(signUpPage.password.getAttribute("placeholder"), "Password");
        Assert.assertEquals(signUpPage.confirmPassword.getAttribute("placeholder"), "Confirm password");
        Assert.assertEquals(signUpPage.submitButton.getText(), "Submit");
        Assert.assertEquals(signUpPage.AlreadySignedUp.getText(), "Already signed up?");
    }

    @Test
    public void checkClickingOnSubmitButtonWithoutFillingFields()
    {
        signUpPage.submitButton.click();
        Assert.assertEquals(signUpPage.fNameErrorMessage.getText(), "Please enter First Name");
        Assert.assertEquals(signUpPage.lNameErrorMessage.getText(), "Please enter Last Name");
        Assert.assertEquals(signUpPage.emailAddressErrorMessage.getText(), "Please enter correct e-mail address");
        Assert.assertEquals(signUpPage.passwordErrorMessage.getText(), "Please enter password at least 6 characters");
    }

    @Test
    public void checkReceivingActivationEmail() throws UnirestException {
        signUpPage.signUp();
        Assert.assertEquals(driver.getCurrentUrl(), "https://qa4.speechmorphing.com/RenderingApp/signup.html");
        //signUpPage.getInbox();
    }

}
