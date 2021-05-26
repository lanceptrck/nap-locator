package com.devops3.naplocator.utils;

import com.devops3.naplocator.model.Branch;
import com.devops3.naplocator.model.Coordinate;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class HaversineDistanceTest {

    private List<Branch> branches = new ArrayList<>();
    private Coordinate bahay = new Coordinate("14.507301", "121.288814");
    private Map<Branch, Double> branchDistanceMap;

    @BeforeEach
    public void init() {
        branches = new ArrayList<>();
        branchDistanceMap = new LinkedHashMap<>();
        initBranches();
    }

    private void initBranches() {
        branches.add(new Branch("Max's Restaurant", "14.506324", "121.289577", "Tanay"));
        branches.add(new Branch("Kamalig Restaurant", "14.508100", "121.287778", "Tanay"));
        branches.add(new Branch("Kata Cafe", "14.505135", "121.290269", "Tanay"));
    }

    @Test
    public void tryHaversineDistanceRaw() {

        String bahayLat = "14.507301";
        String bahayLon = "121.288814";

        String kamaligLat = "14.508100";
        String kamaligLon = "121.287778";

        String maxLat = "14.506324";
        String maxLon = "121.289577";

        String kataLat = "14.505135";
        String kataLon = "14.505135";

        HaversineDistance.getHaversineDistance(bahayLat, bahayLon, kamaligLat, kamaligLon);


    }

    @Test
    public void tryHaversineDistanceCoordinates() {
        Map<Coordinate, Double> coordinateMap = new HashMap<Coordinate, Double>();

        Coordinate kamalig = new Coordinate("14.508100", "121.287778");
        Coordinate maxs = new Coordinate("14.506324", "121.289577");
        Coordinate kataCafe = new Coordinate("14.505135", "121.290269");

        coordinateMap.put(kamalig, HaversineDistance.getHaversineDistance(bahay, kamalig));
        coordinateMap.put(maxs, HaversineDistance.getHaversineDistance(bahay, maxs));
        coordinateMap.put(kataCafe, HaversineDistance.getHaversineDistance(bahay, kataCafe));

        coordinateMap = MapUtil.sortByValue(coordinateMap);

        System.out.println(coordinateMap);


    }

    @Test
    public void haversine_distance_test_on_branch() {
        branchDistanceMap.put(branches.get(0), HaversineDistance.getHaversineDistance(bahay, branches.get(0).getCoordinate()));
        branchDistanceMap.put(branches.get(1), HaversineDistance.getHaversineDistance(bahay, branches.get(1).getCoordinate()));
        branchDistanceMap.put(branches.get(2), HaversineDistance.getHaversineDistance(bahay, branches.get(2).getCoordinate()));

        branchDistanceMap = MapUtil.sortByValue(branchDistanceMap);

        System.out.println(branchDistanceMap.entrySet().iterator().next());
    }


}
