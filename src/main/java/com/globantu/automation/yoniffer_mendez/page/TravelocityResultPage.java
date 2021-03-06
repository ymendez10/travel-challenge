package com.globantu.automation.yoniffer_mendez.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.testng.log4testng.Logger;

public class TravelocityResultPage extends BasePage{
	
//	private static Logger logger = Logger.getLogger(TravelocityResultPage.class);

	public TravelocityResultPage(WebDriver pDriver) {
		super(pDriver);
	}

	@FindBy(id="round-trip-flight")
	private WebElement roundTripRadio;
	
	@FindBy(id="departure-airport-1")
	private WebElement departAirportInput;
	
	@FindBy(id="arrival-airport-1")
	private WebElement arrivalAirportInput;
	
	@FindBy(id="departure-date-1")
	private WebElement departDateInput;
	
	@FindBy(id="return-date-1")
	private WebElement returnDateInput;
	
	@FindBy(xpath="//*[@id='sortBar']/div/fieldset/label/select")
	private WebElement sortSelect; 
	
	@FindBy(id="flightModuleList")
	private WebElement flightModuleList;
	
	@FindBy(id="forcedChoiceNoThanks")
	private WebElement forcedChoiceNoThanks;
	
	@FindBy(id="outboundflightModule")
	private WebElement departureSelected;
	
	public TravelocityTripDetailPage performActions(String sortBy,int departureRowNumber,int arrivalRowNumber) {
		
		sortBy(sortBy);

		selectResult(departureRowNumber);

		getWait().until(ExpectedConditions.visibilityOf(departureSelected));
		
		selectResult(arrivalRowNumber);
		
		getWait().until(ExpectedConditions.elementToBeClickable(forcedChoiceNoThanks));
		forcedChoiceNoThanks.click();
		
		return new TravelocityTripDetailPage(getDriver());
	}
	
	public String sortBy(String data) {
		

		getWait().until(ExpectedConditions.elementToBeClickable(sortSelect));
		Select select= new Select(sortSelect);
		
		select.selectByValue(data);
		
		return select.getFirstSelectedOption().getAttribute("value");

	}
	
	public void selectResult(int recordNumber) {
		int counter=1;
		for(WebElement element: flightModuleList.findElements(liTest)) {
						
			if(counter==recordNumber) {
				element.findElement(buttonTest).click();
				break;
			}
			counter++;
		}
	}
	
	public boolean roundTripIsSelected(){
		return roundTripRadio.isSelected();
	}
	public String getDeparture() {
		return departAirportInput.getAttribute("value");
	}
	
	public String getArrival() {
		return arrivalAirportInput.getAttribute("value");
	}
	
	public String getDepartDate() {
		return departDateInput.getAttribute("value");
	}
	
	public String getReturnDate() {
		return returnDateInput.getAttribute("value");
	}
	
}
