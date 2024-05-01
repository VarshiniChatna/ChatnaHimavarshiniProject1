package lumaTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class QuantityupdateinCart {
	
	private WebDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize page objects
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test(priority = 1)
    public void testProductAttributeFunctionality() {
        driver.get("https://magento.softwaretestingboard.com/");
        loginPage.login("vanillaicecream@gmail.com", "Testcode@123");
        productPage.selectProductAttributes();
        productPage.addToCart();
        productPage.verifyAddToCartSuccessMessage();
    }

    @Test(priority = 2)
    public void todeleteitemfromCart() {
        cartPage.navigateToCart();
        WebDriverWait wait3 = new WebDriverWait(driver, 50);
   	    WebElement  quantityInput = wait3.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input.qty")));
   	    quantityInput.clear(); 
        quantityInput.sendKeys("2");
        
        WebElement updateButton = driver.findElement(By.cssSelector("button.update"));
        updateButton.click();
        
        System.out.println("Cart Updated");
    }
    @AfterClass
    public void tearDown() {
            driver.quit();
        }
}
