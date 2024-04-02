package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Project;
import com.example.capstone2.Service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @GetMapping("/get-all")
    public ResponseEntity getAllProjects(){
        logger.info("Getting all projects");
        return ResponseEntity.status(200).body(projectService.getAllProjects());
    }

    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid Project project){
        logger.info("Adding a project");
        projectService.addProject(project);
        return ResponseEntity.status(200).body(new ApiResponse("Project added successfully"));
    }

    @PostMapping("/add-list")
    public ResponseEntity addProjects(@RequestBody @Valid List<Project> projects){
        logger.info("Adding a list of projects");
        projectService.addListProjects(projects);
        return ResponseEntity.status(200).body(new ApiResponse("Projects added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProject(@PathVariable Integer id, @RequestBody @Valid Project project){
        logger.info("Updating a project");
        projectService.updateProject(project, id);
        return ResponseEntity.status(200).body(new ApiResponse("Project updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProject(@PathVariable Integer id){
        logger.info("Deleting a project");
        projectService.deleteProject(id);
        return ResponseEntity.status(200).body(new ApiResponse("Project deleted successfully"));
    }

    // search for projects based on various criteria such as project title, description, category, university, and city
    @GetMapping("/search/{searchTerm}")
    public ResponseEntity searchProjects(@PathVariable String searchTerm){
        logger.info("Searching for projects");
        return ResponseEntity.status(200).body(projectService.searchProjects(searchTerm));
    }

    // get all projects for a student
    @GetMapping("/get-student-projects/{studentId}")
    public ResponseEntity getStudentProjects(@PathVariable Integer studentId){
        logger.info("Getting all projects for a student");
        return ResponseEntity.status(200).body(projectService.getStudentProjects(studentId));
    }

    // filter projects by funding range
    @GetMapping("/filter-by-funding/{min}/{max}")
    public ResponseEntity filterProjectsByFunding(@PathVariable Double min, @PathVariable Double max){
        logger.info("Filtering projects by funding range");
        return ResponseEntity.status(200).body(projectService.filterProjectsByFunding(min, max));
    }

    // filter projects by category
    @GetMapping("/filter-by-category/{category}")
    public ResponseEntity filterProjectsByCategory(@PathVariable String category){
        logger.info("Filtering projects by category");
        return ResponseEntity.status(200).body(projectService.filterProjectsByCategory(category));
    }

    // filter projects by university
    @GetMapping("/filter-by-university/{university}")
    public ResponseEntity filterProjectsByUniversity(@PathVariable String university){
        logger.info("Filtering projects by university");
        return ResponseEntity.status(200).body(projectService.filterProjectsByUniversity(university));
    }


}
