package com.appium.test.Android.Native;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class T1_StartingAppiumServerProgrammaticaly {
	
	
	//Install appium and node (node >8 , appium = 1.8 version)
	// Find your node and appium main.js path
	
	static String nodePath="C:\\Program Files (x86)\\nodejs\\node.exe";
	static String appiumMainJsPath = "C:\\Users\\PrasadMadge\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
	static int portNo=4723;
	
	
	public static void main(String[] args) {
		
		
		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File(nodePath))
				.withAppiumJS(new File(appiumMainJsPath))
				.withIPAddress("0.0.0.0").usingPort(portNo));
		
		System.out.println("Appium server Starting");
		service.start();
		System.out.println("Appium server started");
		
		
		
		if(checkIfServerIsRunnning(portNo)) {
			service.stop();
			System.out.println("Appium server stopped");
		} else {
			System.out.println("Appium server not started with port" + portNo);
		}
		

	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

}
