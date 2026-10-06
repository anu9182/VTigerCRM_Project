package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
//declare
	@FindBy(xpath ="//img[@src='test/logo/vtiger-crm-logo.gif']")
	private WebElement titleValidate;
	@FindBy(name="user_name")
	private WebElement usernameTF;
	@FindBy(name="user_password")
	private WebElement passwordTF;
	@FindBy (id="submitButton")
	private WebElement loginbtn;
	
	//Initialize 
	public Loginpage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	public String getTitleValidate() {
		return titleValidate.getText();
	}

	public void getUsernameTF(String username) {
		usernameTF.sendKeys(username);
	}

	public void getPasswordTF(String password) {
		passwordTF.sendKeys(password);
	}

	public void getLoginbtn() {
		loginbtn.click();
		}
	
	//business logic 
	public void login(String urname,String password) {
		usernameTF.sendKeys(urname);
		passwordTF.sendKeys(password);
		loginbtn.click();
	}
	
}
