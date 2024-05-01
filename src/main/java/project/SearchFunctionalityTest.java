package project;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SearchFunctionalityTest {

	WebDriver driver;

    @BeforeClass
    public void initializeDriver() {
        System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
        driver = new ChromeDriver();
    }
    
    @Test
    public void testSearchFunctionality() {
        
        driver.get("https://magento.softwaretestingboard.com/");
       
        WebElement searchInput = driver.findElement(By.id("search"));

        String searchQuery = "tshirts for women";
        searchInput.sendKeys(searchQuery);
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new KeyboardEvent('keydown', {keyCode: 13, key: 'Enter'}))", searchInput);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement searchResults = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='maincontent']/div[3]/div[1]/div[3]")));
        System.out.println("Message"+ searchResults.getText());
       
        String actualSearchQuery = driver.findElement(By.id("search")).getAttribute("value");
        System.out.println("Actual Search Query: " + actualSearchQuery);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
