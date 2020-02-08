package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddProductPOM {
	private WebDriver driver;

	public AddProductPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@id='search_button']")
	private WebElement search;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchBox;
	
	@FindBy(xpath="//a[contains(text(),'Integer vitae iaculis massa')]")
	private WebElement actualText;
	
	@FindBy(id="button-cart")
	private WebElement addToCart;
	
	@FindBy(id="cart")
	private WebElement cartIcon;
	
	@FindBy(xpath="//a[contains(text(),'View Cart')]")
	private WebElement viewCart;
	
	@FindBy(xpath="//a[@class='btn btn-primary']")
	private WebElement checkout;
	
	// To mouse hover the search icon
	public void mousehoversearch() {
		
		Actions actions = new Actions(driver);
		actions.moveToElement(search).perform();
	}

	public void enterSearch(String searchText) {
		Actions act = new Actions(driver);
		this.searchBox.sendKeys(searchText);
		System.out.println("Search text - is entered:" + searchText);
		act.sendKeys(Keys.ENTER).build().perform();
		String expected = "Products meeting the search criteria";
		String actual = driver.findElement(By.xpath("//h2[contains(text(),'Products meeting the search criteria')]")).getText();
		assertEquals(actual, expected);
		System.out.println("Item found");
	}

	public void clickItem() {
		this.actualText.click(); 
		}

	public void addToCart() {
		this.addToCart.click(); 
		String expected2 = "Check delivery at your pincode";
		//WebElement actual2 = driver.findElement(By.xpath("//div[@class='noty_text_body')]"));
		//if(actual2!=null)
		//System.out.println("Text is" + actual2.getText());
		//assertEquals(actual2, expected2);
		}
	
	public void mouseHoverCart() {
		Actions actions = new Actions(driver);
		actions.moveToElement(cartIcon).perform();
	}

	public void clickViewCart() {
		this.viewCart.click();
	}

	public void clickcheckout() {
		this.checkout.click();		
	}
	
}
