package com.qa;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.screenrecording.CanRecordScreen;

public class BestTest {
	protected static AppiumDriver driver;
	protected static Properties props;
	protected static HashMap<String, String> strings = new HashMap<String, String>();
	protected static String dateTime;
	InputStream inputStream;
	InputStream stringis;
	TestUtils utils;
	static org.apache.logging.log4j.Logger log = LogManager.getLogger(BestTest.class.getName());
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("super before method");
		((CanRecordScreen) driver).startRecordingScreen();
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		System.out.println("super after method");
		String media = ((CanRecordScreen) driver).stopRecordingScreen();
		
		if(result.getStatus() != 2) {
			return;
		}
		
		Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
		
		String dir = "videos" + File.separator + params.get("udid") + "_" + params.get("avd") + "_"
				+ File.separator + dateTime + File.separator + result.getTestClass().getRealClass().getSimpleName();
		
		File videoDir = new File(System.getProperty("user.dir") + File.separator + dir);
		System.out.println(videoDir.toString());
		if(!videoDir.exists()) {
			videoDir.mkdirs();
			System.out.println("ruta creada");
		}
		
		try {
			FileOutputStream stream = new FileOutputStream(videoDir + File.separator + result.getName() + ".mp4");
			stream.write(Base64.decodeBase64(media));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
		
	@Parameters({"udid", "avd"})
	@BeforeTest
	public void beforeTest(String udid, String avd) throws Exception {
		utils = new TestUtils();
		dateTime = utils.getDateTime();
		log.info("this is info message");
		log.error("this is error message");
		log.debug("this is debug message");
		log.warn("this is warning message");
		try {
			props = new Properties();
			String propFileName = "config.properties";
			String xmlFileName = "strings/strings.xml";
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			props.load(inputStream);
			
			stringis = getClass().getClassLoader().getResourceAsStream(xmlFileName);
			strings = utils.parseStringXML(stringis);
			
			URL appUrl = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
			UiAutomator2Options options = new UiAutomator2Options()
					.setUdid(udid)
					.setAvd(avd)
					.setAvdLaunchTimeout(Duration.ofSeconds(180))
					.setAppPackage(props.getProperty("androidAppPackage"))
					.setAppActivity(props.getProperty("androidAppActiviy"))
					.setAppActivity(props.getProperty("androidAppActiviy"))
					.setApp(appUrl.getPath().substring(1))
					;
			
			URL url = new URL(props.getProperty("appiumURL"));
			AppiumDriver driver = new AndroidDriver(url, options);
			this.driver = driver;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(inputStream != null) {
				inputStream.close();
			}
			if(stringis != null) {
				stringis.close();
			}
		}
	}
	
	public AppiumDriver getDriver() {
		return driver;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	
	public void waitForVisibility(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	public void click(WebElement e) {
		waitForVisibility(e);
		e.click();
	}
	
	public void sendKeys(WebElement e, String txt) {
		waitForVisibility(e);
		e.sendKeys(txt);
	}
	
	public String getAttribute(WebElement e, String attribute) {
		waitForVisibility(e);
		return e.getAttribute(attribute);
	}
	
	public String getText(WebElement e) {
		waitForVisibility(e);
		return e.getText();
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
