package OrganizationTest;

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
import POMUtilities.CreateOrgPage;
import POMUtilities.HomePage;
import POMUtilities.OrgInfoPage;
import POMUtilities.OrgPage;
import UtilityClassObj.UtilitiesClassObject;

//<<<<<<< HEAD=======
////Sam Workspace
//>>>>>>> branch 'master' of https://github.com/anu9182/VTigerCRM_Project.git
//OrganizationTest
@Listeners(ListenersUtility.ListenersImp.class)
public class CteateOrgwithPhNoAndIndustryAndTypeTest extends Baseclass {

	@Test(groups = "smoke", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createOrgTest() throws IOException {

		// using java utilitys
		JavaUtility jutil = new JavaUtility();
		int rnum = jutil.generateRandomNumber();
		UtilitiesClassObject.getTest().log(Status.INFO, "Fetched random number");

		// fetch the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcel("org", 1, 3) + rnum;
		UtilitiesClassObject.getTest().log(Status.INFO, "Fetched data from excel file");

		WebDriverUtility wutil = new WebDriverUtility();

		// validating the homepage using soft assert
		HomePage homepp = new HomePage(driver);
		String home = homepp.getVerifyHomeHeader();
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.contains("Home"), "Validating Home page");
		UtilitiesClassObject.getTest().log(Status.INFO, "Validating home page");

		// Indentify org link Text and click on it
		homepp.getOrgTab();
		UtilitiesClassObject.getTest().log(Status.INFO, "Clicked on org link text");

		// Identify the + icon and click on it
		OrgPage orgpp = new OrgPage(driver);
		orgpp.getOrgplusIcon();
		UtilitiesClassObject.getTest().log(Status.INFO, "clicked on plus icon");

		// Identify org name tf and pass the value on it
		CreateOrgPage createorgpp = new CreateOrgPage(driver);
		createorgpp.getOrgnameTF(orgname);
		UtilitiesClassObject.getTest().log(Status.INFO, "pass the org name");

		// Identify the save btn and click on it
		createorgpp.getSaveBtn();
		UtilitiesClassObject.getTest().log(Status.INFO, "click on save btn");

		// Verifing org name using hard assert
		OrgInfoPage orginfopp = new OrgInfoPage(driver);
		String VerifyOrgname = orginfopp.getVerifyOrgName();
		Assert.assertEquals(VerifyOrgname, orgname);
		UtilitiesClassObject.getTest().log(Status.PASS, " validating org name");

		// identify org link and click on it
		homepp.getOrgTab();
		UtilitiesClassObject.getTest().log(Status.INFO, "clicked on org tab");

		// delete the org name
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant ::a[text()='del']"))
				.click();

		// handle comfirmation popup
		wutil.switchToAlert_ClickOK(driver);
		UtilitiesClassObject.getTest().log(Status.INFO, "Deleted org");

		exutil.closeTheExcelFile();
		soft.assertAll();
		UtilitiesClassObject.getTest().log(Status.INFO, "Closed Excel File and handled soft assert");
	}

	@Test(groups = "reg", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createOrgWithPhNoTest() throws EncryptedDocumentException, IOException {

		// using java utilitys
		JavaUtility jutil = new JavaUtility();
		int rnum = jutil.generateRandomNumber();
		UtilitiesClassObject.getTest().log(Status.INFO, "Fetched random number");
		// fetch the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcel("org", 3, 3) + rnum;
		String phno = exutil.fetchDataFromExcel("org", 3, 4);
		UtilitiesClassObject.getTest().log(Status.INFO, "Fetched data from excel file");

		WebDriverUtility wutil = new WebDriverUtility();
		// validating the homepage using soft Assert
		HomePage homepp = new HomePage(driver);
		String home = homepp.getVerifyHomeHeader();
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.contains("Home"), "validating create contact with org test");
		UtilitiesClassObject.getTest().log(Status.INFO, "Validating home page");

		// Indentify org link Text and click on it
		homepp.getOrgTab();
		UtilitiesClassObject.getTest().log(Status.INFO, "Clicked on org tab");

		// Identify the + icon and click on it
		OrgPage orgpp = new OrgPage(driver);
		orgpp.getOrgplusIcon();
		UtilitiesClassObject.getTest().log(Status.INFO, "Clicked on plus icon");

		// Identify org name tf and pass the value on it
		CreateOrgPage createorgpp = new CreateOrgPage(driver);
		createorgpp.getOrgnameTF(orgname);
		UtilitiesClassObject.getTest().log(Status.INFO, "pass the org name");

		// identify phone no TF and pass the number on it
		createorgpp.getPhoneNoTF(phno);
		UtilitiesClassObject.getTest().log(Status.INFO, "pass the phone no");

		// Identify the save btn and click on it
		createorgpp.getSaveBtn();
		UtilitiesClassObject.getTest().log(Status.INFO, "chicked on save btn");

		// validating orgname with phno using hard Assert
		OrgInfoPage orginfopp = new OrgInfoPage(driver);
		String VerifyOrgname = orginfopp.getVerifyOrgName();
		Assert.assertEquals(VerifyOrgname, orgname, "Validating the orgname with phno");
		UtilitiesClassObject.getTest().log(Status.PASS, "Validating orgname");

		// verify phno using hard Assert
		String VerifyPhno = orginfopp.getVerifyPhoneNo();
		Assert.assertEquals(VerifyPhno, phno, "validating the org with phno");
		UtilitiesClassObject.getTest().log(Status.PASS, "Validating Phno");

		// identify org link and click on it
		homepp.getOrgTab();
		UtilitiesClassObject.getTest().log(Status.INFO, "Clicked on org tab");

		// delete the org name
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant ::a[text()='del']"))
				.click();

		// handle comfirmation popup
		wutil.switchToAlert_ClickOK(driver);
		UtilitiesClassObject.getTest().log(Status.INFO, "Deleted org");
		exutil.closeTheExcelFile();
		soft.assertAll();
		UtilitiesClassObject.getTest().log(Status.INFO, "Closed Excel File and handled soft assert");

	}

	@Test(groups = "reg", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createOrgWithIATTest() throws EncryptedDocumentException, IOException {

		// using java utilitys
		JavaUtility jutil = new JavaUtility();
		int rnum = jutil.generateRandomNumber();
		UtilitiesClassObject.getTest().log(Status.INFO, "Fetched random number");
		// fetch the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcel("org", 5, 3) + rnum;
		String industryname = exutil.fetchDataFromExcel("org", 5, 4);
		String typeI = exutil.fetchDataFromExcel("org", 5, 5);
		UtilitiesClassObject.getTest().log(Status.INFO, "Fetched data from excel file");

		WebDriverUtility wutil = new WebDriverUtility();
		// validate the homepage using soft Assert
		HomePage homepp = new HomePage(driver);
		String home = homepp.getVerifyHomeHeader();

		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.contains("Home"), "Validating Home page");
		UtilitiesClassObject.getTest().log(Status.INFO, "Validating Home page");

		// Indentify org link Text and click on it
		homepp.getOrgTab();
		UtilitiesClassObject.getTest().log(Status.INFO, "clicked on org tab");

		// Identify the + icon and click on it
		OrgPage orgpp = new OrgPage(driver);
		orgpp.getOrgplusIcon();
		UtilitiesClassObject.getTest().log(Status.INFO, "Clicked on plus icon");

		// Identify org name tf and pass the value on it
		CreateOrgPage createorgpp = new CreateOrgPage(driver);
		createorgpp.getOrgnameTF(orgname);
		UtilitiesClassObject.getTest().log(Status.INFO, "pass org name");

		// identify Industry and click on it
		WebElement industryDD = createorgpp.getIndustryDD();
		wutil.handleDDUsing_SelectByValue(driver, industryDD, industryname);
		UtilitiesClassObject.getTest().log(Status.INFO, "clicked on industry");

		// Type
		WebElement typeDD = createorgpp.getTypeDD();
		wutil.handleDDUsing_SelectByValue(driver, typeDD, typeI);
		UtilitiesClassObject.getTest().log(Status.INFO, "clicked on type");

		// Identify the save btn and click on it
		createorgpp.getSaveBtn();
		UtilitiesClassObject.getTest().log(Status.INFO, "clicked on save btn");

		// Identify org info header and validae orgname
		OrgInfoPage orginfopp = new OrgInfoPage(driver);
		String verifyorgname = orginfopp.getVerifyOrgName();
		Assert.assertEquals(verifyorgname, orgname);
		UtilitiesClassObject.getTest().log(Status.PASS, "Validating org name");

		// valiadate Industry type seved on it
		String verifyIndustry = orginfopp.getVerifyIndestryName();
		Assert.assertEquals(verifyIndustry, industryname);
		UtilitiesClassObject.getTest().log(Status.PASS, "Validating Industry");

		// industry and validate type
		String verifyType = orginfopp.getVerifyTypeName();
		Assert.assertEquals(verifyType, typeI);
		UtilitiesClassObject.getTest().log(Status.PASS, "Validating Type");

		// identify org link and click on it
		homepp.getOrgTab();
		UtilitiesClassObject.getTest().log(Status.INFO, "Clicked on org tab");

		// delete the org name
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant ::a[text()='del']"))
				.click();

		// handle comfirmation popup
		wutil.switchToAlert_ClickOK(driver);
		UtilitiesClassObject.getTest().log(Status.INFO, "Deleted org");

		exutil.closeTheExcelFile();
		soft.assertAll();
		UtilitiesClassObject.getTest().log(Status.INFO, "Closed Excel File and handled soft assert	");

	}
}
