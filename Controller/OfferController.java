package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Offer;
import com.example.capstone2.Service.OfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/v1/offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;
    Logger logger = LoggerFactory.getLogger(OfferController.class);

    @GetMapping("/get-all")
    public ResponseEntity getAllOffers(){
        logger.info("Getting all offers");
        return ResponseEntity.status(200).body(offerService.getAllOffers());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateOffer(@PathVariable Integer id, @RequestBody @Valid Offer offer){
        logger.info("Updating offer");
        offerService.updateOffer(offer, id);
        return ResponseEntity.status(200).body(new ApiResponse("Offer updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteOffer(@PathVariable Integer id){
        logger.info("Deleting offer");
        offerService.deleteOffer(id);
        return ResponseEntity.status(200).body(new ApiResponse("Offer deleted successfully"));
    }

    // Student can accept or reject an investor funding offer for a project
    @PutMapping("/set-status/{offerId}/{status}")
    public ResponseEntity setStatus(@PathVariable Integer offerId, @PathVariable String status){
        logger.info("Setting status for offer");
        offerService.setStatus(offerId, status);
        return ResponseEntity.status(200).body(new ApiResponse("Status updated successfully"));
    }

    // Investor can send funding offer to a student project
    @PostMapping("/send")
    public ResponseEntity sendOffer(@RequestBody @Valid Offer offer){
        logger.info("Sending offer");
        offerService.sendOffer(offer);
        return ResponseEntity.status(200).body(new ApiResponse("Offer sent successfully"));
    }

    // get all offers for a project
    @GetMapping("/get-project-offers/{projectId}")
    public ResponseEntity getOffersForProject(@PathVariable Integer projectId){
        logger.info("Getting offers for project");
        return ResponseEntity.status(200).body(offerService.getOffersForProject(projectId));
    }

    // get all pending offers for a project
    @GetMapping("/get-pending-offers/{projectId}")
    public ResponseEntity getPendingOffersForProject(@PathVariable Integer projectId){
        logger.info("Getting pending offers for project");
        return ResponseEntity.status(200).body(offerService.getPendingOffersForProject(projectId));
    }

}