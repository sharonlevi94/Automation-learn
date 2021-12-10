package pages.util_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages._pages_mngt.MainPageManager;
import pages.super_pages.MenusPage;
import util.GenUtils;

public class LoginPage extends MenusPage{

	public LoginPage(MainPageManager pages) {
		super(pages);
	}

	public LoginPage ensurePageLoaded() {
		super.ensurePageLoaded();
		waitBig.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".login-button"))));
		return this;
	}
	
	public LoginPage setEmail(String email) {
		log.info("Fill '" + email + "' on Email field");
		driver.findElement(By.id("Email")).sendKeys(email);
		GenUtils.sleepMillis(1000);
		return this;
	}
	
	public LoginPage setPassword(String password) {
		log.info("Fill '" + password + "' on Password field");
		driver.findElement(By.id("Password")).sendKeys(password);
		GenUtils.sleepMillis(1000);
		return this;
	}
	
	public HomePage clickLoginButton() {
		log.info("Click login button");
		driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
		return pages.homePage.ensurePageLoaded();
	}
}
