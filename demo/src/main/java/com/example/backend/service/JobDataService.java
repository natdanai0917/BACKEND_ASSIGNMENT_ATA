package com.example.backend.service;

import com.example.backend.dto.JobDataDto;
import com.example.backend.entities.JobDataEntitiy;
import com.example.backend.repository.JobDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobDataService {

    private final JobDataRepository jobDataRepository;

    public JobDataService(JobDataRepository jobDataRepository) {
        this.jobDataRepository = jobDataRepository;
    }

    public List<JobDataDto> getAllJobData() {
        List<JobDataEntitiy> entities = jobDataRepository.findAll();
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }

    private JobDataDto toDto(JobDataEntitiy entity) {
        JobDataDto dto = new JobDataDto();
        dto.setTimestamp(entity.getTimestamp());
        dto.setEmployer(entity.getEmployer());
        dto.setLocation(entity.getLocation());
        dto.setJobTitle(entity.getJobTitle());
        dto.setYearsAtEmployer(entity.getYearsAtEmployer());
        dto.setYearsOfExperience(entity.getYearsOfExperience());
        dto.setSalary(entity.getSalary());
        dto.setSigningBonus(entity.getSigningBonus());
        dto.setAnnualBonus(entity.getAnnualBonus());
        dto.setAnnualStockValueBonus(entity.getAnnualStockValueBonus());
        dto.setGender(entity.getGender());
        dto.setAdditionalComments(entity.getAdditionalComments());
        return dto;
    }
}
