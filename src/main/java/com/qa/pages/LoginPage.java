package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.qa.BestTest;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage extends BestTest{
	@AndroidFindBy(accessibility = "test-Username") private WebElement usernameTxtFld;
	@AndroidFindBy(accessibility = "test-Password") private WebElement passwordTxtFld;
	@AndroidFindBy(accessibility = "test-LOGIN") private WebElement loginBtn;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]") private WebElement errTxt;
	
	public LoginPage() {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public LoginPage enterUserName(String username) {
		sendKeys(usernameTxtFld, username);
		return this;
	}
	public LoginPage enterPassword(String password) {
		sendKeys(passwordTxtFld, password);
		return this;
	}
	public ProductsPage pressLoginBtn() {
		click(loginBtn);
		return new ProductsPage();
	}
	public ProductsPage login(String username, String password) {
		enterUserName(username);
		enterPassword(password);
		click(loginBtn);
		return new ProductsPage();
	}
	public String getErr() {
		return getAttribute(errTxt, "text");
	}
}
