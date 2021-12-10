package tests.basic;

import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import tests.supers.TestBase;
import util.GenUtils;

public class LinearOldCode extends TestBase {

	String timestamp, email="", password="";
	
	@Test
	public void test() {
		try {
			initParameters();
			registerNewUser();
			shoppingProcessEndToEnd();
			endTestSuccess();
		} catch (Throwable t) {
			onTestFailure(t);
		}
	}
	
	private void initParameters() {
		timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		email = "randomEmail" + timestamp + "@gmail.com";
		password = timestamp;
	}
	
	private void registerNewUser() {
		WebDriver driver = app.getDriver();
		driver.get("https://demo.nopcommerce.com");
		
		driver.manage().window().setSize(new Dimension(1280, 680));
		
		log.info("Click on Register button for start");
		driver.findElement(By.linkText("Register")).click();
		GenUtils.sleepMillis(1000);
		
		log.info("Fill 'Sharon' on First Name field");
	    driver.findElement(By.id("FirstName")).sendKeys("Sharon");
	    GenUtils.sleepMillis(1000);
	    
	    log.info("Fill 'Levi' on Last Name field");
	    driver.findElement(By.id("LastName")).sendKeys("Levi");
	    GenUtils.sleepMillis(1000);
	    
	    log.info("Fill '" + email + "' on Email field");
	    driver.findElement(By.id("Email")).sendKeys(email);
	    GenUtils.sleepMillis(1000);
	    
	    log.info("Fill '" + password + "' on Password field");
	    driver.findElement(By.id("Password")).sendKeys(password);
	    GenUtils.sleepMillis(1000);
	    
	    log.info("Fill '" + password + "' on Confirm Password field");
	    driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
	    
	    log.info("Click on Register button for finish");
	    driver.findElement(By.id("register-button")).click();
	    GenUtils.sleepMillis(1000);
	    
	    String actualTextRegistration = driver.findElement(By.cssSelector(".result")).getText();
		String expectedTextRegistration = "Your registration completed";
		Assert.assertTrue(actualTextRegistration.equals(expectedTextRegistration),
				"Expected value: '" + expectedTextRegistration + "', but actual is '" + actualTextRegistration + "'");

		driver.findElement(By.cssSelector(".register-continue-button")).click();
		String firstScreenText = driver.findElement(By.cssSelector(".topic-block-title > h2")).getText();
		String expectedTextFirstScreen = "Welcome to our store";
		Assert.assertTrue(firstScreenText.equals(expectedTextFirstScreen),
				"Expected value: '" + expectedTextFirstScreen + "', but actual is '" + actualTextRegistration + "'");

		log.info("Click logout button");
		driver.findElement(By.xpath("//a[@class='ico-logout']")).click();

	}
	
	private void shoppingProcessEndToEnd() {
		
		String productPrice="";

		WebDriver driver = app.getDriver();
		log.info("Click login button");
		driver.findElement(By.linkText("Log in")).click(); 
		
		log.info("Fill '" + email + "' on Email field");
		driver.findElement(By.id("Email")).sendKeys(email);
		GenUtils.sleepMillis(1000);
		    
		log.info("Fill '" + password + "' on Password field");
		driver.findElement(By.id("Password")).sendKeys(password);
		GenUtils.sleepMillis(1000);
		
		log.info("Click login button");
		driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
		GenUtils.sleepMillis(1000);
		
		log.info("click on Digital downloads");
		driver.findElement(By.linkText("Digital downloads")).click();
		GenUtils.sleepMillis(1000);
		
		productPrice = driver.findElement(By.xpath("//div[@class='prices']")).getText();
		log.info("Save the product price: " + productPrice);
		
		log.info("Click on Add to cart button");
		driver.findElement(By.cssSelector(".product-box-add-to-cart-button")).click();
		GenUtils.sleepMillis(1000);
		
		log.info("Verify that (1) appears");
		String actualTextShoppingCart = driver.findElement(By.xpath("//a[@class='ico-cart']")).getText();
		log.info("The actual text shopping cart is: " + actualTextShoppingCart);
		String expectedTextShoppingCart = "(1)";
		Assert.assertTrue(actualTextShoppingCart.contains(expectedTextShoppingCart),
				"Expected value: '" + expectedTextShoppingCart + "', but actual is '" + actualTextShoppingCart + "'");
		
		log.info("Go to Shopping Cart");
		driver.navigate().to("https://demo.nopcommerce.com/cart");
		GenUtils.sleepMillis(1000);
		
		log.info("Verify that " + productPrice + " apears on Total Price");
		String actualProductPrice = driver.findElement(By.xpath("//td[@class='cart-total-right']")).getText();
		log.info("The actual total price is: " + actualProductPrice);
		Assert.assertTrue(actualProductPrice.equals(productPrice),
				"Expected value: '" + productPrice + "', but actual is '" + actualProductPrice + "'");
		
		log.info("Sign V in checkbox");
		driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
		GenUtils.sleepMillis(1000);
		
		log.info("Click on Checkout button");
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		GenUtils.sleepMillis(1000);

//	    ---------WORKING UNTIL HERE---------
//==============================================================================================================

	    log.info("Fill Israel in Country Field");
	    Select country = new Select(driver.findElement(By.name("BillingNewAddress.CountryId")));
	    country.selectByVisibleText("Israel");
	    GenUtils.sleepMillis(1000);
	    
	    log.info("Fill Ramat Gan in City Field");
	    driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Ramat Gan");
	    GenUtils.sleepMillis(1000);
	    
	    log.info("Fill Address Field");
	    driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Toval 20");
	    GenUtils.sleepMillis(1000);
	    
	    log.info("Fill Zip/postal code Field");
	    driver.findElement(By.name("BillingNewAddress.ZipPostalCode")).sendKeys("1134455");
	    GenUtils.sleepMillis(1000);
	    
	    log.info("Fill Phone Number Field");
	    driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("0504755514");
	    GenUtils.sleepMillis(1000);
	    
	    log.info("Click on Continue button");
	    driver.findElement(By.name("save")).click();
	    GenUtils.sleepMillis(1000);
		
		log.info("Click on Continue button");
	    driver.findElement(By.cssSelector(".payment-method-next-step-button")).click();
	    GenUtils.sleepMillis(1000);
	    
	    log.info("Click on Continue button");
	    driver.findElement(By.cssSelector(".payment-info-next-step-button")).click();
	    GenUtils.sleepMillis(1000);
	    
	    log.info("Verify that " + productPrice + " apears on Total Price");
		actualProductPrice = driver.findElement(By.xpath("//td[@class='cart-total-right']")).getText();
		log.info("The actual total price is: " + actualProductPrice);
		Assert.assertTrue(actualProductPrice.equals(productPrice),
				"Expected value: '" + productPrice + "', but actual is '" + actualProductPrice + "'");
		
		log.info("Click on confirm button");
		driver.findElement(By.cssSelector(".confirm-order-next-step-button")).click();
		GenUtils.sleepMillis(1000);
		
		log.info("Verify that highlighted text appears");
		String expectedThankYouTitle = "Thank you";
		String expectedThankYouBody = "Your order has been successfully processed!";
		String actualThankYouTitle =  driver.findElement(By.xpath("//div[@class='page-title']")).getText();
		log.info("The actual thank you title is: " + actualThankYouTitle);
		String actualThankYouBody =  driver.findElement(By.xpath("//div[@class='title']")).getText();
		log.info("The actual thank you body is: " + actualThankYouBody);
		Assert.assertTrue(actualThankYouTitle.equals(expectedThankYouTitle),
				"Expected value: '" + expectedThankYouTitle + "', but actual is '" + actualThankYouTitle + "'");
		Assert.assertTrue(actualThankYouBody.equals(expectedThankYouBody),
				"Expected value: '" + expectedThankYouBody + "', but actual is '" + actualThankYouBody + "'");
		
		log.info("Click on continue button");
		driver.findElement(By.cssSelector(".order-completed-continue-button")).click();
		GenUtils.sleepMillis(1000);
		
		log.info("Verify that Welcome message text appears");
		String expectedWelcome = "Welcome to our store";
		String actualWelcome = driver.findElement(By.xpath("//div[@class='topic-block-title']")).getText();
		Assert.assertTrue(actualWelcome.equals(expectedWelcome),
				"Expected value: '" + expectedWelcome + "', but actual is '" + actualWelcome + "'");
		
		log.info("Verify that (0) appears on shopping cart");
		actualTextShoppingCart = driver.findElement(By.xpath("//a[@class='ico-cart']")).getText();
		log.info("The actual text shopping cart is: " + actualTextShoppingCart);
		expectedTextShoppingCart = "(0)";
		Assert.assertTrue(actualTextShoppingCart.contains(expectedTextShoppingCart),
				"Expected value: '" + expectedTextShoppingCart + "', but actual is '" + actualTextShoppingCart + "'");
	}
}
