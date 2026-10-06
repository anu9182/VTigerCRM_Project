package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
//declare
	@FindBy (xpath="//span[contains(text(),'Contact Information')]")
	private WebElement ConInfoHeader;
	@FindBy (id="dtlview_Last Name")
	private WebElement VarifyLastname;
	@FindBy (xpath="//td[@id='mouseArea_Organization Name']/a")
	private WebElement VarifyOrgname;
	@FindBy (id="dtlview_Support Start Date")
	private WebElement VarifySuppStartDate;
	@FindBy (id="dtlview_Support End Date")
	private WebElement VarifySuppEndDate;
	
	//initialize
	public ContactInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
    //utilize
	public String getConInfoHeader() {
		return ConInfoHeader.getText();
	}

	public String getVarifyLastname() {
		return VarifyLastname.getText();
	}

	public String getVarifyOrgname() {
		return VarifyOrgname.getText();
	}

	public String getVarifySuppStartDate() {
		return VarifySuppStartDate.getText();
	}

	public String getVarifySuppEndDate() {
		return VarifySuppEndDate.getText();
	}
}
