package com.devops3.naplocator.service;

import com.devops3.naplocator.model.Branch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private List<Branch> initialBranches = new ArrayList<>();

    public void initializeUsers() {
        initialBranches.add(new Branch("Max's Restaurant", "14.506324", "121.289577", "Tanay"));
        initialBranches.add(new Branch("Kamalig Restaurant", "14.508100", "121.287778", "Tanay"));
        initialBranches.add(new Branch("Kata Cafe", "14.505135", "121.290269", "Tanay"));
    }

    @Bean
    CommandLineRunner initBranches(BranchRepository repository) {
        initializeUsers();
        return args -> {
            for (Branch b : initialBranches) {
                log.info("Preloading " + repository.save(b));
            }
        };
    }


}
