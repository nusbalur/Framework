package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RegisterPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RegisterTest {

	private WebDriver driver;
	private String baseUrl;
	private RegisterPOM registerPOM;
	private Properties properties;
	private ScreenShot screenShot;

	@BeforeTest
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		registerPOM = new RegisterPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		System.out.println("Executing Before method");
	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		System.out.println("Executing After method");
		driver.quit();
	}

	@Test(priority = 0)
	public void newRegisterTest() throws InterruptedException {
		// Hover over Account icon
		registerPOM.mousehoveraccount();
		Thread.sleep(3000);
		// Click on login/register link and click on Register button
		registerPOM.clicklogin();
		registerPOM.clickregister();
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
		registerPOM.email("manzoormehadi59@gmail.com");
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
		System.out.println("Clicking continue button");
		registerPOM.continueButton();
		// Assertion to check whether the required page is successfully launched
		String expected = "Congratulations! Your new account has been successfully created!";
		String actual = driver
				.findElement(By.xpath("//p[contains(text(),'Congratulations! Your new account has been success')]"))
				.getText();
		assertEquals(expected, actual);
		screenShot.captureScreenShot("FirstD");
	}
}
