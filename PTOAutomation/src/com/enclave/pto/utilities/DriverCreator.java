package com.enclave.pto.utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverCreator {
	private String driverType = "firefox";

	public DriverCreator(String driverType) {
		this.driverType = driverType;
	}

	@SuppressWarnings("unused")
	private WebDriver createAndroidDriver() {
		WebDriver result = null;
		if (Constants.MOBILE_PLATFORM == Platform.APPIUM_ANDROID) {
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
				result = new RemoteWebDriver(new URL(String.format("%s:%s/wd/hub", BaseTestCase.getProperties().getProperty("test.selenium.server"),BaseTestCase.getProperties().getProperty("test.selenium.port"))), capabilities);
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
		System.setProperty("webdriver.chrome.driver", Constants.SELENIUM_WEB_DRIVER_PATH);
		WebDriver result = null;
		if (Boolean.valueOf(BaseTestCase.getProperties().getProperty("test.selenium.grid")))
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
		System.setProperty("webdriver.gecko.drive", Constants.SELENIUM_WEB_DRIVER_PATH_FF);
		WebDriver result = null;
		if (Boolean.valueOf(BaseTestCase.getProperties().getProperty("test.selenium.grid")))
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
		if (Boolean.valueOf(BaseTestCase.getProperties().getProperty("test.selenium.grid")))
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
		WebDriver result = null;
		try {
			result = new RemoteWebDriver(new URL(String.format("%s:%s/wd/hub", BaseTestCase.getProperties().getProperty("test.selenium.server"),BaseTestCase.getProperties().getProperty("test.selenium.port"))),capability);
		} catch (Exception e) {
			System.setProperty("webdriver.gecko.drive", Constants.SELENIUM_WEB_DRIVER_PATH_FF);
			result = new FirefoxDriver();
			System.out.println(e.getMessage());
		}
		return result;
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
			result = createFirefoxDriver();
		}

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
			result = new RemoteWebDriver(new URL(String.format("%s:%s/wd/hub", BaseTestCase.getProperties().getProperty("test.selenium.server"),BaseTestCase.getProperties().getProperty("test.selenium.port"))), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			result = null;
		}
        
        return result;

    }

}
