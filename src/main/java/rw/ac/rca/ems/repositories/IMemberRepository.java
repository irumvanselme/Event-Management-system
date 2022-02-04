package rw.ac.rca.ems.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.ems.models.Event;
import rw.ac.rca.ems.models.EventMember;
import rw.ac.rca.ems.models.User;
import rw.ac.rca.ems.models.enums.EEventRole;
import rw.ac.rca.ems.models.enums.EEventStatus;

@Repository
public interface IMemberRepository extends JpaRepository<EventMember, Long> {

    Page<EventMember> findByInvitation_User(User invitation_user, Pageable pageable);

    Page<EventMember> findByInvitation_Event(Event invitation_event, Pageable pageable);

    Page<EventMember> findByInvitation_UserAndInvitation_Event_Status(User invitation_user, EEventStatus invitation_event_status, Pageable pageable);

    Page<EventMember> findByInvitation_EventAndRole(Event invitation_event, EEventRole role, Pageable pageable);
}
