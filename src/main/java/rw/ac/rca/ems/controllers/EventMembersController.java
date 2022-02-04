package rw.ac.rca.ems.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.ems.models.enums.EEventRole;
import rw.ac.rca.ems.models.enums.EEventStatus;
import rw.ac.rca.ems.services.IEventMemberService;
import rw.ac.rca.ems.utils.Constants;
import rw.ac.rca.ems.utils.Formatter;
import rw.ac.rca.ems.utils.Utility;
import rw.ac.rca.ems.utils.payload.ApiResponse;

@RestController
@RequestMapping("/api/v1/event-members")
public class EventMembersController {

    private final IEventMemberService memberService;

    @Autowired
    public EventMembersController(IEventMemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
        return Formatter.ok(memberService.findById(id));
    }

    @GetMapping("by-member/{userId}")
    public ResponseEntity<ApiResponse> byMemberUser(@PathVariable Long userId, @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {

        Pageable pageable = Utility.from(page, limit);

        return Formatter.ok(memberService.byMemberUser(userId, pageable));
    }

    @GetMapping("in-event/{eventId}")
    public ResponseEntity<ApiResponse> inEvent(@PathVariable Long eventId, @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {

        Pageable pageable = Utility.from(page, limit);

        return Formatter.ok(memberService.inEvent(eventId, pageable));
    }

    @PutMapping("/{eventMemberId}/change-role/{role}")
    public ResponseEntity<ApiResponse> updateRole(@PathVariable Long eventMemberId, @PathVariable EEventRole role) {
        return Formatter.ok(memberService.updateRole(eventMemberId, role));
    }

    @GetMapping("/by-user/{userId}/and-status/{status}")
    public ResponseEntity<ApiResponse> byMemberUserAndStatus(@PathVariable Long userId, @PathVariable EEventStatus status, @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {

        Pageable pageable = Utility.from(page, limit);

        return Formatter.ok(memberService.byMemberUserAndStatus(userId, status, pageable));
    }

    @GetMapping("in-event/{eventId}/by-role/{role}")
    public ResponseEntity<ApiResponse> inEventByRole(@PathVariable Long eventId, @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit, @PathVariable EEventRole role) {

        Pageable pageable = Utility.from(page, limit);

        return Formatter.ok(memberService.inEventByRole(eventId, pageable, role));
    }
}
