//Test the functionality of shopBy option
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

public class ShopByFiltersTest {
	
	 private WebDriver driver;
	 @BeforeClass
	    public void setUp() {
		    System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	    }

	    @Test
	    public void testShopByFilters() {
        
	        driver.get("https://magento.softwaretestingboard.com/collections/yoga-new.html");
	        
	        WebElement sizeFilterTitle = driver.findElement(By.xpath("//*[@id='narrow-by-list']/div[5]"));
	        sizeFilterTitle.click();

	        WebDriverWait wait = new WebDriverWait(driver, 10);
	        WebElement MSizeOption= wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.swatch-option.text[option-label='29']")));
	        MSizeOption.click();
	        
	        WebDriverWait wait1 = new WebDriverWait(driver, 20);
	        WebElement priceFilter= wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='filter-options-title'][text()='Price']")));
	        priceFilter.click();
	        
	        WebDriverWait wait2 = new WebDriverWait(driver, 10);
	        WebElement priceselect= wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='filter-options-content']//a[contains(@href, 'price=20-30')]")));
	        priceselect.click();
	        System.out.println("Price Range Selected: " + priceselect);
	        
        }

	   

	    @AfterClass
	    public void tearDown() {
	      driver.quit();
	    }
	}
