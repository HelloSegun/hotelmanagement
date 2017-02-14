package com.segun.auto.testsuites;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.segun.auto.utilities.CaptureScreenshot;
import com.segun.auto.pageobjects.CreateEntryPage;
import com.segun.auto.pageobjects.HotelManagementLoginPag;
import com.segun.auto.pageobjectsmaps.AutoObjectMap;
import com.segun.auto.utilities.AutoBrowserDriver;

public class CreateAnEntry {
	
	String hotel_name;
	
	AutoObjectMap autoObjMap = new AutoObjectMap("autoobjectmap.properties");
	
	AutoBrowserDriver autosetup = new AutoBrowserDriver();
	BeforeAfterSuiteTestConfig setupDriver = new BeforeAfterSuiteTestConfig();
	CaptureScreenshot screenshot = new CaptureScreenshot();
	
	HotelManagementLoginPag logIn = new HotelManagementLoginPag();
	CreateEntryPage createEntry = new CreateEntryPage();
	
	
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
  public void createAnEntry() {
	  try {
		  System.out.println("\nin test 2: Creating an entry");
		  /*
		  SimpleDateFormat date_time = new SimpleDateFormat("yyyyMMddHHmmss");
		  Date date = new Date();
		  String time_stamp = date_time.format(date);
		  */
		  
		  Random rand = new Random();
		  String num = String.valueOf(rand.nextInt(50) + 1);
		  String num_p = String.valueOf(rand.nextInt(Integer.MAX_VALUE) + 1);
		  
		  this.hotel_name = "Hotel" + num;
		  String address = num + " James Street";
		  String owner = "Segun";
		  String phone_num = "0" + num_p;
		  String email = "segun@segun.com";
		  
		  By hotel_name_field = autoObjMap.getLocator("hotelmanagement.createentrypage.hotelname");
		  By address_field = autoObjMap.getLocator("hotelmanagement.createentrypage.address");
		  By owner_field = autoObjMap.getLocator("hotelmanagement.createentrypage.owner");
		  By phone_num_field = autoObjMap.getLocator("hotelmanagement.createentrypage.phonenumber");
		  By email_field = autoObjMap.getLocator("hotelmanagement.createentrypage.email");
		  By create_button = autoObjMap.getLocator("hotelmanagement.createentrypage.createbtn");
		  
		  
		  createEntry.enterText(autosetup.driver, hotel_name_field, hotel_name);
		  createEntry.enterText(autosetup.driver, address_field, address);
		  createEntry.enterText(autosetup.driver, owner_field, owner);
		  createEntry.enterText(autosetup.driver, phone_num_field, phone_num);
		  createEntry.enterText(autosetup.driver, email_field, email);
		  createEntry.clickButton(autosetup.driver, create_button);
		  //Thread.sleep(5000);
		  
		  
		  
	  } catch (Exception e) {
		  e.printStackTrace();
		  Assert.fail();
	  }
  }
  
  @Test (priority=2)
  public void confirmCreatedEntryPresent() {
	  try {
		  System.out.println("\nin test 3: Confirm hotel entry is created");
		  boolean confirm_entry = createEntry.confirmCreatedHotelEntry(autosetup.driver, "Hotel", hotel_name);
		  System.out.println("Boolean Create: " + confirm_entry);
		  if (confirm_entry == true) {
			  System.out.println(hotel_name + " entry confirmed present");
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
