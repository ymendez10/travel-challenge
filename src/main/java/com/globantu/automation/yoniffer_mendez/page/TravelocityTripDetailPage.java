package com.globantu.automation.yoniffer_mendez.page;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TravelocityTripDetailPage extends BasePage{

	public TravelocityTripDetailPage(WebDriver driver) {
		super(driver);
		driver.get("https://www.travelocity.com/Flight-Information?continuationId=1696e85b-7fd5-4f11-9270-e12ad67438df&rfrr=&superlativeMessages[0]=&superlativeMessages[1]=&udpDisplayMode=showhotelbanneronly");
	}

	@FindBy(css="div.OD0 span.airport.type-300")
	private WebElement departure;
	
	public String getFromText() {
		
		getWait().until(ExpectedConditions.visibilityOfElementLocated(By.id("fisHeader")));
		
		//getWait().until(ExpectedConditions.numberOfElementsToBeMoreThan(spanTest, 1));
		
//		 String getTextElementJS = "document.querySelector('div.OD0 span.airport.type-300').innerText";
//		 String bookTitleValue = (String)getJsExecutor().executeScript(getTextElementJS);
		 
		return departure.getText();
	}
}
