package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.RegisterPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RegisterTest {

	private WebDriver driver;
	private String baseUrl;
	private RegisterPOM registerPOM;
	private Properties properties;
	private ScreenShot screenShot;
	ExtentReports extent;
    ExtentTest logger;


	@BeforeTest
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		registerPOM = new RegisterPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		String userDir = System.getProperty("user.dir");
		System.out.println("RegisterTest.setUpBeforeClass::userDir:" + userDir);
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/RegistrationExtentReport.html", true);
	    extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	    logger = extent.startTest("Launching Application URL in Browser");

		// open the browser
		driver.get(baseUrl);
		System.out.println("Executing Before method");
		String title=driver.getTitle();
        logger.log(LogStatus.PASS, title);
        extent.endTest(logger);

	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		System.out.println("Executing After method");
		extent.flush();
		extent.close();
		driver.quit();
	}

	@Test(priority = 0)
	public void newRegisterTest() throws InterruptedException {
		logger = extent.startTest("Opening the Registration page");
		// Hover over Account icon
		registerPOM.mousehoveraccount();
		Thread.sleep(3000);
		// Click on login/register link and click on Register button
		registerPOM.clicklogin();
		registerPOM.clickregister();
		logger.log(LogStatus.PASS, "Successfully clicked on Register button");
        extent.endTest(logger);
		
        logger = extent.startTest("Enter Registration Info");

	}

	@Test(priority = 1)
	public void validRegistration() throws InterruptedException {
		Thread.sleep(3000);
		// Assertion to check whether the Registration page has successfully launched
		String expectedText = "If you already have an account with us, please login at the login page.";
		String actualText = driver
				.findElement(By.xpath("//p[contains(text(),'If you already')]"))
				.getText();
		assertEquals(expectedText, actualText);
		// Entering values in the fields for Registration
		registerPOM.firstName("manzoor");
		registerPOM.lastName("mehadi");
		registerPOM.email("manzoormehadi63@gmail.com");
		registerPOM.telephone("9876543210");
		screenShot.captureScreenShot("FirstA");
		registerPOM.address1("yeshwanthapur");
		registerPOM.address2("bangalore");
		registerPOM.city("bangalore");
		registerPOM.postCode("560022");
		registerPOM.country("India");
		registerPOM.state("Karnataka");
		screenShot.captureScreenShot("FirstB");
		registerPOM.password("manzoor1");
		registerPOM.confirmPassword("manzoor1");
		System.out.println("Selection of subscribe button");
		registerPOM.selectSubscribe();
		System.out.println("Checking the privacy check box");
		registerPOM.selectPrivacy();
		screenShot.captureScreenShot("FirstC");
		logger.log(LogStatus.PASS, "Successfully Entered all the details");
        extent.endTest(logger);
		System.out.println("Clicking continue button");
		registerPOM.continueButton();
		// Assertion to check whether the required page is successfully launched
		String expected = "Congratulations! Your new account has been successfully created!";
		String actual = driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! Your new account has been success')]"))
				.getText();
		assertEquals(expected, actual);
		logger.log(LogStatus.PASS, "Successfully registered new account");
        extent.endTest(logger);
		screenShot.captureScreenShot("FirstD");
	}
}
