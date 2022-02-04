package rw.ac.rca.ems.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ac.rca.ems.models.EventMember;
import rw.ac.rca.ems.models.Invitation;
import rw.ac.rca.ems.models.enums.EEventRole;
import rw.ac.rca.ems.models.enums.EEventStatus;

public interface IEventMemberService {

    EventMember create(Invitation invitation, EEventRole role);

    EventMember findById(Long id);

    Page<EventMember> byMemberUser(Long userId, Pageable pageable);

    Page<EventMember> inEvent(Long eventId, Pageable pageable);

    EventMember updateRole(Long eventMemberId, EEventRole role);

    Page<EventMember> byMemberUserAndStatus(Long userId, EEventStatus status, Pageable pageable);

    Page<EventMember> inEventByRole(Long eventId, Pageable pageable, EEventRole role);
}
