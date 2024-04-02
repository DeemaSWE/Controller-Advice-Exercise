package com.example.capstone2.Controller;

import com.example.capstone2.Api.ApiResponse;
import com.example.capstone2.Model.Event;
import com.example.capstone2.Service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    Logger logger = LoggerFactory.getLogger(EventController.class);

    @GetMapping("/get-all")
    public ResponseEntity getAllEvents() {
        logger.info("Getting all events");
        return ResponseEntity.status(200).body(eventService.getAllEvents());
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event) {
        logger.info("Adding event");
        eventService.addEvent(event);
        return ResponseEntity.status(200).body(new ApiResponse("Event added successfully"));
    }

    @PostMapping("/add-list")
    public ResponseEntity addEvents(@RequestBody @Valid List<Event> events) {
        logger.info("Adding list of events");
        eventService.addListEvents(events);
        return ResponseEntity.status(200).body(new ApiResponse("Events added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEvent(@PathVariable Integer id, @RequestBody @Valid Event event) {
        logger.info("Updating event");
        eventService.updateEvent(event, id);
        return ResponseEntity.status(200).body(new ApiResponse("Event updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEvent(@PathVariable Integer id) {
        logger.info("Deleting event");
        eventService.deleteEvent(id);
        return ResponseEntity.status(200).body(new ApiResponse("Event deleted successfully"));
    }

    // get a list of upcoming events based on the current date.
    @GetMapping("/upcoming")
    public ResponseEntity getUpcomingEvents() {
        logger.info("Getting upcoming events");
        return ResponseEntity.ok(eventService.getUpcomingEvents());
    }

}