package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	//declare
    @FindBy (partialLinkText="Home")
    private WebElement VerifyhomeHeader;
    @FindBy (linkText="Organizations")
    private WebElement orgTab;
    @FindBy (linkText="Contacts")
    private WebElement conTab;
    @FindBy (xpath="//img [contains(@src,'user.PNG')]")
    private WebElement adminIcon;
    @FindBy (linkText="Sign Out")
    private WebElement signoutbtn;
    
    //Initialize
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
    
	//utilize
	public String getVerifyHomeHeader() {
		return VerifyhomeHeader.getText();
	}

	public void getOrgTab() {
		orgTab.click();
	}

	public void getConTab() {
	   conTab.click();
	}

	public WebElement getAdminIcon() {
		return adminIcon;
	}

	public void getSignoutbtn() {
		signoutbtn.click();
	}
	
	//business logic
	public void logout(WebDriver driver) {
	
		Actions act=new Actions(driver);
		act.moveToElement(adminIcon).perform();
		signoutbtn.click();
	}
	
}
