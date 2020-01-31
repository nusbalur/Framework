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

	private WebDriver driver;
	private String baseUrl;
	private OrderPOM orderPOM;
	private Properties properties;
	private ScreenShot screenShot;

	@BeforeTest
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		orderPOM = new OrderPOM(driver);
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

	//To click Login link
	@Test(priority = 0)
	public void loginLinkTest() throws InterruptedException {
		orderPOM.mousehoveraccount();
		Thread.sleep(3000);
		orderPOM.clicklogin();
	}

	//To submit with email and password
	@Test(priority = 1)
	public void loginTest() throws InterruptedException {
		Thread.sleep(3000);
		orderPOM.email("nusrat30@gmail.com");
		orderPOM.password("12345678");
		screenShot.captureScreenShot("FourthA");
		orderPOM.loginButton();
		screenShot.captureScreenShot("FourthB");
	}

	//To hover over Account icon and click My Orders link
	@Test(priority = 2)
	public void ordersTest() throws InterruptedException {
		Thread.sleep(3000);
		orderPOM.mousehoveraccount();
		Thread.sleep(1000);
		screenShot.captureScreenShot("FourthC");
		orderPOM.ordersLink();
		// Viewing the items in Orders list and assertion the text displayed
		String expectedText = "Order ID";
		String actualText = driver.findElement(By.xpath("//td[contains(text(),'Order ID')]")).getText();
		assertEquals(expectedText, actualText);
		screenShot.captureScreenShot("FourthD");
		// Click on View
		orderPOM.view();
		// Assertion of text displayed under Order Details
		String expected = "Order Details";
		String actual = driver.findElement(By.xpath("//td[contains(text(),'Order Details')]")).getText();
		assertEquals(expected, actual);
		screenShot.captureScreenShot("FourthE");
	}
}
