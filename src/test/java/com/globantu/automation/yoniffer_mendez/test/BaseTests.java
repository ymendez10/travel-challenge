package com.globantu.automation.yoniffer_mendez.test;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.globantu.automation.yoniffer_mendez.driver.DriverHandler;
import com.globantu.automation.yoniffer_mendez.page.TravelocityHomePage;


public class BaseTests {

	private DriverHandler driver;
	private TravelocityHomePage travelocityHome;
	
	@BeforeSuite(alwaysRun=true)
	@BeforeGroups(alwaysRun=true)
	@Parameters({"browser",})
	public void beforeSuite(String browser) {
		driver = new DriverHandler(browser);
		travelocityHome = new TravelocityHomePage(driver.getDriver());
	}
	
	@AfterSuite(alwaysRun=true)
	@AfterGroups(alwaysRun=true)
	public void afterMethod() {
		travelocityHome.dispose();
	}

	public TravelocityHomePage getTravelocityHome() {
		return travelocityHome;
	}
	
}
