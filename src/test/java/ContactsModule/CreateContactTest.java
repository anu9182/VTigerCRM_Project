package ContactsModule;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
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
import POMUtilities.HomePage;
import POMUtilities.Loginpage;

@Listeners(ListenersUtility.ListenersImp.class)
public class CreateContactTest extends Baseclass  {
	@Test(groups = "smoke", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void contactTest() throws InterruptedException, EncryptedDocumentException, IOException {
		
		// fetch the random number
				JavaUtility jutil = new JavaUtility();
				int rnum = jutil.generateRandomNumber();

				// fetch the data from excel file
				ExcelFileUtility exutil = new ExcelFileUtility();
				String lastname = exutil.fetchDataFromExcel("contact", 1, 3) + rnum;

				WebDriverUtility wutil = new WebDriverUtility();

				// validating the home page using soft Assert
				HomePage homepp = new HomePage(driver);
				String home = homepp.getVerifyHomeHeader();
				SoftAssert soft = new SoftAssert();
				soft.assertTrue(home.contains("Home"), "Validating Home page");

				// identify contact tab and click on it
				homepp.getConTab();
				// Identify contact plus icon and click on it
				ContactPage conpp = new ContactPage(driver);
				conpp.getConplusIcon();

				// doubt header
				// validate for create new contact header element
				CreateContactPage createconpp = new CreateContactPage(driver);
				WebElement cncHeader = createconpp.getCreateconHeader();

				// using wait statement
				String timeouts = putil.fetchDataFromPropFile("timeouts");
				wutil.waitUntilEleIsVisible(driver, timeouts, cncHeader);
				// Identify Lastname TF and enter lastname in it
				createconpp.getLastnameTF(lastname);
				// Identify save btn and click on it
				createconpp.getSaveBtn();

				// validating the lastname using hard Assert
				ContactInfoPage coninfopp = new ContactInfoPage(driver);
				String verifylastname = coninfopp.getVarifyLastname();
				Assert.assertEquals(verifylastname, lastname, "validating the contact lastname");

				// Identify contact tab and click on it
				homepp.getConTab();

				// delete the created contact
				driver.findElement(By.xpath("//a[text()='" + lastname + "']/../../descendant::a[text()='del']")).click();
				Thread.sleep(5000);
				// handle confirmation popup and click on ok
				wutil.switchToAlert_ClickOK(driver);

				exutil.closeTheExcelFile();
				soft.assertAll();
			}
}
