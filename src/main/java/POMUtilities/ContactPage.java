package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
//declare
	@FindBy(linkText="Contacts")
	private WebElement VerifyConPage;
	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement conplusIcon;
	
	//Initilize
	public ContactPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	//utilize
	public String getVeriftConPage() {
		return VerifyConPage.getText();
	}
	public void getConplusIcon() {
		conplusIcon.click();
	}
	
}
