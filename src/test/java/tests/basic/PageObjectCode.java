package tests.basic;

import java.text.SimpleDateFormat;

import org.testng.annotations.Test;

import tests.supers.TestBase;

public class PageObjectCode extends TestBase {

	String timestamp, email, password, price;

	@Test
	public void test() {
		try {

			initParameter();
			app.getDriver().get("https://demo.nopcommerce.com/");
			
			registerNewUser();
			shoppingProcessEndToEnd();
			
			endTestSuccess();
		} catch (Throwable t) {
			onTestFailure(t);
		}
	}

	private void initParameter() {
		timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
		email = "randomEmail" + timestamp + "@gmail.com";
		password = timestamp;
	}

	private void registerNewUser() {

		app.pages().menusPage.clickRegister().chooseGender("male")
				.setFirstName("Name1").setLastName("Name2")
				.selectBirthdayDay("1").selectBirthdayMonth("January").selectBirthdayYear("2000")
				.setMail(email)
				.setPassword(password).setPasswordVerification(password)
				.clickRegisterButton()
				.verifySuccessMessage().clickContinue().verifyHomeText().logout();
	}

	private void shoppingProcessEndToEnd() {
		
		app.pages().homePage.clickLogin()
					.setEmail(email).setPassword(password).clickLoginButton();
		
		price = app.pages().menusPage.clickDigitalDownloads().getPrice();
		
		app.pages().digitalDownoads.addToCart("Night Visions")
					.checkAmountInCart_ReturnDigitalDownloadsPage(1)
					.goToCart().verifyPrice(price).signCheckbox()
					.clickCheckOut().setCountry("Israel").setCity("Ramat Gan")
					.setAddress1("Toval 20").setZip("1134455").setPhoneNumber("0504755514")
					.clickContinueToPaymentMethod().clickContinueToPaymentInformation()
					.clickContinueToConfirmOrder().verifyOrderPrice(price).clickConfirmOrder()
					.verifySuccessMessage().clickContinue().checkAmountInCart(0);
	}
}
