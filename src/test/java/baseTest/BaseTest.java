package baseTest;

import java.util.Properties;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.microsoft.playwright.Page;
import ExtentFactory.PWFactory;
import Pages.HomePage;
import Pages.LoginPage;

public class BaseTest {
	PWFactory pf;
	Page page;
	
	protected Properties prop;
	protected HomePage homePage;
	protected LoginPage loginPage;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browserName) {
		pf = new PWFactory();

		prop = pf.init_prop();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}

		page = pf.initBrowser(prop);
		homePage = new HomePage(page);
		loginPage= new LoginPage(page);
	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}
}