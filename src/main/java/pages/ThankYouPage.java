package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ThankYouPage {

    @FindBy(xpath = "//img[@src=\"images/speech_morphing_logo.png\"]")
    public WebElement smorphLogo;

    @FindBy(xpath = "//span[text()=\"2.0\"]")
    public WebElement appVersion;

    @FindBy(xpath = "//*[@href=\"SmorphingApp_userGuide.pdf\"]")
    public WebElement userGuide;

    @FindBy(xpath = "//h1")
    public WebElement thanksMessage;

    @FindBy(xpath = "//a[@href=\"index.html\"]")
    public WebElement backToLoginPage;

    public ThankYouPage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }
}
