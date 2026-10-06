package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgInfoPage {
//declare 
	@FindBy(xpath="//span[contains(text(),'Organization Information')]")
	private WebElement VarifyOrgInfoHeader;
	@FindBy(id="dtlview_Organization Name")
	private WebElement VerifyOrgName;
	@FindBy(id="dtlview_Industry")
	private WebElement VerifyIndestryName;
	@FindBy(id="dtlview_Type")
	private WebElement VerifyTypeName;
	@FindBy(id="dtlview_Phone")
	private WebElement VerifyPhoneNo;
	
	//Initialize
	public OrgInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
     
	//utilize
	public String getVarifyOrgInfoHeader() {
		return VarifyOrgInfoHeader.getText();
	}

	public String getVerifyOrgName() {
		return VerifyOrgName.getText();
	}

	public String getVerifyIndestryName() {
		return VerifyIndestryName.getText();
	}

	public String getVerifyTypeName() {
		return VerifyTypeName.getText();
	}

	public String getVerifyPhoneNo() {
		return VerifyPhoneNo.getText();
	}
}
