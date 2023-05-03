package SauceDemo;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

import com.microsoft.playwright.Page;

import ExtentFactory.PWFactory;
import baseTest.BaseTest;

public class HomePageTest extends BaseTest{
	Page page = PWFactory.getPage();
	
	@Test(priority = 1)
	public void testLogin() {
		try {
			loginPage.loginTest("standard_user", "secret_sauce");
			//PWFactory.getBrowserContext().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=2)
	public void verifyProduct() {
		String product = homePage.verifyFirstProduct();
		assertEquals(product.equalsIgnoreCase("Sauce Labs Backpack"), true);
	}
	
	@Test(priority=3)
	public void addToCart() {
		homePage.addToCart();
	}
	
	@Test(priority=4)
	public void goToCart() {
		homePage.goToCart();
	}
	
	
	@Test(priority=5)
	public void removeFromCart() {
		String text  =  homePage.removeFromCart();
		assertEquals(text.equalsIgnoreCase("Add to cart"), true);
	}
	
	@Test(priority=6)
	public void addTwoProductToCart() {
		homePage.addTwoProduct();
	}
	
	@Test(priority=7)
	public void checkOut() {
		String msg  = homePage.checkout();
		System.out.println("Success Message : "+msg);
		assertEquals(msg.equalsIgnoreCase("Thank you for your order!"), true);
	}
	
	@Test(priority=8)
	public void backToHomePage() {
		String title  = homePage.backToHomePage();
		assertEquals(title.equalsIgnoreCase("Swag Labs"), true);
	}
}