package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SigninandupdateMyAccount {

	WebDriver driver;

    @BeforeTest
    public void initializeDriver() {
        System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://magento.softwaretestingboard.com/");
    }
    
    @Test(priority = 1)
    public void enterCredentialsAndSignIn() {
       
        WebElement SigninLink = driver.findElement(By.linkText("Sign In"));
        SigninLink.click();
        driver.findElement(By.id("email")).sendKeys("vanillaicecream@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("Testcode@123");

        driver.findElement(By.name("send")).click();
        System.out.println("Login Successfull");
    }

    @Test(priority = 2)  // Update Address in the account
    public void updateAddressTest() throws InterruptedException {
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
    	WebElement Myaccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.customer-welcome")));
    	Myaccount.click();
    	
    	WebDriverWait wait1 = new WebDriverWait(driver,30);
    	WebElement MyAccount = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='My Account']")));
    	MyAccount.click();
    	
    	WebDriverWait wait2 = new WebDriverWait(driver,30);
    	WebElement MyAddress = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Address Book']")));
    	 MyAddress.click();
    	 
    	 driver.findElement(By.xpath("//button[@type='button' and @role='add-address' and @title='Add New Address']")).click();  //remove this line if adding address for the first time  	 
         driver.findElement(By.id("street_1")).clear();
         driver.findElement(By.id("street_1")).sendKeys("Updated Street Address");
       
         WebElement countryDropdownElement = driver.findElement(By.xpath("//select[@name='country_id']"));

	        Select countryDropdown = new Select(countryDropdownElement);

	        countryDropdown.selectByVisibleText("United States");
	        System.out.println("Selected 'USA' from the country dropdown");
	        
	        driver.findElement(By.xpath("//input[@type='text' and @name='telephone' and @id='telephone']")).sendKeys("1234567890");
	        
	        Thread.sleep(2000);

	        WebElement StateDropdownElement = driver.findElement(By.name("region_id"));

	        Select StateDropdown = new Select(StateDropdownElement);

	        StateDropdown.selectByVisibleText("California");
	        System.out.println("Selected 'California' from the country dropdown");
	        
	        driver.findElement(By.xpath("//input[@title='City']")).sendKeys("state");
	        
	        driver.findElement(By.xpath("//input[@title='Zip/Postal Code']")).sendKeys("123567");
	        
         driver.findElement(By.xpath("//button[@title='Save Address']")).click();
         
         WebDriverWait waitSuccessMessage = new WebDriverWait(driver, 20);
		    WebElement successMessage = waitSuccessMessage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'You saved the address.')]")));
		    System.out.println("Success Message: " + successMessage.getText());
     }
     
     
    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }

}
