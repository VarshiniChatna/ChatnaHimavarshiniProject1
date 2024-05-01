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

public class CustomerReview {


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
        WebDriverWait wait1 = new WebDriverWait(driver, 10);
        WebElement addReviewLink = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='action add' and contains(@href, 'radiant-tee.html#review-form')]")));
        addReviewLink.click();
        
        WebDriverWait wait2 = new WebDriverWait(driver, 30);
        wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='Rating_3_label']")));
        WebElement threeStarRating = driver.findElement(By.xpath("//*[@id='Rating_3_label']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", threeStarRating);
        
        WebElement nicknameField = driver.findElement(By.id("nickname_field"));
        nicknameField.sendKeys("TestUser");

        WebElement summaryField = driver.findElement(By.id("summary_field"));
        summaryField.sendKeys("Great product!");

        WebElement reviewField = driver.findElement(By.id("review_field"));
        reviewField.sendKeys("I love this tee!");

        WebElement submitButton = driver.findElement(By.cssSelector(".action.submit.primary"));
        submitButton.click();
       
        WebDriverWait waitSuccessMessage = new WebDriverWait(driver, 20);
	    WebElement successMessage = waitSuccessMessage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'You submitted your review for moderation.')]")));
	    System.out.println("Success Message: " + successMessage.getText());
    	
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
