package com.devops3.naplocator.utils;

import com.devops3.naplocator.model.Coordinate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HaversineDistance {

    private final static Logger logger = LoggerFactory.getLogger(HaversineDistance.class);

    private HaversineDistance(){

    }

    public static Double getHaversineDistance(String givenLat, String givenLon, String branchLat, String branchLon){

        final int R = 6371; // Radious of the earth

        Double lat1 = Double.parseDouble(givenLat);
        Double lon1 = Double.parseDouble(givenLon);
        Double lat2 = Double.parseDouble(branchLat);
        Double lon2 = Double.parseDouble(branchLon);
        Double latDistance = toRad(lat2-lat1);
        Double lonDistance = toRad(lon2-lon1);

        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        Double distance = R * c;

        logger.info("The distance between two lat and long is::" + distance);

        return distance;
    }

    public static Double getHaversineDistance(Coordinate reference, Coordinate given){

        final int R = 6371; // Radious of the earth

        Double lat1 = Double.parseDouble(reference.getLat());
        Double lon1 = Double.parseDouble(reference.getLon());
        Double lat2 = Double.parseDouble(given.getLat());
        Double lon2 = Double.parseDouble(given.getLon());
        Double latDistance = toRad(lat2-lat1);
        Double lonDistance = toRad(lon2-lon1);

        Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        Double distance = R * c;

        logger.info("The distance between two lat and long is::" + distance);

        return distance;
    }

    private static Double toRad(Double value) {
        return value * Math.PI / 180;
    }


}
