package pages.util_pages;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import pages._pages_mngt.MainPageManager;
import pages.super_pages.MenusPage;

public class ShoppingCartPage extends MenusPage{

	public ShoppingCartPage(MainPageManager pages) {
		super(pages);
	}

	public ShoppingCartPage ensurePageLoaded() {
		super.ensurePageLoaded();
		waitBig.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".page-title"))));
		return this;
	}
	
	public ShoppingCartPage verifyPrice(String productPrice) {
		log.info("Verify that " + productPrice + " apears on Total Price");
		String actualProductPrice = driver.findElement(By.xpath("//td[@class='cart-total-right']")).getText();
		log.info("The actual total price is: " + actualProductPrice);
		Assert.assertTrue(actualProductPrice.equals(productPrice),
				"Expected value: '" + productPrice + "', but actual is '" + actualProductPrice + "'");
		return this;
	}
	
	public ShoppingCartPage signCheckbox() {
		log.info("Sign V in checkbox");
		driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
		return this;
	}
	
	public CheckoutPage clickCheckOut() {
		log.info("Click on Checkout button");
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		return pages.checkoutPage.ensurePageLoaded();
	}
	
}
