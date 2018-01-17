package com.enclave.pto.utilities;

import java.io.File;

public class Constants {
	public static final int PAGE_WAITING = 3000;
	public static final String REPORT_FOLDER = String.format("%s%s%s%s",System.getProperty("user.dir"), File.separator, "report",File.separator);
	public static final Boolean CAPTURE_SCREENSHOT = true;
	public static final int MOBILE_PLATFORM = Platform.SELENDRIOD;
	public static final String SELENIUM_WEB_DRIVER_PATH = String.format("%s%s%s",System.getProperty("user.dir"), File.separator,"chromedriver.exe");
	public static final String SELENIUM_WEB_DRIVER_PATH_FF = String.format("%s%s%s",System.getProperty("user.dir"), File.separator,"geckodriver.exe");
	
	public static final String SELENIUM_WEB_DRIVER_PATH_LINUX = String.format("%s%s%s%s%s", System.getProperty("user.dir"), File.separator,"chromedriver_linux64", File.separator, "chromedriver");
	public static final String SELENIUM_WEB_DRIVER_PATH_FF_LINUX = String.format("%s%s%s%s%s",System.getProperty("user.dir"), File.separator,"geckodriver-v0.18.0-linux64", File.separator,"geckodriver-v0.18.0-linux64.tar");
	
	public static final String SELENIUM_WEB_DRIVER_PATH_MAC = String.format("%s%s%s%s%s", System.getProperty("user.dir"), File.separator,"chromedriver_mac64", File.separator, "chromedriver");
	public static final String SELENIUM_WEB_DRIVER_PATH_FF_MAC = String.format("%s%s%s%s%s", System.getProperty("user.dir"), File.separator,"geckodriver-v0.18.0-macos", File.separator, "geckodriver-v0.18.0-macos.tar");
	
	public static final String TEMP_FOLDER_REPORT_DATA = "temp_report";
	public static final String TESTDATA_FOLDER = String.format("%s%s%s%s",System.getProperty("user.dir"), File.separator, "TestData",File.separator);
}
