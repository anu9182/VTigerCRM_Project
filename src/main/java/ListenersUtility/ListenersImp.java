package ListenersUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import UtilityClassObj.UtilitiesClassObject;

public class ListenersImp implements ISuiteListener, ITestListener {
	public ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {

		Reporter.log("Configuring Report", true);
		String timestamp = new Date().toString().replace(":", "_").replace(" ", "_");

		// Configure report
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReports/vtigerReports" + timestamp + ".html");
		spark.config().setDocumentTitle("Vtiger CRM_Contact&OrgTest");
		spark.config().setReportName("CRM VTIGER");
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Browser", "Chrome-154");
		report.setSystemInfo("OS Version", "Window-11");
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String timestamp = new Date().toString().replace(" ", "_").replace(":", "_");
		test = report.createTest(testname + timestamp);
		UtilitiesClassObject.setTest(test);
		UtilitiesClassObject.getTest().log(Status.PASS, "Test Execution Started" + testname + timestamp);
		Reporter.log(testname + "Test Execution started", true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String timestamp = new Date().toString().replace(":", "_").replace(" ", "_");
		UtilitiesClassObject.getTest().log(Status.PASS, "Test Execution Success" + testname + timestamp);
		Reporter.log(testname + "Test Execution success", true);

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String timestamp = new Date().toString().replace(":", "_").replace(" ", "_");
		Reporter.log(testname + "Test Execution Failed - Screenshot", true);
		UtilitiesClassObject.getTest().log(Status.FAIL, testname + timestamp + " Test Execution failed");
		TakesScreenshot ts = (TakesScreenshot) UtilitiesClassObject.getDriver();
		String src = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(src, testname + timestamp + "screenshot.ong");

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String timestamp = new Date().toString().replace(":", "_").replace(" ", "_");
		Reporter.log(testname + "Test Execution skipped", true);
		UtilitiesClassObject.getTest().log(Status.SKIP, testname + timestamp + "Test Execution file");
	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("Report Backup", true);
		report.flush();

//		UtilitiesClassObject.getTest().log(Status.INFO, "Test Execution Finisged");
		report.flush();

	}
}
