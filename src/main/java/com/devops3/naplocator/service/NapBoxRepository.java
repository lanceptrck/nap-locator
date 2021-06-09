package com.devops3.naplocator.service;

import com.devops3.naplocator.model.NapBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NapBoxRepository extends JpaRepository<NapBox, Long> {


}
