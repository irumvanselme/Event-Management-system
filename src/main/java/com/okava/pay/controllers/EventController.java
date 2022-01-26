package com.okava.pay.controllers;

import com.okava.pay.models.enums.EEventStatus;
import com.okava.pay.services.IEventService;
import com.okava.pay.utils.Constants;
import com.okava.pay.utils.Formatter;
import com.okava.pay.utils.Utility;
import com.okava.pay.utils.dtos.CreateOrUpdateEventDTO;
import com.okava.pay.utils.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final IEventService eventService;

    @Autowired
    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> all() {
        return Formatter.ok(eventService.all());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
        return Formatter.ok(eventService.findById(id));
    }

    @GetMapping("/created-by-me")
    public ResponseEntity<ApiResponse> createdByMe() {
        return Formatter.ok(eventService.createdByMe());
    }

    @GetMapping("/created-by/{userId}")
    public ResponseEntity<ApiResponse> createdBy(@PathVariable Long userId) {
        return Formatter.ok(eventService.createdBy(userId));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> search(@RequestParam(defaultValue = "") String title) {
        return Formatter.ok(eventService.search(title));
    }

    @GetMapping("/happening-between")
    public ResponseEntity<ApiResponse> between(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return Formatter.ok(eventService.between(start, end));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody CreateOrUpdateEventDTO dto) {
        return Formatter.created(eventService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Long id, @Valid @RequestBody CreateOrUpdateEventDTO dto) {
        return Formatter.ok(eventService.update(id, dto));
    }

    @PutMapping("/{id}/change-status/{newStatus}")
    public ResponseEntity<ApiResponse> changeStatus(@PathVariable Long id, @PathVariable EEventStatus newStatus) {
        return Formatter.ok(eventService.changeStatus(id, newStatus));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> all(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        Pageable pageable = Utility.from(page, limit);

        return Formatter.ok(eventService.all(pageable));
    }

    @GetMapping("/by-status/{status}/all")
    public ResponseEntity<ApiResponse> byStatus(@PathVariable EEventStatus status) {
        return Formatter.ok(eventService.byStatus(status));
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<ApiResponse> byStatus(@PathVariable EEventStatus status, @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        Pageable pageable = Utility.from(page, limit);

        return Formatter.ok(eventService.byStatus(status, pageable));
    }
}
