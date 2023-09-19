package PlaywrightBasics;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.GetByRoleOptions;
import com.microsoft.playwright.Page.ScreenshotOptions;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Video;
import com.microsoft.playwright.options.AriaRole;

public class PWFirstProgram {
	static Playwright pw;
	static Browser browser;
	static Page page;

	public static void main(String[] args) {
		pw = Playwright.create();
		browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context =  browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("video/")).setRecordVideoSize(1280, 720));
		page = context.newPage();
		
		page.navigate("https://saucedemo.com");
	
		// System.out.println(page.title());

		assertThat(page).hasTitle("Swag Labs");

		page.locator("#user-name").fill("standard_user");
		page.getByPlaceholder("Password").type("secret_sauce");
		// page.click("#login-button");
		// page.getByRole(AriaRole.BUTTON, new
		// GetByRoleOptions().setName("Login")).click();
		// page.getByText("Login").click();
		// page.locator(".submit-button").click();
		page.locator("input[value='Login']").click(); // Here we can use xpath or css selectors of the element

		assertThat(page.getByText("Swag Labs")).isVisible();

		aboutAndGoBack();
		openCloseMenu();
		addToCartAndCheckout();
		Logout();

		
		context.close();
		page.close();
		browser.close();
		pw.close();
	}

	public static void Logout() {
		page.getByText("Open Menu").click();
		page.getByRole(AriaRole.LINK, new GetByRoleOptions().setName("Logout")).click();
	}

	public static void openCloseMenu() {
		page.getByText("Open Menu").click();
		page.getByText("Close Menu").click();
	}

	public static void aboutAndGoBack() {
		page.getByText("Open Menu").click();
		page.getByRole(AriaRole.LINK, new GetByRoleOptions().setName("About")).click();
		page.goBack();
	}

	public static void addToCartAndCheckout() {

		Locator addToCart = page.locator("button[id='add-to-cart-test.allthethings()-t-shirt-(red)']");
		addToCart.elementHandle().scrollIntoViewIfNeeded();
		addToCart.click();

		Locator cart = page.locator(".shopping_cart_link");
		cart.elementHandle().scrollIntoViewIfNeeded();
		cart.click();

		assertThat(page.getByText("Your Cart")).isVisible();
		assertThat(page.getByText("Remove")).isVisible();

		page.locator("[data-test=\"checkout\"]").click();
		assertThat(page.getByText("Checkout: Your Information")).isVisible();

		Locator fName = page.getByPlaceholder("First Name");
		fName.clear();
		fName.type("Amit");

		Locator lName = page.getByPlaceholder("Last Name");
		lName.clear();
		lName.type("Kumar");

		Locator postalCode = page.getByPlaceholder("Zip/Postal Code");
		postalCode.clear();
		postalCode.type("825402");

		page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Continue")).click();
		assertThat(page.getByText("Checkout: Overview")).isVisible();
		assertThat(page.getByText("Payment Information")).isVisible();
		assertThat(page.getByText("SauceCard #31337")).isVisible();
		assertThat(page.getByText("Shipping Information")).isVisible();
		assertThat(page.getByText("Free Pony Express Delivery!")).isVisible();
		assertThat(page.getByText("Item total: $15.99")).isVisible();
		assertThat(page.getByText("Tax: $1.28")).isVisible();
		assertThat(page.getByText("Total: $17.27")).isVisible();
		page.screenshot(new ScreenshotOptions().setFullPage(true).setPath(Paths.get("video/"+java.time.LocalDateTime.now()+".png")));

		page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Finish")).click();
		assertThat(page.getByText("Checkout: Complete!")).isVisible();
		assertThat(page.getByText("Thank you for your order!")).isVisible();
		assertThat(page
				.getByText("Your order has been dispatched, and will arrive just as fast as the pony can get there!"))
				.isVisible();
		assertThat(page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Back Home"))).isVisible();

		page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Back Home")).click();
		assertThat(page.getByText("Swag Labs")).isVisible();
	}

}
