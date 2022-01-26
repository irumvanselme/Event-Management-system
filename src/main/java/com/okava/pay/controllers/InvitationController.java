package com.okava.pay.controllers;

import com.okava.pay.models.enums.EInvitationStatus;
import com.okava.pay.services.IInvitationService;
import com.okava.pay.utils.Constants;
import com.okava.pay.utils.Formatter;
import com.okava.pay.utils.Utility;
import com.okava.pay.utils.dtos.CreateInvitationsByTagDTO;
import com.okava.pay.utils.dtos.CreateOrUpdateInvitationDTO;
import com.okava.pay.utils.dtos.InviteManyDTO;
import com.okava.pay.utils.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invitations")
public class InvitationController {

    private final IInvitationService iInvitationService;

    @Autowired
    public InvitationController(IInvitationService iInvitationService) {
        this.iInvitationService = iInvitationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findById(@PathVariable Long id) {
        return Formatter.ok(iInvitationService.findById(id));
    }

    @PostMapping("/invite")
    public ResponseEntity<ApiResponse> invite(CreateOrUpdateInvitationDTO dto) {
        return Formatter.ok(iInvitationService.invite(dto));
    }

    @PostMapping("invite-by-tag")
    public ResponseEntity<ApiResponse> inviteByTag(CreateInvitationsByTagDTO dto) {
        return Formatter.ok(iInvitationService.inviteByTag(dto));
    }

    @PostMapping("invite-many")
    public ResponseEntity<ApiResponse> inviteMany(InviteManyDTO dto) {
        return Formatter.ok(iInvitationService.inviteMany(dto));
    }

    @PutMapping("/{invitationId}/change-status/{status}")
    public ResponseEntity<ApiResponse> changeStatus(@PathVariable Long invitationId, @PathVariable EInvitationStatus status) {
        return Formatter.ok(iInvitationService.changeStatus(invitationId, status));
    }

    @PutMapping("/{invitationId}/accept")
    public ResponseEntity<ApiResponse> accept(@PathVariable Long invitationId) {
        return Formatter.ok(iInvitationService.accept(invitationId));
    }

    @PutMapping("/{invitationId}/decline")
    public ResponseEntity<ApiResponse> decline(@PathVariable Long invitationId) {
        return Formatter.ok(iInvitationService.decline(invitationId));
    }

    @GetMapping("in-event/{eventId}/by-status/{status}")
    public ResponseEntity<ApiResponse> inEventByStatus(@PathVariable Long eventId, @PathVariable EInvitationStatus status, @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit) {
        Pageable pageable = Utility.from(page, limit);

        return Formatter.ok(iInvitationService.inEventByStatus(eventId, status, pageable));
    }
}
