package com.appium.test.Android;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class T8_MyFirstNativeTest extends T6_DriverUtilityToStartStopDriver{
	// Note
	// we are extending T6_DriverUtilityToStartStopDriver in our main class

	// ***************** PreConditions
	// APK is already installed
	// Connect android device via USB with adb enabled
	// Install appium and node (node >8 , appium = 1.8 version)
	// Update your node and appium main.js path
	// Open "uiautomatorviewer.bat in Android SDK folder like
	// "Android_SDKNew\\tools\\uiautomatorviewer.bat"
	// Select your device and locate properties for the application

	// ******************** Locator Strategy *****************
	// resource-id --> MobileBy.id
	// content-desc--> MobileBy.AccessibilityId
	// class --> MobileBy.className
	// text --> MobileBy.AndroidUIAutomator("new UiSelector().text(\"text\")") ,
	// will discuss this in more detail in future

	public static void main(String[] args) {
		driver=getDriver();
		
		// our test code starts from here
		MobileElement username = driver.findElement(MobileBy.AccessibilityId("username"));
		username.clear();
		username.sendKeys("admin");
		
		MobileElement password = driver.findElement(MobileBy.AccessibilityId("password"));
		password.clear();
		password.sendKeys("admin");
		
		MobileElement login = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"LOG IN\")"));
		login.click();
		
		stopDriver();
	}
}
