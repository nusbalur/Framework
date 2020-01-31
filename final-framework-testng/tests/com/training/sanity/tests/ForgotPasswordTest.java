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
import com.training.pom.RegisterPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ForgotPasswordTest {

	private WebDriver driver;
	private String baseUrl;
	private ForgotPasswordPOM forgotPasswordPOM;
	private Properties properties;
	private ScreenShot screenShot;

	@BeforeTest
	public void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		forgotPasswordPOM = new ForgotPasswordPOM(driver);
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

	//To click Login link
	@Test(priority = 0)
	public void loginLinkTest() throws InterruptedException {
		forgotPasswordPOM.mousehoveraccount();
		Thread.sleep(3000);
		forgotPasswordPOM.clicklogin();
		screenShot.captureScreenShot("ThirdA");
	}

	//To submit with email and password
	@Test(priority = 1)
	public void loginTest() throws InterruptedException {
		Thread.sleep(3000);
		forgotPasswordPOM.email("nusrat30@gmail.com");
		forgotPasswordPOM.password("manzoor");
		forgotPasswordPOM.loginButton();
		screenShot.captureScreenShot("ThirdB");
		forgotPasswordPOM.forgotPasswordLink();
		String expectedText = "Enter the e-mail address associated with your account. Click submit to have a password reset link e-mailed to you.";
		String actualText = driver
				.findElement(By.xpath("//p[contains(text(),'Enter the e-mail address associated with your acco')]"))
				.getText();
		assertEquals(expectedText, actualText);
	}

	//To enter registered email ID and continue
	@Test(priority = 2)
	public void enterRegisteredEmail() throws InterruptedException {
		Thread.sleep(3000);
		forgotPasswordPOM.email("nusrat30@gmail.com");
		screenShot.captureScreenShot("ThirdC");
		forgotPasswordPOM.continueButton();
		screenShot.captureScreenShot("ThirdD");
		String expectedText = "An email with a confirmation link has been sent your email address.";
		String actualText = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		assertEquals(expectedText, actualText);
	}
}
