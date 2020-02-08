package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPOM {
	private WebDriver driver;

	public AdminPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "input-username")
	private WebElement userName;

	@FindBy(id = "input-password")
	private WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginBtn;

	@FindBy(xpath = "//ul[@id='menu']/li[3]/a")
	private WebElement catalog;

	@FindBy(xpath = "//a[contains(text(),'Products')]")
	private WebElement products;

	@FindBy(xpath = "//div[@class='pull-right']//a[@class='btn btn-primary']")
	private WebElement addIcon;

	@FindBy(id = "input-name1")
	private WebElement productName;

	@FindBy(id = "input-meta-title1")
	private WebElement metaTag;

	@FindBy(linkText = "Data")
	private WebElement dataTab;

	@FindBy(id = "input-model")
	private WebElement model;

	@FindBy(id = "input-price")
	private WebElement price;

	@FindBy(id = "input-quantity")
	private WebElement quantity;

	@FindBy(linkText = "Links")
	private WebElement linksTab;

	@FindBy(id = "input-category")
	private WebElement category;

	@FindBy(linkText = "Discount")
	private WebElement discountTab;

	@FindBy(xpath = "//table[@id='discount']//button[@class='btn btn-primary']")
	private WebElement addDiscountIcon;

	@FindBy(name = "product_discount[0][quantity]")
	private WebElement quantity1;

	@FindBy(name = "product_discount[0][price]")
	private WebElement price1;

	@FindBy(name = "product_discount[0][date_start]")
	private WebElement startDate;

	@FindBy(name = "product_discount[0][date_end]")
	private WebElement endDate;

	@FindBy(linkText = "Reward Points")
	private WebElement rewardPointsTab;

	@FindBy(id = "input-points")
	private WebElement points;

	@FindBy(xpath = "//div[@class='pull-right']//button[@class='btn btn-primary']")
	private WebElement saveIcon;

	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}

	public void sendPassword(String password) {
		this.password.clear();
		this.password.sendKeys(password);
	}

	public void clickLoginBtn() {
		this.loginBtn.click();
	}

	public void clickcatalog() {
		this.catalog.click();
	}

	public void clickproducts() {
		this.products.click();
	}

	public void clickAddIcon() {
		this.addIcon.click();
	}

	public void enterProductName(String productName) {
		this.productName.clear();
		this.productName.sendKeys(productName);
	}

	public void enterMetaTag(String metaTag) {
		this.metaTag.clear();
		this.metaTag.sendKeys(metaTag);
	}

	public void clickDataTab() {
		this.dataTab.click();
	}

	public void enterModel(String model) {
		this.model.clear();
		this.model.sendKeys(model);
	}

	public void enterPrice(String price) {
		this.price.clear();
		this.price.sendKeys(price);

	}

	public void enterQuantity(String quantity) {
		this.quantity.clear();
		this.quantity.sendKeys(quantity);
	}

	public void clickLinksTab() {
		this.linksTab.click();
	}

	public void enterCategory(String category) {
		this.category.clear();
		this.category.sendKeys(category);

	}

	public void clickDiscountTab() {
		this.discountTab.click();
	}


	public void clickAddDiscount() {
		this.addDiscountIcon.click();
	}
	
	public void enterQuantity1(String quantity1) {
		this.quantity1.clear();
		this.quantity1.sendKeys(quantity1);
	}

	public void enterPrice1(String price1) {
		this.price1.clear();
		this.price1.sendKeys(price1);
	}
	public void enterStartDate(String startDate) {
		this.startDate.clear();
		this.startDate.sendKeys(startDate);
	}

	public void enterEndDate(String endDate) {
		this.endDate.clear();
		this.endDate.sendKeys(endDate);
	}

	public void clickRewardPoints() {
		this.rewardPointsTab.click();
	}

	public void enterPoints(String points) {
		this.points.clear();
		this.points.sendKeys(points);
	}

	public void saveIcon() {
		this.saveIcon.click();
	}

}
