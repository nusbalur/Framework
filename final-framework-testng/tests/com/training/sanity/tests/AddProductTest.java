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
import com.training.pom.AddProductPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AddProductTest {

	private WebDriver driver;
	private String baseUrl;
	private AddProductPOM addProductPOM;
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
		addProductPOM = new AddProductPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		String userDir = System.getProperty("user.dir");
		System.out.println("AddProductTest.setUpBeforeClass::userDir:" + userDir);
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/AddProductExtentReport.html", true);
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

	// 
	@Test(priority = 0)
	public void searchHoverTest() throws InterruptedException {
		addProductPOM.mousehoversearch();
		Thread.sleep(1000);
	}
	// 
	@Test(priority = 1)
	public void checkout() throws InterruptedException {
		Thread.sleep(1000);
		addProductPOM.enterSearch("integer");
		screenShot.captureScreenShot("TC 32a");
		//Click on item
		addProductPOM.clickItem();
		logger.log(LogStatus.PASS, "Successfully clicked on Item");
        extent.endTest(logger);
        logger = extent.startTest("Checking Product Details");
		//Asserting to check if the product description page is displayed
		String expected1 = "Check delivery at your pincode";
		String actual1 = driver.findElement(By.xpath("//h4[contains(text(),'Check delivery at your pincode')]")).getText();
		assertEquals(actual1, expected1);
		
		//Add it to the cart
		screenShot.captureScreenShot("TC 32b");
		addProductPOM.addToCart();
		
		logger.log(LogStatus.PASS, "Successfully clicked on Add to Cart button");
        extent.endTest(logger);
		
		//Wait until the pop up for Shopping cart updated disappears and cart icon becomes visible
		WebDriverWait wait = new WebDriverWait(driver, 20);
 	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='http://retailm1.upskills.in/checkout/cart']")));
 	    
 	    //Mouse over the cart icon
 	    addProductPOM.mouseHoverCart();
 	    
 	    //Wait until the pop up with product details is visible
 	    WebDriverWait wait1 = new WebDriverWait(driver, 15);
	    wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='name']//a[contains(text(),'Integer vitae iaculis massa')]")));
	    
	    //Asserting the product details which is added to the cart
	    String expected2 = "Integer vitae iaculis massa";
		String actual2 = driver.findElement(By.xpath("//td[@class='name']//a[contains(text(),'Integer vitae iaculis massa')]")).getText();
		assertEquals(actual2, expected2);
		screenShot.captureScreenShot("TC 32d");
		addProductPOM.clickViewCart();
		logger.log(LogStatus.PASS, "Successfully clicked on View Cart button");
        extent.endTest(logger);
		//Assertion to check if product details page is displayed
		String expected3 = "Product Name";
		String actual3 = driver.findElement(By.xpath("//td[contains(text(),'Product Name')]")).getText();
		assertEquals(actual3, expected3);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight )");
		screenShot.captureScreenShot("TC 32e");
		addProductPOM.clickcheckout();
		logger.log(LogStatus.PASS, "Successfully clicked on Checkout button");
        extent.endTest(logger);
		//Assertion to check if login screen is displayed
		String expected4 = "Returning Customer";
		String actual4 = driver.findElement(By.xpath("//legend[contains(text(),'Returning Customer')]")).getText();
		assertEquals(actual4, expected4);
		screenShot.captureScreenShot("TC 32f");
		logger.log(LogStatus.PASS, "Displaying login screen successfully");
        extent.endTest(logger);
	}
}
