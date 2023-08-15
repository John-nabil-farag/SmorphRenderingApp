package testCases;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class UdemyPractice {

    WebDriver driver;

    @BeforeSuite
    public void initializeWebDriver()
    {
        ChromeDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions=new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }
}
