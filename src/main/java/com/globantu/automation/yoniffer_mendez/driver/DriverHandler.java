package com.globantu.automation.yoniffer_mendez.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverHandler {

	private WebDriver driver;
	
	public DriverHandler(String browser) {
		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver= new ChromeDriver(options);
			break;
		case "firefox":
			driver = new FirefoxDriver();
		default:
			break;
		}
	}

	public WebDriver getDriver() {
		return driver;
	}
}
