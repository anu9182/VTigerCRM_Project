package ContactsModule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

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
public class CreateAContactWithSupportDateTest extends Baseclass {
	@Test(groups = "reg", retryAnalyzer = ListenersUtility.RetryAnalyser.class)

	public void cContactWithDateTest() throws InterruptedException, IOException {
     
	        // using java utilitys
			JavaUtility jutil = new JavaUtility();
			int rnum = jutil.generateRandomNumber();

			// fetch the data from excel file
			ExcelFileUtility exutil = new ExcelFileUtility();
			String lastname = exutil.fetchDataFromExcel("contact", 5, 3) + rnum;

			WebDriverUtility wutil = new WebDriverUtility();

			// validate the home page using soft Assert
			HomePage homepp = new HomePage(driver);
			String home = homepp.getVerifyHomeHeader();
			SoftAssert soft = new SoftAssert();
			soft.assertTrue(home.contains("Home"), "Validating Home page");

			// identify contact tab and click on it
			homepp.getConTab();

			// Identify contact plus icon and click on it
			ContactPage conpp = new ContactPage(driver);
			conpp.getConplusIcon();

			// waiting for create new contact header element
			CreateContactPage createconpp = new CreateContactPage(driver);
			WebElement cncHeader = createconpp.getCreateconHeader();
			String timeouts = putil.fetchDataFromPropFile("timeouts");
			wutil.waitUntilEleIsVisible(driver,timeouts,cncHeader);
			
			// Identify Lastname TF and enter lastname in it
			createconpp.getLastnameTF(lastname);

			// fetch the current dat
			String startdate = jutil.fetchCurrentDate();
			// Get end date after 30 days
			String enddate = jutil.fetchDateAfterGivenNoOfDays(30);
			// Identify supp start date TF and pass current date
			createconpp.getSuppStartdateTF(startdate);

			// Identify supp end date TF and pass date after 30 days
			createconpp.getSuppEnddateTF(enddate);

			// Identify save btn and click on it
			createconpp.getSaveBtn();

			// doubt

			// validating lastname using hard Assert
			ContactInfoPage coninfopp = new ContactInfoPage(driver);
			String Verifylastname = coninfopp.getVarifyLastname();
			Assert.assertEquals(Verifylastname, lastname, "validating contact page lastname");

			// validate suppstartdate using hard Assert
			String verifyStartdate = coninfopp.getVarifySuppStartDate();
			Assert.assertEquals(verifyStartdate, startdate, "validating contact page suppstartdate");

			// validate suppenddate using hard Assert
			String verifyenddate = coninfopp.getVarifySuppEndDate();
			Assert.assertEquals(verifyenddate, enddate, "validating contact page suppenddate");

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