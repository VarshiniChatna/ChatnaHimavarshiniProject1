//Test the behavior of the website when navigating using browser back and forward buttons 
package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Browsernavigationtest {
	
	WebDriver driver;
	 private String baseUrl = "https://magento.softwaretestingboard.com/";

    @BeforeClass
    public void initializeDriver() {
        System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
        driver = new ChromeDriver();
    }
    
    @Test(priority = 1)
    public void testNavigation() {

    	 driver.get(baseUrl);
        String currentUrl = driver.getCurrentUrl();
        driver.navigate().to("https://magento.softwaretestingboard.com/collections/yoga-new.html"); // Navigate to another page

        String newUrl = driver.getCurrentUrl();
        assert !newUrl.equals(currentUrl) : "Navigation to another page failed";

		System.out.println(driver.getCurrentUrl());
        
        driver.navigate().back();
        String backUrl = driver.getCurrentUrl();
        assert backUrl.equals(currentUrl) : "Back navigation success";
		System.out.println(driver.getCurrentUrl());
        
        driver.navigate().forward();
        String forwardUrl = driver.getCurrentUrl();
        assert !forwardUrl.equals(currentUrl) : "Forward navigation failed";
		System.out.println(driver.getCurrentUrl());
    }
    
    @Test(priority = 2)
    public void changemodeofView() {
    	
         //change from list view to grid view
    	
    	WebElement gridViewElement = driver.findElement(By.xpath("//a[@id='mode-list']"));

        WebElement listViewElement = driver.findElement(By.xpath("//strong[@class='modes-mode active mode-grid']"));

        if (gridViewElement.getAttribute("class").contains("active")) {
            System.out.println("Grid view is currently selected.");
        } else {
            listViewElement.click();
            System.out.println("Switched to list view.");
        }
        }
        @AfterClass
        public void tearDown() {
            // Close the browser
            driver.quit();
        }
    }
