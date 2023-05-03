package SauceDemo;

import java.nio.file.Paths;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import ExtentFactory.PWFactory;
import baseTest.BaseTest;
import utility.Utilities;

public class LoginPageTest extends BaseTest {
	static String username = "Username";
	static String password = "secret_sauce";

	@Test(priority = 1)
	public void loginPageValidation() {
		try {
			System.out.println(loginPage.getLoginPageTitle());
			Assert.assertEquals(loginPage.getLoginPageTitle(), "Swag Labs");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 2)
	public void testLogin() {
		try {
			loginPage.loginTest("standard_user", "secret_sauce");
			PWFactory.getBrowserContext().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 3)
	public void testLogout() {
		try {
			homePage.logoutTest();
			Assert.assertEquals(homePage.getHomePageTitle(), "Swag Labs");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=4, enabled=false)
	public void storageStateLogin() {
		PWFactory.getBrowser().newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("Auth.json")));
		System.out.println(" #### "+homePage.getHomePageTitle());
		homePage.addToCart();
	}
	
	@Test(priority=5, dataProvider = "testData")
	public void TestLogin(Object username) {
		try {
			String name = username.toString();
			homePage.navigateToLoginPage().multiLoginTest(username);
			if(name.equals("locked_out_user")) {
				System.out.println("No need to logout");
			}
			else{
				homePage.logoutTest();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider(name = "testData")
	public static Object[][] testData() {
		return new Object[][] { { Utilities.getData(1, username) }, { Utilities.getData(2, username) },
				{ Utilities.getData(3, username) }, { Utilities.getData(4, username) } };
	}
}