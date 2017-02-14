package com.segun.auto.pageobjects;

import org.openqa.selenium.WebDriver;

import com.segun.auto.utilities.AutoBase;
import com.segun.auto.utilities.AutoBrowserDriver;

public class CreateEntryPage {

	AutoBrowserDriver autosetup = new AutoBrowserDriver();
	AutoBase autoBaseSetup = new AutoBase();
	
	String page_validator_text = "Logout";
	
	
	public String validateGroupPageName(WebDriver driver, Object page_validator) throws Exception {
		System.out.println("Driver session :" + driver);
		System.out.println("Group name header element: " + page_validator);
		return autoBaseSetup.compareElementTextNotIgnoringCase(driver, page_validator, page_validator_text);
	}
	
	public void clickButton(WebDriver driver, Object btn_element) throws Exception {
		System.out.println("Driver session :" + driver);
		System.out.println("button element: " + btn_element);
		autoBaseSetup.clickAButtonOrElementALocateCoordinates(driver, btn_element);
	}
	
	public void enterText(WebDriver driver, Object text_element, String text) throws Exception {
		System.out.println("Driver session :" + driver);
		System.out.println("Text element: " + text_element);
		System.out.println("Text : " + text);
		autoBaseSetup.sendKeysToTextField(driver, text_element, text);
	}
	
	public void clickDeleteButton(WebDriver driver, Object delete_btn_element) throws Exception {
		System.out.println("Driver session :" + driver);
		System.out.println("Delete button element: " + delete_btn_element);
		autoBaseSetup.clickAButtonOrElement(driver, delete_btn_element);
	}
	
	public boolean confirmCreatedHotelEntry(WebDriver driver, String characteristics, String hotel_entry) throws Exception {
		System.out.println("Driver session page:" + driver);
		System.out.println("Hotel entry list element page : " + hotel_entry);
		autoBaseSetup.readValidateFromList(driver, hotel_entry);
		return autoBaseSetup.getTextPresent();
	}
	
	public String validateNameOfHotelToDelete(WebDriver driver, Object delete_btn) throws Exception {
		System.out.println("Driver session:" + driver);
		System.out.println("Delete button element: " + delete_btn);
		return autoBaseSetup.confirmNameFromList(driver, delete_btn);
	}
	
}
