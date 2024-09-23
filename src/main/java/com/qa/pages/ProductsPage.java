package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.BestTest;
import com.qa.MenuPage;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage extends MenuPage{
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"PRODUCTS\"]")
	private WebElement productTitleTxt;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Item title\" and @text=\"Sauce Labs Backpack\"]")
	private WebElement SLBTitle;
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Price\" and @text=\"$29.99\"]")
	private WebElement SLBPrice;
	
	public String getTitle() {
		return getText(productTitleTxt);
	}
	public String getSBLTitle() {
		return getText(SLBTitle);
	}
	public String getSBLPrice() {
		return getText(SLBPrice);
	}
	
	public ProductDetailsPage pressSLBTitle() {
		click(SLBTitle);
		return new ProductDetailsPage();
	}
	
	public ProductsPage() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
}
