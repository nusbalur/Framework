package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.AdminPOM;
import com.training.pom.RegisterPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AdminTest {

	private WebDriver driver;
	private String adminUrl;
	private AdminPOM adminPOM;
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
		adminPOM = new AdminPOM(driver);
		adminUrl = properties.getProperty("adminURL");
		screenShot = new ScreenShot(driver);
		String userDir = System.getProperty("user.dir");
		System.out.println("RegisterTest.setUpBeforeClass::userDir:" + userDir);
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/AdminTestExtentReport.html", true);
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		logger = extent.startTest("Launching Application URL in Browser");

		// open the browser
		driver.get(adminUrl);
		System.out.println("Executing Before method");
		String title = driver.getTitle();
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
	public void LoginTest() throws InterruptedException {
		logger = extent.startTest("Opening the login page");
		logger = extent.startTest("Enter login Info");
		adminPOM.sendUserName("admin");
		adminPOM.sendPassword("admin@123");
		screenShot.captureScreenShot("TC35a");
		adminPOM.clickLoginBtn();
		logger.log(LogStatus.PASS, "Successfully logged in");
		extent.endTest(logger);
	}
	@Test(priority = 1)
	public void AddNewItem() throws InterruptedException {
		adminPOM.clickcatalog();
		adminPOM.clickproducts();
		screenShot.captureScreenShot("TC35b");
		adminPOM.clickAddIcon();
		logger = extent.startTest("Entering details in General tab");
		adminPOM.enterProductName("Finger Ring");
		adminPOM.enterMetaTag("Finger Ring for ladies");
		screenShot.captureScreenShot("TC35c");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-500)");
		adminPOM.clickDataTab();
		logger = extent.startTest("Entering details in Data tab");
		adminPOM.enterModel("SKU-012");
		js.executeScript("window.scrollBy(0,400)");
		adminPOM.enterPrice("500");
		adminPOM.enterQuantity("50");
		screenShot.captureScreenShot("TC35d");
		js.executeScript("window.scrollBy(0,-500)");
		adminPOM.clickLinksTab();
		logger = extent.startTest("Entering details in Links tab");
		adminPOM.enterCategory("Earrings");
		screenShot.captureScreenShot("TC35e");
		adminPOM.clickDiscountTab();
		logger = extent.startTest("Entering details in Discount tab");
		adminPOM.clickAddDiscount();
		adminPOM.enterQuantity1("1");
		adminPOM.enterPrice1("50");
		adminPOM.enterStartDate("07-02-2020");
		adminPOM.enterEndDate("08-02-2020");
		screenShot.captureScreenShot("TC35f");
		adminPOM.clickRewardPoints();
		logger = extent.startTest("Entering details in Reward Points tab");
		adminPOM.enterPoints("20");
		screenShot.captureScreenShot("TC35g");
		adminPOM.saveIcon();
		screenShot.captureScreenShot("TC35h");
		logger.log(LogStatus.PASS, "Successfully entered all details");
		extent.endTest(logger);
		String expected = "Success: You have modified products!\r\n";
		String actual = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		assertEquals(actual, expected);
	}
}
