package com.okava.pay.repositories;

import com.okava.pay.models.Event;
import com.okava.pay.models.Invitation;
import com.okava.pay.models.enums.EInvitationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInvitationRepository extends JpaRepository<Invitation, Long> {

    Page<Invitation> findByEventAndStatus(Event event, EInvitationStatus status, Pageable pageable);
}
