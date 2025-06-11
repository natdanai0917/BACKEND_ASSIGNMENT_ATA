package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_data")
public class JobDataEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("employer")
    private String employer;
    @JsonProperty("location")
    private String location;
    @JsonProperty("jobTitle")
    private String jobTitle;
    @JsonProperty("yearsAtEmployer")
    private String yearsAtEmployer;
    @JsonProperty("yearsOfExperience")
    private String yearsOfExperience;
    @JsonProperty("salary")
    private String salary;
    @JsonProperty("signingBonus")
    private String signingBonus;
    @JsonProperty("annualBonus")
    private String annualBonus;
    @JsonProperty("annualStockValueBonus")
    private String annualStockValueBonus;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("additionalComments")
    private String additionalComments;
}
