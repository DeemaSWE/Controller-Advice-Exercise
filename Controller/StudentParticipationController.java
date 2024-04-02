package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.StudentParticipation;
import com.example.capstone2.Service.StudentParticipationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/student/event")
@RequiredArgsConstructor
public class StudentParticipationController {

    private final StudentParticipationService studentParticipationService;
    Logger logger = LoggerFactory.getLogger(StudentParticipationController.class);

    @GetMapping("/get-all")
    public ResponseEntity getAllStudentParticipation(){
        logger.info("Getting all student participation");
        return ResponseEntity.status(200).body(studentParticipationService.getAllStudentParticipation());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudentParticipation(@PathVariable Integer id, @RequestBody @Valid StudentParticipation studentParticipation){
        logger.info("Updating student participation");
        studentParticipationService.updateStudentParticipation(studentParticipation, id);
        return ResponseEntity.status(200).body(new ApiResponse("Student Participation updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudentParticipation(@PathVariable Integer id){
        logger.info("Deleting student participation");
        studentParticipationService.deleteStudentParticipation(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student Participation deleted successfully"));
    }

    // student can participate in an event
    @PostMapping("/participate")
    public ResponseEntity studentParticipateInEvent(@RequestBody @Valid StudentParticipation studentParticipation){
        logger.info("Student participating in event");
        studentParticipationService.studentParticipateInEvent(studentParticipation);
        return ResponseEntity.status(200).body(new ApiResponse("Student successfully participated in event"));
    }

    // get all student participants for an event
    @GetMapping("/get-participants/{eventId}")
    public ResponseEntity getEventParticipants(@PathVariable Integer eventId){
        logger.info("Getting all student participants for an event");
        return ResponseEntity.status(200).body(studentParticipationService.getEventParticipants(eventId));
    }

}