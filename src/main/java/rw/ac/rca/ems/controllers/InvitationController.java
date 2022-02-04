package rw.ac.rca.ems.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.ems.models.enums.EInvitationStatus;
import rw.ac.rca.ems.services.IInvitationService;
import rw.ac.rca.ems.utils.Constants;
import rw.ac.rca.ems.utils.Formatter;
import rw.ac.rca.ems.utils.Utility;
import rw.ac.rca.ems.utils.dtos.CreateInvitationsByTagDTO;
import rw.ac.rca.ems.utils.dtos.CreateOrUpdateInvitationDTO;
import rw.ac.rca.ems.utils.dtos.InviteManyDTO;
import rw.ac.rca.ems.utils.payload.ApiResponse;

import javax.validation.Valid;

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
    public ResponseEntity<ApiResponse> invite(@Valid @RequestBody CreateOrUpdateInvitationDTO dto) {
        return Formatter.ok(iInvitationService.invite(dto));
    }

    @PostMapping("invite-by-tag")
    public ResponseEntity<ApiResponse> inviteByTag(@Valid @RequestBody CreateInvitationsByTagDTO dto) {
        return Formatter.ok(iInvitationService.inviteByTag(dto));
    }

    @PostMapping("invite-many")
    public ResponseEntity<ApiResponse> inviteMany(@Valid @RequestBody InviteManyDTO dto) {
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
