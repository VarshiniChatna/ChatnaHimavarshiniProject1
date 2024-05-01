package lumaTest;

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

public class Proceedtocheckout {

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
       
     WebDriverWait Shippingratebtn = new WebDriverWait(driver, 30);
   	 WebElement Shippingrate = Shippingratebtn.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='block-shipping']")));
   	 Shippingrate.click();
        
     WebDriverWait Fixedratebtn = new WebDriverWait(driver, 30);
   	 WebElement Fixedrate = Fixedratebtn.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='s_method_flatrate_flatrate']")));
   	 Fixedrate.click();
	    	    
	 WebDriverWait Checkoutbtn = new WebDriverWait(driver, 30);
   	 WebElement ProceedtoCheckout = Checkoutbtn.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='maincontent']/div[3]/div/div[3]/div[1]/ul/li[1]/button")));
   	 ProceedtoCheckout.click();
   	 
   	 WebDriverWait overlayWait = new WebDriverWait(driver, 20);
	 overlayWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkout-loader")));

	 WebElement nextButton = driver.findElement(By.xpath("//*[@id='shipping-method-buttons-container']/div/button"));
	 nextButton.click();	      

	 WebDriverWait checkbox = new WebDriverWait(driver, 50);
     checkbox.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox' and @name='billing-address-same-as-shipping']")));
	
     WebElement placeOrderButton = driver.findElement(By.cssSelector("button.action.primary.checkout"));
     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
     placeOrderButton.click();

     WebDriverWait SuccessMessage = new WebDriverWait(driver, 20);
     WebElement Message = SuccessMessage.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Thank you for your purchase!')]")));
     System.out.println("Success Message: " + Message.getText());
   
     WebElement Orderdetails = driver.findElement(By.xpath("//*[@id='maincontent']/div[3]/div/div[3]"));
     String OrderID = Orderdetails.getText();
     System.out.println("Cart Total: " + OrderID);
}
@AfterClass
public void tearDown() {
   driver.quit();
}

    }
