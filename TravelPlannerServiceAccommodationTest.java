package com.jap.lambdas;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class TravelPlannerServiceAccommodationTest {

    Accommodation accommodation1,accommodation2,accommodation3,accommodation4;
    TravelPlannerServiceAccommodation travelPlannerServiceAccommodation;
    @Before
    public void setUp(){

        travelPlannerServiceAccommodation = new TravelPlannerServiceAccommodation();
    }

    @After
    public void tearDown(){
        accommodation1 = accommodation2 = accommodation3 = accommodation4=null;
    }

    // Helper method to add multiple flights to the TravelPlannerService instance
    private void addMultipleAccommodation(TravelPlannerServiceAccommodation travelPlannerServiceAccommodation, List<Accommodation> accommodations) {
        for (Accommodation accommodation : accommodations) {
            travelPlannerServiceAccommodation.addAccommodation(accommodation);
        }
    }
    private List<Accommodation> createSampleAccommodation() {
        List<Accommodation> accommodations = new ArrayList<>();
        accommodation1 = new Accommodation("A1", "Hotel1", "Location1", 100.0, 4.3, Arrays.asList("Wifi", "Breakfast"));
        accommodation2 = new Accommodation("A2", "Hotel2", "Location2", 120.0, 4.2, Arrays.asList("Pool", "Gym"));
        accommodation3 = new Accommodation("A3", "Hotel3", "Location3", 200.0, 3.8, Arrays.asList("Pool", "Gym"));
        accommodation4 = new Accommodation("A4", "Hotel4", "Location4", 150.0, 4.0, Arrays.asList("Pool", "Gym","Breakfast"));
        accommodations.add(accommodation1);
        accommodations.add(accommodation2);
        accommodations.add(accommodation3);
        accommodations.add(accommodation4);
        return accommodations;
    }
    @Test
    public void testToRemoveAccommodation(){
        addMultipleAccommodation(travelPlannerServiceAccommodation, createSampleAccommodation());


        // Remove flight1 and check if it was removed successfully
        assertTrue(travelPlannerServiceAccommodation.removeAccommodation("A1"));
        assertFalse(travelPlannerServiceAccommodation.getAccommodations().contains(accommodation1));

        // Try to remove a non-existing flight and check if it returns false
        assertFalse(travelPlannerServiceAccommodation.removeAccommodation("NonExistingAccommodation"));
    }

    @Test
    public void testToGetAllAccommodation() {
        addMultipleAccommodation(travelPlannerServiceAccommodation, createSampleAccommodation());

        List<Accommodation> accommodations = travelPlannerServiceAccommodation.getAccommodations();

        // Check if the returned list contains the added flights
        assertTrue(accommodations.contains(accommodation1));
        assertTrue(accommodations.contains(accommodation2));
        assertTrue(accommodations.contains(accommodation3));

        // Optionally, you can also check the size of the list
        assertEquals(4, accommodations.size());
    }

    // Define a hardcoded predicate to filter flights based on price
    private static Predicate<Accommodation> pricePredicate() {
        return new Predicate<Accommodation>() {
            @Override
            public boolean test(Accommodation accommodation) {
                // Hardcoded logic for filtering flights based on price
                return accommodation.getPricePerNight() < 150.0;
            }
        };
    }

    @Test
    public void testFilterAccommodation() {
        addMultipleAccommodation(travelPlannerServiceAccommodation, createSampleAccommodation());

        // Filter flights based on the hardcoded predicate
        List<Accommodation> filteredAccommodation = travelPlannerServiceAccommodation.filterAccommodations(pricePredicate());
        // Check if the filtered list contains only flights with price less than 250
        assertTrue(filteredAccommodation.contains(accommodation1));
        assertTrue(filteredAccommodation.contains(accommodation2));
        assertFalse(filteredAccommodation.contains(accommodation3));
        assertEquals(2, filteredAccommodation.size());
    }

    @Test
    public void testSortAccommodationByPrice(){
        addMultipleAccommodation(travelPlannerServiceAccommodation, createSampleAccommodation());



        // Get the comparator from TravelOptionComparator class
        Comparator<Accommodation> priceComparator = TravelOptionComparator.sortByAccommodationPricePerNight();
        // Sort flights based on the price comparator
        List<Accommodation> sortedAccommodation = travelPlannerServiceAccommodation.sortAccommodations(priceComparator);
        // Check if the sorted list is in ascending order of price

        assertEquals(accommodation1, sortedAccommodation.get(0));
        assertEquals(accommodation2, sortedAccommodation.get(1));
        assertEquals(accommodation4, sortedAccommodation.get(2));
        assertEquals(accommodation3, sortedAccommodation.get(3));
    }

    @Test
    public void testFilterAccommodationsByAmenity() {
        addMultipleAccommodation(travelPlannerServiceAccommodation, createSampleAccommodation());


        // Filter accommodations by 'Pool' amenity
        List<Accommodation> filteredAccommodations = travelPlannerServiceAccommodation.filterAccommodationsByAmenity("Pool");

        // Check if the filtered list contains only accommodations with the 'Pool' amenity
        assertTrue(filteredAccommodations.contains(accommodation2));
        assertTrue(filteredAccommodations.contains(accommodation3));
        assertTrue(filteredAccommodations.contains(accommodation4));
        assertFalse(filteredAccommodations.contains(accommodation1));
        assertEquals(3, filteredAccommodations.size());
    }

    @Test
    public void testSortAccommodationsByRating() {
        addMultipleAccommodation(travelPlannerServiceAccommodation, createSampleAccommodation());

        System.out.println("In Accommmmm!!!!!");
        // Define a comparator to sort accommodations by ratings
        Comparator<Accommodation> ratingComparator = TravelOptionComparator.sortByRatings();

        // Sort accommodations based on the rating comparator
        List<Accommodation> sortedAccommodations = travelPlannerServiceAccommodation.sortAccommodations(ratingComparator);

        System.out.println(sortedAccommodations);

        // Create a list of expected sorted accommodations
        List<Accommodation> expectedSortedAccommodations = new ArrayList<>();
        expectedSortedAccommodations.add(accommodation1);
        expectedSortedAccommodations.add(accommodation2);
        expectedSortedAccommodations.add(accommodation4);
        expectedSortedAccommodations.add(accommodation3);
        // Check if the sorted list is in ascending order of ratings
        assertEquals(expectedSortedAccommodations, sortedAccommodations);
    }


}
