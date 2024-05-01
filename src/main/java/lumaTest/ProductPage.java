package lumaTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	
	private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        wait = new WebDriverWait(driver, 10);
    }

    public void selectProductAttributes() {
        WebElement productLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[@class='product-item-name']/a[@class='product-item-link'][@title='Radiant Tee']")));
        productLink.click();

        WebElement sizeOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("option-label-size-143-item-168")));
        sizeOption.click();

        WebElement colorOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("option-label-color-93-item-50")));
        colorOption.click();
    }

    public void addToCart() {
        WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='product-addtocart-button']")));
        addToCartButton.click();
    }

    public void verifyAddToCartSuccessMessage() {
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@data-bind, 'prepareMessageForHtml') and contains(text(), 'You added Radiant Tee to your')]")));
        System.out.println("Success Message: " + successMessage.getText());
    }
	}

