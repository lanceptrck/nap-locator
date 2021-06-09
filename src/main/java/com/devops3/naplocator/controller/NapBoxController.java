package com.devops3.naplocator.controller;

import com.devops3.naplocator.dto.Data;
import com.devops3.naplocator.dto.EntityDTO;
import com.devops3.naplocator.dto.Status;
import com.devops3.naplocator.model.NapBox;
import com.devops3.naplocator.model.Coordinate;
import com.devops3.naplocator.service.NapBoxRepository;
import com.devops3.naplocator.utils.HaversineDistance;
import com.devops3.naplocator.utils.MapUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/branch")
public class NapBoxController {

    @Autowired
    private NapBoxRepository repository;

    private final Logger logger = LoggerFactory.getLogger(NapBoxController.class);

    public NapBoxController(NapBoxRepository repository) {
        this.repository = repository;

    }

    @Operation(summary = "Find the nearest nap box given a coordinate")
    @PostMapping("nearest")
    public ResponseEntity<EntityDTO> findNearestNapBox(@RequestBody(required = true) Coordinate coordinate) {

        logger.debug("Finding the nearest nap box of: ", coordinate);

        Map<NapBox, Double> branchDistanceMap = new LinkedHashMap<>();

        for (NapBox napBox : repository.findAll()) {
            branchDistanceMap.put(napBox, HaversineDistance.getHaversineDistance(coordinate, napBox.getCoordinate()));
        }

        branchDistanceMap = MapUtil.sortByValue(branchDistanceMap);

        Data d = new Data();
        NapBox b = branchDistanceMap.entrySet().iterator().next().getKey();
        logger.debug("The nearest branch was", b.toString());
        d.addBranches(b);

        EntityDTO dto = new EntityDTO<>();
        dto.setStatus(Status.SUCCESS);
        dto.addData(d);
        dto.setResponseCode(HttpStatus.OK.value());

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
