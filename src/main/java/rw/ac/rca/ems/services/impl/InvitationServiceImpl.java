package rw.ac.rca.ems.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.ac.rca.ems.models.Event;
import rw.ac.rca.ems.models.Invitation;
import rw.ac.rca.ems.models.Tag;
import rw.ac.rca.ems.models.User;
import rw.ac.rca.ems.models.enums.EInvitationStatus;
import rw.ac.rca.ems.repositories.IInvitationRepository;
import rw.ac.rca.ems.services.*;
import rw.ac.rca.ems.utils.dtos.CreateInvitationsByTagDTO;
import rw.ac.rca.ems.utils.dtos.CreateOrUpdateInvitationDTO;
import rw.ac.rca.ems.utils.dtos.InviteManyDTO;
import rw.ac.rca.ems.utils.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvitationServiceImpl implements IInvitationService {

    private final IInvitationRepository iInvitationRepository;

    private final IEventMemberService eventMemberService;
    private final IEventService eventService;
    private final IUserService userService;
    private final ITagService tagService;

    @Autowired
    public InvitationServiceImpl(IInvitationRepository iInvitationRepository, IEventMemberService eventMemberService, IEventService eventService, IUserService userService, ITagService tagService) {
        this.iInvitationRepository = iInvitationRepository;
        this.eventMemberService = eventMemberService;
        this.eventService = eventService;
        this.userService = userService;
        this.tagService = tagService;
    }

    @Override
    public Invitation findById(Long id) {
        return iInvitationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invitation", "id", id.toString()));
    }

    @Override
    public Invitation invite(CreateOrUpdateInvitationDTO dto) {
        Event event = eventService.findById(dto.getEventId());
        User user = userService.findById(dto.getUserId());
        return iInvitationRepository.save(new Invitation(event, user, dto.getMessage(), dto.getRole()));
    }

    @Override
    public List<Invitation> inviteByTag(CreateInvitationsByTagDTO dto) {
        List<Invitation> invitations = new ArrayList<>();

        Event event = eventService.findById(dto.getEventId());
        Tag tag = tagService.findById(dto.getTagId());

        for (User user : tag.getUsers())
            invitations.add(iInvitationRepository.save(new Invitation(event, user, dto.getMessage(), dto.getRole())));

        return invitations;
    }

    @Override
    public List<Invitation> inviteMany(InviteManyDTO dto) {
        List<Invitation> invitations = new ArrayList<>();

        Event event = eventService.findById(dto.getEventId());
        List<User> users = userService.findMany(dto.getUserIds());

        for (User user : users)
            invitations.add(iInvitationRepository.save(new Invitation(event, user, dto.getMessage(), dto.getRole())));

        return invitations;
    }

    @Override
    public Invitation changeStatus(Long invitationId, EInvitationStatus status) {
        Invitation invitation = findById(invitationId);

        invitation.setStatus(status);

        return iInvitationRepository.save(invitation);
    }

    @Override
    public Invitation accept(Long invitationId) {
        Invitation invitation = findById(invitationId);

        invitation.setStatus(EInvitationStatus.ACCEPTED);

        eventMemberService.create(invitation, invitation.getRole());

        return iInvitationRepository.save(invitation);
    }

    @Override
    public Invitation decline(Long invitationId) {
        Invitation invitation = findById(invitationId);

        invitation.setStatus(EInvitationStatus.REJECTED);

        return iInvitationRepository.save(invitation);
    }

    @Override
    public Page<Invitation> inEventByStatus(Long eventId, EInvitationStatus status, Pageable pageable) {
        Event event = eventService.findById(eventId);

        return iInvitationRepository.findByEventAndStatus(event, status, pageable);
    }
}
