package PlaywrightBasics;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class PWFirstProgram {

	public static void main(String[] args) {
		try (Playwright playwright =  Playwright.create()){
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			Page page = browser.newPage();
			page.navigate("https://www.saucedemo.com");
			System.out.println(page.title());
			
			PlaywrightAssertions.assertThat(page).hasTitle("Swag Labs");
		}
			
	}

}
