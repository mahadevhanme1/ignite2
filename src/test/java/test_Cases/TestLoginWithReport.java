package test_Cases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import factory.BrowserFactory;
import factory.DataProviderFactory;
import pages.LoginPage;

public class TestLoginWithReport {

	WebDriver d;
	public static ExtentReports report;
	ExtentTest logger;
	
	@BeforeMethod
	public void setup() throws IOException
	{
		report = new ExtentReports("./Reports/LoginPageReport.html", true);
		
		logger = report.startTest("Login Test");
		report.addSystemInfo("Host Name", "Mahadev");
		d = BrowserFactory.getBrowser("chrome");
		d.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		d.get(DataProviderFactory.getConfig().getApplicationUrl());
		logger.log(LogStatus.INFO, "Application is started running");
	}
	
	@Test
	public void verifyLogin()
	{
		LoginPage login = PageFactory.initElements(d, LoginPage.class);
		logger.assignCategory("Smoke");
		String title = login.getApplicationTitle();
		Assert.assertEquals(title, "Gutenberg Project", "title is not matching");
		logger.log(LogStatus.PASS,"Home Page is loaded and verified successfully");
		login.clickDramaButton();

		String title2 = login.getApplicationTitle();
		Assert.assertEquals(title2, "Drama", "title is not matching");
		logger.log(LogStatus.PASS,"Drama Page is loaded and verified successfully");
		login.clickBook();
		

		logger.log(LogStatus.PASS, "Logout link is verified successfully");  
	}
	
	@AfterMethod()
	public void cleanUp()
	{
		BrowserFactory.closeBrowser(d);
		report.endTest(logger);
		//report.flush();
	}
	
}
