package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class ProductSortingTest {
	
	private WebDriver driver;
	
	@BeforeClass
    public void setUp() {
		System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testProductSorting() {
       
    	 driver.get("https://magento.softwaretestingboard.com/");
    	 
        driver.findElement(By.xpath("//a[@class='block-promo home-main']")).click();
        
        WebElement sortDropdown = driver.findElement(By.id("sorter"));
        sortDropdown.click();
        WebElement priceOption = driver.findElement(By.xpath("//select[@id='sorter']/option[@value='price']"));
        priceOption.click();
        
        List<WebElement> productPrices = driver.findElements(By.cssSelector(".product_price"));
        double prevPrice = Double.MIN_VALUE;
        boolean isSorted = true;
        for (WebElement productPrice : productPrices) {
            double currentPrice = Double.parseDouble(productPrice.getText().replaceAll("[^0-9.]", ""));
            if (currentPrice < prevPrice) {
                isSorted = false;
                break;
            }
            prevPrice = currentPrice;
        }
        
        if (isSorted) {
            System.out.println("Prices are sorted.");
        } else {
            System.out.println("Prices are not sorted.");
        }
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
