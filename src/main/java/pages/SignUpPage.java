package pages;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage{

    String fName = RandomStringUtils.randomAlphanumeric(4).toLowerCase();
    String lName = RandomStringUtils.randomAlphanumeric(4).toLowerCase();
    static String username = RandomStringUtils.randomAlphanumeric(8).toLowerCase();/*usernameGenerator();*/
    String EMAIL_DOMAIN = "mail.io";
    String emailID = username.concat("@"+EMAIL_DOMAIN);
    private static final String EMAIL_APIKEY = "mail7_api_key";
    private static final String EMAIL_APISecret = "mail7_api_secret";
    String enteredPassword = RandomStringUtils.randomAlphanumeric(6).toLowerCase();
    @FindBy(xpath = "//img[@src=\"images/speech_morphing_logo.png\"]")
    public WebElement smorphLogo;

    @FindBy(xpath = "//span[text()=\"2.0\"]")
    public WebElement appVersion;

    @FindBy(xpath = "//*[@href=\"SmorphingApp_userGuide.pdf\"]")
    public WebElement userGuide;

    @FindBy(xpath = "//*[text()='SIGN UP']")
    public WebElement pageTitle;

    @FindBy(xpath = "//*[@id=\"fname\"]")
    public WebElement firstName;

    @FindBy(xpath = "//*[text()='Please enter First Name']")
    public WebElement fNameErrorMessage;

    @FindBy(xpath = "//*[@id=\"lname\"]")
    public WebElement lastName;

    @FindBy(xpath = "//*[text()='Please enter Last Name']")
    public WebElement lNameErrorMessage;

    @FindBy(xpath = "//*[@id=\"email\"]")
    public WebElement emailAddress;

    @FindBy(xpath = "//*[text()=\"Please enter correct e-mail address\"]")
    public WebElement emailAddressErrorMessage;

    @FindBy(xpath = "//*[@id=\"password\"]")
    public WebElement password;

    @FindBy(xpath = "//*[text()=\"Please enter password at least 6 characters\"]")
    public WebElement passwordErrorMessage;

    @FindBy(xpath = "//*[@id=\"pass_confirmation\"]")
    public WebElement confirmPassword;

    @FindBy(xpath = "//*[@id=\"Passwords are not the same. Please re-enter\"]")
    public WebElement confirmPasswordErrorMessage;

    @FindBy(xpath = "//*[@id=\"SubmitButtonSignup\"]")
    public WebElement submitButton;

    @FindBy(xpath = "//*[@href=\"index.html\"]")
    public WebElement AlreadySignedUp;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public SignUpPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    public void signUp()
    {
        this.firstName.sendKeys(fName);
        this.lastName.sendKeys(lName);
        this.emailAddress.sendKeys(emailID);
        this.password.sendKeys(enteredPassword);
        this.confirmPassword.sendKeys(enteredPassword);
        submitButton.click();
    }

//    public String getInbox(String username) throws UnirestException {
//        HttpResponse<String> httpResponse = Unirest.get("https://api.mail7.io/inbox?apikey=" +
//                EMAIL_APIKEY + "&apisecret=" + EMAIL_APISecret + "&to=" + username).asString();
//
//        System.out.println(httpResponse.getHeaders().get("Content-Type"));
//        System.out.println(httpResponse.getBody());
//        return httpResponse.getBody();
//    }
}
