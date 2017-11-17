package com.globantu.automation.yoniffer_mendez.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Date;

import org.joda.time.DateTime;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.globantu.automation.yoniffer_mendez.page.TravelocityResultPage;

public class TravelocityTest extends BaseTests {
	
	private static Logger logger = Logger.getLogger(TravelocityTest.class);
	
	private DateTime dt = new DateTime(new Date());// current date time
	private String df= "MM/dd/yyyy";

	@Test
	@Parameters({ "flyingFrom", "flyingTo", "monthsToFuture", "daysForTravel", "sortBy"})
	public void testSearchFlight(String flyingFrom, String flyingTo, int monthsToFuture, int daysForTravel, String sortBy) {
		
		// get Result page
		TravelocityResultPage resultPage=getTravelocityHome().searchFlight(flyingFrom, flyingTo, monthsToFuture, daysForTravel);

		// Validate RoundTrip
		assertTrue(resultPage.roundTripIsSelected());
		
		// validate Departure
		assertTrue(resultPage.getDeparture().contains(flyingFrom));

		// validate Arrival
		assertTrue(resultPage.getArrival().contains(flyingTo));

		// validate Depart Date
		assertEquals(resultPage.getDepartDate(),dt.plusMonths(monthsToFuture).toString(df));
		
		// validate Return Date
		assertEquals(resultPage.getReturnDate(),dt.plusMonths(monthsToFuture).plusDays(daysForTravel).toString(df));
		
		// validate sort by
		assertEquals(resultPage.sortBy(sortBy), sortBy);
		
		assertEquals(resultPage.performActions(sortBy, 1, 3), resultPage.getDeparture());
		

		 //assertEquals(getTravelocityHome().searchFlight(flyingFrom, flyingTo, monthsToFuture, daysForTravel).performActions("duration:asc",1,4),"Duration(Shortest)");
	}
}
