package com.devops3.naplocator.controller;

import com.devops3.naplocator.dto.Data;
import com.devops3.naplocator.dto.EntityDTO;
import com.devops3.naplocator.dto.Status;
import com.devops3.naplocator.model.Branch;
import com.devops3.naplocator.model.Coordinate;
import com.devops3.naplocator.service.BranchRepository;
import com.devops3.naplocator.utils.HaversineDistance;
import com.devops3.naplocator.utils.MapUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class BranchController {

    @Autowired
    private BranchRepository repository;

    private final Logger logger = LoggerFactory.getLogger(BranchController.class);

    public BranchController(BranchRepository repository) {
        this.repository = repository;

    }

    @PostMapping("/branch/nearest")
    public ResponseEntity<EntityDTO> findNearestBranch(@RequestBody Coordinate coordinate) {

        logger.info("findingNearestBranch");

        Map<Branch, Double> branchDistanceMap = new LinkedHashMap<>();

        for (Branch branch : repository.findAll()) {
            branchDistanceMap.put(branch, HaversineDistance.getHaversineDistance(coordinate, branch.getCoordinate()));
        }

        branchDistanceMap = MapUtil.sortByValue(branchDistanceMap);

        Data d = new Data();
        Branch b = branchDistanceMap.entrySet().iterator().next().getKey();
        logger.info(b.toString());
        d.addBranches(b);

        EntityDTO dto = new EntityDTO<>();
        dto.setStatus(Status.SUCCESS);
        dto.addData(d);
        dto.setResponseCode(HttpStatus.CREATED.value());

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

}
