package com.jap.lambdas;

import java.util.List;

/**
 * Represents an accommodation option.
 */
public class Accommodation {

    // Declare attributes: id, name, location, pricePerNight, ratings, amenities
    private String id;
    private String name;
    private String location;
    private double pricePerNight;
    private double ratings;
    private List<String> amenities;

    // Constructor
    public Accommodation(String id, String name, String location, double pricePerNight,
                         double ratings, List<String> amenities)
    {
        // Initialize attributes with provided values
        this.id =id;
        this.name=name;
        this.location=location;
        this.pricePerNight=pricePerNight;
        this.ratings=ratings;
        this.amenities=amenities;
    }

    // create the Getters and Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    //modify the return statement to return the price.
    public double getPricePerNight()
    {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    // Method to check if accommodation has a specific amenity
    public boolean hasAmenity(String amenity)
    {
        // Implement logic to check if the accommodation has the specified amenity

        return amenities.stream().anyMatch(a -> a.equalsIgnoreCase(amenity));
    }

    // Method to add an amenity
    public void addAmenity(String amenity)
    {
        // Implement logic to add the specified amenity to the accommodation
        if(!amenities.contains(amenity))
        {
            amenities.add(amenity);
        }
    }

    // toString method

    @Override
    public String toString() {
        return "Accommodation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", pricePerNight=" + pricePerNight +
                ", ratings=" + ratings +
                ", amenities=" + amenities +
                '}';
    }
}
