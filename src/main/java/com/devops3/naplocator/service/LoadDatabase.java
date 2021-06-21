package com.devops3.naplocator.service;

import com.devops3.naplocator.model.NapBox;
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
    private List<NapBox> initialNapBoxes = new ArrayList<>();



    public void initializeUsers() {
        initialNapBoxes.add(new NapBox("NB MX-325", "14.506324", "121.289577", "Tanay"));
        initialNapBoxes.add(new NapBox("NB KR-198", "14.508100", "121.287778", "Tanay"));
        initialNapBoxes.add(new NapBox("NB KC-823", "14.505135", "121.290269", "Tanay"));
    }

    @Bean
    CommandLineRunner initBranches(NapBoxRepository repository) {
        initializeUsers();
        return args -> {
            for (NapBox b : initialNapBoxes) {
                log.info("Preloading " + repository.save(b));
            }
        };
    }


}
