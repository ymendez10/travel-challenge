package com.globantu.automation.yoniffer_mendez.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TravelocityTripDetailPage extends BasePage{

	public TravelocityTripDetailPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css="div.flex-card .flex-tile .details .OD0 > h4")
	private WebElement fromLabel;
	
	public String getFromText() {
		return fromLabel.getText();
	}
}
