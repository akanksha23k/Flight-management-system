package com.jap.lambdas;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class TravelPlannerServiceFlightsTest {

    Flight flight1, flight2, flight3, flight4, flight5;
    TravelPlannerFlightsService travelPlannerServiceFlights;


    @Before
    public void setUp() {

        travelPlannerServiceFlights = new TravelPlannerFlightsService();

    }

    // Helper method to add multiple flights to the TravelPlannerService instance
    private void addMultipleFlights(TravelPlannerFlightsService travelPlannerServiceFlights, List<Flight> flights) {
        for (Flight flight : flights) {
            travelPlannerServiceFlights.addFlight(flight);
        }
    }

    // Helper method to create a list of sample flights
    private List<Flight> createSampleFlights() {
        List<Flight> flights = new ArrayList<>();
        flight1 = new Flight("F1", "Airline1", "Departure1", "Arrival1", 200.0, 4.5);
        flight2 = new Flight("F2", "Airline2", "Departure2", "Arrival2", 300.0, 4.2);
        flight3 = new Flight("F3", "Airline3", "Departure3", "Arrival3",  250.0, 5);
        flight4 = new Flight("F4", "Airline4", "Departure4", "Arrival4",  450.0, 3.5);
        flight5 = new Flight("F5", "Airline5", "Departure5", "Arrival5",  300.0, 4.2);
       flights.add(flight1);
       flights.add(flight2);
       flights.add(flight3);
       flights.add(flight4);
       flights.add(flight5);

        return flights;
    }


    @After
    public void tearDown(){

        flight1 = flight2 = flight3 = flight4 = flight5 = null;
    }

    @Test
    public void testToRemoveFlight(){
        addMultipleFlights(travelPlannerServiceFlights, createSampleFlights());


        // Remove flight1 and check if it was removed successfully
        assertTrue(travelPlannerServiceFlights.removeFlight("F1"));
        assertFalse(travelPlannerServiceFlights.getFlights().contains(flight1));

        // Try to remove a non-existing flight and check if it returns false
        assertFalse(travelPlannerServiceFlights.removeFlight("NonExistingFlight"));
    }

    @Test
public void testToGetAllFlights(){
    // Get the list of flights
        addMultipleFlights(travelPlannerServiceFlights, createSampleFlights());
    List<Flight> flights = travelPlannerServiceFlights.getFlights();

    // Check if the returned list contains the added flights
    assertTrue(flights.contains(flight1));
    assertTrue(flights.contains(flight2));

    // Optionally, you can also check the size of the list
    assertEquals(5, flights.size());
}
    @Test
    public void testGetFlightsEmpty() {
        // Create a TravelPlannerService instance


        // Get the list of flights (which should be initially empty)
        List<Flight> flights = travelPlannerServiceFlights.getFlights();

        // Check if the returned list is empty
        assertTrue(flights.isEmpty());
    }

    // Define a hardcoded predicate to filter flights based on price
    private static Predicate<Flight> pricePredicate() {
        return new Predicate<Flight>() {
            @Override
            public boolean test(Flight flight) {
                // Hardcoded logic for filtering flights based on price
                return flight.getPrice() < 250.0;
            }
        };
    }

    @Test
    public void testFilterFlight(){


        addMultipleFlights(travelPlannerServiceFlights, createSampleFlights());
        // Filter flights based on the hardcoded predicate
        List<Flight> filteredFlights = travelPlannerServiceFlights.filterFlights(pricePredicate());

        // Check if the filtered list contains only flights with price less than 250
        assertTrue(filteredFlights.contains(flight1));
        assertFalse(filteredFlights.contains(flight2));
        assertEquals(1, filteredFlights.size());
    }

    @Test
    public void testSortFlightsByPrice(){

        addMultipleFlights(travelPlannerServiceFlights, createSampleFlights());
        // Get the comparator from TravelOptionComparator class
        Comparator<Flight> priceComparator = TravelOptionComparator.sortByFlightTicketPrice();
        // Sort flights based on the price comparator
        List<Flight> sortedFlights = travelPlannerServiceFlights.sortFlights(priceComparator);
        // Check if the sorted list is in ascending order of price
        assertEquals(flight1, sortedFlights.get(0));
        assertEquals(flight3, sortedFlights.get(1));
        assertEquals(flight2, sortedFlights.get(2));
        assertEquals(flight5, sortedFlights.get(3));
        assertEquals(flight4, sortedFlights.get(4));
    }

    @Test
    public void testSortFlightsByRating() {


        addMultipleFlights(travelPlannerServiceFlights, createSampleFlights());
        // Define a comparator to sort accommodations by ratings
        Comparator<Flight> ratingComparator = TravelOptionComparator.sortByRatings();

        // Sort accommodations based on the rating comparator
        List<Flight> sortedFlights = travelPlannerServiceFlights.sortFlights(ratingComparator);

        // Create a list of expected sorted accommodations
        List<Flight> expectedSortedFlight = new ArrayList<>();
        //31254
        expectedSortedFlight.add(flight3);
        expectedSortedFlight.add(flight1);
        expectedSortedFlight.add(flight2);
        expectedSortedFlight.add(flight5);
        expectedSortedFlight.add(flight4);
        // Check if the sorted list is in ascending order of ratings
        assertEquals(expectedSortedFlight, sortedFlights);

    }


}