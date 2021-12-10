package pages.super_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import pages._pages_mngt.MainPageManager;
import pages.util_pages.DigitalDownloads;
import pages.util_pages.HomePage;
import pages.util_pages.LoginPage;
import pages.util_pages.RegisterPage;

public class MenusPage extends AnyPage {

	public MenusPage(MainPageManager pages) {
		super(pages);
	}

	public MenusPage ensurePageLoaded() {
		super.ensurePageLoaded();
		waitBig.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("body")));
		return this;
	}

	public RegisterPage clickRegister() {
		log.info("Click Register link.");
		driver.findElement(By.linkText("Register")).click();
		return pages.registerPage.ensurePageLoaded();
	}

	public HomePage logout() {
		log.info("Click logout button");
		driver.findElement(By.xpath("//a[@class='ico-logout']")).click();
		return pages.homePage.ensurePageLoaded();
	}

	
	public MenusPage checkAmountInCart(int expectedAmount) {		
		String actualTextCart = driver.findElement(By.id("topcartlink")).getText(); 
	    String expectedTextCart = "Shopping cart (" +expectedAmount+ ")";    
	    Assert.assertTrue(actualTextCart.equals(expectedTextCart), "Expected value: '"+expectedTextCart+"', but actual is '" + actualTextCart + "'");
	    return this;
	}

	public LoginPage clickLogin() {
		log.info("Click login button");
		driver.findElement(By.linkText("Log in")).click();
		return pages.loginPage.ensurePageLoaded();
	}
	
	public DigitalDownloads clickDigitalDownloads() {
		log.info("click on Digital downloads");
		driver.findElement(By.linkText("Digital downloads")).click();
		return pages.digitalDownoads.ensurePageLoaded();
	}
	
	public DigitalDownloads checkAmountInCart_ReturnDigitalDownloadsPage(int expectedAmount) {
		checkAmountInCart(expectedAmount);
		return pages.digitalDownoads;
	}

}