package com.segun.auto.utilities;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class AutoBrowserDriver {
	
	public WebDriver driver;
	String baseUrl;
	

	public void browser(String browser) throws Exception {
	
			System.out.println("****************************");
			System.out.println("Launching Chromedriver");
			String driverRelativePath = "";
			String chrome = "";
			if (SystemUtils.IS_OS_MAC_OSX) {
				driverRelativePath = "mac32";
				chrome = "/chromedriver";
			}
			else if (SystemUtils.IS_OS_WINDOWS) {
				driverRelativePath = "win32";
				chrome = "/chromedriver.exe";
			}
			else if (SystemUtils.IS_OS_LINUX) {
				driverRelativePath = "linux64";
				chrome = "/chromedriver";
			}

			if (browser.equals("chrome"))
			{
				//driver = new ChromeDriver();
				File file = new File("browser_drivers/" + driverRelativePath + chrome);
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				System.setProperty("webdriver.chrome.logfile", "browser_drivers/log/chromedriver.log");
				driver = new ChromeDriver();
				System.out.println("Browser used is Chrome");
			}
			else if (browser.equals("firefox"))
			{
				driver = new FirefoxDriver();
				System.out.println("Browser used is FireFox");

			} 
			//This will never work in Mac OSX and LINUX
			else if (browser.equals("internet explorer"))
			{
				File file = new File("browser_drivers//" + driverRelativePath + "/IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver();
				System.out.println("Browser used is IE");
			}
			//This not advisable to use in Windows OS and LINUX
			else if (browser.equals("safari"))
			{
				driver = new SafariDriver();
				System.out.println("Browser used is Safari");
			}
			else
			{
				System.out.println("Enter a browser");
			}
		
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
			driver.manage().window().maximize();;
	}
	
	public void goTo(String url) {
		try {
			baseUrl = url;
			driver.navigate().to(baseUrl);
			System.out.println("Web portal is: " + baseUrl);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void clearBrowserCache() {
		try {
			driver.manage().deleteAllCookies();
			System.out.println("Cleared browser cache");
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void shutDownBrowser() {
		try {
			driver.quit();
			System.out.println("Shutdown browser");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeBrowser() {
		try {
			driver.close();;
			System.out.println("Browser closed");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
