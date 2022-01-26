package com.okava.pay.services;

import com.okava.pay.models.EventMember;
import com.okava.pay.models.Invitation;
import com.okava.pay.models.enums.EEventRole;
import com.okava.pay.models.enums.EEventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEventMemberService {

    EventMember create(Invitation invitation, EEventRole role);

    EventMember findById(Long id);

    Page<EventMember> byMemberUser(Long userId, Pageable pageable);

    Page<EventMember> inEvent(Long eventId, Pageable pageable);

    EventMember updateRole(Long eventMemberId, EEventRole role);

    Page<EventMember> byMemberUserAndStatus(Long userId, EEventStatus status, Pageable pageable);

    Page<EventMember> inEventByRole(Long eventId, Pageable pageable, EEventRole role);
}
