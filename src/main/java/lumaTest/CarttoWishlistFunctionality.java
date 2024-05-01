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

public class CarttoWishlistFunctionality {
	
	private WebDriver driver;
    private LoginPage loginPage;
    private ProductPage productPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

	 loginPage = new LoginPage(driver);
     productPage = new ProductPage(driver);
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
 public void moveitemtowishlist() {
	
	  WebDriverWait wait2 = new WebDriverWait(driver, 20);
	    WebElement myCartLink = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a")));
	    myCartLink.click();

	    WebDriverWait waitCartPage = new WebDriverWait(driver, 20);
	    WebElement viewAndEditCartLink = waitCartPage.until(ExpectedConditions.elementToBeClickable(By.linkText("View and Edit Cart")));
	    viewAndEditCartLink.click();

	    WebDriverWait waitGotoCart = new WebDriverWait(driver, 20);
	    WebElement moveToWishlistButton = waitGotoCart.until(ExpectedConditions.elementToBeClickable(By.linkText("Move to Wishlist")));
	    moveToWishlistButton.click();
	    
	    WebDriverWait waitSuccessMessage = new WebDriverWait(driver, 20);
	    WebElement successMessage = waitSuccessMessage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Radiant Tee has been moved to your wish list.')]")));
	    System.out.println("Success Message: " + successMessage.getText());
  	
  }
 @AfterClass
 public void tearDown() {
     driver.quit();
 }
 }
