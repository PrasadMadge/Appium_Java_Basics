package com.appium.test.Android.Web;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class T2_CustomizingChromeDriver {

	
	//***************** PreConditions
	// Chrome browser needs to be installed
	// Connect android device via USB with adb enabled
	// Install appium and node (node >8 , appium = 1.8 version)
	// Update your node and appium main.js path

	static String nodePath = "C:\\Program Files (x86)\\nodejs\\node.exe";
	static String appiumMainJsPath = "C:\\Users\\PrasadMadge\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
	static int portNo = 4723;
	static AndroidDriver<MobileElement> driver = null;

	public static void main(String[] args) {

		// Note
		
		/*
		 * If may happen that your chrome browser version is not matching with the chrome driver in appium default path
		 * double click chromedriver.exe and you will come to know its version 
		 * Path : C:\Users\USER\AppData\Roaming\npm\node_modules\appium\node_modules\appium-chromedriver\chromedriver\win\chromedriver.exe
		 * 
		 * Hence there are 2 solutions to it
		 * 1 : downgrade your chrome browser version matching with chromedriver
		 * 2 : update chromedriver in default path on appium ,
		 * Path : C:\Users\USER\AppData\Roaming\npm\node_modules\appium\node_modules\appium-chromedriver\chromedriver\win\chromedriver.exe
		 * 
		 * But both the solutions are not good , why?
		 * what if you are doing parallel execution , with different chrome browser versions.
		 * What if one android phone is getting chrome browser update till specific version
		 * 
		 * You need to point different chromedriver to according to chrome browser installed on your phone
		 * Appium allows us to do this with one additional capability mentioned below
		 * chromedriverExecutable --> path to new chromedriver.exe
		 * 
		 * More Info : http://appium.io/docs/en/writing-running-appium/web/chromedriver/
		
		 * 
		*/
		
		
		AppiumDriverLocalService service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File(nodePath))
						.withAppiumJS(new File(appiumMainJsPath)).withIPAddress("0.0.0.0").usingPort(portNo));

		System.err.println("Appium server Starting");
		service.start();
		System.err.println("Appium server started");

		DesiredCapabilities caps = new DesiredCapabilities();
		// minimum mandatory capabilites required for android
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "MotoG5");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		caps.setCapability(MobileCapabilityType.NO_RESET, true);
		caps.setCapability(MobileCapabilityType.UDID, "ZY32287RPN");

		// capabilities for web
		caps.setCapability(AndroidMobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
		
		caps.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, "D:\\DriversMobile\\chromedriver37\\chromedriver.exe");
		try {
			// App will launch if everything goes fine
			driver = new AndroidDriver<MobileElement>(service, caps);

			driver.get("https://www.google.com");
			
			driver.findElement(By.name("q")).sendKeys("adb");
			driver.findElement(By.xpath("//button[@aria-label='Google Search']")).click();
			
			
			
			
		} catch (Exception e) {

			System.err.println("Error :" + e.getMessage());

		} finally {
			if (driver != null)
				driver.quit();
			System.err.println("App closed");
		}
		service.stop();
		System.err.println("Appium server stopped");
	}

}
