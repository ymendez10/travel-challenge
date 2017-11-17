package com.globantu.automation.yoniffer_mendez.page;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TravelocityTripDetailPage extends BasePage{

	public TravelocityTripDetailPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="div.OD1 span.airport.type-300")
	private WebElement departure;
	
	public String getFromText() {
		getWait().until(ExpectedConditions.numberOfElementsToBeMoreThan(spanTest, 1));
		return departure.getText();
	}
}
