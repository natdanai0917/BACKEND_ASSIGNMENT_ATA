package com.example.backend.service;

import com.example.backend.dto.JobDataDto;
import com.example.backend.entities.JobDataEntitiy;
import com.example.backend.repository.JobDataRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct; // For Spring Boot 3.x
// import javax.annotation.PostConstruct; // For Spring Boot 2.x
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class DataLoaderService {

    @Autowired
    private JobDataRepository jobDataRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void loadData() {
        // Check if data already exists
        if (jobDataRepository.count() > 0) {
            System.out.println("Data already exists. Skipping data load.");
            return;
        }

        try {
            // Read JSON file from resources
            ClassPathResource resource = new ClassPathResource("datasource/salary_survey-3.json");
            InputStream inputStream = resource.getInputStream();

            // Parse JSON to List of JobDataDto
            List<JobDataDto> dtoList = objectMapper.readValue(inputStream,
                    new TypeReference<List<JobDataDto>>() {});

            // Map DTOs to Entities
            List<JobDataEntitiy> entityList = dtoList.stream().map(dto -> {
                JobDataEntitiy entity = new JobDataEntitiy();
                entity.setTimestamp(dto.getTimestamp());
                entity.setEmployer(dto.getEmployer());
                entity.setLocation(dto.getLocation());
                entity.setJobTitle(dto.getJobTitle());
                entity.setYearsAtEmployer(dto.getYearsAtEmployer());
                entity.setYearsOfExperience(dto.getYearsOfExperience());
                entity.setSalary(dto.getSalary());
                entity.setSigningBonus(dto.getSigningBonus());
                entity.setAnnualBonus(dto.getAnnualBonus());
                entity.setAnnualStockValueBonus(dto.getAnnualStockValueBonus());
                entity.setGender(dto.getGender());
                entity.setAdditionalComments(dto.getAdditionalComments());
                return entity;
            }).toList();

            // Save to database
            jobDataRepository.saveAll(entityList);

            System.out.println("Data loaded successfully: " + entityList.size() + " salary records");

        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
