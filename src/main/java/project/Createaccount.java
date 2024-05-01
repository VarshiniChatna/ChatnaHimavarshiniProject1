package project;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;

	public class Createaccount {

		WebDriver driver;

	    @BeforeTest
	    public void initializeDriver() {
	        System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
	        driver = new ChromeDriver();
	    }
	    
	    @Test
	    public void createaccount() {
	        driver.get("https://magento.softwaretestingboard.com/");
	    
	        WebElement createAccountLink = driver.findElement(By.linkText("Create an Account"));
	        createAccountLink.click();
	   
	        driver.findElement(By.id("firstname")).sendKeys("Vanilla");
	        driver.findElement(By.id("lastname")).sendKeys("icecream");
	        driver.findElement(By.id("email_address")).sendKeys("vanillaicecream@gmail.com");
	        driver.findElement(By.id("password")).sendKeys("Testcode@123");
	        driver.findElement(By.name("password_confirmation")).sendKeys("Testcode@123");	   
	        WebElement registerButton = driver.findElement(By.xpath("//*[@id='form-validate']/div/div[1]/button"));
	        registerButton.click();
	        WebElement successMessage = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div"));
	        System.out.println("Success Message: " + successMessage.getText());//Remove the success message if already created the account
	    }

	    @AfterTest
	    public void tearDown() {
	        // Close the browser
	        driver.quit();
	    }
	}
