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
import com.training.pom.LoginRetailPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class InvalidLoginTest {

	private WebDriver driver;
	private String baseUrl;
	private LoginRetailPOM loginRetailPOM;
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
		loginRetailPOM = new LoginRetailPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		String userDir = System.getProperty("user.dir");
		System.out.println("InvalidLoginTest.setUpBeforeClass::userDir:" + userDir);
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/InvalidLoginExtentReport.html", true);
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

	// To click Login link
	@Test(priority = 0)
	public void clickLoginTest() throws InterruptedException {
		loginRetailPOM.mousehoveraccount();
		Thread.sleep(3000);
		loginRetailPOM.clicklogin();
	}

	// To submit with email and password
	@Test(priority = 1)
	public void LoginTest() throws InterruptedException {
		Thread.sleep(3000);
		logger = extent.startTest("Opening the Login page");
		loginRetailPOM.email("nusrat30@gmail.com");
		loginRetailPOM.password("password");
		screenShot.captureScreenShot("TC 31a");
		loginRetailPOM.loginButton();
		screenShot.captureScreenShot("TC 31b");
		logger.log(LogStatus.PASS, "Successfully clicked on Login button");
        extent.endTest(logger);
		String expectedText = "Warning: No match for E-Mail Address and/or Password.";
		String actualText = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		assertEquals(expectedText, actualText);
		
	}
}
