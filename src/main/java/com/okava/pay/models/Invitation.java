package com.okava.pay.models;

import com.okava.pay.audits.Model;
import com.okava.pay.models.enums.EInvitationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "invitations")
public class Invitation extends Model {

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "invitation_message")
    private String message;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EInvitationStatus status;
}
