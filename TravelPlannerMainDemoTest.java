package com.jap.lambdas;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TravelPlannerMainDemoTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    TravelPlannerMainDemo travelPlannerMainDemo = new TravelPlannerMainDemo();
    TravelPlannerFlightsService travelPlannerServiceFlights = new TravelPlannerFlightsService();
    TravelPlannerServiceAccommodation travelPlannerServiceAccommodation = new TravelPlannerServiceAccommodation();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));

    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    // Helper method to create a list of sample flights
    private List<Flight> createSampleFlights() {
        List<Flight> flights = new ArrayList<>();
        Flight flight1 = new Flight("F1", "Airline1", "Departure1", "Arrival1",  200.0, 4.5);
        Flight flight2 = new Flight("F2", "Airline2", "Departure2", "Arrival2",  300.0, 4.2);
        Flight flight3 = new Flight("F3", "Airline3", "Departure3", "Arrival3",  250.0, 5.0);
        Flight flight4 = new Flight("F4", "Airline4", "Departure4", "Arrival4",  450.0, 3.5);
        Flight flight5 = new Flight("F5", "Airline5", "Departure5", "Arrival5", 300.0, 4.2);
flights.add(flight1);
flights.add(flight2);
flights.add(flight3);
flights.add(flight4);

flights.add(flight5);
        return flights;
    }

    private List<Accommodation> createSampleAccommodation() {
        List<Accommodation> accommodations = new ArrayList<>();

        Accommodation accommodation1 = new Accommodation("A1", "Hotel1", "Location1", 100.0, 4.0, Arrays.asList("Wifi", "Breakfast"));
        Accommodation accommodation2 = new Accommodation("A2", "Hotel2", "Location2", 120.0, 4.2, Arrays.asList("Pool", "Gym"));
        Accommodation accommodation3 = new Accommodation("A3", "Hotel3", "Location3", 200.0, 4, Arrays.asList("Pool", "Gym"));
        Accommodation accommodation4 = new Accommodation("A4", "Hotel4", "Location4", 150.0, 3.8, Arrays.asList("Pool", "Gym","Breakfast"));

        accommodations.add(accommodation1);
        accommodations.add(accommodation2);
        accommodations.add(accommodation3);
        accommodations.add(accommodation4);


        return accommodations;
    }
    @Test
    public void testPrintAllFlights() {


        // Create a TravelPlannerService instance


        // Add some flights to the TravelPlannerService instance
        List<Flight> flights = createSampleFlights();

        for (Flight flight : flights) {
            travelPlannerServiceFlights.addFlight(flight);
        }


        // Call the method to print all flights
        travelPlannerMainDemo.printAllFlights(travelPlannerServiceFlights);

        // Get the printed output
        String printedOutput = outContent.toString();
        originalOut.println("printed output" +printedOutput);

        // Define the expected output based on the sample flights
        String expectedOutput = "All Flights:\n" +
                "Flight{id='F1', airline='Airline1', departureAirport='Departure1', arrivalAirport='Arrival1',price=200.0, ratings=4.5}\n" +
                "Flight{id='F2', airline='Airline2', departureAirport='Departure2', arrivalAirport='Arrival2',price=300.0, ratings=4.2}\n" +
                "Flight{id='F3', airline='Airline3', departureAirport='Departure3', arrivalAirport='Arrival3',price=250.0, ratings=5.0}\n" +
                "Flight{id='F4', airline='Airline4', departureAirport='Departure4', arrivalAirport='Arrival4',price=450.0, ratings=3.5}\n" +
                "Flight{id='F5', airline='Airline5', departureAirport='Departure5', arrivalAirport='Arrival5',price=300.0, ratings=4.2}\n";

        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput.replaceAll("\\s", ""), printedOutput.replaceAll("\\s", ""));

    }


    @Test
    public void testFilterAndPrintFlights() {
        // Add some flights to the TravelPlannerService instance
        List<Flight> flights = createSampleFlights();
        for (Flight flight : flights) {
            travelPlannerServiceFlights.addFlight(flight);
        }
        // Call the method to filter and print flights
        travelPlannerMainDemo.filterAndPrintFlights(travelPlannerServiceFlights);


        // Get the printed output
        String printedOutput = outContent.toString();

        // Define the expected output
        String expectedOutput = "\nFlights with Price less than 300:\n" +
                "Flight{id='F1', airline='Airline1', departureAirport='Departure1', arrivalAirport='Arrival1', price=200.0, ratings=4.5}\n" +
                "Flight{id='F3', airline='Airline3', departureAirport='Departure3', arrivalAirport='Arrival3', price=250.0, ratings=5.0}\n";

        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput.replaceAll("\\s", ""), printedOutput.replaceAll("\\s", ""));
    }

    @Test
    public void testSortByPriceAndPrintFlights() {
        List<Flight> flights = createSampleFlights();
        for (Flight flight : flights) {
            travelPlannerServiceFlights.addFlight(flight);
        }

        // Call the method to sort and print flights by price
        travelPlannerMainDemo.sortByPriceAndPrintFlights(travelPlannerServiceFlights);

        // Get the printed output
        String printedOutput = outContent.toString();
        originalOut.println("printed output " + printedOutput);
        // Define the expected output
        String expectedOutput = "\nFlights Sorted by Price:\n" +
                "Flight{id='F1', airline='Airline1', departureAirport='Departure1', arrivalAirport='Arrival1', price=200.0, ratings=4.5}\n" +
                "Flight{id='F3', airline='Airline3', departureAirport='Departure3', arrivalAirport='Arrival3', price=250.0, ratings=5.0}\n" +
                "Flight{id='F2', airline='Airline2', departureAirport='Departure2', arrivalAirport='Arrival2', price=300.0, ratings=4.2}\n" +
                "Flight{id='F5', airline='Airline5', departureAirport='Departure5', arrivalAirport='Arrival5', price=300.0, ratings=4.2}\n" +
                "Flight{id='F4', airline='Airline4', departureAirport='Departure4', arrivalAirport='Arrival4', price=450.0, ratings=3.5}\n" ;

        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput.replaceAll("\\s", ""), printedOutput.replaceAll("\\s", ""));
    }

    @Test
    public void testSortByRatingAndPrintFlights(){
        List<Flight> flights = createSampleFlights();
        for (Flight flight : flights) {
            travelPlannerServiceFlights.addFlight(flight);
        }

        travelPlannerMainDemo.sortByRatingAndPrintFlights(travelPlannerServiceFlights);
        String printedOutput = outContent.toString();
        originalOut.println("printed output " + printedOutput);

        // Define the expected output
        String expectedOutput = "\nFlights Sorted by Rating:\n" +
                "Flight{id='F3', airline='Airline3', departureAirport='Departure3', arrivalAirport='Arrival3', price=250.0, ratings=5.0}\n"+
                "Flight{id='F1', airline='Airline1', departureAirport='Departure1', arrivalAirport='Arrival1', price=200.0, ratings=4.5}\n" +
                "Flight{id='F2', airline='Airline2', departureAirport='Departure2', arrivalAirport='Arrival2', price=300.0, ratings=4.2}\n" +
                "Flight{id='F5', airline='Airline5', departureAirport='Departure5', arrivalAirport='Arrival5', price=300.0, ratings=4.2}\n" +
                "Flight{id='F4', airline='Airline4', departureAirport='Departure4', arrivalAirport='Arrival4', price=450.0, ratings=3.5}\n";

        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput.replaceAll("\\s", ""), printedOutput.replaceAll("\\s", ""));


    }

    @Test
    public void testRemoveFlightAndPrintFlights(){
        List<Flight> flights = createSampleFlights();
        for (Flight flight : flights) {
            travelPlannerServiceFlights.addFlight(flight);
        }

        travelPlannerMainDemo.removeFlightAndPrintFlights(travelPlannerServiceFlights);
        String printedOutput = outContent.toString();
        originalOut.println("printed output " + printedOutput);
        // Define the expected output
        String expectedOutput = "Remove flight with Flight no\ntrue\n";
        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput.replaceAll("\\s", ""), printedOutput.replaceAll("\\s", ""));
    }

    @Test
    public void testPrintAllAccommodations() {
        // Add some flights to the TravelPlannerService instance
        List<Accommodation> accommodations = createSampleAccommodation();

        for (Accommodation accommodation : accommodations) {
            travelPlannerServiceAccommodation.addAccommodation(accommodation);
        }


        // Call the method to print all flights
        travelPlannerMainDemo.printAllAccommodation(travelPlannerServiceAccommodation);

        // Get the printed output
        String printedOutput = outContent.toString();
        originalOut.println("printed output" +printedOutput);

        // Define the expected output based on the sample flights
        String expectedOutput = "All Accommodation:\n" +
                "Accommodation{id='A1', name='Hotel1', location='Location1', pricePerNight=100.0, ratings=4.0, amenities=[Wifi, Breakfast]}\n" +
                "Accommodation{id='A2', name='Hotel2', location='Location2', pricePerNight=120.0, ratings=4.2, amenities=[Pool, Gym]}\n" +
                "Accommodation{id='A3', name='Hotel3', location='Location3', pricePerNight=200.0, ratings=4.0, amenities=[Pool, Gym]}\n" +
                "Accommodation{id='A4', name='Hotel4', location='Location4', pricePerNight=150.0, ratings=3.8, amenities=[Pool, Gym, Breakfast]}\n";
        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput.replaceAll("\\s", ""), printedOutput.replaceAll("\\s", ""));
    }

    @Test
    public void testFilterAndPrintAccommodation() {

        List<Accommodation> accommodations = createSampleAccommodation();
        for (Accommodation accommodation : accommodations) {
            travelPlannerServiceAccommodation.addAccommodation(accommodation);
        }
        // Call the method to filter and print accomodation
        travelPlannerMainDemo.filterAndPrintAccommodation(travelPlannerServiceAccommodation);


        // Get the printed output
        String printedOutput = outContent.toString();
        originalOut.println("printed output" +printedOutput);
        // Define the expected output
        String expectedOutput = "\nAccommodation with Price less than 300:\n" +
                "Accommodation{id='A1', name='Hotel1', location='Location1', pricePerNight=100.0, ratings=4.0, amenities=[Wifi, Breakfast]}\n" +
                "Accommodation{id='A2', name='Hotel2', location='Location2', pricePerNight=120.0, ratings=4.2, amenities=[Pool, Gym]}\n" +
                "Accommodation{id='A3', name='Hotel3', location='Location3', pricePerNight=200.0, ratings=4.0, amenities=[Pool, Gym]}\n" +
                "Accommodation{id='A4', name='Hotel4', location='Location4', pricePerNight=150.0, ratings=3.8, amenities=[Pool, Gym, Breakfast]}\n";

        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput.replaceAll("\\s", ""), printedOutput.replaceAll("\\s", ""));
    }

    @Test
    public void testSortByPriceAndPrintAccommodation() {
        List<Accommodation> accommodations = createSampleAccommodation();
        for (Accommodation accommodation : accommodations) {
            travelPlannerServiceAccommodation.addAccommodation(accommodation);
        }

        // Call the method to sort and print accommodation by price
        travelPlannerMainDemo.sortByPriceAndPrintAccommodation(travelPlannerServiceAccommodation);

        // Get the printed output
        String printedOutput = outContent.toString();
        originalOut.println("printed output " + printedOutput);
        // Define the expected output
        String expectedOutput = "\nAccommodation Sorted by Price:\n" +
                "Accommodation{id='A1', name='Hotel1', location='Location1', pricePerNight=100.0, ratings=4.0, amenities=[Wifi, Breakfast]}\n" +
                "Accommodation{id='A2', name='Hotel2', location='Location2', pricePerNight=120.0, ratings=4.2, amenities=[Pool, Gym]}\n" +
                "Accommodation{id='A4', name='Hotel4', location='Location4', pricePerNight=150.0, ratings=3.8, amenities=[Pool, Gym, Breakfast]}\n" +
                "Accommodation{id='A3', name='Hotel3', location='Location3', pricePerNight=200.0, ratings=4.0, amenities=[Pool, Gym]}\n";

        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput.replaceAll("\\s", ""), printedOutput.replaceAll("\\s", ""));
    }

    @Test
    public void testSortByRatingAndPrintAccommodation(){
        List<Accommodation> accommodations = createSampleAccommodation();
        for (Accommodation accommodation : accommodations) {
            travelPlannerServiceAccommodation.addAccommodation(accommodation);
        }

        travelPlannerMainDemo.sortByRatingAndPrintAccommodation(travelPlannerServiceAccommodation);
        String printedOutput = outContent.toString();
        originalOut.println("printed output " + printedOutput);

        // Define the expected output
        String expectedOutput = "\nAccommodation Sorted by Rating:\n" +
                "Accommodation{id='A2', name='Hotel2', location='Location2', pricePerNight=120.0, ratings=4.2, amenities=[Pool, Gym]}\n" +
                "Accommodation{id='A1', name='Hotel1', location='Location1', pricePerNight=100.0, ratings=4.0, amenities=[Wifi, Breakfast]}\n" +
                "Accommodation{id='A3', name='Hotel3', location='Location3', pricePerNight=200.0, ratings=4.0, amenities=[Pool, Gym]}\n" +
                "Accommodation{id='A4', name='Hotel4', location='Location4', pricePerNight=150.0, ratings=3.8, amenities=[Pool, Gym, Breakfast]}\n";

        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput.replaceAll("\\s", ""), printedOutput.replaceAll("\\s", ""));


    }

    @Test
    public void testFilterAccommodationsByAmenity() {
        List<Accommodation> accommodations = createSampleAccommodation();
        for (Accommodation accommodation : accommodations) {
            travelPlannerServiceAccommodation.addAccommodation(accommodation);
        }

        // Call the method to filter accommodations by amenity
        travelPlannerMainDemo.filterAccommodationsByAmenity(travelPlannerServiceAccommodation, "Pool");

        // Get the printed output
        String printedOutput = outContent.toString();
        originalOut.println("printed output " + printedOutput);
        // Define the expected output
        String expectedOutput = "Accommodations with " + "Pool" + " amenity:\n" +
                "Accommodation{id='A2', name='Hotel2', location='Location2', pricePerNight=120.0, ratings=4.2, amenities=[Pool, Gym]}\n" +
                "Accommodation{id='A3', name='Hotel3', location='Location3', pricePerNight=200.0, ratings=4.0, amenities=[Pool, Gym]}\n" +
                "Accommodation{id='A4', name='Hotel4', location='Location4', pricePerNight=150.0, ratings=3.8, amenities=[Pool, Gym, Breakfast]}\n";

        // Assert that the printed output matches the expected output
        assertEquals(expectedOutput.replaceAll("\\s", ""), printedOutput.replaceAll("\\s", ""));

    }
}