package Pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	private Page page;
	
	// 1. page constructor:
	public LoginPage(Page page) {
		this.page = page;
	}

	// 2. String Locators - OR
	private String username = "input[placeholder='Username']";
	private String password = "input[placeholder='Password']";
	private String LoginBtn = "input[id='login-button']";
	
	// 3. page actions/methods:
	public String getLoginPageTitle() {
		String title =  page.title();
		System.out.println("page title: " + title);
		return title;
	}

	public String getLoginPageURL() {
		String url =  page.url();
		System.out.println("page url : " + url);
		return url;
	}
	
	public void loginTest(String userName, String Passwd) {
		page.fill(username, userName);
		page.fill(password, Passwd);
		page.click(LoginBtn);
	}
	
	public void multiLoginTest(Object userName) {
		page.fill(username, userName.toString());
		page.fill(password, "secret_sauce");
		page.click(LoginBtn);
	}
}