package com.jap.lambdas;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Main class for demonstrating travel planner functionalities.
 */
public class TravelPlannerMainDemo {

    public static void main(String[] args) {
        TravelPlannerFlightsService flightService = initializeFlightService();
        TravelPlannerServiceAccommodation accommodationService = initializeAccommodationService();

        printAllFlights(flightService);
        filterAndPrintFlights(flightService);
        sortByPriceAndPrintFlights(flightService);
        sortByRatingAndPrintFlights(flightService);
        removeFlightAndPrintFlights(flightService);

        printAllAccommodation(accommodationService);
        filterAndPrintAccommodation(accommodationService);
        sortByPriceAndPrintAccommodation(accommodationService);
        sortByRatingAndPrintAccommodation(accommodationService);
        filterAccommodationsByAmenity(accommodationService, "Pool");
    }

    private static TravelPlannerFlightsService initializeFlightService() {
        TravelPlannerFlightsService service = new TravelPlannerFlightsService();
        service.addFlight(new Flight("F1", "Airline1", "Departure1", "Arrival1", 200, 4.5));
        service.addFlight(new Flight("F2", "Airline2", "Departure2", "Arrival2", 300, 4.2));
        service.addFlight(new Flight("F3", "Airline3", "Departure3", "Arrival3", 250, 5.0));
        service.addFlight(new Flight("F4", "Airline4", "Departure4", "Arrival4", 450, 3.5));
        service.addFlight(new Flight("F5", "Airline5", "Departure5", "Arrival5", 300, 4.2));
        return service;
    }

    private static TravelPlannerServiceAccommodation initializeAccommodationService() {
        TravelPlannerServiceAccommodation service = new TravelPlannerServiceAccommodation();
        service.addAccommodation(new Accommodation("A1", "Hotel1", "Location1", 100, 4.0, java.util.Arrays.asList("Wifi", "Breakfast")));
        service.addAccommodation(new Accommodation("A2", "Hotel2", "Location2", 120, 4.2, java.util.Arrays.asList("Pool", "Gym")));
        service.addAccommodation(new Accommodation("A3", "Hotel3", "Location3", 200, 4.0, java.util.Arrays.asList("Pool", "Gym")));
        service.addAccommodation(new Accommodation("A4", "Hotel4", "Location4", 150, 3.8, java.util.Arrays.asList("Pool", "Gym", "Breakfast")));
        return service;
    }

    public static void printAllFlights(TravelPlannerFlightsService service) {
        System.out.println("All Flights:");
        service.getFlights().forEach(System.out::println);
    }

    public static void filterAndPrintFlights(TravelPlannerFlightsService service) {
        System.out.println("\nFlights with Price less than 300:");
        service.filterFlights(f -> f.getPrice() < 300).forEach(System.out::println);
    }

    public static void sortByPriceAndPrintFlights(TravelPlannerFlightsService service) {
        System.out.println("\nFlights Sorted by Price:");
        service.sortFlights(TravelOptionComparator.sortByFlightTicketPrice()).forEach(System.out::println);
    }

    public static void sortByRatingAndPrintFlights(TravelPlannerFlightsService service) {
        System.out.println("\nFlights Sorted by Rating:");
        service.sortFlights(TravelOptionComparator.sortByRatings()).forEach(System.out::println);
    }

    public static void removeFlightAndPrintFlights(TravelPlannerFlightsService service) {
        System.out.println("Remove flight with Flight no");
        System.out.println(service.removeFlight("F1"));
    }

    public static void printAllAccommodation(TravelPlannerServiceAccommodation service) {
        System.out.println("All Accommodation:");
        service.getAccommodations().forEach(System.out::println);
    }

    public static void filterAndPrintAccommodation(TravelPlannerServiceAccommodation service) {
        System.out.println("\nAccommodation with Price less than 300:");
        service.filterAccommodations(a -> a.getPricePerNight() < 300).forEach(System.out::println);
    }

    public static void sortByPriceAndPrintAccommodation(TravelPlannerServiceAccommodation service) {
        System.out.println("\nAccommodation Sorted by Price:");
        service.sortAccommodations(TravelOptionComparator.sortByAccommodationPricePerNight()).forEach(System.out::println);
    }

    public static void sortByRatingAndPrintAccommodation(TravelPlannerServiceAccommodation service) {
        System.out.println("\nAccommodation Sorted by Rating:");
        service.sortAccommodations(TravelOptionComparator.sortByRatings()).forEach(System.out::println);
    }

    public static void filterAccommodationsByAmenity(TravelPlannerServiceAccommodation service, String amenity) {
        System.out.println("Accommodations with " + amenity + " amenity:");
        service.filterAccommodationsByAmenity(amenity).forEach(System.out::println);
    }
}
