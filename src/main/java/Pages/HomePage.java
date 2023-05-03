package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import ExtentFactory.PWFactory;

public class HomePage {

	private Page page;
	
	// 1. page constructor:
	public HomePage(Page page) {
		this.page = page;
	}

	// 2. String Locators - OR
	private String Hamburger= "button[id='react-burger-menu-btn']";
	private String logout   = "a[id='logout_sidebar_link']";
	private String addTocartBackPack= "button[id='add-to-cart-sauce-labs-backpack']";
	private String firstProduct= "a[id='item_4_title_link']";
	private Locator goToCart = PWFactory.getPage().locator("a").filter(new Locator.FilterOptions().setHasText("1"));
	private Locator removeFromCart = PWFactory.getPage().locator("[data-test=\"remove-sauce-labs-backpack\"]");
	private Locator continueShopping = PWFactory.getPage().locator("[data-test=\"continue-shopping\"]");
	private Locator addTocartBikeLight= PWFactory.getPage().locator("[data-test=\"add-to-cart-sauce-labs-bike-light\"]");
	private Locator goToCart2 = PWFactory.getPage().locator("a").filter(new Locator.FilterOptions().setHasText("2"));
	private Locator checkout= PWFactory.getPage().locator("[data-test=\"checkout\"]");
	private Locator firstName= PWFactory.getPage().locator("[data-test=\"firstName\"]");
	private Locator lastName= PWFactory.getPage().locator("[data-test=\"lastName\"]");
	private Locator zipCode= PWFactory.getPage().locator("[data-test=\"postalCode\"]");
	private Locator continueButton= PWFactory.getPage().locator("[data-test=\"continue\"]");
	private Locator finishButton= PWFactory.getPage().locator("[data-test=\"finish\"]");
	private Locator successMsg= PWFactory.getPage().getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Thank you for your order!"));
	private Locator backToHomePage= PWFactory.getPage().locator("[data-test=\"back-to-products\"]");
	
	

	// 3. page actions/methods:
	public String getHomePageTitle() {
		String title =  page.title();
		System.out.println("page title: " + title);
		return title;
	}

	public String getHomePageURL() {
		String url =  page.url();
		System.out.println("page url : " + url);
		return url;
	}

	public LoginPage navigateToLoginPage() {
		return new LoginPage(page);
	}
	
	
	public void logoutTest() {
		page.click(Hamburger);
		page.click(logout);
	}
	
	public void addToCart() {
		page.click(addTocartBackPack);
	}
	
	public String verifyFirstProduct() {
		String text  = page.textContent(firstProduct);
		return text;
	}

	public void goToCart() {
		goToCart.click();
	}
	
	public String removeFromCart() {
		removeFromCart.click();
		continueShopping.click();
		return page.textContent(addTocartBackPack);
	}
	
	public void addTwoProduct() {
		//page.click(addTocartBackPack);
		addToCart();
		addTocartBikeLight.click();
		goToCart2.click();
	}
	
	public String checkout() {
		checkout.click();
		firstName.fill("Amit");
		lastName.fill("Kumar");
		zipCode.fill("560037");
		continueButton.click();
		finishButton.click();
		String text = successMsg.textContent();
		//backToHomePage.click();
		return text;
	}
	
	public String backToHomePage() {
		backToHomePage.click();
		String str  = getHomePageTitle();
		return str;
	}
}