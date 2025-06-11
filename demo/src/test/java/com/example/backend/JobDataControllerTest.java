package com.example.backend;

import com.example.backend.dto.JobDataDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class JobDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetJobDataIntegration() throws Exception {
        mockMvc.perform(get("/getJobData")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testFilterByJobTitle() throws Exception {
        mockMvc.perform(get("/getJobData")
                .param("job_title", "Software Engineer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jobTitle").value("Software Engineer"));
    }

    @Test
    public void testFilterBySalaryGte() throws Exception {
        mockMvc.perform(get("/getJobData")
                .param("salary[gte]", "120000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].salary").exists());
    }

    @Test
    public void testFilterByGender() throws Exception {
        mockMvc.perform(get("/getJobData")
                .param("gender", "Male")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].gender").value("Male"));
    }

    @Test
    public void testFilterByMultipleFields() throws Exception {
        mockMvc.perform(get("/getJobData")
                .param("job_title", "Software Engineer")
                .param("gender", "Male")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jobTitle").value("Software Engineer"))
                .andExpect(jsonPath("$[0].gender").value("Male"));
    }

    @Test
    public void testSparseFields() throws Exception {
        mockMvc.perform(get("/getJobData")
                .param("fields", "jobTitle,salary")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].jobTitle").exists())
                .andExpect(jsonPath("$[0].salary").exists())
                .andExpect(jsonPath("$[0].gender").doesNotExist());
    }

    @Test
    public void testSortByJobTitleDesc() throws Exception {
        mockMvc.perform(get("/getJobData")
                .param("sort", "job_title")
                .param("sort_type", "DESC")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testFilterReturnsEmpty() throws Exception {
        mockMvc.perform(get("/getJobData")
                .param("job_title", "Nonexistent Title")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void testInvalidParamReturnsOkOrEmpty() throws Exception {
        mockMvc.perform(get("/getJobData")
                .param("invalid_param", "value")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
