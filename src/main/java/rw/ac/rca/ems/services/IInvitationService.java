package rw.ac.rca.ems.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rw.ac.rca.ems.models.Invitation;
import rw.ac.rca.ems.models.enums.EInvitationStatus;
import rw.ac.rca.ems.utils.dtos.CreateInvitationsByTagDTO;
import rw.ac.rca.ems.utils.dtos.CreateOrUpdateInvitationDTO;
import rw.ac.rca.ems.utils.dtos.InviteManyDTO;

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
