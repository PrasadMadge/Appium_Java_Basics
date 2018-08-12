package com.appium.test.Android;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class T7_UsingDriverUtility extends T6_DriverUtilityToStartStopDriver {

	// Note
	// we are extending T6_DriverUtilityToStartStopDriver in our main class

	// ***************** PreConditions
	// APK is already installed
	// Connect android device via USB with adb enabled
	// Install appium and node (node >8 , appium = 1.8 version)
	// Update your node and appium main.js path
	

	public static void main(String[] args) {

		// this function will launch the application
		driver = getDriver();

		// our test code will be written here

		/*
		 * // enter username
		 * 
		 * // enter password
		 * 
		 * // click login button
		 */
		
		// this function will close the application
		stopDriver();

	}

}
