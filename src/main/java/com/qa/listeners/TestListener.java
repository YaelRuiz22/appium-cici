package com.qa.listeners;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.google.common.io.Files;
import com.qa.BestTest;

import io.appium.java_client.InteractsWithApps;

public class TestListener implements ITestListener{
	
	@Override
	public void onTestFailure(ITestResult result) {
		if(result.getThrowable() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
			System.out.println(sw.toString());
		}
		
		BestTest base = new BestTest();
		File file = base.getDriver().getScreenshotAs(OutputType.FILE);
		Map<String, String> params = result.getTestContext().getCurrentXmlTest().getAllParameters();
		String exePath="src\\main\\resources";
		File exeFile=new File(".",exePath);
		System.out.println(exeFile.getAbsolutePath());
		String imagePath = "Screenshots" + File.separator + params.get("udid") + "_" + params.get("avd")
			+ File.separator + base.getDateTime() + File.separator 
			+ result.getTestClass().getRealClass().getSimpleName() + File.separator + result.getName() + ".png";
		
		String completeImagePath = System.getProperty("user.dir") + File.separator + imagePath;
		try {
			FileUtils.copyFile(file, new File(imagePath));
			Reporter.log("This is the sample screenshot");
			Reporter.log("<a href='" + completeImagePath + "'> <img src='" + completeImagePath + "' height='100' + width='100'/></a>" );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
}
