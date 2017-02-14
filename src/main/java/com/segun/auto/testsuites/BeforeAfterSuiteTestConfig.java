package com.segun.auto.testsuites;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import com.segun.auto.utilities.CaptureScreenshot;
import com.segun.auto.utilities.AutoBrowserDriver;

public class BeforeAfterSuiteTestConfig {
	AutoBrowserDriver setup = new AutoBrowserDriver();
	WebDriver beforeTestDriver;
	
	CaptureScreenshot screenshot = new CaptureScreenshot();
	
	
	@BeforeSuite
	public void beforeSuite() {
		try {
			System.out.println("beforeSuite");
			screenshot.deleteAllFailedMethodScreenshot();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	@AfterSuite
	public void afterSuite() {
		try {
			System.out.println("afterSuite");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
	

}
