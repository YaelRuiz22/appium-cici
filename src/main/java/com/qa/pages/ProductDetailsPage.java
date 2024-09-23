package com.qa.pages;

import org.openqa.selenium.WebElement;

import com.qa.MenuPage;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductDetailsPage extends MenuPage{
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"test-Description\"]/android.widget.TextView[1]")
	private WebElement SLBTitle;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\\\"test-Description\\\"]/android.widget.TextView[2]")
	private WebElement SLBText;
	
	@AndroidFindBy(accessibility = "test-BACK TO PRODUCTS") private WebElement backToProductsBtn;
	
	public String getSBLTitle() {
		return getText(SLBTitle);
	}
	public String getSBLText() {
		return getText(SLBText);
	}
	
	public ProductsPage pressBackToProductsBtn() {
		click(backToProductsBtn);
		return new ProductsPage();
	}
}
