package pages.util_pages;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import pages._pages_mngt.MainPageManager;
import pages.super_pages.MenusPage;
import util.GenUtils;

public class CheckoutPage extends MenusPage{

	public CheckoutPage(MainPageManager pages) {
		super(pages);
	}

	public CheckoutPage ensurePageLoaded() {
		super.ensurePageLoaded();
		waitBig.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".page-title"))));
		return this;
	}

	public CheckoutPage setCountry(String countryStr) {
	    log.info("Fill" + countryStr + "in Country Field");
	    Select country = new Select(driver.findElement(By.name("BillingNewAddress.CountryId")));
	    country.selectByVisibleText(countryStr);
	    return this;
	}

	public CheckoutPage setCity(String cityStr) {
		log.info("Fill" + cityStr + "in City Field");
	    driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Ramat Gan");
		return this;
	}

	public CheckoutPage setAddress1(String address) {
		log.info("Fill" + address + "in Address1 Field");
		driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys(address);
		return this;
	}

	public CheckoutPage setZip(String zip) {
		log.info("Fill" + zip + "in Zip code Field");
	    driver.findElement(By.name("BillingNewAddress.ZipPostalCode")).sendKeys("1134455");
	    return this;
	}

	public CheckoutPage setPhoneNumber(String phoneNumber) {
		log.info("Fill" + phoneNumber + "in Phone Number Field");
	    driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("0504755514");
		return this;
	}

	public CheckoutPage clickContinueToPaymentMethod() {
		log.info("Click Continue button - addvance to Payment method");
	    driver.findElement(By.name("save")).click();
	    waitBig.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".payment-method-next-step-button"))));
		return this;
	}

	public CheckoutPage clickContinueToPaymentInformation() {
		log.info("Click Continue button - addvance to Payment information");
	    driver.findElement(By.cssSelector(".payment-method-next-step-button")).click();
	    waitBig.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".payment-info-next-step-button"))));
		return this;
	}

	public CheckoutPage clickContinueToConfirmOrder() {
		log.info("Click Continue button - addvance to Confirm order");
	    driver.findElement(By.cssSelector(".payment-info-next-step-button")).click();
	    waitBig.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".confirm-order-next-step-button"))));
		return this;
	}

	public CheckoutPage verifyOrderPrice(String expectedPrice) {
		log.info("Verify that " + expectedPrice + " apears on Total Price");
		String actualProductPrice = driver.findElement(By.xpath("//td[@class='cart-total-right']")).getText();
		log.info("The actual total price is: " + actualProductPrice);
		Assert.assertTrue(actualProductPrice.equals(expectedPrice),
				"Expected value: '" + expectedPrice + "', but actual is '" + actualProductPrice + "'");
		return this;
	}

	public OrderSuccessPage clickConfirmOrder() {
		log.info("Click on confirm button");
		driver.findElement(By.cssSelector(".confirm-order-next-step-button")).click();
		GenUtils.sleepMillis(1000);
		return pages.orderSuccessPage.ensurePageLoaded();
	}
}
