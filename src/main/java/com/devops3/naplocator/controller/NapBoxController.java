package com.devops3.naplocator.controller;

import com.devops3.naplocator.dto.ErrorDTO;
import com.devops3.naplocator.dto.NapBoxDTO;
import com.devops3.naplocator.dto.Status;
import com.devops3.naplocator.exception.EmptyFieldException;
import com.devops3.naplocator.model.NapBox;
import com.devops3.naplocator.model.Coordinate;
import com.devops3.naplocator.service.NapBoxRepository;
import com.devops3.naplocator.utils.HaversineDistance;
import com.devops3.naplocator.utils.MapUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/napbox")
public class NapBoxController {

    @Autowired
    private NapBoxRepository repository;

    private final Logger logger = LoggerFactory.getLogger(NapBoxController.class);

    public NapBoxController(NapBoxRepository repository) {
        this.repository = repository;

    }

    @Operation(summary = "Find the nearest nap box given a coordinate")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the nearest nap box",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = NapBoxDTO.class))}),
            @ApiResponse(responseCode = "406", description = "Unacceptable coordinates",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class))})
    })

    @PostMapping("nearest")
    public ResponseEntity<?> findNearestNapBox(@RequestBody(required = true) Coordinate coordinate) {

        logger.debug("Finding the nearest nap box of: ", coordinate);

        Map<NapBox, Double> branchDistanceMap = new LinkedHashMap<>();

        for (NapBox napBox : repository.findAll()) {
            branchDistanceMap.put(napBox, HaversineDistance.getHaversineDistance(coordinate, napBox.getCoordinate()));
        }

        branchDistanceMap = MapUtil.sortByValue(branchDistanceMap);

        Map.Entry<NapBox, Double> entry = branchDistanceMap.entrySet().iterator().next();
        Double hDistance = entry.getValue();
        NapBox nearest = entry.getKey();
        logger.debug("The nearest branch was", nearest);

        NapBoxDTO napBoxDTO = new NapBoxDTO();
        napBoxDTO.setStatus(Status.SUCCESS);
        napBoxDTO.addData(nearest);
        if (hDistance.compareTo(0.3) < 0) {
            napBoxDTO.setServiceable(true);
        } else
            napBoxDTO.setServiceable(false);
        napBoxDTO.setResponseCode(HttpStatus.OK.value());

        return new ResponseEntity<>(napBoxDTO, HttpStatus.OK);
    }

    @GetMapping("nearest")
    public ResponseEntity<?> getNearestNapBox(@RequestParam(name = "latitude", required = true) String latitude, @RequestParam(name = "longitude", required = true) String longitude) {

        if(latitude.isEmpty() || longitude.isEmpty()){
            throw new EmptyFieldException("Latitude or longitude cannot be empty");
        }

        if(!StringUtils.isNumeric(latitude) || !StringUtils.isNumeric(longitude)){
            throw new NumberFormatException("Latitude and Longitude must be a number.");
        }

        Coordinate coordinate = new Coordinate(latitude, longitude);

        logger.debug("Finding the nearest nap box of: ", coordinate);

        Map<NapBox, Double> branchDistanceMap = new LinkedHashMap<>();

        for (NapBox napBox : repository.findAll()) {
            branchDistanceMap.put(napBox, HaversineDistance.getHaversineDistance(coordinate, napBox.getCoordinate()));
        }

        branchDistanceMap = MapUtil.sortByValue(branchDistanceMap);

        Map.Entry<NapBox, Double> entry = branchDistanceMap.entrySet().iterator().next();
        Double hDistance = entry.getValue();
        NapBox nearest = entry.getKey();
        logger.debug("The nearest branch was", nearest);

        NapBoxDTO napBoxDTO = new NapBoxDTO();
        napBoxDTO.setStatus(Status.SUCCESS);
        napBoxDTO.addData(nearest);
        if (hDistance.compareTo(0.3) < 0) {
            napBoxDTO.setServiceable(true);
        } else
            napBoxDTO.setServiceable(false);
        napBoxDTO.setResponseCode(HttpStatus.OK.value());

        return new ResponseEntity<>(napBoxDTO, HttpStatus.OK);
    }

}
