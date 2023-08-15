package pages;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import helpers.JsonReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage{

    @FindBy(xpath = "//img[@src=\"images/speech_morphing_logo.png\"]" )
    public WebElement smorphLogo;

    @FindBy(xpath = "//span[text()=\"2.0\"]")
    public WebElement appVersion;

    @FindBy(xpath = "//*[text()='SIGN IN']")
    public WebElement pageTitle;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Username related fields
    @FindBy(xpath = "//*[@id=\"user_name\"]")
    public WebElement emailAddress;

    @FindBy(xpath = "//*[@class=\"name\"]//*[@class=\"help-block form-error\"]")
    public WebElement usernameErrorMessage;


    //Password related fields
    @FindBy(xpath = "//*[@id=\"password\"]")
    public WebElement password;

    @FindBy(xpath = "//*[@class=\"passw\"]//*[@class=\"help-block form-error\"]")
    public WebElement passwordErrorMessage;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FindBy(xpath = "//*[@id=\"SubmitButtonLogin\"]")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@href=\"signup.html\"]")
    public WebElement notSignedUpYet;

    @FindBy(xpath = "//*[@href=\"forgot.html\"]")
    public WebElement forgotPassword;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public LoginPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    public void login(String emailAddress, String password)
    {
        this.emailAddress.sendKeys(emailAddress);
        this.password.sendKeys(password);
        loginButton.click();
    }

    public boolean hasFingerCursor(WebDriver driver, WebElement rowElement)
    {
        Actions builder = new Actions(driver);
        builder.moveToElement(rowElement).perform();
        return "pointer".equals(rowElement.getCssValue("cursor"));
    }
}
