package com.jap.lambdas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TravelPlannerFlightsService {

    private List<Flight> flights;

    // Constructor
    public TravelPlannerFlightsService() {
        // Initialize the flights list
        this.flights=new ArrayList<>();
    }

    // Method to add a flight
    public void addFlight(Flight flight) {
        // Add the provided flight to the flights list
        // Implement logic here
        flights.add(flight);
    }

    //Method to remove a flight
      public boolean removeFlight(String flightId) {
        // Remove the flight with the specified id from the flights list if present
        // Implement logic here
        return flights.removeIf(f -> f.getId().equalsIgnoreCase(flightId));
    }

     // Method to get all flights
    public List<Flight> getFlights()
    {
        // Return all flights in the flights list
        // Implement logic here
        return new ArrayList<>(flights);
    }

    // Method to filter flights based on predicate
    public List<Flight> filterFlights(Predicate<Flight> predicate) {
        // Filter flights based on the provided predicate and return the filtered list
        // Implement logic here
        return flights.stream().filter(predicate).collect(Collectors.toList());
    }

   //Method to sort a flight using comparators
    public List<Flight> sortFlights(Comparator<Flight> comparator) {
        // Sort flights using the provided comparator and return the sorted list
        // Implement logic here
        return flights.stream().sorted(comparator).collect(Collectors.toList());
    }


}