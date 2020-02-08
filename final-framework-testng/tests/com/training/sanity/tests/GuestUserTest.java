package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.GuestUserPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class GuestUserTest {

	private WebDriver driver;
	private String baseUrl;
	private GuestUserPOM guestUserPOM;
	private Properties properties;
	private ScreenShot screenShot;

	@BeforeTest
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		guestUserPOM = new GuestUserPOM(driver);
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

	//
	@Test(priority = 0)
	public void searchHoverTest() throws InterruptedException {
		guestUserPOM.mousehoversearch();
		Thread.sleep(1000);
	}

	//
	@Test(priority = 1)
	public void checkout() throws InterruptedException {
		Thread.sleep(1000);
		guestUserPOM.enterSearch("integer");
		screenShot.captureScreenShot("TC 34a");
		String expected = "Products meeting the search criteria";
		String actual = driver.findElement(By.xpath("//h2[contains(text(),'Products meeting the search criteria')]"))
				.getText();
		assertEquals(actual, expected);
		System.out.println("Item found");
		screenShot.captureScreenShot("TC 34b");

		// To scroll the page down
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		 */

		// Click on item
		guestUserPOM.clickItem();

		// Asserting to check if the product description page is displayed
		String expected1 = "Check delivery at your pincode";
		String actual1 = driver.findElement(By.xpath("//h4[contains(text(),'Check delivery at your pincode')]"))
				.getText();
		assertEquals(actual1, expected1);
		screenShot.captureScreenShot("TC 34c");
		// Add it to the cart
		guestUserPOM.addToCart();
		screenShot.captureScreenShot("TC 34d");

		// Wait until the pop up for Shopping cart updated disappears and cart icon
		// becomes visible
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//a[@href='http://retailm1.upskills.in/checkout/cart']")));

		// Mouse over the cart icon
		guestUserPOM.mouseHoverCart();

		// Wait until the pop up with product details is visible
		WebDriverWait wait1 = new WebDriverWait(driver, 15);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//td[@class='name']//a[contains(text(),'Integer vitae iaculis massa')]")));
		screenShot.captureScreenShot("TC 34e");
		// Asserting the product details which is added to the cart
		String expected2 = "Integer vitae iaculis massa";
		String actual2 = driver
				.findElement(By.xpath("//td[@class='name']//a[contains(text(),'Integer vitae iaculis massa')]"))
				.getText();
		assertEquals(actual2, expected2);
		guestUserPOM.clickViewCart();

		// Assertion to check if product details page is displayed
		String expected3 = "Product Name";
		String actual3 = driver.findElement(By.xpath("//td[contains(text(),'Product Name')]")).getText();
		assertEquals(actual3, expected3);
		screenShot.captureScreenShot("TC 34f");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight )");
		guestUserPOM.clickcheckout();

		// Assertion to check if login screen is displayed
		// Assertion to check if login screen is displayed
		String expected4 = "Returning Customer";
		String actual4 = driver.findElement(By.xpath("//legend[contains(text(),'Returning Customer')]")).getText();
		assertEquals(actual4, expected4);
		// Selecting Guest Checkout radio button and clicking continue
		guestUserPOM.selectGuestCheckout();
		screenShot.captureScreenShot("TC 34g");
		guestUserPOM.clickguestContinue();
	}

	@Test(priority = 2)
	public void enterDetails() throws InterruptedException {
		guestUserPOM.firstName("manzoor");
		guestUserPOM.lastName("mehadi");
		guestUserPOM.email("manzoormehadi63@gmail.com");
		guestUserPOM.telephone("9876543210");
		guestUserPOM.address1("yeshwanthapur");
		guestUserPOM.address2("bangalore");
		guestUserPOM.city("bangalore");
		guestUserPOM.postCode("560022");
		screenShot.captureScreenShot("TC 34h");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,450)");
		guestUserPOM.country("India");
		guestUserPOM.state("Karnataka");
		guestUserPOM.selectCheckbox();
		screenShot.captureScreenShot("TC 34i");
		guestUserPOM.clickbtnGuest();
		guestUserPOM.freeshipping();
		guestUserPOM.entercomments("This product is nice");
		screenShot.captureScreenShot("TC 34j");
		guestUserPOM.shippingbtn();
		guestUserPOM.termsandconditions();
		screenShot.captureScreenShot("TC 34k");
		guestUserPOM.paymentbtn();
		// Assertion to check if product details screen is displayed
		/*String expected5 = "Product Name";
		String actual5 = driver.findElement(By.xpath("//td[contains(text(),'Product Name')]")).getText();
		assertEquals(actual5, expected5);*/
		guestUserPOM.confirmbtn();
		screenShot.captureScreenShot("TC 34l");
		String expected6 = "Your order has been successfully processed!";
		String actual6 = driver
				.findElement(By.xpath("//p[contains(text(),'Your order has been successfully processed!')]")).getText();
		assertEquals(actual6, expected6);
	}
}
