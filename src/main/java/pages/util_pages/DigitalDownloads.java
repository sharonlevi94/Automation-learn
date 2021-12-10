package pages.util_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages._pages_mngt.MainPageManager;
import pages.super_pages.MenusPage;
import util.GenUtils;

public class DigitalDownloads extends MenusPage{

	public DigitalDownloads(MainPageManager pages) {
		super(pages);
	}

	public DigitalDownloads ensurePageLoaded() {
		super.ensurePageLoaded();
		waitBig.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".page-title"))));
		return this;
	}

	public String getPrice() {
		String price = "";
		price = driver.findElement(By.xpath("//div[@class='prices']")).getText();
		log.info("Save the product price: " + price); 
		return price;
	}

	public DigitalDownloads addToCart(String dDownload) {
		log.info("Click on Add to cart button");
		if(dDownload.equals("Night Visions"))
			driver.findElement(By.cssSelector(".product-box-add-to-cart-button")).click();
		GenUtils.sleepMillis(1000);
		return this;
	}

	public ShoppingCartPage goToCart() {
		log.info("Go to Shopping Cart");
		driver.navigate().to("https://demo.nopcommerce.com/cart");
		return pages.shoppingCartPage.ensurePageLoaded();
	}

}
