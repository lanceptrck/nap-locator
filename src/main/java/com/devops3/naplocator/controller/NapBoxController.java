package com.devops3.naplocator.controller;

import com.devops3.naplocator.dto.ErrorDTO;
import com.devops3.naplocator.dto.NapBoxDTO;
import com.devops3.naplocator.dto.Status;
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
            @ApiResponse(responseCode = "200", description = "Found the account",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = NapBoxDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Account doesn't exist",
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

        NapBox b = branchDistanceMap.entrySet().iterator().next().getKey();
        logger.debug("The nearest branch was", b.toString());

        NapBoxDTO napBoxDTO = new NapBoxDTO();
        napBoxDTO.setStatus(Status.SUCCESS);
        napBoxDTO.addData(b);
        napBoxDTO.setResponseCode(HttpStatus.OK.value());


        return new ResponseEntity<>(napBoxDTO, HttpStatus.OK);
    }

}
