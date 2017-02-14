package com.segun.auto.pageobjects;

import org.openqa.selenium.WebDriver;

import com.segun.auto.utilities.AutoBase;
import com.segun.auto.utilities.AutoBrowserDriver;

public class HotelManagementLoginPag {

	AutoBrowserDriver autosetup = new AutoBrowserDriver();
	AutoBase autoBaseSetup = new AutoBase();
	
	
	public void validateLoginPage(WebDriver driver, String browser_title) throws Exception {
		System.out.println("Driver session :" + driver);
		
	}
	
	public void clickLoginInButton(WebDriver driver, Object loginin_btn_element) throws Exception {
		System.out.println("Driver session :" + driver);
		System.out.println("Login button element: " + loginin_btn_element);
		autoBaseSetup.clickAButtonOrElement(driver, loginin_btn_element);
	}
	
	public void enterUsername(WebDriver driver, Object username_element, String username) throws Exception {
		System.out.println("Driver session :" + driver);
		System.out.println("Username element: " + username_element);
		System.out.println("Username : " + username);
		autoBaseSetup.sendKeysToTextField(driver, username_element, username);
		/**
		WebElement textbox = autoBaseSetup.getElementWhenVisible(driver, username_element, 20);
		((WebElement) textbox).sendKeys(username);
		String userName = ((WebElement) textbox).getAttribute("value");
		System.out.println("Entered username: " + userName);
		*/
	}
	
	public void enterPassword(WebDriver driver, Object password_element, String password) throws Exception {
		System.out.println("Driver session :" + driver);
		System.out.println("Password element: " + password_element);
		System.out.println("Password : " + password);
		autoBaseSetup.sendKeysToTextField(driver, password_element, password);
	}
	
	
}
