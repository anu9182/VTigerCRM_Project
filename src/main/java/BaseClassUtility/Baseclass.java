package BaseClassUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import GenericUtilities.DataBaseUtilities;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import POMUtilities.HomePage;
import POMUtilities.Loginpage;
import UtilityClassObj.UtilitiesClassObject;

public class Baseclass {
	public DataBaseUtilities dutil = new DataBaseUtilities();
	public WebDriverUtility wutil = new WebDriverUtility();
	public PropertyFileUtility putil = new PropertyFileUtility();
	public WebDriver driver = null;
	// github lo undi e step undi comment cheyaledu
//	public static WebDriver sdriver = null;

	@BeforeSuite(alwaysRun = true)
	public void connectToDB() throws SQLException {
		dutil.getDatabaseConnection();
		Reporter.log("Connected To DB", true);
	}

	@BeforeTest(alwaysRun = true)
	public void configparallelExe() {
		Reporter.log("Configuration of Parallel Execution", true);
	}

     @Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void launchTheBrowser(String browser) throws IOException {

		//String browser = putil.fetchDataFromPropFile("browser");
        if (browser.equals("chrome"))
			driver = new ChromeDriver();
         else if (browser.equals("edge"))
			driver = new EdgeDriver();
		 else if (browser.equals("fireFox"))
			driver = new FirefoxDriver();
             else
             driver = new ChromeDriver();

//		sdriver = driver;
		UtilitiesClassObject.setDriver(driver);
		Reporter.log("Launched browser", true);
	}

	@BeforeMethod(alwaysRun = true)
	public void login() throws IOException {
		// doubt
		// String url = System.getProperty("url", putil.fetchDataFromPropFile("url"));
		String url = putil.fetchDataFromPropFile("url");
		String username = putil.fetchDataFromPropFile("username");
		String password = putil.fetchDataFromPropFile("password");
		String timeouts = putil.fetchDataFromPropFile("timeouts");

		// maximize the window
		wutil.maximizeTheWindow(driver);
		// implicite wait
		wutil.waitForAnElement(driver, timeouts);
		// navigate to an appln
		wutil.navigateToAnAppln(driver, url);

		// login
		Loginpage loginpp = new Loginpage(driver);
		loginpp.login(username, password);

		Reporter.log("logged in to VTiger", true);
	}

	@AfterMethod(alwaysRun = true)
	public void logout() {
		HomePage homepp = new HomePage(driver);
		homepp.logout(driver);
		Reporter.log("Logged out of Vtiger", true);
	}

	@AfterClass(alwaysRun = true)
	public void quitTheBrowser() {
		wutil.quitTheBrowser(driver);
		Reporter.log("Closed Browser", true);
	}

	@AfterClass(alwaysRun = true)
	public void CloseParallelExe() {
		Reporter.log("close configuration of parallel Execution", true);
	}

	@AfterSuite(alwaysRun = true)
	public void DisConnectToDB() throws SQLException {
		dutil.closeDatabaseConnection();
		Reporter.log("DisConnected with DB", true);

	}

}
