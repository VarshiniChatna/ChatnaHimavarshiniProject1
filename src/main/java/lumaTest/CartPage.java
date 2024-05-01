package lumaTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	
	 private WebDriver driver;
	    private WebDriverWait wait;

	    public CartPage(WebDriver driver) {
	        this.driver = driver;
	        wait = new WebDriverWait(driver, 10);
	    }

	    public void navigateToCart() {
	    	WebElement myCartLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/header/div[2]/div[1]/a")));
		    myCartLink.click();

		    WebDriverWait waitCartPage = new WebDriverWait(driver, 20);
		    WebElement viewAndEditCartLink = waitCartPage.until(ExpectedConditions.elementToBeClickable(By.linkText("View and Edit Cart")));
		    viewAndEditCartLink.click();
	    }

}
