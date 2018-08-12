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

public class T6_DriverUtilityToStartStopDriver {

	//Note 
	// we will be using this utility for our next examples
	// it will start appium server set caps for our applicaiton under test
	// It will reduce verbose in our main test script
	

	static String nodePath = "C:\\Program Files (x86)\\nodejs\\node.exe";
	static String appiumMainJsPath = "C:\\Users\\PrasadMadge\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
	static int portNo = 4723;
	static AndroidDriver<MobileElement> driver = null;
	static AppiumDriverLocalService service = null;

	public static AndroidDriver<MobileElement> getDriver() {

		service = AppiumDriverLocalService
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

		} catch (Exception e) {

			System.err.println("Error :" + e.getMessage());

		} 
		
		return driver;
	}
	
	public static void stopDriver(){
		if (driver != null)
			driver.quit();
		System.err.println("App closed");
		service.stop();
		System.err.println("Appium server stopped");
	}

}
