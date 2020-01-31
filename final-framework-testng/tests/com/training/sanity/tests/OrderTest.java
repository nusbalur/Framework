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
import com.training.pom.ForgotPasswordPOM;
import com.training.pom.LoginPOM;
import com.training.pom.LoginRetailPOM;
import com.training.pom.OrderPOM;
import com.training.pom.RegisterPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class OrderTest {

	private static WebDriver driver;
	private static String baseUrl;
	private static OrderPOM orderPOM;
	private static Properties properties;
	private static ScreenShot screenShot;

	@BeforeTest
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		orderPOM = new OrderPOM(driver);
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

	// Method to click Login link
	@Test(priority = 0)
	public void loginLinkTest() throws InterruptedException {
		orderPOM.mousehoveraccount();
		Thread.sleep(3000);
		orderPOM.clicklogin();
	}

	// Method to submit with email and password
	@Test(priority = 1)
	public void loginTest() throws InterruptedException {
		Thread.sleep(3000);
		orderPOM.email("myself@gmail.com");
		orderPOM.password("12345678");
		screenShot.captureScreenShot("FourthA");
		orderPOM.loginButton();
		screenShot.captureScreenShot("FourthB");
	}

	// Method to hover over Account icon and click My Orders link
	@Test(priority = 2)
	public void ordersTest() throws InterruptedException {
		Thread.sleep(3000);
		orderPOM.mousehoveraccount();
		Thread.sleep(1000);
		screenShot.captureScreenShot("FourthC");
		orderPOM.ordersLink();
		String expectedText = "You have not made any previous orders!";
		String actualText = driver.findElement(By.xpath("//p[@class='tb_empty']")).getText();
		assertEquals(expectedText, actualText);
		screenShot.captureScreenShot("FourthD");
	}
}
