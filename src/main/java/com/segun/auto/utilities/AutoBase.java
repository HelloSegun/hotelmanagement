package com.segun.auto.utilities;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AutoBase {

	String element_text_update;
	boolean element_text_present;
	
	//Method for an expected condition of waiting for an element to be visible before getting it
	public WebElement getElementWhenVisible(WebDriver driver, Object locator, int timeout) throws Exception {
		System.out.println("Browser session: " + driver);
		System.out.println("Locator element: " + locator);
		System.out.println("Timeout: " + timeout);
	    WebElement element = null;
	    WebDriverWait wait = new WebDriverWait(driver, timeout);
	    element = wait.until(ExpectedConditions.visibilityOfElementLocated((By) locator));
	    return element;
	}
	
	/*
	 * This section is for click button of element methods
	 */
	//Click a button or element interaction by locating coordinates before hand *****************************
	public void clickAButtonOrElementALocateCoordinates(WebDriver driver, Object button_element) throws Exception {
		System.out.println("Browser session: " + driver);
		System.out.println("Button element: " + button_element);
		WebElement save_button_element = driver.findElement((By) button_element);
		
		Point element_location = this.getElementWhenVisible(driver, button_element, 20).getLocation();
		int element_x_point = element_location.getX() * 3;
		int element_y_point = element_location.getY() * 3;
		System.out.println("Save button element: " +  save_button_element);
		System.out.println("Save button element coordinates are: (" + element_x_point + ", " + element_y_point + ")" );
		System.out.println("Text name of button element: " +  save_button_element.getText());
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scroll(" + element_x_point + ", " + element_y_point + ");");
		executor.executeScript("arguments[0].click();", save_button_element);
	}
	
	
	//Click a button or element interaction **************************************
	public void clickAButtonOrElement(WebDriver driver, Object element_to_click) throws Exception {
		System.out.println("Browser session: " + driver);
		System.out.println("Element to click: " + element_to_click);
		WebElement locator_to_click = driver.findElement((By) element_to_click);
		locator_to_click.click();
	}
	
	
	
	
	
	//Method for Implicitly wait timeout
	public void implicitlyWait(WebDriver driver, int timeout) throws Exception {
		System.out.println("Browser session: " + driver);
		System.out.println("Wait timeout: " + timeout);
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	
	
	/*
	 * Section with methods pertaining to text field elements
	 */
	//Method to send keys to a text field ***************************
	public void sendKeysToTextField(WebDriver driver, Object field_element, String key_text) throws Exception {
		System.out.println("Browser session: " + driver);
		System.out.println("Field element :" + field_element);
		System.out.println("Key text :" + key_text);
		WebElement field_textbox = getElementWhenVisible(driver, field_element, 20);
		((WebElement) field_textbox).sendKeys(key_text);
		String field_text = ((WebElement) field_textbox).getAttribute("value");
		System.out.println("Entered string: " + field_text);
	}
	
	//Validate that a string is present in a text field *********************************
	public void validateStringPresentInTextField(WebDriver driver, Object field_element, String text_to_validate) throws Exception {
		System.out.println("Browser session: " + driver);
		System.out.println("Field element :" + field_element);
		System.out.println("Text to validate :" + text_to_validate);
		WebElement field_textbox = getElementWhenVisible(driver, field_element, 20);
		String text_present_in_textbox = field_textbox.getAttribute("value");
		if (text_present_in_textbox.equals(text_to_validate)) {
			System.out.println("\033[32;1;2m" + text_present_in_textbox + " validated to be present in the text box\033[0m");
		}
	}
	//End of section***************************************************************

	
	//Validate page header menu title ****************************************
	public void validateHeaderPage(WebDriver driver, Object header_name, String get_page_header, String page_header) throws Exception {
		System.out.println(page_header + " name element: " + header_name);
		implicitlyWait(driver, 10);
		WebElement locateheader = driver.findElement((By) header_name);
		String getpageheader = locateheader.getText();
		System.out.println(getpageheader + " page header: " + getpageheader);
		System.out.println("Expected page header: " + page_header);
		if (getpageheader.equals(page_header)) {
			System.out.println("Re-directs to: " + page_header);
		} else {
			System.out.println("Exception thrown when trying to re-direct to: " + page_header);
			Assert.fail();
		}
	}


	/*
	 * This section is for methods that compare element text string
	 */
	//Method to compare two element text not ignoring case **********************************************
	public String compareElementTextNotIgnoringCase(WebDriver driver, Object first_element, Object second_element) throws Exception {
		System.out.println("Browser session: " + driver);
		System.out.println("First element: " + first_element);
		System.out.println("Second element: " + second_element);
		WebElement first_element_locator = driver.findElement((By) first_element);
		WebElement second_element_locator = driver.findElement((By) second_element);
		String first_text = first_element_locator.getText();
		String second_text = second_element_locator.getText();
		
		if(first_text.equals(second_text)) {
			return first_text;
		} else {
			return "no match";
		}
	}
	
	//Method to compare two element text ignoring case ******************************************************
	public String compareElementTextIgnoringCase(WebDriver driver, Object first_element, Object second_element) throws Exception {
		System.out.println("Browser session: " + driver);
		System.out.println("First element: " + first_element);
		System.out.println("Second element: " + second_element);
		WebElement first_element_locator = driver.findElement((By) first_element);
		WebElement second_element_locator = driver.findElement((By) second_element);
		String first_text = first_element_locator.getText();
		String second_text = second_element_locator.getText();
		
		if(first_text.equalsIgnoreCase(second_text)) {
			return first_text;
		} else {
			return "no match";
		}
	}
	//End of section***************************************************************

	
	public void readValidateFromList(WebDriver driver, String text_from_list) throws Exception {
		System.out.println("Browser session base: " + driver);
		System.out.println("Text from the list base: " + text_from_list);
		for(int i = 2; i >= 2; i++) {
			Thread.sleep(5000); //Bad practice however works for this one
			By list_element = By.xpath("html/body/div/div["+ i +"]/div[1]/div[1]/p");
			System.out.println("Xpath element: " + list_element);
			WebElement list_ele = this.getElementWhenVisible(driver, list_element, 60);
			String element_text = list_ele.getText();
			System.out.println(element_text + " element_text 1");
			System.out.println(text_from_list + " text_from_list 1");
			if(element_text.equals(text_from_list)) {
				this.element_text_present = true;
				break;
			} else {
				this.element_text_present = false;
				break;
			}
		}

	}
	
	
	public boolean getTextPresent() throws Exception{
		boolean text_present = element_text_present;
		return text_present;
	}
	
	
	public String confirmNameFromList(WebDriver driver, Object button_element) throws Exception {
		System.out.println("Browser session base2: " + driver);
		System.out.println("Text from the list base2: " + button_element);
		
		String element_to_string = new String(button_element.toString());
		Matcher matcher = Pattern.compile("\\d+").matcher(element_to_string);
		matcher.find();
		int i = Integer.valueOf(matcher.group());
		System.out.println("Matcher base2: " + i);
		implicitlyWait(driver, 60);
		By list_element = By.xpath("html/body/div/div["+ i +"]/div[1]/div[1]/p");
		WebElement list_ele = this.getElementWhenVisible(driver, list_element, 60);
		String element_text = list_ele.getText();
		System.out.println("Element Text base2: " + element_text);
		return element_text;
	}
	
	public WebElement getElementWhenReady(WebDriver driver, Object locator, int timeout) throws Exception {
		System.out.println("Browser session: " + driver);
		System.out.println("Locator element: " + locator);
		System.out.println("Timeout: " + timeout);
	    WebElement element = null;
	    WebDriverWait wait = new WebDriverWait(driver, timeout);
	    element = wait.until(ExpectedConditions.presenceOfElementLocated((By) locator));
	    return element;
	}
	
}
