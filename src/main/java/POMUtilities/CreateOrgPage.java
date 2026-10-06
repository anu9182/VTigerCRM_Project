package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrgPage {
//declare
	@FindBy(xpath="//span[contains(text(),'Creating New Organization')]")
	private WebElement createOrgHeader;
	@FindBy (name="accountname")
	private WebElement orgnameTF;
	@FindBy (id="phone")
	private WebElement phoneNoTF;
	@FindBy (name="industry")
	private WebElement industryDD;
	@FindBy (name="accounttype")
	private WebElement typeDD;
	@FindBy (xpath= "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//Initialize
	public CreateOrgPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	//utiliaze
	public  String getcreateOrgHeader() {
		return createOrgHeader.getText();
	}

	public void getOrgnameTF(String orgname) {
		orgnameTF.sendKeys(orgname);
	}

		public void getPhoneNoTF(String Phno) {
		phoneNoTF.sendKeys(Phno);
	}

	public WebElement getIndustryDD() {
		return industryDD;
	}

	public WebElement getTypeDD() {
		return typeDD;
	}
	public void getSaveBtn() {
		saveBtn.click();
	}
}
