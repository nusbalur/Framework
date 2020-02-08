package com.training.pom;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class GuestUserPOM {
	private WebDriver driver;

	public GuestUserPOM(WebDriver driver) {
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
	
	@FindBy(xpath="//input[@name='account' and @value='guest']")
	private WebElement guestRadioButton;
	
	@FindBy(id="button-account")
	private WebElement guestContinue;
	
	@FindBy(name="firstname")
	private WebElement firstName;
	
	@FindBy(name="lastname")
	private WebElement lastName;
	
	@FindBy(id="input-payment-email")
	private WebElement email;
	
	@FindBy(name= "telephone")
	private WebElement telephone;
	
	@FindBy(name = "address_1")
	private WebElement address1;

	@FindBy(name = "address_2")
	private WebElement address2;

	@FindBy(name = "city")
	private WebElement city;

	@FindBy(id = "input-payment-postcode")
	private WebElement postCode;

	@FindBy(id = "input-payment-country")
	private WebElement country;

	@FindBy(id = "input-payment-zone")
	private WebElement state;
	
	@FindBy(name = "shipping_address")
	private WebElement shippingCheckBox;
	
	@FindBy(id = "button-guest")
	private WebElement btnGuest;
	
	@FindBy(name = "shipping_method")
	private WebElement shippingRadioButton;
	
	@FindBy(xpath = "//textarea[@name='comment']")
	private WebElement comments;
	
	@FindBy(id = "button-shipping-method")
	private WebElement shippingbtn;
	
	@FindBy(name = "agree")
	private WebElement agreeckbox;
	
	@FindBy(id = "button-payment-method")
	private WebElement paymentbtn;
	
	@FindBy(id = "button-confirm")
	private WebElement confirmbtn;
	
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
	
	//Checking if the Radio button is displayed, enabled and selected by default
		public void selectGuestCheckout() {
			boolean radiobuttonIsDisplayed = guestRadioButton.isDisplayed();
			boolean radiobuttonIsEnabled = guestRadioButton.isEnabled();
			boolean radiobuttonIsSelected = guestRadioButton.isSelected();

			if (radiobuttonIsDisplayed) {
				System.out.println("Radio button for Guest Checkout is displayed");

				if (radiobuttonIsEnabled) {
					System.out.println("Radio button for Guest Checkout is enabled");

					if (!radiobuttonIsSelected) {
						guestRadioButton.click();
						System.out.println("Guest Checkout was not selected by default. But now its selected");
					} else {
						System.out.println("Guest Checkout radio button is already selected");
					}
				}
			}
		}
		public void clickguestContinue() {
			this.guestContinue.click();		
		}
		
		public void firstName(String firstName) {
			this.firstName.clear();
			this.firstName.sendKeys(firstName);
		}

		public void lastName(String lastName) {
			this.lastName.clear();
			this.lastName.sendKeys(lastName);
		}

		public void email(String email) {
			this.email.clear();
			this.email.sendKeys(email);
		}

		public void telephone(String telephone) {
			this.telephone.clear();
			this.telephone.sendKeys(telephone);
		}

		public void address1(String address1) {
			this.address1.clear();
			this.address1.sendKeys(address1);
		}

		public void address2(String address2) {
			this.address2.clear();
			this.address2.sendKeys(address2);
		}

		public void city(String city) {
			this.city.clear();
			this.city.sendKeys(city);
		}

		public void postCode(String postCode) {
			this.postCode.clear();
			this.postCode.sendKeys(postCode);
		}

		public void country(String countryName) {
			this.country.click();
			Select selFromCountry = new Select(country);
			selFromCountry.selectByVisibleText(countryName);
		}

		public void state(String stateName) {
			this.state.click();
			Select selFromState = new Select(state);
			selFromState.selectByVisibleText(stateName);
		}
		
		//Checking if the Checkbox is displayed, enabled and selected by default
		public void selectCheckbox() {
			boolean ckIsChkd = shippingCheckBox.isSelected();
			boolean ckIsEnabled = shippingCheckBox.isEnabled();
			boolean ckIsDisplayed = shippingCheckBox.isDisplayed();

			if (ckIsDisplayed) {
				System.out.println("Checkbox is displayed");
				if (ckIsEnabled) {
					System.out.println("Checkbox is enabled");
					if (!ckIsChkd) {
						shippingCheckBox.click();
						System.out.println("Checkbox was not checked by default. Now it has been checked");
					}
					else 
						System.out.println("Checkbox is already selected");
				}
			}
		}
		
		public void clickbtnGuest() {
			this.btnGuest.click();		
		}


		//Checking if the Radio button is displayed, enabled and selected by default
			public void freeshipping() {
				boolean radiobuttonIsDisplayed = shippingRadioButton.isDisplayed();
				boolean radiobuttonIsEnabled = shippingRadioButton.isEnabled();
				boolean radiobuttonIsSelected = shippingRadioButton.isSelected();

				if (radiobuttonIsDisplayed) {
					System.out.println("Radio button for Free Shipping is displayed");

					if (radiobuttonIsEnabled) {
						System.out.println("Radio button for  Free Shipping is enabled");

						if (!radiobuttonIsSelected) {
							shippingRadioButton.click();
							System.out.println(" Free Shipping was not selected by default. But now its selected");
						} else {
							System.out.println(" Free Shipping radio button is already selected");
						}
					}
				}
			}

			public void entercomments(String comments) {
				this.comments.click();
				this.comments.sendKeys(comments);
				
			}
			public void shippingbtn() {
				this.shippingbtn.click();
			}
			
			//Checking if the Checkbox is displayed, enabled and selected by default
			public void termsandconditions() {
				boolean ckIsChkd = agreeckbox.isSelected();
				boolean ckIsEnabled = agreeckbox.isEnabled();
				boolean ckIsDisplayed = agreeckbox.isDisplayed();

				if (ckIsDisplayed) {
					System.out.println("Checkbox is displayed");
					if (ckIsEnabled) {
						System.out.println("Checkbox is enabled");
						if (!ckIsChkd) {
							agreeckbox.click();
							System.out.println("Checkbox was not checked by default. Now it has been checked");
						}
						else 
							System.out.println("Checkbox is already selected");
					}
				}
			}
			public void paymentbtn() {
				this.paymentbtn.click();
			}

			public void confirmbtn() {
				this.confirmbtn.click();
				
			}
}

