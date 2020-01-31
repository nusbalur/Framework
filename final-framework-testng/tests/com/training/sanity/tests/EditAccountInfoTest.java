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
import com.training.pom.EditAccountInfoPOM;
import com.training.pom.ForgotPasswordPOM;
import com.training.pom.LoginPOM;
import com.training.pom.LoginRetailPOM;
import com.training.pom.OrderPOM;
import com.training.pom.RegisterPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class EditAccountInfoTest {

	private WebDriver driver;
	private String baseUrl;
	private EditAccountInfoPOM editAccountInfoPOM;
	private Properties properties;
	private ScreenShot screenShot;

	@BeforeTest
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		editAccountInfoPOM = new EditAccountInfoPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		System.out.println("Executing before method");
	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		System.out.println("Executing after method");
		driver.quit();
	}

	// To click Login link
	@Test(priority = 0)
	public void loginLinkTest() throws InterruptedException {
		editAccountInfoPOM.mousehoveraccount();
		Thread.sleep(3000);
		editAccountInfoPOM.clicklogin();
	}

	// To submit with email and password
	@Test(priority = 1)
	public void loginTest() throws InterruptedException {
		Thread.sleep(3000);
		editAccountInfoPOM.email("manzoor28@gmail.com");
		editAccountInfoPOM.password("12345678");
		editAccountInfoPOM.loginButton();
		screenShot.captureScreenShot("FifthA");
	}

	//To hover over Account icon and click My Orders link
	@Test(priority = 2)
	public void editInfoTest() throws InterruptedException {
		editAccountInfoPOM.editLink();
		Thread.sleep(1000);
		screenShot.captureScreenShot("FifthB");
		editAccountInfoPOM.firstName("manzoor");
		editAccountInfoPOM.lastName("mehdi");
		editAccountInfoPOM.newEmail("manzoor29@gmail.com");
		editAccountInfoPOM.telephone("9845098459");
		screenShot.captureScreenShot("FifthC");
		editAccountInfoPOM.continueButton();
		String expected = "Success: Your account has been successfully updated.";
		String actual = driver.findElement(By.xpath("//div[contains(text(),'Success')]")).getText();
		assertEquals(expected, actual);
		screenShot.captureScreenShot("FifthD");
	}
}
