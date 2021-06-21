package com.devops3.naplocator.utils;

import com.devops3.naplocator.model.NapBox;
import com.devops3.naplocator.model.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class HaversineDistanceTest {

    private List<NapBox> napBoxes = new ArrayList<>();
    private Coordinate bahay = new Coordinate("14.507301", "121.288814");
    private Coordinate reliance = new Coordinate("14.57761", "121.07418");
    private Map<NapBox, Double> napBoxDistanceMap;

    @BeforeEach
    public void init() {
        napBoxes = new ArrayList<>();
        napBoxDistanceMap = new LinkedHashMap<>();
        initNapBoxes();
    }

    private void initNapBoxes() {
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
    public void haversine_distance_test_on_nap_box() {

        napBoxDistanceMap.put(napBoxes.get(0), HaversineDistance.getHaversineDistance(bahay, napBoxes.get(0).getCoordinate()));
        napBoxDistanceMap.put(napBoxes.get(1), HaversineDistance.getHaversineDistance(bahay, napBoxes.get(1).getCoordinate()));
        napBoxDistanceMap.put(napBoxes.get(2), HaversineDistance.getHaversineDistance(bahay, napBoxes.get(2).getCoordinate()));

        napBoxDistanceMap = MapUtil.sortByValue(napBoxDistanceMap);

        System.out.println(napBoxDistanceMap.entrySet().iterator().next());
        Map.Entry<NapBox, Double> entry = napBoxDistanceMap.entrySet().iterator().next();
        NapBox nBox = entry.getKey();
        Double hDistance = entry.getValue();

        System.out.println(nBox);
        if (hDistance.compareTo(0.3) < 0) {
            System.out.println("Serviceable");
        } else System.out.println("Unserviceable");

    }

    @Test
    public void deviation_distance() {
        final Coordinate cnvrg_OSM = new Coordinate("14.57761", "121.07418");
        final Coordinate cnvrg_GM = new Coordinate("14.5778682", "121.0740411");

        final Coordinate oap_OSM = new Coordinate("14.56679", "120.99275");
        final Coordinate oap_GM = new Coordinate("14.5666565", "120.9926058");

        final Coordinate gw_OSM = new Coordinate("14.62146", "121.05252");
        final Coordinate gw_GM = new Coordinate("14.6222136", "121.0504502");

        final Coordinate wr_OSM = new Coordinate("14.5601", "121.0573");
        final Coordinate wr_GM = new Coordinate("14.5610611", "121.0560644");

        final Coordinate vv_OSM = new Coordinate("14.57539", "121.06879");
        final Coordinate vv_GM = new Coordinate("14.5780336", "121.0701782");

        System.out.println(HaversineDistance.getHaversineDistance(cnvrg_OSM, cnvrg_GM) * 1000);
        System.out.println(HaversineDistance.getHaversineDistance(oap_OSM, oap_GM) * 1000);
        System.out.println(HaversineDistance.getHaversineDistance(gw_OSM, gw_GM) * 1000);
        System.out.println(HaversineDistance.getHaversineDistance(wr_OSM, wr_GM) * 1000);
        System.out.println(HaversineDistance.getHaversineDistance(vv_OSM, vv_GM) * 1000);
    }


}
