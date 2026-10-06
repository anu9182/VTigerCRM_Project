package ContactsModule;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BaseClassUtility.Baseclass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import POMUtilities.ContactInfoPage;
import POMUtilities.ContactPage;
import POMUtilities.CreateContactPage;
import POMUtilities.CreateOrgPage;
import POMUtilities.HomePage;
import POMUtilities.Loginpage;
import POMUtilities.OrgInfoPage;
import POMUtilities.OrgPage;

@Listeners(ListenersUtility.ListenersImp.class)
public class CreateContactWithOrgTest extends Baseclass {
	@Test(groups = "reg", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void cContactWithOrgTest() throws InterruptedException, IOException {
		
		// using java utilitys
				JavaUtility jutil = new JavaUtility();
				int rnum = jutil.generateRandomNumber();

				// using excel file properties
				ExcelFileUtility exutil = new ExcelFileUtility();
				String lastname = exutil.fetchDataFromExcel("contact", 3, 3) + rnum;
				String orgname = exutil.fetchDataFromExcel("contact", 3, 4) + rnum;

				WebDriverUtility wutil = new WebDriverUtility();

				// validate the home page using soft Assert
				HomePage homepp = new HomePage(driver);
				String home = homepp.getVerifyHomeHeader();
				SoftAssert soft = new SoftAssert();
				soft.assertTrue(home.contains("Home"), "Validating Home page");

				// Indentify org link Text and click on it
				homepp.getOrgTab();

				// Identify the + icon and click on it
				OrgPage orgpp = new OrgPage(driver);
				orgpp.getOrgplusIcon();

				// Identify org name tf and pass the value on it
				CreateOrgPage createorgpp = new CreateOrgPage(driver);
				createorgpp.getOrgnameTF(orgname);
				// Identify the save btn and click on it
				createorgpp.getSaveBtn();

				// Identify org info header and validate orgname using hard Assert
				OrgInfoPage orginfopp = new OrgInfoPage(driver);
				String VerifyOrgname = orginfopp.getVerifyOrgName();
				Assert.assertEquals(VerifyOrgname, orgname, "validating the orgname in org info page");

				// identify contact tab and click on it
				homepp.getConTab();
				// Identify contact plus icon and click on it
				ContactPage conpp = new ContactPage(driver);
				conpp.getConplusIcon();
				// waiting for create new contact header element
				CreateContactPage createconpp = new CreateContactPage(driver);
				WebElement cncHeader = createconpp.getCreateconHeader();
				String timeouts = putil.fetchDataFromPropFile("timeouts");
				wutil.waitUntilEleIsVisible(driver, timeouts, cncHeader);

				// Identify Lastname TF and enter lastname in it
				createconpp.getLastnameTF(lastname);

				// identify org TF and click on + icon
				createconpp.getOrgplusIcon();
				// switch to the child window
				String pwid = wutil.fetchCurrentWindowID(driver);

				// Switch to child window
				wutil.switchToChildWindow_URL(driver, "module=Accounts&action");

				// Identify search TF and enter orgname
				createconpp.getSearchTab();

				// Identify search btn and click on it
				createconpp.getSearchbtn();

				// Identify org name and click on it
				driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

				// switch back to parent window
				wutil.switchToParentWindow(driver,pwid);
				// Identify save btn and click on it
				createconpp.getSaveBtn();

				// validate lastname using hard Assert
				ContactInfoPage coninfopp = new ContactInfoPage(driver);
				String VerifyLastname = coninfopp.getVarifyLastname();
				Assert.assertEquals(VerifyLastname, lastname, "validating contact page lastname");

				// validate orgname in contact info
				String Verifyorgname = coninfopp.getVarifyOrgname();
				Assert.assertEquals(Verifyorgname, orgname, "validating contact page orgname");
				// Identify contact tab and click on it
				homepp.getConTab();
				// delete the created contact
				driver.findElement(By.xpath("//a[text()='" + lastname + "']/../../descendant::a[text()='del']")).click();
				Thread.sleep(5000);

				// handle confirmation popup and click on ok
				wutil.switchToAlert_ClickOK(driver);

				// identify org link and click on it
				homepp.getOrgTab();
				// delete the org name
				driver.findElement(
						By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant ::a[text()='del']"))
						.click();
				Thread.sleep(5000);

				// handle confirmation popup and click on ok
				wutil.switchToAlert_ClickOK(driver);
				
				exutil.closeTheExcelFile();
				soft.assertAll();

			}

}
