package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "job_data")
public class JobDataEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("Timestamp")
    private String timestamp;

    @JsonProperty("Employer")
    private String employer;

    @JsonProperty("Location")
    private String location;

    @JsonProperty("Job Title")
    private String jobTitle;

    @JsonProperty("Years at Employer")
    private String yearsAtEmployer;

    @JsonProperty("Years of Experience")
    private String yearsOfExperience;

    @JsonProperty("Salary")
    private String salary;

    @JsonProperty("Signing Bonus")
    private String signingBonus;

    @JsonProperty("Annual Bonus")
    private String annualBonus;

    @JsonProperty("Annual Stock Value/Bonus")
    private String annualStockValueBonus;

    @JsonProperty("Gender")
    private String gender;

    @JsonProperty("Additional Comments")
    @jakarta.persistence.Column(length = 2000)
    private String additionalComments;
}
