package baseTest;

import static org.testng.Assert.assertEquals;

import java.awt.Button;
import java.nio.file.Paths;

import javax.lang.model.element.Element;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.xml.sax.ext.Locator2Impl;
import org.yaml.snakeyaml.LoaderOptions;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.MouseButton;

public class Sample {

	@Test
	public void firstTest() {
		Playwright pw = Playwright.create();
		Page page = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)).newPage();
		
		page.navigate("https://www.saucedemo.com");
		System.out.println(page.title());
		
		
		page.locator("[data-test=\"username\"]").fill("standard_user");
		page.locator("[data-test=\"password\"]").fill("secret_sauce");
		page.locator("[data-test=\"login-button\"]").click();
		
		assertEquals(page.title(), "Swag Labs");
		
		Locator lastItem = page.locator("button[id='add-to-cart-test.allthethings()-t-shirt-(red)']");
		lastItem.waitFor();
		//lastItem.elementHandle().scrollIntoViewIfNeeded();
		lastItem.click();
		
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Screenshot.png")));
		
	}

}
