package com.okava.pay.repositories;

import com.okava.pay.models.Event;
import com.okava.pay.models.EventMember;
import com.okava.pay.models.User;
import com.okava.pay.models.enums.EEventRole;
import com.okava.pay.models.enums.EEventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberRepository extends JpaRepository<EventMember, Long> {

    Page<EventMember> findByInvitation_User(User invitation_user, Pageable pageable);

    Page<EventMember> findByInvitation_Event(Event invitation_event, Pageable pageable);

    Page<EventMember> findByInvitation_UserAndInvitation_Event_Status(User invitation_user, EEventStatus invitation_event_status, Pageable pageable);

    Page<EventMember> findByInvitation_EventAndRole(Event invitation_event, EEventRole role, Pageable pageable);
}
