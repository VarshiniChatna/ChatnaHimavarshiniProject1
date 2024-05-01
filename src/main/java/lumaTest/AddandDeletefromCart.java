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

public class AddandDeletefromCart {
	
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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        
        WebElement removeItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='shopping-cart-table']/tbody/tr[2]/td/div/a[3]")));
        removeItem.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='maincontent']/div[3]/div/div[3]")));
        System.out.println("Success Message: " + successMessage.getText());
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
