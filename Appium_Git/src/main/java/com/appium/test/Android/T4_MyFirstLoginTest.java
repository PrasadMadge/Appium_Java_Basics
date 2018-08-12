package com.appium.test.Android;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class T4_MyFirstLoginTest {

	
	//***************** PreConditions
	// APK is already installed
	// Connect android device via USB with adb enabled
	// Install appium and node (node >8 , appium = 1.8 version)
	// Update your node and appium main.js path

	static String nodePath = "C:\\Program Files (x86)\\nodejs\\node.exe";
	static String appiumMainJsPath = "C:\\Users\\PrasadMadge\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
	static int portNo = 4723;
	static AndroidDriver<MobileElement> driver = null;

	public static void main(String[] args) {

		// Note
		
		/*To find object locators use UIautomatorViewer tool
		This tool is present in Android SDK setup , check below path for example
		D:\Android_SDKNew\tools\UIautomatorviewer.bat
		Open this bat file and select your UDID to inspect elements
		
		Tips
		If automating and Native,Hybrid app always use MobileBy object
		If automating and Web app always use By object
		
		content-desc --> MobileBy.AccessibilityId("")
		resource-id  --> MobileBy.id("")
		text         --> MobileBy.AndroidUIAutomator("new UiSelector().text(\"TEXT\")")
		
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

		// capabilities for apk
		caps.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.vodqareactnative");
		caps.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.vodqareactnative.MainActivity");

		try {
			// App will launch if everything goes fine
			driver = new AndroidDriver<MobileElement>(service, caps);

			MobileElement username = driver.findElement(MobileBy.AccessibilityId("username"));
			username.clear();
			username.sendKeys("admin");
			
			MobileElement password = driver.findElement(MobileBy.AccessibilityId("password"));
			password.clear();
			password.sendKeys("admin");
			
			MobileElement login = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"LOG IN\")"));
			login.click();
			
			
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
