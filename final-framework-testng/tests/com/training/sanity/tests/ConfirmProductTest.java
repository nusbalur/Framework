package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

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

import com.training.generics.ScreenShot;
import com.training.pom.ConfirmProductPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ConfirmProductTest {

	private WebDriver driver;
	private String baseUrl;
	private ConfirmProductPOM confirmProductPOM;
	private Properties properties;
	private ScreenShot screenShot;

	@BeforeTest
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		confirmProductPOM = new ConfirmProductPOM(driver);
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

	// To click Login link
		@Test(priority = 0)
		public void clickLoginTest() throws InterruptedException {
			confirmProductPOM.mousehoveraccount();
			Thread.sleep(3000);
			confirmProductPOM.clicklogin();
		}

		// To submit with email and password
		@Test(priority = 1)
		public void LoginTest() throws InterruptedException {
			Thread.sleep(3000);
			confirmProductPOM.email("nusrat30@gmail.com");
			confirmProductPOM.password("12345678");
			screenShot.captureScreenShot("TC 33a");
			confirmProductPOM.loginButton();
		} 
	@Test(priority = 2)
	public void checkout() throws InterruptedException {
		confirmProductPOM.mousehoversearch();
		Thread.sleep(1000);
		confirmProductPOM.enterSearch("integer");
		screenShot.captureScreenShot("TC 33b");
		
		//To scroll the page down
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,450)");
		
		//Click on item
		//confirmProductPOM.mouseHoverItem();
		confirmProductPOM.clickItem();
		
		//Asserting to check if the product description page is displayed
		String expected1 = "Check delivery at your pincode";
		String actual1 = driver.findElement(By.xpath("//h4[contains(text(),'Check delivery at your pincode')]")).getText();
		assertEquals(actual1, expected1);
		
		//Add it to the cart
		confirmProductPOM.addToCart();
		
		//Wait until the pop up for Shopping cart updated disappears and cart icon becomes visible
		WebDriverWait wait = new WebDriverWait(driver, 15);
 	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='http://retailm1.upskills.in/checkout/cart']")));
 	    
 	    //Mouse over the cart icon
 	   confirmProductPOM.mouseHoverCart();
 	    
 	    //Wait until the pop up with product details is visible
 	    WebDriverWait wait1 = new WebDriverWait(driver, 15);
	    wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='name']//a[contains(text(),'Integer vitae iaculis massa')]")));
	    
	    //Asserting the product details which is added to the cart
	    screenShot.captureScreenShot("TC 33c");
	    String expected3 = "Integer vitae iaculis massa";
		String actual3 = driver.findElement(By.xpath("//td[@class='name']//a[contains(text(),'Integer vitae iaculis massa')]")).getText();
		assertEquals(actual3, expected3);
		confirmProductPOM.clickViewCart();
		
		//Assertion to check if product details page is displayed
		String expected4 = "Product Name";
		String actual4 = driver.findElement(By.xpath("//td[contains(text(),'Product Name')]")).getText();
		assertEquals(actual4, expected4);
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		screenShot.captureScreenShot("TC 33d");
		js.executeScript("window.scrollTo(0,document.body.scrollHeight )");
 	   confirmProductPOM.clickcheckout();
		
		confirmProductPOM.billingaddress("389");
		screenShot.captureScreenShot("TC 33e");
		confirmProductPOM.clickbillingcontinueBtn();
		confirmProductPOM.deliveryaddress("N b, 123, ba, Chandigarh, India");
		screenShot.captureScreenShot("TC 33f");
		confirmProductPOM.clickDelDetailsBtn();
		confirmProductPOM.shipping();
		confirmProductPOM.entercomments("This product is nice");
		screenShot.captureScreenShot("TC 33g");
		confirmProductPOM.clickDelMethodBtn();
		confirmProductPOM.termsandconditions();
		screenShot.captureScreenShot("TC 33h");
		confirmProductPOM.clickpaybtn();
		confirmProductPOM.clickconfirmorder();
	}
}
