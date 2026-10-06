package ContactTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseClassUtility.Baseclass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.WebDriverUtility;
import POMUtilities.ContactInfoPage;
import POMUtilities.ContactPage;
import POMUtilities.CreateContactPage;
import POMUtilities.CreateOrgPage;
import POMUtilities.HomePage;
import POMUtilities.OrgInfoPage;
import POMUtilities.OrgPage;
import UtilityClassObj.UtilitiesClassObject;

@Listeners(ListenersUtility.ListenersImp.class)

//CreateContact

public class CreateContactswithOrgNameAndSuppDateTest extends Baseclass {

	@Test(groups = "smoke", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void contactTest() throws InterruptedException, EncryptedDocumentException, IOException {

		// fetch the random number
		JavaUtility jutil = new JavaUtility();
		int rnum = jutil.generateRandomNumber();
		UtilitiesClassObject.getTest().log(Status.INFO, "Fetched random number");

		// fetch the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String lastname = exutil.fetchDataFromExcel("contact", 1, 3) + rnum;
		UtilitiesClassObject.getTest().log(Status.INFO, "Fetched data from excel file");

		WebDriverUtility wutil = new WebDriverUtility();

		// validating the home page using soft Assert
		HomePage homepp = new HomePage(driver);
		String home = homepp.getVerifyHomeHeader();
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.contains("Home"), "Validating Home page");
		UtilitiesClassObject.getTest().log(Status.INFO, "Validating home page");

		// identify contact tab and click on it
		homepp.getConTab();
		UtilitiesClassObject.getTest().log(Status.INFO, "Identifying contact tab and click on it");
		// Identify contact plus icon and click on it
		ContactPage conpp = new ContactPage(driver);
		conpp.getConplusIcon();
		UtilitiesClassObject.getTest().log(Status.INFO, "Indentify contact plus icon and click on it");

		// doubt header
		// validate for create new contact header element
		CreateContactPage createconpp = new CreateContactPage(driver);
		WebElement cncHeader = createconpp.getCreateconHeader();
		UtilitiesClassObject.getTest().log(Status.INFO, "validate for create new contact header element");

		// using wait statement
		String timeouts = putil.fetchDataFromPropFile("timeouts");
		wutil.waitUntilEleIsVisible(driver, timeouts, cncHeader);
		UtilitiesClassObject.getTest().log(Status.INFO, "Using wait statement");

		// Identify Lastname TF and enter lastname in it
		createconpp.getLastnameTF(lastname);
		UtilitiesClassObject.getTest().log(Status.INFO, "Identify Lastname TF and enter lastname in it");
		// Identify save btn and click on it
		createconpp.getSaveBtn();
		UtilitiesClassObject.getTest().log(Status.INFO, "Identify save btn and click on it");

		// validating the lastname using hard Assert
		ContactInfoPage coninfopp = new ContactInfoPage(driver);
		String verifylastname = coninfopp.getVarifyLastname();
		Assert.assertEquals(verifylastname, lastname, "validating the contact lastname");
		UtilitiesClassObject.getTest().log(Status.PASS, "Identify save btn and click on it");

		// Identify contact tab and click on it
		homepp.getConTab();
		UtilitiesClassObject.getTest().log(Status.INFO, " Clicked on contact tab ");

		// delete the created contact
		driver.findElement(By.xpath("//a[text()='" + lastname + "']/../../descendant::a[text()='del']")).click();
		Thread.sleep(5000);
		// handle confirmation popup and click on ok
		wutil.switchToAlert_ClickOK(driver);
		UtilitiesClassObject.getTest().log(Status.INFO, " Deleted contact");

		exutil.closeTheExcelFile();
		soft.assertAll();
		UtilitiesClassObject.getTest().log(Status.INFO, "Closed Excel File and handled soft assert");
	}

	@Test(groups = "reg", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void cContactWithOrgTest() throws InterruptedException, IOException {

		// using java utilitys
		JavaUtility jutil = new JavaUtility();
		int rnum = jutil.generateRandomNumber();
		UtilitiesClassObject.getTest().log(Status.INFO, "Fetch the random number");

		// using excel file properties
		ExcelFileUtility exutil = new ExcelFileUtility();
		String lastname = exutil.fetchDataFromExcel("contact", 3, 3) + rnum;
		String orgname = exutil.fetchDataFromExcel("contact", 3, 4) + rnum;
		UtilitiesClassObject.getTest().log(Status.INFO, " Fetched data from excel file");

		WebDriverUtility wutil = new WebDriverUtility();

		// validate the home page using soft Assert
		HomePage homepp = new HomePage(driver);
		String home = homepp.getVerifyHomeHeader();
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.contains("Home"), "Validating Home page");
		UtilitiesClassObject.getTest().log(Status.INFO, " Validating the home page ");

		// Indentify org link Text and click on it
		homepp.getOrgTab();
		UtilitiesClassObject.getTest().log(Status.INFO, " Clicked on org tab ");

		// Identify the + icon and click on it
		OrgPage orgpp = new OrgPage(driver);
		orgpp.getOrgplusIcon();
		UtilitiesClassObject.getTest().log(Status.INFO, "Clicked on contact plus icon");

		// Identify org name tf and pass the value on it
		CreateOrgPage createorgpp = new CreateOrgPage(driver);
		createorgpp.getOrgnameTF(orgname);
		UtilitiesClassObject.getTest().log(Status.INFO, " pass the org value ");
		// Identify the save btn and click on it
		createorgpp.getSaveBtn();
		UtilitiesClassObject.getTest().log(Status.INFO, " Click on save btn");

		// Identify org info header and validate orgname using hard Assert
		OrgInfoPage orginfopp = new OrgInfoPage(driver);
		String VerifyOrgname = orginfopp.getVerifyOrgName();
		Assert.assertEquals(VerifyOrgname, orgname, "validating the orgname in org info page");
		UtilitiesClassObject.getTest().log(Status.PASS, " validate the org name");

		// identify contact tab and click on it
		homepp.getConTab();
		UtilitiesClassObject.getTest().log(Status.INFO, " Clicked on contact tab ");
		// Identify contact plus icon and click on it
		ContactPage conpp = new ContactPage(driver);
		conpp.getConplusIcon();
		UtilitiesClassObject.getTest().log(Status.INFO, " Clicked on contact plus icon ");
		// waiting for create new contact header element
		CreateContactPage createconpp = new CreateContactPage(driver);
		WebElement cncHeader = createconpp.getCreateconHeader();
		String timeouts = putil.fetchDataFromPropFile("timeouts");
		wutil.waitUntilEleIsVisible(driver, timeouts, cncHeader);
		UtilitiesClassObject.getTest().log(Status.INFO, " open the new contact page ");

		// Identify Lastname TF and enter lastname in it
		createconpp.getLastnameTF(lastname);
		UtilitiesClassObject.getTest().log(Status.INFO, "Enter the lastname ");

		// identify org TF and click on + icon
		createconpp.getOrgplusIcon();
		UtilitiesClassObject.getTest().log(Status.INFO, " Clicked on org plus icon");
		// switch to the child window
		String pwid = wutil.fetchCurrentWindowID(driver);
		// Switch to child window
		wutil.switchToChildWindow_URL(driver, "module=Accounts&action");
		UtilitiesClassObject.getTest().log(Status.INFO, " switched to the child window");
		// Identify search TF and enter orgname
		createconpp.getSearchTab();
		UtilitiesClassObject.getTest().log(Status.INFO, "Identify search TF and enter orgname");

		// Identify search btn and click on it
		createconpp.getSearchbtn();
		UtilitiesClassObject.getTest().log(Status.INFO, "Clicked on search btn");

		// Identify org name and click on it
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();
		UtilitiesClassObject.getTest().log(Status.INFO, "Clicked on org name");

		// switch back to parent window
		wutil.switchToParentWindow(driver, pwid);
		UtilitiesClassObject.getTest().log(Status.INFO, "Switch back to parent window");

		// Identify save btn and click on it
		createconpp.getSaveBtn();
		UtilitiesClassObject.getTest().log(Status.INFO, "click on save btn");

		// validate lastname using hard Assert
		ContactInfoPage coninfopp = new ContactInfoPage(driver);
		String VerifyLastname = coninfopp.getVarifyLastname();
		Assert.assertEquals(VerifyLastname, lastname, "validating contact page lastname");
		UtilitiesClassObject.getTest().log(Status.PASS, "Validate the lastname");

		// validate orgname in contact info
		String Verifyorgname = coninfopp.getVarifyOrgname();
		Assert.assertEquals(Verifyorgname, orgname, "validating contact page orgname");
		UtilitiesClassObject.getTest().log(Status.PASS, "Validate org name");

		// Identify contact tab and click on it
		homepp.getConTab();
		UtilitiesClassObject.getTest().log(Status.INFO, "Clicked on contact tab");

		// delete the created contact
		driver.findElement(By.xpath("//a[text()='" + lastname + "']/../../descendant::a[text()='del']")).click();
		Thread.sleep(5000);

		// handle confirmation popup and click on ok
		wutil.switchToAlert_ClickOK(driver);
		UtilitiesClassObject.getTest().log(Status.INFO, "Deleted contact");

		// identify org link and click on it
		homepp.getOrgTab();
		UtilitiesClassObject.getTest().log(Status.INFO, "Clicked on org tab");

		// delete the org name
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant ::a[text()='del']"))
				.click();
		Thread.sleep(5000);

		// handle confirmation popup and click on ok
		wutil.switchToAlert_ClickOK(driver);
		UtilitiesClassObject.getTest().log(Status.INFO, "Deleted org");

		exutil.closeTheExcelFile();
		soft.assertAll();
		UtilitiesClassObject.getTest().log(Status.INFO, "Closed Excel File and handled soft assert");

	}

	@Test(groups = "reg", retryAnalyzer = ListenersUtility.RetryAnalyser.class)

	public void cContactWithDateTest() throws InterruptedException, IOException {

		// using java utilitys
		JavaUtility jutil = new JavaUtility();
		int rnum = jutil.generateRandomNumber();
		UtilitiesClassObject.getTest().log(Status.INFO, "Fetch the random number");

		// fetch the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String lastname = exutil.fetchDataFromExcel("contact", 5, 3) + rnum;
		UtilitiesClassObject.getTest().log(Status.INFO, "Fetch data from excel file");

		WebDriverUtility wutil = new WebDriverUtility();

		// validate the home page using soft Assert
		HomePage homepp = new HomePage(driver);
		String home = homepp.getVerifyHomeHeader();
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.contains("Home"), "Validating Home page");
		UtilitiesClassObject.getTest().log(Status.INFO, "Validating Home page");

		// identify contact tab and click on it
		homepp.getConTab();
		UtilitiesClassObject.getTest().log(Status.INFO, "Click on contact tab");

		// Identify contact plus icon and click on it
		ContactPage conpp = new ContactPage(driver);
		conpp.getConplusIcon();
		UtilitiesClassObject.getTest().log(Status.INFO, "Click on contact plus icon");

		// waiting for create new contact header element
		CreateContactPage createconpp = new CreateContactPage(driver);
		WebElement cncHeader = createconpp.getCreateconHeader();
		String timeouts = putil.fetchDataFromPropFile("timeouts");
		wutil.waitUntilEleIsVisible(driver, timeouts, cncHeader);
		UtilitiesClassObject.getTest().log(Status.INFO, "Validating new contact header");

		// Identify Lastname TF and enter lastname in it
		createconpp.getLastnameTF(lastname);
		UtilitiesClassObject.getTest().log(Status.INFO, "Enter lastname");

		// fetch the current dat
		String startdate = jutil.fetchCurrentDate();
		// Get end date after 30 days
		UtilitiesClassObject.getTest().log(Status.INFO, "Fetch the current date");
		String enddate = jutil.fetchDateAfterGivenNoOfDays(30);
		UtilitiesClassObject.getTest().log(Status.INFO, "Fetch the date after given No of days");
		// Identify supp start date TF and pass current date
		createconpp.getSuppStartdateTF(startdate);
		UtilitiesClassObject.getTest().log(Status.INFO, "Pass the supp start date ");

		// Identify supp end date TF and pass date after 30 days
		createconpp.getSuppEnddateTF(enddate);
		UtilitiesClassObject.getTest().log(Status.INFO, "pass the supp end date ");

		// Identify save btn and click on it
		createconpp.getSaveBtn();
		UtilitiesClassObject.getTest().log(Status.INFO, "click on save btn");

		// doubt

		// validating lastname using hard Assert
		ContactInfoPage coninfopp = new ContactInfoPage(driver);
		String Verifylastname = coninfopp.getVarifyLastname();
		Assert.assertEquals(Verifylastname, lastname, "validating contact page lastname");
		UtilitiesClassObject.getTest().log(Status.PASS, "validate lastname");

		// validate suppstartdate using hard Assert
		String verifyStartdate = coninfopp.getVarifySuppStartDate();
		Assert.assertEquals(verifyStartdate, startdate, "validating contact page suppstartdate");
		UtilitiesClassObject.getTest().log(Status.PASS, "Validate supp start date");
		// validate suppenddate using hard Assert
		String verifyenddate = coninfopp.getVarifySuppEndDate();
		Assert.assertEquals(verifyenddate, enddate, "validating contact page suppenddate");
		UtilitiesClassObject.getTest().log(Status.PASS, "Validate supp enddate");
		// Identify contact tab and click on it
		homepp.getConTab();
		UtilitiesClassObject.getTest().log(Status.INFO, "Clicked on contact tab");

		// delete the created contact
		driver.findElement(By.xpath("//a[text()='" + lastname + "']/../../descendant::a[text()='del']")).click();
		Thread.sleep(5000);
		// handle confirmation popup and click on ok
		wutil.switchToAlert_ClickOK(driver);
		UtilitiesClassObject.getTest().log(Status.INFO, "deleted contact");
		exutil.closeTheExcelFile();
		soft.assertAll();
		UtilitiesClassObject.getTest().log(Status.INFO, "Closed Excel File and handled soft assert");

	}
}
