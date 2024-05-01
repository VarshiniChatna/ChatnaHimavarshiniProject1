package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ForgotPasswordTest {
	
	private WebDriver driver;

    @BeforeClass
    public void setUp() {
    	System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/");
    }
        
        @Test
        public void testForgotPassword() {
        	
            driver.findElement(By.linkText("Sign In")).click();
            WebElement forgotPasswordLink = driver.findElement(By.linkText("Forgot Your Password?"));
            forgotPasswordLink.click();

            WebElement forgotPasswordHeader = driver.findElement(By.cssSelector("h1.page-title"));
            Assert.assertEquals(forgotPasswordHeader.getText(), "Forgot Your Password?");

            WebElement emailField = driver.findElement(By.id("email_address"));
            emailField.sendKeys("vanillaicecream@gmail.com");

            WebElement submitButton = driver.findElement(By.xpath("//span[text()='Reset My Password']"));
            submitButton.click();

            WebElement successMessage = driver.findElement(By.xpath("//div[@class='page messages']//div[contains(@class, 'message-success')]/div"));
            System.out.println("Message " + successMessage.getText());
        }

        @AfterClass
        public void tearDown() {
            driver.quit();
        }
}
