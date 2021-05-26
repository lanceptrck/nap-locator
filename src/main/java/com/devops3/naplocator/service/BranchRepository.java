package com.devops3.naplocator.service;

import com.devops3.naplocator.model.Branch;
import com.devops3.naplocator.model.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {


}
