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

	private WebDriver driver;
	private String baseUrl;
	private LoginRetailPOM loginRetailPOM;
	private Properties properties;
	private ScreenShot screenShot;

	@BeforeTest
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginRetailPOM = new LoginRetailPOM(driver);
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
		loginRetailPOM.mousehoveraccount();
		Thread.sleep(3000);
		loginRetailPOM.clicklogin();
	}

	// To submit with email and password
	@Test(priority = 1)
	public void LoginTest() throws InterruptedException {
		Thread.sleep(3000);
		loginRetailPOM.email("nusrat30@gmail.com");
		loginRetailPOM.password("12345678");
		screenShot.captureScreenShot("SecondA");
		loginRetailPOM.loginButton();
		screenShot.captureScreenShot("SecondB");
		String expectedText = "My Account";
		String actualText = driver.findElement(By.xpath("//h2[contains(text(),'My Account')]")).getText();
		assertEquals(expectedText, actualText);
	}
}
