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

public class CompareProduct {

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

    @Test
    public void testProductAttributeFunctionality() {
        driver.get("https://magento.softwaretestingboard.com/");
        loginPage.login("vanillaicecream@gmail.com", "Testcode@123");
        WebDriverWait wait = new WebDriverWait(driver,30);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[@class='product-item-name']/a[@class='product-item-link'][@title='Radiant Tee']")));
        element.click();
        
        WebElement addToCompareButton = driver.findElement(By.cssSelector(".action.tocompare"));
        addToCompareButton.click();

        WebElement successMessage = driver.findElement(By.cssSelector(".message-success"));
        System.out.println("Success Message" + successMessage.getText());
        
    	WebElement Myaccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.customer-welcome")));
    	Myaccount.click();
    	 	
    	WebElement MyAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='My Account']")));
    	MyAccount.click();
    	
     WebElement compareLink = driver.findElement(By.cssSelector(".primary a.compare.primary"));
     compareLink.click();
        
     WebElement skuElement = driver.findElement(By.xpath("//*[@id='product-comparison']/tbody[2]/tr[1]"));
     String sku = skuElement.getText();
     System.out.println("SKU: " + sku);

     WebElement descriptionElement = driver.findElement(By.xpath("//*[@id='product-comparison']/tbody[2]/tr[2]"));
     String description = descriptionElement.getText();
     System.out.println("Description: " + description);
      // to delete compared products from account
     driver.navigate().back();
     WebElement Clear = wait.until(ExpectedConditions.elementToBeClickable(By.id("compare-clear-all")));
     Clear.click();
     
     WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class, 'action-primary') and contains(@class, 'action-accept') and @type='button' and @data-role='action']")));
     ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", okButton);
     okButton.click();
     
     WebDriverWait waitSuccessMessage = new WebDriverWait(driver, 20);
	    WebElement Message = waitSuccessMessage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-bind='html: $parent.prepareMessageForHtml(message.text)']")));
     System.out.println("Success Message: " + Message.getText());
     
	    }
	    
	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
    }
