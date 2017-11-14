package com.globantu.automation.yoniffer_mendez.page;

import java.util.ArrayList;
import java.util.List;


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
	
	private List<String> options = new ArrayList<String>();

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
	
	@FindBy(xpath="//*[@id='titleBar']/h1/div/span[1]")
	private WebElement tittleBar;
	
	private final By liTest = By.tagName("li");
	private static final By buttonTest = By.tagName("button");
	
	public String performActions(String sortby,int departureRowNumber,int arrivalRowNumber) {
		
		sortBy(sortby);

		selectResult(departureRowNumber);

//		getWait().until(ExpectedConditions.attributeContains(tittleBar, "innerText", "return to"));
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		selectResult(arrivalRowNumber);
		
		getWait().until(ExpectedConditions.elementToBeClickable(forcedChoiceNoThanks));
		forcedChoiceNoThanks.click();
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new TravelocityTripDetailPage(getDriver()).getFromText();
	}
	
	public String sortBy(String data) {
		
		getWait().until(ExpectedConditions.elementToBeClickable(sortSelect));
		Select select= new Select(sortSelect);
		select.selectByValue(data);
		
		return select.getFirstSelectedOption().getText();

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
	
	public List<String> getResultOptions() {
		
		options.add(roundTripRadio.isSelected()+"");
		options.add(departAirportInput.getAttribute("value"));
		options.add(arrivalAirportInput.getAttribute("value"));
		options.add(departDateInput.getAttribute("value"));
		options.add(returnDateInput.getAttribute("value"));
		
		return options;
	}
	
}
