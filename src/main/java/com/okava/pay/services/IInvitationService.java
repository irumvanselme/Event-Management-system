package com.okava.pay.services;

import com.okava.pay.models.Invitation;
import com.okava.pay.models.enums.EInvitationStatus;
import com.okava.pay.utils.dtos.CreateInvitationsByTagDTO;
import com.okava.pay.utils.dtos.CreateOrUpdateInvitationDTO;
import com.okava.pay.utils.dtos.InviteManyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInvitationService {

    Invitation findById(Long id);

    Invitation invite(CreateOrUpdateInvitationDTO dto);

    List<Invitation> inviteByTag(CreateInvitationsByTagDTO dto);

    List<Invitation> inviteMany(InviteManyDTO dto);

    Invitation changeStatus(Long invitationId, EInvitationStatus status);

    Invitation accept(Long invitationId);

    Invitation decline(Long invitationId);

    Page<Invitation> inEventByStatus(Long eventId, EInvitationStatus status, Pageable pageable);
}
