package com.qa.pages;

import org.openqa.selenium.WebElement;

import com.qa.BestTest;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class SettingsPage extends BestTest{
	@AndroidFindBy(accessibility = "test-LOGOUT")
	private WebElement logoutBtn;
	
	public LoginPage pressLogoutBtn() {
		click(logoutBtn);
		return new LoginPage();
	}
}
