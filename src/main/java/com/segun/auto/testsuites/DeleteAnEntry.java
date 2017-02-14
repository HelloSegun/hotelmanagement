package com.segun.auto.testsuites;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.segun.auto.pageobjects.CreateEntryPage;
import com.segun.auto.pageobjects.HotelManagementLoginPag;
import com.segun.auto.pageobjectsmaps.AutoObjectMap;
import com.segun.auto.utilities.AutoBrowserDriver;
import com.segun.auto.utilities.CaptureScreenshot;

public class DeleteAnEntry {
	
	String hotel_entry_to_delete;
	
	AutoObjectMap autoObjMap = new AutoObjectMap("autoobjectmap.properties");
	
	AutoBrowserDriver autosetup = new AutoBrowserDriver();
	BeforeAfterSuiteTestConfig setupDriver = new BeforeAfterSuiteTestConfig();
	CaptureScreenshot screenshot = new CaptureScreenshot();
	
	HotelManagementLoginPag logIn = new HotelManagementLoginPag();
	CreateEntryPage createEntry = new CreateEntryPage();
	
	/*
	  @Test
	  public void f() {
		  try {
			  
		  } catch (Exception e) {
			  e.printStackTrace();
			  Assert.fail();
		  }
	  }
	  */
  
  @AfterMethod
  public void takeScreenshot(ITestResult result) throws IOException {
	  try {
		  screenshot.failedResultMethod(autosetup.driver, result);
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
  }

  @BeforeTest
  @Parameters ({"browser", "url"})
  public void beforeTest(String browser, String url) {
	  try {
		  System.out.println("in beforeTest");
		  autosetup.browser(browser);
		  autosetup.clearBrowserCache();
		  autosetup.goTo(url);
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
  }

  @AfterTest
  public void afterTest() {
	  try {
		  System.out.println("in AfterTest");
		  autosetup.shutDownBrowser();
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
  }
  
  
  @Test (priority=0)
  @Parameters ({"sUsername", "sPassword"})
  public void loginToHotelManagement(String sUsername, String sPassword) {
	  try {	  
		  System.out.println("\nin test 1: Logging in");
		  By first_login_btn = autoObjMap.getLocator("hotelmanagement.loginpage.login");
		  By user_name = autoObjMap.getLocator("hotelmanagement.loginpage.username");
		  By pass_word = autoObjMap.getLocator("hotelmanagement.loginpage.password");
		  By login_btn = autoObjMap.getLocator("hotelmanagement.loginpage.loginbutton");
		  String page_title = autosetup.driver.getTitle();
		  logIn.validateLoginPage(autosetup.driver, page_title);
		  logIn.clickLoginInButton(autosetup.driver, first_login_btn);
		  logIn.enterUsername(autosetup.driver, user_name, sUsername);
		  logIn.enterPassword(autosetup.driver, pass_word, sPassword);
		  logIn.clickLoginInButton(autosetup.driver, login_btn);
		  
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
  }


  @Test (priority=1)
  public void deleteAnEntry() {
	  try {
		  System.out.println("\nin test 2: Deleting an entry");
		  By hotel_entry_delete_btn = autoObjMap.getLocator("hotelmanagement.createentrypage.entrytodelete");
		  this.hotel_entry_to_delete = createEntry.validateNameOfHotelToDelete(autosetup.driver, hotel_entry_delete_btn);
		  createEntry.clickDeleteButton(autosetup.driver, hotel_entry_delete_btn);
  
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
  }
  
  @Test (priority=2)
  public void confirmDeletedEntryNotPresent() {
	  try {
		  System.out.println("\nin test 3: Confirm hotel entry is deleted");
		  System.out.println("TESTTEST" + hotel_entry_to_delete);
		  boolean confirm_entry = createEntry.confirmCreatedHotelEntry(autosetup.driver, "Hotel", hotel_entry_to_delete);
		  System.out.println("Boolean Create: " + confirm_entry);
		  if (confirm_entry == false) {
			  System.out.println(hotel_entry_to_delete + " entry confirmed deleted");
		  } else {
			  System.out.println(hotel_entry_to_delete + " entry not deleted");
			  Assert.fail();
		  }
		  
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
  }
  
  @Test (priority=3)
  public void loggingOutOfHotelManagement() {
	  try {
		  System.out.println("\nin test 4: Logging out");
		  By logout_btn = autoObjMap.getLocator("hotelmanagement.createentrypage.logoutbutton");
		  createEntry.clickButton(autosetup.driver, logout_btn);
		  
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
  }

}
