package testCases;

import com.google.gson.JsonObject;
import helpers.JsonReader;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AccountAndProjectPage;
import pages.LoginPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class LoginTest extends BaseTest{

    JsonObject credentials;
    LoginPage loginPage;
    AccountAndProjectPage accountAndProjectPage;


    @BeforeClass
    public void initializePages()
    {
        loginPage = new LoginPage(driver);
        accountAndProjectPage = new AccountAndProjectPage(driver);
    }

    @BeforeClass
    public void readTestData()
    {
        //Read the data from json
        JsonReader jsonReader = new JsonReader();
        credentials = jsonReader.readJsonFileToJsonObject("src/main/resources/Data/loginData.json");
    }

    @BeforeMethod
    public void navigateToLoginPage()
    {
        driver.get("https://qa4.speechmorphing.com/RenderingApp/");
    }

    @Test
    public void checkImplementedDesignIsMatching()
    {
        Assert.assertEquals(driver.getTitle(), "Speech Rendering App - Login");
        //Javascript executor to check smorph logo
        Boolean p = (Boolean)((JavascriptExecutor)driver).executeScript("return arguments[0].complete" + "&& typeof arguments[0].naturalWidth != \"undefined\" " + "&& arguments[0].naturalWidth > 0", loginPage.smorphLogo);
        Assert.assertEquals(loginPage.appVersion.getText(), "2.0");
        Assert.assertEquals(loginPage.pageTitle.getText(), "SIGN IN");
        Assert.assertEquals(loginPage.emailAddress.getAttribute("placeholder"), "Email Address");
        Assert.assertEquals(loginPage.password.getAttribute("placeholder"), "Password");
        Assert.assertEquals(loginPage.loginButton.getText(), "Login");
        Assert.assertEquals(loginPage.notSignedUpYet.getText(), "Not signed up yet?");
        Assert.assertEquals(loginPage.forgotPassword.getText(), "Forgot password?");
    }


    @Test
    public void checkChangeOfMouseCursorShape()
    {
        Assert.assertEquals(loginPage.hasFingerCursor(driver, loginPage.loginButton), true);
    }

    @Test
    public void checkAogInWithActivatedEmailAndValidPassword()
    {
        loginPage.login(credentials.get("EmailAddress").getAsString(), credentials.get("Password").getAsString());
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe(" "));
        Assert.assertEquals(driver.getTitle(), "Speech Rendering App - Setup");
    }

    @Test
    public void checkLogInWithInvalidEmailAndValidPassword()
    {
        loginPage.login(credentials.get("invalidEmail").getAsString(), credentials.get("Password").getAsString());
        Assert.assertEquals(driver.getTitle(), "Speech Rendering App - Login");
        Assert.assertEquals(loginPage.usernameErrorMessage.getText(), "Error:user not Found with details.");
        Assert.assertEquals(loginPage.passwordErrorMessage.getText(), "Error:user not Found with details.");
    }

    @Test
    public void checkLogInWithValidActivatedEmailAndInvalidPassword()
    {
        loginPage.login(credentials.get("EmailAddress").getAsString(), credentials.get("invalidPassword").getAsString());
        Assert.assertEquals(driver.getTitle(), "Speech Rendering App - Login");
        Assert.assertEquals(loginPage.usernameErrorMessage.getText(), "Error:incorrect Password.");
        Assert.assertEquals(loginPage.passwordErrorMessage.getText(), "Error:incorrect Password.");
    }

    @Test
    public void checkLogInWithInvalidEmailAndInvalidPassword()
    {
        loginPage.login(credentials.get("invalidEmail").getAsString(), credentials.get("invalidPassword").getAsString());
        Assert.assertEquals(driver.getTitle(), "Speech Rendering App - Login");
        Assert.assertEquals(loginPage.usernameErrorMessage.getText(), "Error:user not Found with details.");
        Assert.assertEquals(loginPage.passwordErrorMessage.getText(), "Error:user not Found with details.");
    }

    @Test
    public void checkEmailIsMandatory()
    {
        loginPage.login("",credentials.get("Password").getAsString());
        Assert.assertEquals(driver.getTitle(), "Speech Rendering App - Login");
        Assert.assertEquals(loginPage.usernameErrorMessage.getText(), "Please enter username");
    }

    @Test
    public void checkPasswordIsMandatory()
    {
        loginPage.login(credentials.get("EmailAddress").getAsString(), "");
        Assert.assertEquals(driver.getTitle(), "Speech Rendering App - Login");
        Assert.assertEquals(loginPage.passwordErrorMessage.getText(), "Please enter password");
    }

    @Test
    public void checkEmailIsNotCaseSensitive()
    {
        loginPage.login(credentials.get("EmailAddressCaps").getAsString(),credentials.get("Password").getAsString());
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlToBe("https://qa4.speechmorphing.com/RenderingApp/select.html"));
        Assert.assertEquals(driver.getTitle(), "Speech Rendering App - Setup");
    }

    @Test
    public void checkPasswordIsCaseSensitive()
    {
        loginPage.login(credentials.get("EmailAddress").getAsString(), credentials.get("PasswordCaps").getAsString());
        Assert.assertEquals(driver.getTitle(), "Speech Rendering App - Login");
        Assert.assertEquals(loginPage.usernameErrorMessage.getText(), "Error:incorrect Password.");
        Assert.assertEquals(loginPage.passwordErrorMessage.getText(), "Error:incorrect Password.");
    }

    @Test
    public void checkLogInWithNonActivatedEmail()
    {
        loginPage.login(credentials.get("nonActivatedEmail").getAsString(), credentials.get("nonActivatedPassword").getAsString());
        Assert.assertEquals(driver.getTitle(), "Speech Rendering App - Login");
        Assert.assertEquals(loginPage.usernameErrorMessage.getText(), "Error:please activate your account.");
        Assert.assertEquals(loginPage.passwordErrorMessage.getText(), "Error:please activate your account.");
    }

    @Test
    public void checkClickingOnNotSignedUpYet()
    {
        loginPage.notSignedUpYet.click();
        Assert.assertEquals(driver.getTitle(), "SpeechMorphing Rendering App Signup Page");
    }

    @Test
    public void checkClickingOnForgotPassword()
    {
        loginPage.forgotPassword.click();
        Assert.assertEquals(driver.getTitle(), "Speech Rendering App - Forgot password");
    }

    @Test
    public void checkClickingOnBrowserRefreshButton()
    {
        driver.navigate().refresh();
        Assert.assertEquals(driver.getTitle(), "Speech Rendering App - Login");
    }

    @Test
    public void checkClickingOnBrowserBackButton()
    {
        driver.navigate().back();
        Assert.assertEquals(driver.getTitle(), "");
    }

    @AfterMethod
    public void testScreenshot(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./Screenshots/" + result.getName() + ".png"));
        }
    }
}
