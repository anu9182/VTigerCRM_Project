package GenericUtilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Anusha This class contains all the reusable methods from
 *         seleniumlibrary
 * 
 */

public class WebDriverUtility {

	/**
	 * This is a reusable method for navigate to an application
	 * 
	 * @param url
	 */

	public void navigateToAnAppln(WebDriver driver, String url) {
		driver.get(url);
	}

	/**
	 * This is a reusable method for fetching the current webpage title
	 * 
	 * @return
	 */
	public String fetchTheTitle(WebDriver driver) {
		return driver.getTitle();
	}

	/**
	 * This is a reusable method for fetching the current webpage URl
	 * 
	 * @return
	 */
	public String fetchTheUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	/**
	 * this is a reusable method for fetching the current html source code
	 * 
	 * @return
	 */
	public String fetchTheSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	/**
	 * This is a reusable method for maximizing the browser window
	 * 
	 */
	public void maximizeTheWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * This is a reusable method for minimizing the browser window
	 * 
	 */
	public void minimizeTheWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}

	/**
	 * This is a reusable method for making the browser window full screen
	 * 
	 */

	public void windowFullScreen(WebDriver driver) {
		driver.manage().window().fullscreen();
	}

	/**
	 * This is a reusable method for fetching the browser window size
	 * 
	 * @return
	 */
	public Dimension fetchWindowSize(WebDriver driver) {
		return driver.manage().window().getSize();
	}

	/**
	 * This is a reusable method for setting the browser window size
	 * 
	 * @param width
	 * @param height
	 */
	public void setWindowSize(WebDriver driver, int width, int height) {
		driver.manage().window().setSize(new Dimension(width, height));
	}

	/**
	 * This is a reusable method for fetching the browser window position
	 * 
	 * @return
	 */
	public Point fetchWindowposition(WebDriver driver) {
		return driver.manage().window().getPosition();
	}

	/**
	 * This is a reusable method for setting the browser window position
	 * 
	 * @param x
	 * @param y
	 */
	public void setWindowPosition(WebDriver driver, int x, int y) {
		driver.manage().window().setPosition(new Point(x, y));
	}

	/**
	 * This is a reusable method for navigating to a particular URL
	 * 
	 * @param url
	 */
	public void navigateUsingStringUrl(WebDriver driver, String url) {
		driver.navigate().to(url);
	}

	public void navigateUsingURLurl(WebDriver driver, String url) throws MalformedURLException {
		driver.navigate().to(new URL(url));
	}

	/**
	 * This is a reusable method for navigating forward to the next webpage
	 * 
	 */
	public void navigateToNextWebpage(WebDriver driver) {
		driver.navigate().forward();
	}

	/**
	 * This is a reusable method for navigating back to the previous webpage
	 * 
	 */
	public void navigateToPreviousWebpage(WebDriver driver) {
		driver.navigate().back();
	}

	/**
	 * This is a reusable method for refreshing the current webpage
	 */
	public void refreshTheWindow(WebDriver driver) {
		driver.navigate().refresh();
	}

	/**
	 * This is a reusable method for closing the current browser window
	 */
	public void closeTheBrowser(WebDriver driver) {
		driver.close();
	}

	/**
	 * This is a reusable method for closing the all browser window
	 */
	public void quitTheBrowser(WebDriver driver) {
		driver.quit();
	}

	/**
	 * This is a reusable method for getting the current window ID
	 * 
	 * @return
	 */
	public String fetchCurrentWindowID(WebDriver driver) {
		return driver.getWindowHandle();
	}

	/**
	 * This is a reusable method for getting all the current window IDs
	 * 
	 * @return
	 */
	public Set<String> fetchAllWindowIDS(WebDriver driver) {
		return driver.getWindowHandles();
	}

	/**
	 * This is a reusable method for switching to an iframme using index
	 * 
	 * @param index
	 */
	public void switchToFrameUsingIndex(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * This is a reusable method for switching to a particular window
	 * 
	 * @param id
	 */
	public void switchToWindow(WebDriver driver, String id) {
		driver.switchTo().window(id);
	}

	/**
	 * This is a reusable method for switching to an iframme using IDname
	 * 
	 * @param ID_NAME
	 */
	public void switchToFrameUsingIDNAME(WebDriver driver, String ID_NAME) {
		driver.switchTo().frame(ID_NAME);
	}

	/**
	 * This is a reusable method for switching to an iframme using webElement
	 * 
	 * @param frameele
	 */
	public void switchToFrameUsingWebElement(WebDriver driver, WebElement frameele) {
		driver.switchTo().frame(frameele);
	}

	/**
	 * this is a reusable method for switching to an alert and clicking on OK button
	 */

	public void switchToAlert_ClickOK(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void switchToAlert_ClickCANCEL(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void switchToAlert_EnterText(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	public void switchToAlert_fetchTheText(WebDriver driver) {
		driver.switchTo().alert().getText();
	}

	public void waitForAnElement(WebDriver driver, String timeouts) {
		long time = Long.parseLong(timeouts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	public void waitUntilEleIsVisible(WebDriver driver, String timeouts, WebElement ele) {
		long time = Long.parseLong(timeouts);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitUntilEleIsClickable(WebDriver driver, String timeouts, WebElement ele) {
		long time = Long.parseLong(timeouts);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitUntilTitleIsVisible(WebDriver driver, String timeouts, String title) {
		long time = Long.parseLong(timeouts);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		wait.until(ExpectedConditions.titleContains(title));
	}

	/**
	 * This is a reusable method for selecting by using value
	 * 
	 * @param dropdown
	 * @param value
	 */
	public void handleDDUsing_SelectByValue(WebDriver driver, WebElement dropdown, String value) {
		Select s = new Select(dropdown);
		s.selectByValue(value);
	}

	/**
	 * This is a reusable method for selecting by using index
	 * 
	 * @param dropdown
	 * @param index
	 */
	public void handleDDUsing_SelectByIndex(WebDriver driver, WebElement dropdown, int index) {
		Select s = new Select(dropdown);
		s.selectByIndex(index);
	}

	/**
	 * This is a reusable method for selecting by using visible text
	 * 
	 * @param dropdown
	 * @param text
	 */
//Select by visible text
	public void handleDDUsing_selectByVisibleText(WebDriver driver, WebElement dropdown, String text) {
		Select s = new Select(dropdown);
		s.selectByVisibleText(text);
	}

	/**
	 * This is a reusable method for getting all the options from the dropdown
	 * 
	 * @param dropdown
	 * @return
	 */
//Get all options
	public List<WebElement> handleDDUsing_getAllOptions(WebDriver driver, WebElement dropdown) {
		Select s = new Select(dropdown);
		List<WebElement> opts = s.getOptions();
		return opts;
	}

	/**
	 * This is a reusable method for getting all selected the options
	 * 
	 * @param dropdown
	 * @return
	 */
//Get selected options
	public List<WebElement> handleDDUsing_getAllSelectedOptions(WebDriver driver, WebElement dropdown) {
		Select s = new Select(dropdown);
		List<WebElement> opts = s.getAllSelectedOptions();
		return opts;
	}

	/**
	 * This is a reusable method for getting first selected the options
	 * 
	 * @param dropdown
	 * @return
	 */

//Get first selected option
	public WebElement handleDDUsing_getFirstSelectedOption(WebDriver driver, WebElement dropdown) {
		Select s = new Select(dropdown);
		WebElement opt = s.getFirstSelectedOption();
		return opt;
	}

	/**
	 * This is a reusable method for checking weather the dropdown supports multiple
	 * options
	 * 
	 * @param dropdown
	 * @return
	 */
//Check whether multiple selection is allowed
	public boolean handleDDUsing_isMultiple(WebDriver driver, WebElement dropdown) {
		Select s = new Select(dropdown);
		return s.isMultiple();
	}

	/**
	 * This is a reusable method for deselecting an options by visible text
	 * 
	 * @param dropdown
	 * @param text
	 */
//Deselect by visible text
	public void handleDDUsing_deselectByVisibleText(WebDriver driver, WebElement dropdown, String text) {
		Select s = new Select(dropdown);
		s.deselectByVisibleText(text);
	}

	/**
	 * This is a reusable method for deselecting an options by visible value
	 * 
	 * @param dropdown
	 * @param value
	 */

//Deselect by value
	public void handleDDUsing_deselectByValue(WebDriver driver, WebElement dropdown, String value) {
		Select s = new Select(dropdown);
		s.deselectByValue(value);
	}

	/**
	 * This is a reusable method for deselecting an options by visible index
	 * 
	 * @param dropdown
	 * @param index
	 */

//Deselect by index
	public void handleDDUsing_deselectByIndex(WebDriver driver, WebElement dropdown, int index) {
		Select s = new Select(dropdown);
		s.deselectByIndex(index);
	}

	/**
	 * This is a reusable method for deselecting all options by visible elements
	 * 
	 * @param dropdown
	 */
//Deselect all
	public void handleDDUsing_deselectAll(WebDriver driver, WebElement dropdown) {
		Select s = new Select(dropdown);
		s.deselectAll();
	}

	/**
	 * This is a reusable method for
	 * 
	 * @param ele
	 */
	public void mouseHoverOnAnEle(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele).perform();
	}

	public void contextClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}

//Double click
	public void actionClass_doubleClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}

//Click and hold
	public void actionClass_clickAndHold(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.clickAndHold(element).perform();
	}

//Drag and drop
	public void actionClass_dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
		Actions act = new Actions(driver);
		act.dragAndDrop(source, target).perform();
	}

	public void scrollToElement(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.scrollToElement(element).perform();
	}

	public void scrollByAmount(WebDriver driver, int x, int y) {
		Actions act = new Actions(driver);
		act.scrollByAmount(x, y).perform();
	}

	public void switchToChildWindow_Title(WebDriver driver, String exptitle) {
		Set<String> wids = driver.getWindowHandles();

		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getTitle().contains(exptitle)) {
				break;
			}
		}
	}

	/**
	 * This is a reusable method for
	 * 
	 * @param expUrl
	 */
	public void switchToChildWindow_URL(WebDriver driver, String expUrl) {
		Set<String> wids = driver.getWindowHandles();

		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getTitle().contains(expUrl)) {
				break;
			}
		}
	}

	/**
	 * This is a reusable method for switching back to the parent window
	 * 
	 * @param pwid
	 */
	public void switchToParentWindow(WebDriver driver, String pwid) {
		driver.switchTo().window(pwid);
	}

}
