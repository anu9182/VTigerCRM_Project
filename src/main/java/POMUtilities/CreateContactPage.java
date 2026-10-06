package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage {
//declare
	@FindBy(xpath="//span[text()='Creating New Contact']")
	private WebElement createconHeader;
	@FindBy (name="lastname")
	private WebElement lastnameTF;
	@FindBy (xpath="//img[contains(@onclick,'module=Accounts&action')]")
	private WebElement orgplusIcon;
	@FindBy (id="search_txt")
	private WebElement searchTab;
	@FindBy (name="search")
	private WebElement searchbtn;
	@FindBy (name="support_start_date")
	private WebElement SuppStartdateTF;
	@FindBy (name="support_end_date")
	private WebElement SuppEnddateTF;
	@FindBy (xpath= "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//Initialize
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
     //utilize
	public WebElement getCreateconHeader() {
		return createconHeader;
	}

	public void getLastnameTF(String lastname) {
		lastnameTF.sendKeys(lastname);
	}

	public void getOrgplusIcon() {
		orgplusIcon.click();
	}

	public WebElement getSearchTab() {
		return searchTab;
	}

	public void getSearchbtn() {
		searchbtn.click();
	}

	public void getSuppStartdateTF(String startdate) {
		SuppStartdateTF.clear();
		SuppStartdateTF.sendKeys(startdate);
	}

	public void getSuppEnddateTF(String enddate) {
		SuppEnddateTF.clear();
		SuppEnddateTF.sendKeys(enddate);
	}

	public void getSaveBtn() {
		saveBtn.click();
	}

	
}
	

