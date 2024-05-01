package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OrdersandreturndforGuestUser {
	 private WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	    	System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://magento.softwaretestingboard.com/");
	    }

	@Test
    public void testOrdersAndReturns() {
        // Open the Magento website
        driver.get("https://magento.softwaretestingboard.com/");

        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement ordersAndReturnsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Orders and Returns")));
        ordersAndReturnsLink.click();

        // Fill out the form  
      
        WebElement orderNumberInput = driver.findElement(By.xpath("//input[@id='oar-order-id']"));
        orderNumberInput.sendKeys("000000217");
        
        WebElement Billingname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='oar-billing-lastname']")));
        Billingname.sendKeys("Coffee");
        
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='oar_email']")));
        emailInput.sendKeys("Icedcoffee@gmail.com");

        WebElement submitButton = driver.findElement(By.xpath("//button[@title='Continue']"));
        submitButton.click();

        // Verify success message
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='maincontent']/div[1]")));
        System.out.println("Order Status:" + successMessage.getText());
        
        WebElement Orderdetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='maincontent']/div[3]/div/div[3]")));
        System.out.println("Order Details:" + Orderdetails.getText());
	}

	@AfterClass
    public void tearDown() {
        driver.quit();
    }
   
}
