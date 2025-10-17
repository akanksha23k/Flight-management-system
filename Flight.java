package com.jap.lambdas;

/**
 * Represents a flight option.
 */
public class Flight {

    // Declare attributes: id, airline, departureAirport, arrivalAirport, price, ratings
    private String id;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private double price;
    private double ratings;

    // Constructor
    public Flight(String id, String airline, String departureAirport, String arrivalAirport,
                  double price, double ratings) {
        // Initialize attributes with provided values
        this.id=id;
        this.airline=airline;
        this.departureAirport=departureAirport;
        this.arrivalAirport=arrivalAirport;
        this.price=price;
        this.ratings=ratings;
    }
    // Getters and Setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    //modify the return statement to return the price.
    public double getPrice() {

        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }

    // toString method

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", airline='" + airline + '\'' +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", price=" + price +
                ", ratings=" + ratings +
                '}';
    }
}
