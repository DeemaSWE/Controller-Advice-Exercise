package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.InvestorParticipation;
import com.example.capstone2.Service.InvestorParticipationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/v1/investor/event")
@RequiredArgsConstructor
public class InvestorParticipationController {

    private final InvestorParticipationService investorParticipationService;
    Logger logger = LoggerFactory.getLogger(InvestorParticipationController.class);

    @GetMapping("/get-all")
    public ResponseEntity getAllInvestorParticipation(){
        logger.info("Getting all investor participation");
        return ResponseEntity.status(200).body(investorParticipationService.getAllInvestorParticipation());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateInvestorParticipation(@PathVariable Integer id, @RequestBody @Valid InvestorParticipation investorParticipation){
        logger.info("Updating investor participation");
        investorParticipationService.updateInvestorParticipation(investorParticipation, id);
        return ResponseEntity.status(200).body(new ApiResponse("Investor Participation updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInvestorParticipation(@PathVariable Integer id){
        logger.info("Deleting investor participation");
        investorParticipationService.deleteInvestorParticipation(id);
        return ResponseEntity.status(200).body(new ApiResponse("Investor Participation deleted successfully"));
    }

    // investor can participate in an event
    @PostMapping("/participate")
    public ResponseEntity investorParticipateInEvent(@RequestBody @Valid InvestorParticipation investorParticipation){
        logger.info("Investor participating in event");
        investorParticipationService.investorParticipateInEvent(investorParticipation);
        return ResponseEntity.status(200).body(new ApiResponse("Investor successfully participated in event"));
    }

    // get all investor participants for an event
    @GetMapping("/get-participants/{eventId}")
    public ResponseEntity getEventParticipants(@PathVariable Integer eventId){
        logger.info("Getting all investor participants for an event");
        return ResponseEntity.status(200).body(investorParticipationService.getEventParticipants(eventId));
    }
}