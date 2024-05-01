package lumaTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WishlistFunctionality {
	
	private WebDriver driver;
    private LoginPage loginPage;
    
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize page objects
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void testProductAttributeFunctionality() {
        driver.get("https://magento.softwaretestingboard.com/");
        loginPage.login("vanillaicecream@gmail.com", "Testcode@123");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[@class='product-item-name']/a[@class='product-item-link'][@title='Radiant Tee']")));
        element.click();

        WebElement addToWishlistButton = driver.findElement(By.xpath("//a[@class='action towishlist']"));
        addToWishlistButton.click();
        
        WebElement successMessage = driver.findElement(By.xpath("//*[@id='maincontent']/div[1]/div[2]/div"));
        System.out.println("Item added to Wishlist: " + successMessage.getText());
    }
        @Test(priority = 2)
        public void testRemoveFromWishlist() {

        WebElement frame = driver.findElement(By.xpath("//div[@class='product-item-info']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(frame).perform();

        WebElement removeButton = driver.findElement(By.xpath("//div[@class='product-item-inner']//a[@class='btn-remove action delete']"));
        removeButton.click();
       
        WebElement removeMessage = driver.findElement(By.xpath("//*[@id='maincontent']/div[1]/div[2]/div"));
        System.out.println("Item removed from Wishlist: " + removeMessage.getText());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
