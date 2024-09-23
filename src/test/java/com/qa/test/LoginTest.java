package com.qa.test;

import org.testng.annotations.Test;

import com.qa.BestTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;

import io.appium.java_client.AppiumBy;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class LoginTest extends BestTest{
	LoginPage loginPage;
	ProductsPage productsPage;
	InputStream datais;
	JSONObject loginUsers;
	
	@BeforeClass
	public void beforeClass() throws IOException { 
		try {
			String dataFileName = "data/loginUsers.json";
			datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
			JSONTokener	 tokener = new JSONTokener(datais);
			loginUsers = new JSONObject(tokener);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if(datais != null) {
				datais.close();
			}
		}
		
		
	}
	@AfterClass
	public void afterClass() {
	}
	@BeforeMethod
	public void beforeMethod(Method m) { 
		System.out.println("loginTest before method");
		loginPage = new LoginPage();
		System.out.println("\n *************** starting test:" + m.getName() + "**********\n");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("loginTest after method");
	}
	@Test
    public void invalidUsername() {
		
		loginPage.enterUserName(loginUsers.getJSONObject("invalidUser").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("invalidUser").getString("password"));
		loginPage.pressLoginBtn();
    	
    	String actualerror = loginPage.getErr() + "fdf";
    	String expectedError = strings.get("err_invalid_username_or_password");
    	System.out.println(actualerror);
    	
    	assertEquals(actualerror, expectedError);
		
    }
    
    @Test
    public void invalidPassword() {
		loginPage.enterUserName(loginUsers.getJSONObject("invalidPassword").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("invalidPassword").getString("password"));
		loginPage.pressLoginBtn();
    	
    	String actualerror = loginPage.getErr();
    	String expectedError = strings.get("err_invalid_username_or_password");
    	System.out.println(actualerror);
    	
    	assertEquals(actualerror, expectedError);
    }
    
    @Test
    public void successfulLogin() {
		loginPage.enterUserName(loginUsers.getJSONObject("validUser").getString("username"));
		loginPage.enterPassword(loginUsers.getJSONObject("validUser").getString("password"));
		productsPage = loginPage.pressLoginBtn();
    	
    	String actualTitle = productsPage.getTitle();
    	String expectedTitle = strings.get("product_title");
    	System.out.println(actualTitle);
    	assertEquals(actualTitle, expectedTitle);
    }
}