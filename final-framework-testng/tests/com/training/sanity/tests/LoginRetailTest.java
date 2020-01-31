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
import com.training.pom.LoginRetailPOM;
import com.training.pom.RegisterPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class LoginRetailTest {

	private static WebDriver driver;
	private static String baseUrl;
	private static LoginRetailPOM loginRetailPOM;
	private static Properties properties;
	private static ScreenShot screenShot;

	@BeforeTest
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginRetailPOM = new LoginRetailPOM(driver);
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

	//Method to click Login link
	@Test(priority=0)
	public void loginTest() throws InterruptedException{
		loginRetailPOM.mousehoveraccount();
	Thread.sleep(3000);
	loginRetailPOM.clicklogin();
	screenShot.captureScreenShot("SecondA");
	}
	
	//Method to submit with email and password
	@Test(priority=1)
	public void LoginTest() throws InterruptedException {
		Thread.sleep(3000);
		loginRetailPOM.email("myself@gmail.com");
		loginRetailPOM.password("12345678");
		loginRetailPOM.loginButton();
		screenShot.captureScreenShot("SecondB");
		String expectedText = "My Account";
		String actualText = driver.findElement(By.xpath("//h2[contains(text(),'My Account')]")).getText();
		assertEquals(expectedText, actualText);
	}
	}

