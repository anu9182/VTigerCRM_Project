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
public class CreateOrgWithPhoneNoTest extends Baseclass{
	@Test(groups = "reg", retryAnalyzer = ListenersUtility.RetryAnalyser.class)
	public void createOrgWithPhNoTest() throws EncryptedDocumentException, IOException {
		
			// using java utilitys
			JavaUtility jutil = new JavaUtility();
			int rnum = jutil.generateRandomNumber();
			// doubt
			ExcelFileUtility exutil = new ExcelFileUtility();
			String orgname = exutil.fetchDataFromExcel("org", 5, 3) + rnum;
			String industryname = exutil.fetchDataFromExcel("org", 5, 4);
			String typeI = exutil.fetchDataFromExcel("org", 5, 5);

			WebDriverUtility wutil = new WebDriverUtility();
			// validate the homepage using soft Assert
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

			// identify Industry and click on it
			WebElement industryDD = createorgpp.getIndustryDD();
			wutil.handleDDUsing_SelectByValue(driver, industryDD, industryname);
			// Type
			WebElement typeDD = createorgpp.getTypeDD();
			wutil.handleDDUsing_SelectByValue(driver, typeDD, typeI);

			// Identify the save btn and click on it
			createorgpp.getSaveBtn();

			// Identify org info header and validae orgname
			OrgInfoPage orginfopp = new OrgInfoPage(driver);
			String verifyorgname = orginfopp.getVerifyOrgName();
			Assert.assertEquals(verifyorgname, orgname);

			// valiadate Industry type seved on it
			String verifyIndustry = orginfopp.getVerifyIndestryName();
			Assert.assertEquals(verifyIndustry, industryname);

			// industry and validate type
			String verifyType = orginfopp.getVerifyTypeName();
			Assert.assertEquals(verifyType, typeI);

			// identify org link and click on it
			homepp.getOrgTab();

			// delete the org name
			driver.findElement(
					By.xpath("//a[text()='" + orgname + "' and @title='Organizations']/../../descendant ::a[text()='del']"))
					.click();

			// handle comfirmation popup
			wutil.switchToAlert_ClickOK(driver);

			exutil.closeTheExcelFile();
			soft.assertAll();

		} //tommorrow which topicextent reports
	
}
