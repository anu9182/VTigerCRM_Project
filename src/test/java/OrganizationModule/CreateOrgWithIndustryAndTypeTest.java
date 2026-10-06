package OrganizationModule;

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
import org.openqa.selenium.support.ui.Select;
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
import POMUtilities.CreateOrgPage;
import POMUtilities.HomePage;
import POMUtilities.Loginpage;
import POMUtilities.OrgInfoPage;
import POMUtilities.OrgPage;
@Listeners(ListenersUtility.ListenersImp.class)
public class CreateOrgWithIndustryAndTypeTest extends Baseclass{
@Test
	public void createOrgWithIATTest() throws EncryptedDocumentException, IOException {
	
		// using java utilitys
		JavaUtility jutil = new JavaUtility();
		int rnum = jutil.generateRandomNumber();
		// fetch the data from excel file
		ExcelFileUtility exutil = new ExcelFileUtility();
		String orgname = exutil.fetchDataFromExcel("org", 3, 3) + rnum;
		String phno = exutil.fetchDataFromExcel("org", 3, 4);

		WebDriverUtility wutil = new WebDriverUtility();
		// validating the homepage using soft Assert
		HomePage homepp = new HomePage(driver);
		String home = homepp.getVerifyHomeHeader();
		SoftAssert soft = new SoftAssert();
		soft.assertTrue(home.contains("Home"), "validating create contact with org test");

		// Indentify org link Text and click on it
		homepp.getOrgTab();

		// Identify the + icon and click on it
		OrgPage orgpp = new OrgPage(driver);
		orgpp.getOrgplusIcon();

		// Identify org name tf and pass the value on it
		CreateOrgPage createorgpp = new CreateOrgPage(driver);
		createorgpp.getOrgnameTF(orgname);

		// identify phone no TF and pass the number on it
		createorgpp.getPhoneNoTF(phno);

		// Identify the save btn and click on it
		createorgpp.getSaveBtn();

		// validating orgname with phno using hard Assert
		OrgInfoPage orginfopp = new OrgInfoPage(driver);
		String VerifyOrgname = orginfopp.getVerifyOrgName();
		Assert.assertEquals(VerifyOrgname, orgname, "Validating the orgname with phno");

		// verify phno using hard Assert
		String VerifyPhno = orginfopp.getVerifyPhoneNo();
		Assert.assertEquals(VerifyPhno, phno, "validating the org with phno");

		// identify org link and click on it
		homepp.getOrgTab();
		// driver.findElement(By.linkText("Organizations")).click();

		// delete the org name
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant ::a[text()='del']"))
				.click();

		// handle comfirmation popup
		wutil.switchToAlert_ClickOK(driver);
		exutil.closeTheExcelFile();
		soft.assertAll();

	}

}