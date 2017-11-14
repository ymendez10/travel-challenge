package com.globantu.automation.yoniffer_mendez.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TravelocityTest extends BaseTests{

	@Test
	@Parameters({"flyingFrom","flyingTo","monthsToFuture","daysForTravel"})
	public void testSearchFlight(String flyingFrom, String flyingTo, int monthsToFuture, int daysForTravel) {
		assertEquals(getTravelocityHome().searchFlight(flyingFrom, flyingTo, monthsToFuture, daysForTravel).performActions("duration:asc",1,3),"Duration (Shortest)");
	}
}
