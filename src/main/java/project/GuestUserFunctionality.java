package project;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class GuestUserFunctionality {
	
	private WebDriver driver;

    @BeforeClass
    public void setUp() {
    	System.setProperty("webdriver.chrome.driver","./Driver Files/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/");
    }

    @Test(priority = 1)
    public void AddItemtoCartasGUEST() {
    	 WebDriverWait wait = new WebDriverWait(driver, 10);
         WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[@class='product-item-name']/a[@class='product-item-link'][@title='Radiant Tee']")));
         element.click();
         
         WebDriverWait wait1 = new WebDriverWait(driver, 10);
         WebElement element1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("option-label-size-143-item-168")));
         element1.click();
         driver.findElement(By.id("option-label-color-93-item-50")).click();
         driver.findElement(By.xpath("//button[@id='product-addtocart-button']")).click();
         
         WebDriverWait waitSuccessMessage = new WebDriverWait(driver, 20);
 	    WebElement successMessage = waitSuccessMessage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@data-bind, 'prepareMessageForHtml') and contains(text(), 'You added Radiant Tee to your')]")));
 	    System.out.println("Success Message: " + successMessage.getText());
    }

    @Test(priority = 2)
    public void applyDicountcode(){    
   
	    WebDriverWait wait2 = new WebDriverWait(driver, 50);
   	    WebElement myCartLink = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a")));
   	    myCartLink.click();  
   	    
   	    WebElement viewcart= wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='minicart-content-wrapper']/div[2]/div[5]/div/a")));
	    viewcart.click();

    	        WebDriverWait applyDiscount = new WebDriverWait(driver, 50);
    	        WebElement clickdiscountcode = applyDiscount.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='block-discount']")));
    	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clickdiscountcode);
    	        clickdiscountcode.click();
    	        
    	        WebDriverWait wait3 = new WebDriverWait(driver, 10);
    	        WebElement discountCodeInput = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("coupon_code"))); 	       
    	        discountCodeInput.sendKeys("DISCOUNT_CODE");
    	       
    	        WebElement applyButton = driver.findElement(By.cssSelector("button.action.apply"));
    	        applyButton.click();
    	        
    	        WebDriverWait SuccessMessage = new WebDriverWait(driver, 20);
    		    WebElement Message = SuccessMessage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='maincontent']/div[2]/div[2]/div/div")));
    	        System.out.println("Message: " + Message.getText());
    	}
    
    @Test(priority = 3)
    public void ShippingandTaxRates() {
    	
    	WebDriverWait Shippingratebtn = new WebDriverWait(driver, 30);
    	 WebElement Shippingrate = Shippingratebtn.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='block-shipping']")));
    	 Shippingrate.click();
    	
    	 WebDriverWait Fixedratebtn = new WebDriverWait(driver, 30);
    	 WebElement Fixedrate = Fixedratebtn.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cart-totals']/div/table")));
    	 Fixedrate.click();

         WebElement cartFixedrate = driver.findElement(By.xpath("//*[@id='cart-totals']/div/table"));
         String cartFixedTotal = cartFixedrate.getText();
         System.out.println("Cart Total: " + cartFixedTotal);
         
         WebDriverWait Tableratebtn = new WebDriverWait(driver, 30);
    	 WebElement Tablerate = Tableratebtn.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='s_method_tablerate_bestway']")));
    	 Tablerate.click();
    	 
    	 WebElement cartTablerate = driver.findElement(By.xpath("//*[@id='cart-totals']/div/table"));
         String cartTableTotal = cartTablerate.getText();
         System.out.println("Cart Total: " + cartTableTotal);
    	        
    }
    
    @Test(priority = 4)
    public void proceedtoCheckout() throws InterruptedException {    	  
    	
    	WebDriverWait Checkoutbtn = new WebDriverWait(driver, 30);
    	 WebElement ProceedtoCheckout = Checkoutbtn.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='maincontent']/div[3]/div/div[3]/div[1]/ul/li[1]/button")));
    	 ProceedtoCheckout.click();
    	 
    	 WebDriverWait overlayWait = new WebDriverWait(driver, 20);
 	    overlayWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkout-loader")));

 	    WebElement emailInput = driver.findElement(By.xpath("//*[@id='customer-email']"));
 	    emailInput.click();
 	    emailInput.sendKeys("Icedcoffee@gmail.com");
 	
 	 WebElement firstNameInput = driver.findElement(By.name("firstname"));
      firstNameInput.clear();
      firstNameInput.sendKeys("Iced");

      WebElement lastNameInput = driver.findElement(By.name("lastname"));
      lastNameInput.clear();
      lastNameInput.sendKeys("Coffee");
      
      WebElement Street = driver.findElement(By.name("street[0]"));
      Street.clear();
      Street.sendKeys("123 Street");
      
      WebElement City = driver.findElement(By.xpath("//input[@name='city']"));
      City.clear();
      City.sendKeys("City");   
      
      WebElement StateDropdownElement = driver.findElement(By.name("region_id"));
      Select StateDropdown = new Select(StateDropdownElement);
      StateDropdown.selectByVisibleText("California");
      System.out.println("Selected 'California' from the country dropdown");
 
      WebElement postcode = driver.findElement(By.xpath("//input[@name='postcode']"));
       postcode.clear();
       postcode.sendKeys("10001");
	      
	         WebElement telephone = driver.findElement(By.xpath("//input[@name='telephone']"));
	         telephone.clear();
	         telephone.sendKeys("1234567890");

      WebElement nextButton = driver.findElement(By.xpath("//*[@id='shipping-method-buttons-container']/div/button"));
      nextButton.click();
      
      WebDriverWait checkbox = new WebDriverWait(driver, 50);
      WebElement checkboxElement = checkbox.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='checkbox' and @name='billing-address-same-as-shipping']")));
      checkboxElement.click();
     
      WebElement placeOrderButton = driver.findElement(By.cssSelector("button.action.primary.checkout"));
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
      placeOrderButton.click();
 	
 	
 	 WebDriverWait waitSuccessMessage = new WebDriverWait(driver, 20);
	    WebElement successMessage = waitSuccessMessage.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(), 'Thank you for your purchase!')]")));
	    System.out.println("Success Message: " + successMessage.getText());
	    
	   WebElement Orderdetails = driver.findElement(By.xpath("//*[@id='maincontent']/div[3]/div/div[3]"));
    String OrderID = Orderdetails.getText();
    System.out.println("Cart Total: " + OrderID);       
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
    
    }
