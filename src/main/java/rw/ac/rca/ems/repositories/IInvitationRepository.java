package rw.ac.rca.ems.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.ems.models.Event;
import rw.ac.rca.ems.models.Invitation;
import rw.ac.rca.ems.models.enums.EInvitationStatus;

@Repository
public interface IInvitationRepository extends JpaRepository<Invitation, Long> {

    Page<Invitation> findByEventAndStatus(Event event, EInvitationStatus status, Pageable pageable);
}
