package rw.ac.rca.ems.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rw.ac.rca.ems.models.Event;
import rw.ac.rca.ems.models.EventMember;
import rw.ac.rca.ems.models.Invitation;
import rw.ac.rca.ems.models.User;
import rw.ac.rca.ems.models.enums.EEventRole;
import rw.ac.rca.ems.models.enums.EEventStatus;
import rw.ac.rca.ems.repositories.IMemberRepository;
import rw.ac.rca.ems.services.IEventMemberService;
import rw.ac.rca.ems.services.IEventService;
import rw.ac.rca.ems.services.IUserService;
import rw.ac.rca.ems.utils.exceptions.ResourceNotFoundException;

@Service
public class EventMemberServiceImpl implements IEventMemberService {

    private final IMemberRepository memberRepository;

    private final IUserService userService;
    private final IEventService eventService;

    @Autowired
    public EventMemberServiceImpl(IMemberRepository memberRepository, IUserService userService, IEventService eventService) {
        this.memberRepository = memberRepository;
        this.userService = userService;
        this.eventService = eventService;
    }

    @Override
    public EventMember create(Invitation invitation, EEventRole role) {
        return memberRepository.save(new EventMember(invitation, role));
    }

    @Override
    public EventMember findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event Member", "id", id.toString()));
    }

    @Override
    public Page<EventMember> byMemberUser(Long userId, Pageable pageable) {
        User user = userService.findById(userId);

        return memberRepository.findByInvitation_User(user, pageable);
    }

    @Override
    public Page<EventMember> inEvent(Long eventId, Pageable pageable) {
        Event event = eventService.findById(eventId);

        return memberRepository.findByInvitation_Event(event, pageable);
    }

    @Override
    public EventMember updateRole(Long eventMemberId, EEventRole role) {
        EventMember member = findById(eventMemberId);

        member.setRole(role);

        return memberRepository.save(member);
    }

    @Override
    public Page<EventMember> byMemberUserAndStatus(Long userId, EEventStatus status, Pageable pageable) {
        User user = userService.findById(userId);

        return memberRepository.findByInvitation_UserAndInvitation_Event_Status(user, status, pageable);
    }

    @Override
    public Page<EventMember> inEventByRole(Long eventId, Pageable pageable, EEventRole role) {
        Event event = eventService.findById(eventId);

        return memberRepository.findByInvitation_EventAndRole(event, role, pageable);
    }
}
