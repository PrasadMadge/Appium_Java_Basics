package com.appium.test.Android;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class T3_LaunchingAndroidAppThroughAPK {

	// Note
	/*Please connect your laptop or PC to a internet connection for this test or you may face the below bug in appium
	https://github.com/mozilla/web-ext/issues/1083
*/	
	//***************** PreConditions
	// Install VodQA app from APK folder in your android phone
	// Connect android device via USB with adb enabled
	// Install appium and node (node >8 , appium = 1.8 version)
	// Update your node and appium main.js path

	static String nodePath = "C:\\Program Files (x86)\\nodejs\\node.exe";
	static String appiumMainJsPath = "C:\\Users\\PrasadMadge\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
	static int portNo = 4723;
	static AndroidDriver driver = null;

	public static void main(String[] args) {

		AppiumDriverLocalService service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder().usingDriverExecutable(new File(nodePath))
						.withAppiumJS(new File(appiumMainJsPath)).withIPAddress("0.0.0.0").usingPort(portNo));

		System.err.println("Appium server Starting");
		service.start();
		System.err.println("Appium server started");

		// Setting up capabilties to create AndroidDriver object
		// connect androd device and check get UDID of the device through adb
		// devices
		// DesiredCapabilities is one way of saying appium server which type of
		// automation
		// you are interested in

		DesiredCapabilities caps = new DesiredCapabilities();
		// minimum mandatory capabilites required for android
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "MotoG5");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		caps.setCapability(MobileCapabilityType.NO_RESET, true);
		caps.setCapability(MobileCapabilityType.UDID, "ZY32287RPN");

		// capabilities for apk file install and launch
		caps.setCapability(MobileCapabilityType.APP, "APK/VodQA.apk");

		try {
			// App will launch if everything goes fine
			driver = new AndroidDriver<MobileElement>(service, caps);

			Thread.sleep(5000);
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
