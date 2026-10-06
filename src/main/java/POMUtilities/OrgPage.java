package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgPage {
//declare 
	@FindBy (linkText="Organizations")
	private WebElement orgHeader;
	//wrong
	@FindBy (xpath="//img[@alt='Create Organization...']")
	private WebElement orgplusIcon;
	
	//Initialize
	public OrgPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	//utilize

	public String getOrgHeader() {
		return orgHeader.getText();
	}

	public void getOrgplusIcon() {
		orgplusIcon.click();;
	}
	
}
