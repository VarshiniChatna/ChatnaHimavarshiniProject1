package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Homepagetest {

	 private WebDriver driver;

	    @BeforeClass
	    public void setUp() {
	    	System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	    }

	    @Test
	    public void testHomePageLoad() {
	    	driver.get("https://magento.softwaretestingboard.com/");
	        String expectedTitle = "Home Page";
	        String actualTitle = driver.getTitle();
	        Assert.assertEquals(actualTitle, expectedTitle, "Home Page");
	        WebElement logoElement = driver.findElement(By.tagName("h1"));
	        Assert.assertTrue(logoElement.isDisplayed(), "Logo is displayed on the homepage");     
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
	}
