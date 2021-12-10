package pages.util_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import pages._pages_mngt.MainPageManager;
import pages.super_pages.MenusPage;

public class OrderSuccessPage extends MenusPage {

	public OrderSuccessPage(MainPageManager pages) {
		super(pages);
	}
	
	public OrderSuccessPage ensurePageLoaded() {
		super.ensurePageLoaded();
		waitBig.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".page-title > h1"))));
		return this;
	}

	public OrderSuccessPage verifySuccessMessage() {
		log.info("Check if the massage 'Your order has been successfully processed!' appears");
	    String text = driver.findElement(By.cssSelector(".section .title > strong")).getText();
	    String expText = "Your order has been successfully processed!";
	    Assert.assertTrue(expText.equals(text), "Expected value: '"+expText+"', but actual is '" + text + "'");
	    return this;
	}

	public HomePage clickContinue() {
		log.info("Click continue");
	    driver.findElement(By.cssSelector(".order-completed-continue-button")).click();
	    return pages.homePage.ensurePageLoaded();
	}

}
