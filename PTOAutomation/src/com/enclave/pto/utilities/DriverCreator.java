package com.enclave.pto.utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverCreator {
	private String driverType = "chrome";
	private String osType = "windows";
	public DriverCreator(String driverType) {
		this.driverType = driverType;
	}
	public DriverCreator(String driverType, String osType) {
			this.driverType = driverType;
			this.osType = osType;
		}


	public WebDriver getWebDriver() throws MalformedURLException {
		WebDriver result = null;
		switch (driverType) {
		case "firefox":
			result = createFirefoxDriver();
			break;
		case "chrome":
			result = createChromeDriver();
			break;
		case "android":
			result = createAndroidDriver();
			break;
		case "ie":
			result = createIEDriver();
			break;	
		case "ios":
			result = createIOSDriver();
			break;
		default:
			result = createChromeDriver();
		}

		return result;
	}
	private WebDriver createAndroidDriver() {
		WebDriver result = null;
		if (Constants.MOBILE_PLATFORM == com.enclave.pto.utilities.Platform.APPIUM_ANDROID) {
			// To create an object of Desired Capabilities
			final DesiredCapabilities capabilities = new DesiredCapabilities();

			// Name of mobile web browser to automate. It should be an empty
			// string, as we are automation an app
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");

			// Name of the OS: Android, iOS or FirefoxOS
			capabilities.setCapability("platformName", "Android");
			// Mobile OS version ? My device is running Android 4.4.2
			capabilities.setCapability(CapabilityType.VERSION, "4.3");

			// Device name: ? I am using Micromax A311
			capabilities.setCapability("deviceName", "HTC One");

			capabilities.setCapability("recreateChromeDriverSessions", true);

			// Constructor to initialize driver object with new Url and
			// Capabilities

			try {
				result = new RemoteWebDriver(new URL(String.format("%s:%s/wd/hub", BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_GRID_URL),BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_PORT))), capabilities);
			} catch (final MalformedURLException e) {
				// TODO Auto-generated catch block
				result = null;
			}
		} else {
			result = new RemoteWebDriver(DesiredCapabilities.android());
		}

		// WebDriver result = new
		// RemoteWebDriver(DesiredCapabilities.android());
		return result;
	}

	private WebDriver createChromeDriver() {
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		capability.setBrowserName("chrome");
		switch (osType) {
			case "linux":
				System.setProperty("webdriver.chrome.driver", Constants.SELENIUM_WEB_DRIVER_PATH_LINUX);
				capability.setPlatform(org.openqa.selenium.Platform.LINUX);
				break;
			case "mac":
				System.setProperty("webdriver.chrome.driver", Constants.SELENIUM_WEB_DRIVER_PATH_MAC);
				capability.setPlatform(org.openqa.selenium.Platform.MAC);
				break;
			default:
				System.setProperty("webdriver.chrome.driver", Constants.SELENIUM_WEB_DRIVER_PATH);
				capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}
		WebDriver result = null;
		boolean onGrid=false;
		if (BaseTestCase.getProperties().containsKey(BaseTestCase.TEST_SELENIUM_GRID)){
			onGrid = Boolean.valueOf(BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_GRID));
		}
		if (BaseTestCase.getProperties().containsKey(BaseTestCase.TEST_SELENIUM_GRID)){
			onGrid = Boolean.valueOf(BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_GRID));
		}
		
		if (onGrid)
		{
			result = createRemoteWebDriver(capability);
		}
		else
		{
			result = new ChromeDriver();
		}
		return result;
	}

	private WebDriver createFirefoxDriver() {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox"); 
		switch (osType) {
			case "linux":
				System.setProperty("webdriver.gecko.drive", Constants.SELENIUM_WEB_DRIVER_PATH_FF_LINUX);
				capability.setPlatform(org.openqa.selenium.Platform.LINUX);
				break;
			case "mac":
				System.setProperty("webdriver.gecko.drive", Constants.SELENIUM_WEB_DRIVER_PATH_FF_MAC);
				capability.setPlatform(org.openqa.selenium.Platform.MAC);
				break;
			default:
				System.setProperty("webdriver.gecko.drive", Constants.SELENIUM_WEB_DRIVER_PATH_FF);
				capability.setPlatform(org.openqa.selenium.Platform.WINDOWS);
			}

		WebDriver result = null;
		boolean onGrid=false;
		if (null != BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_GRID)){
			onGrid = Boolean.valueOf(BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_GRID));
		}else{
			onGrid = Boolean.valueOf(BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_GRID));
		}
		if (onGrid)
		{
			result = createRemoteWebDriver(capability);
		}
		else
		{
			result = new FirefoxDriver();
		}
		return result;
	}
	
	private WebDriver createIEDriver() {
		DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
		capability.setBrowserName("internetexplorer"); 
		WebDriver result = null;
		boolean onGrid=false;
		if (null != BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_GRID)){
			onGrid = Boolean.valueOf(BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_GRID));
		}else{
			onGrid = Boolean.valueOf(BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_GRID));
		}
		if (onGrid)
		{
			result = createRemoteWebDriver(capability);
		}
		else
		{
			result = new InternetExplorerDriver();
		}
		return result;
	}
	
	private WebDriver createRemoteWebDriver(DesiredCapabilities capability)
	{
		System.out.println("------------Run from grid----------------------");
		RemoteWebDriver result = null;
		try {
			if (BaseTestCase.getProperties().containsKey(BaseTestCase.TEST_SELENIUM_GRID_URL)){
				result = new RemoteWebDriver(new URL(String.format("%s:%s/wd/hub", BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_GRID_URL),BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_PORT))),capability);
				result.setFileDetector(new LocalFileDetector());
			}
			else{
				result = new RemoteWebDriver(new URL(String.format("%s:%s/wd/hub", BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_GRID_URL),BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_PORT))),capability);
			}
//			result = new RemoteWebDriver(new URL(String.format("%s:%s/wd/hub", BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_GRID_URL),BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_PORT))),capability);
//			result = new RemoteWebDriver(new URL("https://nicholaskhuong1:DHcS62YhmPCBTwLqyJ8q@hub-cloud.browserstack.com/wd/hub"),capability);
		} catch (Exception e) {
			System.out.println("----------------Exception when run on Grid: "+e.getMessage() +" OS Type: " + osType +" -----------------------------------");
			if ("windows".equals(osType))
			{
				System.setProperty("webdriver.chrome.driver", Constants.SELENIUM_WEB_DRIVER_PATH);
			}else
			{
				System.setProperty("webdriver.chrome.driver", Constants.SELENIUM_WEB_DRIVER_PATH_LINUX);
			}
			result = new ChromeDriver();
			
		}
		System.out.println("------------End Run from grid----------------------");
		return result;
	}

	private WebDriver createIOSDriver() {
		WebDriver result = null;

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformVersion", "9.3");
        capabilities.setCapability("launchTimeout", 300000);
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
        capabilities.setCapability("deviceName", "iPhone 6");
        capabilities.setCapability("udid", "1806356E-AB4E-407B-91DC-26F091A0CF90");
        try {
			result = new RemoteWebDriver(new URL(String.format("%s:%s/wd/hub", BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_GRID_URL),BaseTestCase.getProperties().getProperty(BaseTestCase.TEST_SELENIUM_PORT))), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			result = null;
		}
        
        return result;

    }


}
