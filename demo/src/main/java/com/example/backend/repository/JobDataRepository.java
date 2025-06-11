package com.example.backend.repository;

import com.example.backend.entities.JobDataEntitiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface JobDataRepository extends JpaRepository<JobDataEntitiy, Long> {

}
