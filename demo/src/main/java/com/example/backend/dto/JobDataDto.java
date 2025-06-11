package com.example.backend.dto;

import lombok.Data;

@Data
public class JobDataDto {
    private String timestamp;
    private String employer;
    private String location;
    private String jobTitle;
    private String yearsAtEmployer;
    private String yearsOfExperience;
    private String salary;
    private String signingBonus;
    private String annualBonus;
    private String annualStockValueBonus;
    private String gender;
    private String additionalComments;
}
