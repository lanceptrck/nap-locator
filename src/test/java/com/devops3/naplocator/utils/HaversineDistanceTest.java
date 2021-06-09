package com.devops3.naplocator.utils;

import com.devops3.naplocator.model.NapBox;
import com.devops3.naplocator.model.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class HaversineDistanceTest {

    private List<NapBox> napBoxes = new ArrayList<>();
    private Coordinate bahay = new Coordinate("14.507301", "121.288814");
    private Map<NapBox, Double> branchDistanceMap;

    @BeforeEach
    public void init() {
        napBoxes = new ArrayList<>();
        branchDistanceMap = new LinkedHashMap<>();
        initBranches();
    }

    private void initBranches() {
        napBoxes.add(new NapBox("Max's Restaurant", "14.506324", "121.289577", "Tanay"));
        napBoxes.add(new NapBox("Kamalig Restaurant", "14.508100", "121.287778", "Tanay"));
        napBoxes.add(new NapBox("Kata Cafe", "14.505135", "121.290269", "Tanay"));
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
        branchDistanceMap.put(napBoxes.get(0), HaversineDistance.getHaversineDistance(bahay, napBoxes.get(0).getCoordinate()));
        branchDistanceMap.put(napBoxes.get(1), HaversineDistance.getHaversineDistance(bahay, napBoxes.get(1).getCoordinate()));
        branchDistanceMap.put(napBoxes.get(2), HaversineDistance.getHaversineDistance(bahay, napBoxes.get(2).getCoordinate()));

        branchDistanceMap = MapUtil.sortByValue(branchDistanceMap);

        System.out.println(branchDistanceMap.entrySet().iterator().next());
    }


}
