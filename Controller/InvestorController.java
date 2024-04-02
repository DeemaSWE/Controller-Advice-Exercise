package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Investor;
import com.example.capstone2.Service.InvestorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/investor")
@RequiredArgsConstructor
public class InvestorController {

    private final InvestorService investorService;
    Logger logger = LoggerFactory.getLogger(InvestorController.class);

    @GetMapping("/get-all")
    public ResponseEntity getAllInvestors(){
        logger.info("Getting all investors");
        return ResponseEntity.status(200).body(investorService.getAllInvestors());
    }

    @PostMapping("/add")
    public ResponseEntity addInvestor(@RequestBody @Valid Investor investor){
        logger.info("Adding investor");
        investorService.addInvestor(investor);
        return ResponseEntity.status(200).body(new ApiResponse("Investor added successfully"));
    }

    @PostMapping("/add-list")
    public ResponseEntity addInvestors(@RequestBody @Valid List<Investor> investors){
        logger.info("Adding list of investors");
        investorService.addListInvestors(investors);
        return ResponseEntity.status(200).body(new ApiResponse("Investors added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateInvestor(@PathVariable Integer id, @RequestBody @Valid Investor investor){
        logger.info("Updating investor");
        investorService.updateInvestor(investor, id);
        return ResponseEntity.status(200).body(new ApiResponse("Investor updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInvestor(@PathVariable Integer id){
        logger.info("Deleting investor");
        investorService.deleteInvestor(id);
        return ResponseEntity.status(200).body(new ApiResponse("Investor deleted successfully"));
    }

    // get personalized recommendations for projects based on investor preferences
    @GetMapping("/project-recommendations/{investorId}")
    public ResponseEntity getProjectRecommendations(@PathVariable Integer investorId) {
        logger.info("Getting project recommendations for investor");
        return ResponseEntity.status(200).body(investorService.recommendProjectsForInvestor(investorId));
    }


}
