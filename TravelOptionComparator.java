package com.jap.lambdas;

import java.util.Comparator;
public class TravelOptionComparator
{

    //Comparator for sorting travel options by flight ticket price.
    public static <T> Comparator<T> sortByFlightTicketPrice()
    {
        // Implement comparator logic here
        return (o1, o2) ->
        {
            if (o1 instanceof Flight && o2 instanceof Flight)
            {
                return Double.compare(((Flight) o1).getPrice(), ((Flight) o2).getPrice());
            }
            throw  new IllegalArgumentException("Objects must be Flights");
        };
    }

     // Comparator for sorting travel options by accommodation price per night.
    public static <T> Comparator<T> sortByAccommodationPricePerNight()
    {
        // Implement comparator logic here
        return (o1,o2)->{
            if(o1 instanceof  Accommodation && o2 instanceof Accommodation)
            {
                return  Double.compare(((Accommodation)o1).getPricePerNight(),
                        ((Accommodation)o2).getPricePerNight());
            }
            throw new IllegalArgumentException("Objects must be Accommodations");
        };
        // Placeholder, replace with actual implementation
    }
     //Comparator for sorting travel options by ratings (either flight or accommodation)
    public static <T> Comparator<T> sortByRatings()
    {
        return (o1,o2)->
        {
            if(o1 instanceof  Flight && o2 instanceof Flight)
            {
                return  Double.compare(((Flight)o2).getRatings(),((Flight)o1).getRatings());
            }
            else if (o1 instanceof  Accommodation && o2 instanceof Accommodation)
            {
                return Double.compare(((Accommodation)o2).getRatings(),
                        ((Accommodation)o1).getRatings());
            }
            throw  new IllegalArgumentException("Objects must be both Flights or both Accommodations");
        };
        // Implement comparator logic here

    }
}
