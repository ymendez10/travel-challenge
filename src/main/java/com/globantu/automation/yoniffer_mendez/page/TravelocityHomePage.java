package com.globantu.automation.yoniffer_mendez.page;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TravelocityHomePage extends BasePage {

	public TravelocityHomePage(WebDriver driver) {
		super(driver);
		driver.get("https://www.travelocity.com/");
	}
	
	@FindBy(id = "tab-flight-tab-hp")
	private WebElement flightsButton;

	@FindBy(id = "flight-type-roundtrip-label-hp-flight")
	private WebElement roundTripButton;

	@FindBy(id = "flight-origin-hp-flight")
	private WebElement flyingFromInput;

	@FindBy(id = "flight-destination-hp-flight")
	private WebElement flyingToInput;

	@FindBy(id = "typeaheadDataPlain")
	private WebElement flyingFromItems;

	@FindBy(id = "typeaheadDataPlain")
	private WebElement flyingToItems;

	@FindBy(id = "flight-departing-hp-flight")
	private WebElement departDateInput;

	@FindBy(xpath = "//*[@id='flight-departing-wrapper-hp-flight']/div/div/div[3]/table/tbody")
	private WebElement departDatePicker;
	
	@FindBy(id = "flight-returning-hp-flight")
	private WebElement returnDateInput;

	@FindBy(xpath = "//*[@id=\"flight-returning-wrapper-hp-flight\"]/div/div/div[3]/table/tbody")
	private WebElement returnDatePicker;

	@FindBy(css = ".datepicker-paging.datepicker-next.btn-paging.btn-secondary.next")
	private WebElement departNextMonthButton;
	
	@FindBy(xpath = "//*[@id='gcw-flights-form-hp-flight']/div[7]/label/button")
	private WebElement searchButton;

	public TravelocityResultPage searchFlight(String flyingFrom, String flyingTo, int monthsToFuture, int daysForTravel) {
		
		DateTime dt = new DateTime(new Date());// current date time
		
		getWait().until(ExpectedConditions.elementToBeClickable(flightsButton));
		flightsButton.click();

		getWait().until(ExpectedConditions.elementToBeClickable(roundTripButton));
		roundTripButton.click();

		getWait().until(ExpectedConditions.elementToBeClickable(flyingFromInput));
		flyingFromInput.sendKeys(flyingFrom);
		getWait().until(ExpectedConditions.visibilityOf(flyingFromItems));

		// Select flying from
		for (WebElement webElement : flyingFromItems.findElements(By.tagName("li"))) {
			if (webElement.getText().contains(flyingFrom)) {
				webElement.click();
				break;
			}
		}

		getWait().until(ExpectedConditions.elementToBeClickable(flyingToInput));
		flyingToInput.sendKeys(flyingTo);
		getWait().until(ExpectedConditions.visibilityOf(flyingToItems));
		// Select flying to
		for (WebElement webElement : flyingToItems.findElements(By.tagName("li"))) {
			if (webElement.getText().contains(flyingTo)) {
				webElement.click();
				break;
			}
		}

		
		// Select departing date
		getWait().until(ExpectedConditions.elementToBeClickable(departDateInput));
		departDateInput.click();

		for(int i=1; i < monthsToFuture;i++) {
			getWait().until(ExpectedConditions.elementToBeClickable(departNextMonthButton));
			departNextMonthButton.click(); // go to the next n months
		}
		
		List<WebElement> departingDates = departDatePicker.findElements(By.tagName("td"));

		for (WebElement date : departingDates) {
			if (date.getText().equals(dt.getDayOfMonth() + "")) {
				date.click();	
				break;
			}
		}

		// Select returning date
		
		getWait().until(ExpectedConditions.elementToBeClickable(returnDateInput));
		returnDateInput.click();

		List<WebElement> returningDates = returnDatePicker.findElements(By.tagName("td"));

		for (WebElement date : returningDates) {
			if (date.getText().equals(dt.getDayOfMonth()+daysForTravel + "")) {
				date.click();
				break;
			}
		}

		// Clicking Search button

		getWait().until(ExpectedConditions.elementToBeClickable(searchButton));
		searchButton.click();
		
		return new TravelocityResultPage(getDriver());
	}
}
