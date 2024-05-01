package lumaTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	 private WebDriver driver;

	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	    }

	    public void login(String email, String password) {
	        driver.findElement(By.linkText("Sign In")).click();
	        driver.findElement(By.id("email")).sendKeys(email);
	        driver.findElement(By.id("pass")).sendKeys(password);
	        driver.findElement(By.name("send")).click();
	    }
	}
