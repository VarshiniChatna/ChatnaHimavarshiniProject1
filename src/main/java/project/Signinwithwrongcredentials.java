package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Signinwithwrongcredentials {

	WebDriver driver;

    @BeforeTest
    public void initializeDriver() {
        System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
        driver = new ChromeDriver();
    }
    
    @Test
    public void enterInvalidCredentialsAndSignIn() {
        driver.get("https://magento.softwaretestingboard.com/");
        WebElement SigninLink = driver.findElement(By.linkText("Sign In"));
        SigninLink.click();
        driver.findElement(By.id("email")).sendKeys("invalid_email@example.com");
        driver.findElement(By.id("pass")).sendKeys("invalid_password");
        driver.findElement(By.name("send")).click();
        WebDriverWait waitSuccessMessage = new WebDriverWait(driver, 20);
	    WebElement Message = waitSuccessMessage.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-error")));
	    System.out.println("Message: " + Message.getText());
    	
    	 WebElement loginForm = driver.findElement(By.cssSelector("form#login-form"));
    	 System.out.println("Message: " + loginForm.getText());
     }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
